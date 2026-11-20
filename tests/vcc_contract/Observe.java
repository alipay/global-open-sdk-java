import java.lang.reflect.*;
import java.util.*;
import com.alipay.global.api.tools.JsonUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
public class Observe {
 static Map<String,Class<?>> types=new HashMap<>();
 static Map<String,Object> obj(Object... kv){Map<String,Object> m=new LinkedHashMap<>();for(int i=0;i<kv.length;i+=2)m.put((String)kv[i],kv[i+1]);return m;}
 static Object shape(Type t){
  if(t instanceof ParameterizedType){ParameterizedType p=(ParameterizedType)t;Class<?> c=(Class<?>)p.getRawType();if(Collection.class.isAssignableFrom(c))return obj("type","array","items",shape(p.getActualTypeArguments()[0]));if(Map.class.isAssignableFrom(c))return obj("type","object","additionalProperties",shape(p.getActualTypeArguments()[1]),"properties",obj());return shape(c);}
  Class<?> c=(Class<?>)t;
  if(c.isEnum()||c==String.class||c==char.class||c==Character.class)return obj("type","string");
  if(c==boolean.class||c==Boolean.class)return obj("type","boolean");
  if(c==int.class||c==Integer.class||c==long.class||c==Long.class||c==short.class||c==Short.class)return obj("type","integer");
  if(Number.class.isAssignableFrom(c)||c==double.class||c==float.class)return obj("type","number");
  if(c.isArray())return obj("type","array","items",shape(c.getComponentType()));
  if(c==Object.class)return obj();
  return obj("$ref","#/components/schemas/"+c.getSimpleName());
 }
 static Field[] fields(Class<?> c){
  List<Field> result=new ArrayList<>();
  for(Class<?> owner=c;owner!=null&&owner!=Object.class;owner=owner.getSuperclass()){
   for(Field f:owner.getDeclaredFields())if(!Modifier.isStatic(f.getModifiers())&&!f.isSynthetic()&&f.getAnnotation(JsonIgnore.class)==null)result.add(f);
  }
  return result.toArray(new Field[0]);
 }
 static Object sample(Type t,int depth)throws Exception{
  if(depth>8)return null;
  if(t instanceof ParameterizedType){ParameterizedType p=(ParameterizedType)t;Class<?> c=(Class<?>)p.getRawType();if(Collection.class.isAssignableFrom(c))return new ArrayList<>(Arrays.asList(sample(p.getActualTypeArguments()[0],depth+1)));if(Map.class.isAssignableFrom(c))return Collections.singletonMap("key",sample(p.getActualTypeArguments()[1],depth+1));return sample(c,depth);}
  Class<?> c=(Class<?>)t;
  if(c==String.class)return "USD";if(c==Boolean.class||c==boolean.class)return false;
  if(c==Integer.class||c==int.class)return 0;if(c==Long.class||c==long.class)return 0L;if(c==Double.class||c==double.class)return 0d;if(c==Float.class||c==float.class)return 0f;
  if(c.isEnum())return c.getEnumConstants()[0];if(c==Object.class)return "value";
  Object v=c.getDeclaredConstructor().newInstance();
  for(Field f:fields(c)){f.setAccessible(true);f.set(v,sample(f.getGenericType(),depth+1));}return v;
 }
 public static void main(String[] args)throws Exception{
  // Command args are actual compiled FQCNs, not expected property definitions.
  for(String a:args){Class<?> c=Class.forName(a);types.put(c.getSimpleName(),c);}
  Map<String,Object> schemas=new TreeMap<>();Map<String,Object> bindings=new TreeMap<>();
  for(Class<?> c:types.values()){
   Map<String,Object> props=new TreeMap<>();Object v=sample(c,0);
   Map wire=JsonUtil.fromJson(JsonUtil.toJson(v),Map.class);
   for(Field f:fields(c)){f.setAccessible(true);String key=f.getName();JsonProperty annotation=f.getAnnotation(JsonProperty.class);if(annotation!=null&&!annotation.value().isEmpty())key=annotation.value();
    if(!wire.containsKey(key))throw new AssertionError("Serializer omitted "+c.getName()+"."+key);props.put(key,shape(f.getGenericType()));}
   Object parsed=JsonUtil.fromJson(JsonUtil.toJson(v),c);
   if(!JsonUtil.fromJson(JsonUtil.toJson(parsed),Map.class).equals(JsonUtil.fromJson(JsonUtil.toJson(v),Map.class)))throw new AssertionError("roundtrip "+c);
   if(c.getSimpleName().endsWith("PaymentPreference")){
    Field order=c.getDeclaredField("currencyOrder");order.setAccessible(true);
    for(List<String> currencies:Arrays.asList(Arrays.asList("HKD","USD"),Arrays.asList("USD","HKD"),Collections.<String>emptyList())){
     Object policy=c.getDeclaredConstructor().newInstance();order.set(policy,currencies);Object decoded=JsonUtil.fromJson(JsonUtil.toJson(policy),c);
     if(!order.get(decoded).equals(currencies))throw new AssertionError("ordered/empty list lost "+c);
    }
   }
   schemas.put(c.getSimpleName(),obj("type","object","properties",props));
   if(c.getSimpleName().startsWith("Alipay")&&c.getSimpleName().endsWith("Request")){
    String operation=c.getSimpleName().substring(6,c.getSimpleName().length()-7);operation=operation.substring(0,1).toLowerCase()+operation.substring(1);
    Object request=c.getDeclaredConstructor().newInstance();String path=(String)c.getMethod("getPath").invoke(request);Class<?> response=(Class<?>)c.getMethod("getResponseClass").invoke(request);
    bindings.put(operation,obj("path",path,"request",c.getSimpleName(),"response",response.getSimpleName()));
   }
  }
  System.out.print(JsonUtil.toJson(obj("models",schemas,"bindings",bindings,"evidence",Arrays.asList("Compiled field/generic-type reflection; SDK JsonUtil/Jackson populated-field serialization and typed roundtrip; request path and response class"))));
 }
}
