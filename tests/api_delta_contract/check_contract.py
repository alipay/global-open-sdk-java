"""Observe real SDK metadata and JSON behavior for the two requested deltas."""
import ast
import json
import os
from pathlib import Path
import re
import subprocess
import sys

ROOT = Path.cwd()
HERE = Path(__file__).resolve().parent


def run(argv, data=None, cwd=None):
    p = subprocess.run(argv, input=json.dumps(data) if data is not None else None,
                       text=True, capture_output=True, cwd=cwd)
    if p.returncode:
        raise RuntimeError(f'{argv[0]} failed ({p.returncode}): {p.stdout[-2500:]} {p.stderr[-3500:]}')
    return p.stdout


def declarations(language, text):
    if language == 'python':
        tree = ast.parse(text)
        return sorted((n.name, ast.dump(n.args)) for n in ast.walk(tree)
                      if isinstance(n, (ast.FunctionDef, ast.AsyncFunctionDef))
                      and (not n.name.startswith('_') or n.name == '__init__'))
    code = re.sub(r'/\*.*?\*/|//[^\n]*', '', text, flags=re.S)
    if language == 'java':
        rows = re.findall(r'\b(?:public|protected)\s+[^;{}]+[;{]', code)
        if '@Data' in code:
            for typ, name in re.findall(r'\bprivate\s+([\w<>?, .]+?)\s+(\w+)\s*;', code):
                stem=name[0].upper()+name[1:]
                rows += [f'get{stem}():{typ}', f'set{stem}({typ}):void']
        return sorted(re.sub(r'\s+', '', row) for row in rows)
    if language == 'go':
        rows = re.findall(r'^func\s+(?:\([^\n]+?\)\s*)?[^\n{]+',code,re.M)
        rows += [f'field:{name}:{typ.strip()}' for name,typ in re.findall(r'^\s+([A-Z]\w*)\s+([^`\n]+)\s+`json:',code,re.M)]
        return sorted(re.sub(r'\s+', '', row) for row in rows)
    if language == 'nodejs':
        rows = re.findall(r'^(?:export\s+)?(?:declare\s+)?(?:interface|type|class|function|const)\s+[^\n{;]+',code,re.M)
        for name, body in re.findall(r'export\s+interface\s+(\w+)\s*\{([^}]*)\}',code,re.S):
            rows += [f'{name}.{field}:{typ}' for field,typ in re.findall(r'"([^"]+)"\??\s*:\s*([^;]+);',body)]
        return sorted(re.sub(r'\s+', '', row) for row in rows)
    patterns = {
        'java': r'\b(?:public|protected)\s+[^;{}]+[;{]',
        'dotnet': r'\bpublic\s+[^;{}]+[;{]',
        'php': r'\b(?:public|protected)\s+(?:static\s+)?function\s+[^\{;]+',
        'go': r'^func\s+(?:\([^\n]+?\)\s*)?[^\n{]+',
        'nodejs': r'^(?:export\s+)?(?:declare\s+)?(?:interface|type|class|function|const)\s+[^\n{;]+',
    }
    return sorted(re.sub(r'\s+', '', row) for row in re.findall(patterns[language], code, re.M))


def review_api(req):
    differences = []
    paths = sorted(set(req.get('compatibility_source_files', []) + req.get('constructor_source_files', [])))
    aliases = {}
    changed_scalar_fields = set()
    for item in req.get('material_decisions', []):
        before, after = item.get('before'), item.get('after')
        pointer = item.get('pointer', '')
        if '/properties/' in pointer and pointer.endswith('/type'):
            changed_scalar_fields.add(pointer.split('/properties/')[-1].rsplit('/', 1)[0])
        if (isinstance(before, str) and isinstance(after, str)
                and before.startswith('#/components/schemas/') and after.startswith('#/components/schemas/')):
            aliases[after.rsplit('/', 1)[-1]] = before.rsplit('/', 1)[-1]
    for path in paths:
        old = run(['git', 'show', req['baseline_commit'] + ':' + path])
        current = ROOT / path
        assert current.is_file(), ('unexpected removed existing source', path)
        new = current.read_text(encoding='utf-8-sig')
        before, after = declarations(req['language'], old), declarations(req['language'], new)
        if before != after:
            differences.append({'symbol': path, 'kind': 'declaration_changed', 'before': before, 'after': after})
        # Type replacement is assessed only against exact material-backed aliases.
        # Current generated all-args constructors may track the current fields;
        # the existing no-arg/property/setter construction paths are exercised below.
        if req['language'] == 'python':
            old_methods = {x[0] for x in before if x[0] != '__init__'}
            assert old_methods <= {x[0] for x in after}, ('removed Python callable', path)
        elif req['language'] in {'java', 'dotnet', 'php'}:
            normalized = []
            for declaration in after:
                for a, b in aliases.items():
                    declaration = declaration.replace(a, b)
                normalized.append(declaration)
            typename = Path(path).stem
            for declaration in before:
                if re.match(r'(?:public|protected)' + re.escape(typename) + r'\([^)]*\)\{', declaration):
                    if '()' in declaration:
                        assert declaration in normalized, ('removed no-arg constructor', path)
                    continue
                if any(field.lower() in declaration.lower() for field in changed_scalar_fields):
                    # This is an explicitly material-backed wire-type correction,
                    # not a claim that old typed getter/setter callers are unchanged.
                    assert any(field.lower() in candidate.lower() for candidate in normalized for field in changed_scalar_fields), ('missing corrected field API', path)
                    continue
                assert declaration in normalized, ('removed/changed existing callable or property', path, declaration)
        elif req['language'] == 'go':
            normalized = [d for d in after]
            for a, b in aliases.items():
                normalized = [d.replace(a, b) for d in normalized]
            for declaration in before:
                if any(field.lower() in declaration.lower() for field in changed_scalar_fields):
                    assert any(field.lower() in candidate.lower() for candidate in normalized for field in changed_scalar_fields), ('missing corrected Go field', path)
                    continue
                assert declaration in normalized, ('removed Go function or field', path, declaration)
    return differences


def observe(req):
    lang = req['language']
    payload = {'models': list(req['expected']['models']),
               'operation_keys': list(req['expected']['operations']),
               'model_targets': req.get('model_targets', [])}
    if lang == 'python':
        return json.loads(run([sys.executable, str(HERE / 'observe_python.py')], payload))
    if lang == 'php':
        return json.loads(run(['php', str(HERE / 'observe.php')], payload))
    if lang == 'go':
        return json.loads(run(['go', 'run', str(HERE / 'observe.go')], payload))
    if lang == 'nodejs':
        return json.loads(run(['node', str(HERE / 'observe_node.cjs')], payload))
    if lang == 'java':
        assert (ROOT / 'target/classes').is_dir(), 'Reuse the native Maven compilation'
        out = ROOT / 'target/api-delta-contract'
        out.mkdir(parents=True, exist_ok=True)
        cpfile = out / 'classpath.txt'
        run(['mvn', '-q', '-f', str(ROOT/'pom.xml'), 'dependency:build-classpath', '-Dmdep.outputFile=' + str(cpfile)], cwd=out)
        cp = str(ROOT / 'target/classes') + os.pathsep + cpfile.read_text().strip()
        binpath = Path(os.environ['JAVA_HOME']) / 'bin' if os.environ.get('JAVA_HOME') else None
        run([str(binpath / 'javac') if binpath else 'javac', '-cp', cp, '-d', str(out), str(HERE / 'Observe.java'), str(HERE / 'BaselineBuyer.java')], cwd=out)
        return json.loads(run([str(binpath / 'java') if binpath else 'java', '-Dsdk.classes='+str(ROOT/'target/classes'), '-cp', str(out) + os.pathsep + cp, 'Observe'], payload, cwd=out))
    if lang == 'dotnet':
        assemblies = list((ROOT / 'ams-dotnet/bin').glob('Debug/*/ams-dotnet.dll'))
        assert len(assemblies) == 1, 'Reuse exactly one native .NET assembly'
        build = ROOT/'ams-dotnet/obj/api-delta-contract'
        build.mkdir(parents=True,exist_ok=True)
        import shutil
        shutil.copyfile(HERE/'Probe.csproj.template',build/'Probe.csproj')
        shutil.copyfile(HERE/'Program.cs',build/'Program.cs')
        run(['dotnet', 'build', str(build / 'Probe.csproj'), '--nologo', '-v:q', '-p:SdkAssemblyPath=' + str(assemblies[0])])
        return json.loads(run(['dotnet', str(build / 'bin/Debug/net8.0/Probe.dll'), str(assemblies[0])], payload))
    raise AssertionError(lang)


def main():
    req = json.load(sys.stdin)
    assert req['build_evidence']['native_status'] == 'passed', 'Native build must pass first'
    raw = observe(req)
    operations = {}
    for key in req['expected']['operations']:
        method, tail = key.split(':', 1)[1].split(' ', 1)
        path = tail.split('/requestBody/', 1)[0].split('/responses/', 1)[0]
        canonical = 'Alipay' + Path(key.split(':', 1)[0]).stem.split('-v', 1)[0]
        row = next(x for x in raw['bindings'] if x['path'] == path and x['request'] == canonical)
        assert row['method'].upper() == method, ('HTTP binding', row, method)
        name = row['request'] if '/requestBody/' in key else row['response']
        operations[key] = {'$ref': '#/components/schemas/' + name}
    observed = {'models': raw['models'], 'operations': operations}
    diffs = review_api(req)
    assert raw.get('wire_checks', 0) >= len(raw['models']), 'Every model must execute a real JSON roundtrip'
    checks = [{'kind': kind, 'status': 'passed', 'covers': req['targets_by_check'][kind],
               'test': ('Native SDK metadata and actual serializer/parser roundtrips; string buyer flags, '
                        'ordered credit-note lists, retained scalar and amount string, absent/null cases and operation bindings'
                        if kind in {'serialization', 'parsing'} else
                        'Pinned baseline declaration comparison, exact material-backed type aliases, '
                        'existing no-argument/setter/property paths and current generated constructors')}
              for kind in req['required_checks']]
    json.dump({'schema_version': 'sdk-contract-observation-v1', 'baseline_commit': req['baseline_commit'],
               'observed': observed, 'public_api_diff': diffs, 'checks': checks,
               'runtime_evidence': raw.get('evidence', [])}, sys.stdout)


if __name__ == '__main__':
    main()
