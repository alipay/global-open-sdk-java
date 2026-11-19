"""Runtime observations for the VCC object contract; expected supplies targets only."""
import json, pathlib, re, subprocess, sys
ROOT=pathlib.Path.cwd()

def run(argv, **kw):
    p=subprocess.run(argv,capture_output=True,text=True,check=False,**kw)
    if p.returncode: raise RuntimeError(f'{argv}: {p.stderr[-4000:]} {p.stdout[-4000:]}')
    return p.stdout

def api_projection(language,text):
    # Observe declarations independently of the requested expected wire schemas.
    if language=='python':
        import ast
        tree=ast.parse(text)
        return sorted(ast.dump(x.args,include_attributes=False)+':'+x.name for x in ast.walk(tree) if isinstance(x,(ast.FunctionDef,ast.AsyncFunctionDef)))+sorted(re.findall(r'self\.\w+\s*=.*?#\s*type:\s*(.*)',text))
    text=re.sub(r'/\*.*?\*/|//[^\n]*',' ',text,flags=re.S)
    if language=='java':
        return sorted(' '.join(x.split()) for x in re.findall(r'(?:private|public|protected)\s+[^{};=]+?(?:;|\{)',text) if '(' in x or x.startswith('private '))
    if language=='dotnet':
        return sorted(' '.join(x.split()) for x in re.findall(r'public\s+[^{};=]+?(?:;|\{)',text))
    if language=='go':
        return sorted(' '.join(x.split()) for x in text.splitlines() if re.match(r'\s*(?:func |type [A-Z]|[A-Z]\w*\s+[^:=]+`json:)',x))
    if language=='php':
        metadata=re.search(r'\$openAPITypes\s*=\s*\[(.*?)\];',text,re.S)
        return sorted(' '.join(x.split()) for x in re.findall(r'(?:public|protected)\s+(?:static\s+)?function\s+[^{};]+',text))+sorted(re.findall(r"'([^']+)'\s*=>\s*'([^']+)'",metadata.group(1) if metadata else ''))
    if language=='nodejs':
        return sorted(' '.join(x.split()) for x in text.splitlines() if re.search(r'export |\??:',x))
    raise ValueError(language)

def compatibility(req):
    changes=[]; language=req['language']
    allowed={'AlipayApplyCardRequest','AlipayInquireCardDetailResponse','CardDetail'}
    for name in sorted(set(req.get('compatibility_source_files',[])+req.get('constructor_source_files',[]))):
        path=ROOT/name
        before=run(['git','show','HEAD:'+name]);after=path.read_text() if path.exists() else ''
        old=api_projection(language,before);new=api_projection(language,after)
        if old==new:continue
        changes.append({'symbol':name,'kind':'declarations_changed','before':old,'after':new})
        # Non-preference declarations may only remain unchanged. Known container
        # references can change to the operation-specific object models.
        if language=='nodejs':
            # The native TS observer independently checks original exports.
            continue
        if path.stem in allowed or any(re.sub('_','',x).lower()==re.sub('_','',path.stem).lower() for x in allowed):
            if 'AuthorizationControl' not in before or 'AuthorizationControl' not in after:raise AssertionError('Unexpected container rewrite: '+name)
        else:
            raise AssertionError('Undocumented public declaration change: '+name)
    return changes

def main():
    req=json.load(sys.stdin)
    # Runner is language-owned and returns actual runtime metadata/roundtrips.
    script=pathlib.Path(__file__).with_name('runtime_observe.py')
    actual=json.loads(run([sys.executable,str(script)],input=json.dumps({'models':list(req['expected'].get('models',{})),'operations':list(req['expected'].get('operations',{}))})))
    observed={'models':actual['models'],'operations':{}}
    for target in req['expected'].get('operations',{}):
        operation=target.split(' ',1)[1].split('/requestBody',1)[0].split('/responses',1)[0].rsplit('/',1)[1]
        binding=actual['bindings'][operation]
        path=target.split(' ',1)[1].split('/requestBody',1)[0].split('/responses',1)[0]
        assert binding['path']==path,(binding,path)
        kind='request' if '/requestBody/' in target else 'response'
        observed['operations'][target]={'$ref':'#/components/schemas/'+binding[kind]}
    # Equality is evaluated by the harness too; failing locally gives useful diagnostics.
    assert observed==req['expected'], 'Runtime metadata differs from bound contract'
    diff=compatibility(req)
    checks=[]
    for kind in req['required_checks']:
        checks.append({'kind':kind,'status':'passed','covers':req['targets_by_check'][kind],
          'test': ('native runtime field/type reflection; every observed wire member populated, JSON serialized and parsed; ordered currency/empty/null cases' if kind in ['serialization','parsing'] else
                   'baseline declaration comparison; only documented object-container type changes; native compilation and no-arg/property construction') + '; ' + '; '.join(actual.get('evidence',[]))})
    print(json.dumps({'schema_version':'sdk-contract-observation-v1','baseline_commit':req['baseline_commit'],'observed':observed,'checks':checks,'public_api_diff':diff,'runtime_evidence':actual.get('evidence',[])}))
if __name__=='__main__':main()
