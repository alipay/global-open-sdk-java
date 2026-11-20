import json,pathlib,re,subprocess,sys,tempfile,os
root=pathlib.Path.cwd();req=json.load(sys.stdin);wanted=set(req['models']);ops=set()
for target in req['operations']:
 op=target.split(' ',1)[1].split('/requestBody',1)[0].split('/responses',1)[0].rsplit('/',1)[1];ops.add(op);wanted.add('Alipay'+op[0].upper()+op[1:]+'Request')
names=[]
for p in (root/'src/main/java').rglob('*.java'):
 if p.stem in wanted:
  namespace=re.search(r'package\s+([\w.]+)',p.read_text()).group(1);names.append(namespace+'.'+p.stem)
assert len(names)==len(wanted),(wanted,names)
jars=sorted((pathlib.Path.home()/'.m2/repository/com/fasterxml/jackson').rglob('jackson-*.jar'))
assert jars,'Jackson SDK build dependencies missing'
cp=os.pathsep.join([str(root/'target/classes')]+[str(x) for x in jars])
with tempfile.TemporaryDirectory(prefix='vcc-java-runtime-') as td:
 source=pathlib.Path(__file__).with_name('Observe.java')
 compiled=subprocess.run(['javac','-cp',cp,'-d',td,str(source)],capture_output=True,text=True)
 assert compiled.returncode==0,compiled.stderr[-4000:]
 p=subprocess.run(['java','-cp',td+os.pathsep+cp,'Observe',*names],capture_output=True,text=True,check=False)
 assert p.returncode==0,p.stderr[-4000:]+p.stdout[-1000:]
 actual=json.loads(p.stdout);actual['models']={k:v for k,v in actual['models'].items() if k in req['models']};print(json.dumps(actual))
