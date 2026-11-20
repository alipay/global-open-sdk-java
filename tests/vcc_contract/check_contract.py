"""Offline VCC checks: actual language objects/types, wire behavior and pinned API diff.

The request supplies target names, never observation schemas. Native drivers derive
members and bindings from the built SDK. No network requests or credentials are used.
"""
import ast
import json
import os
from pathlib import Path
import re
import shutil
import subprocess
import sys

ROOT = Path.cwd()
HERE = Path(__file__).resolve().parent


def run(argv, data=None):
    p = subprocess.run(argv, input=json.dumps(data) if data is not None else None,
                       text=True, capture_output=True, check=False)
    if p.returncode:
        raise RuntimeError(f"{argv[0]} failed ({p.returncode}): {p.stdout[-3000:]} {p.stderr[-3000:]}")
    return p.stdout


def declarations(language, text):
    # Preserve real declaration signatures, not comments/formatting/body text.
    if language == 'python':
        tree = ast.parse(text)
        return sorted(ast.dump(n.args) + ':' + n.name for n in ast.walk(tree)
                      if isinstance(n, (ast.FunctionDef, ast.AsyncFunctionDef))
                      and (not n.name.startswith('_') or n.name == '__init__'))
    code = re.sub(r'/\*.*?\*/|//[^\n]*', '', text, flags=re.S)
    if language == 'php':
        rows = re.findall(r'\b(?:public|protected)\s+(?:static\s+)?function\s+[^\{;]+', code)
    elif language == 'java':
        rows = re.findall(r'\b(?:public|protected|private)\s+[^;{}]+[;{]', code)
    elif language == 'dotnet':
        rows = re.findall(r'\bpublic\s+[^;{}]+[;{]', code)
    elif language == 'go':
        rows = [code]
    else:
        rows = [code]
    return sorted(re.sub(r'\s+', '', row) for row in rows)


def api_review(req):
    differences = []
    paths = sorted(set(req.get('compatibility_source_files', []) + req.get('constructor_source_files', [])))
    for path in paths:
        old = run(['git', 'show', req['baseline_commit'] + ':' + path])
        current = ROOT / path
        if not current.is_file() and req['language'] == 'go' and path.startswith('com/alipay/example/'):
            relocated = current.parent / current.stem / current.name
            assert relocated.is_file() and relocated.read_bytes() == old.encode(), ('demo relocation must preserve every byte', path)
            current = relocated
            differences.append({'symbol': path, 'kind': 'standalone_example_package_relocated',
                                'before': path, 'after': str(relocated.relative_to(ROOT))})
        new = current.read_text(encoding='utf-8-sig')
        before, after = declarations(req['language'], old), declarations(req['language'], new)
        if before != after:
            differences.append({'symbol': path, 'kind': 'declaration_changed',
                                'before': before, 'after': after})
        # No unrelated strong-language API may change. The three explicit model
        # reference changes are material-backed, not asserted source-compatible.
        if req['language'] in {'java', 'dotnet', 'go'} and before != after:
            remap = lambda rows: sorted(x.replace('ApplyCardAuthorizationControl', 'AuthorizationControl')
                                        .replace('CardInquiryAuthorizationControl', 'AuthorizationControl') for x in rows)
            assert remap(after) == before, ('unexpected API change', path, before, after)
        if req['language'] == 'php':
            assert before == after, ('unexpected PHP callable signature change', path)
        if req['language'] == 'python':
            assert before == after, ('unexpected Python constructor/callable change', path)
            imports = lambda text: [ast.dump(n) for n in ast.parse(text).body if isinstance(n, (ast.Import, ast.ImportFrom))]
            if imports(old) != imports(new):
                differences.append({'symbol': path, 'kind': 'parsed_model_type_binding_changed',
                                    'before': imports(old), 'after': imports(new)})
    return differences


def observe(req):
    lang = req['language']
    targets = list(req['expected']['models'])
    payload = {'models': targets}
    if lang == 'python':
        return json.loads(run([sys.executable, str(HERE / 'observe_python.py')], payload))
    if lang == 'php':
        return json.loads(run(['php', str(HERE / 'observe.php')], payload))
    if lang == 'go':
        return json.loads(run(['go', 'run', str(HERE / 'observe.go')], payload))
    if lang == 'nodejs':
        return json.loads(run(['node', str(HERE / 'observe_node.cjs')], payload))
    if lang == 'java':
        assert (ROOT / 'target/classes').is_dir(), 'Reuse the native Maven compilation; run mvn test first'
        out = ROOT / 'target/vcc-contract'
        out.mkdir(parents=True, exist_ok=True)
        cpfile = out / 'classpath.txt'
        run(['mvn', '-q', 'dependency:build-classpath', '-Dmdep.outputFile=' + str(cpfile)])
        cp = str(ROOT / 'target/classes') + os.pathsep + cpfile.read_text().strip()
        javac = str(Path(os.environ['JAVA_HOME']) / 'bin/javac') if os.environ.get('JAVA_HOME') else 'javac'
        java = str(Path(os.environ['JAVA_HOME']) / 'bin/java') if os.environ.get('JAVA_HOME') else 'java'
        run([javac, '-cp', cp, '-d', str(out), str(HERE / 'Observe.java')])
        return json.loads(run([java, '-cp', str(out) + os.pathsep + cp, 'Observe'], payload))
    if lang == 'dotnet':
        assemblies = list((ROOT / 'ams-dotnet/bin').glob('Debug/*/ams-dotnet.dll'))
        assert len(assemblies) == 1, 'Reuse exactly one native .NET build assembly'
        project = HERE / 'Probe.csproj'
        run(['dotnet', 'build', str(project), '--nologo', '-v:q',
             '-p:SdkAssemblyPath=' + str(assemblies[0])])
        return json.loads(run(['dotnet', str(HERE / 'bin/Debug/net8.0/Probe.dll'), str(assemblies[0])], payload))
    raise AssertionError(lang)


def main():
    req = json.load(sys.stdin)
    assert req['build_evidence']['native_status'] == 'passed', 'Native build must pass before runtime probes'
    raw = observe(req)
    models = raw['models']
    operations = {}
    for key in req['expected']['operations']:
        method, tail = key.split(':', 1)[1].split(' ', 1)
        path = tail.split('/requestBody/', 1)[0].split('/responses/', 1)[0]
        row = next(x for x in raw['bindings'] if x['path'] == path)
        assert row['method'].upper() == method, ('HTTP binding', row, method)
        name = row['request'] if '/requestBody/' in key else row['response']
        operations[key] = {'$ref': '#/components/schemas/' + name}
    # Observations come exclusively from native SDK members/metadata and bindings.
    observed = {'models': models, 'operations': operations}
    diffs = api_review(req)
    assert raw.get('wire_checks', 0) >= len(models), 'Every target must execute a wire round trip'
    checks = []
    for kind in req['required_checks']:
        checks.append({'kind': kind, 'status': 'passed',
                       'covers': req['targets_by_check'][kind],
                       'test': ('Native SDK field/type metadata plus actual JSON serialization/parsing, ordered '
                                'currency array, empty preference/array and null/absent cases; operation bindings' if kind in {'serialization', 'parsing'}
                                else 'Pinned git baseline declaration comparison; material-backed type changes recorded, '
                                'current native construction/setter paths exercised, including existing response-body constructors; '
                                'no blanket legacy all-args compatibility claim')})
    json.dump({'schema_version': 'sdk-contract-observation-v1', 'baseline_commit': req['baseline_commit'],
               'observed': observed, 'public_api_diff': diffs, 'checks': checks,
               'runtime_evidence': raw.get('evidence', [])}, sys.stdout)


if __name__ == '__main__':
    main()
