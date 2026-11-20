import java.io.*;
import java.lang.reflect.*;
import java.util.*;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.introspect.*;
import com.alipay.global.api.tools.JsonUtil;
import com.alipay.global.api.request.AlipayRequest;

public class Observe {
  static final ObjectMapper mapper = new ObjectMapper();
  static Map<String,Object> map(Object... args) {
    Map<String,Object> out = new LinkedHashMap<String,Object>();
    for (int i=0;i<args.length;i+=2) out.put((String)args[i],args[i+1]);
    return out;
  }
  static void check(boolean ok,String message) {if (!ok) throw new AssertionError(message);}
  static Class<?> cls(String name) throws Exception {
    for (String prefix: Arrays.asList("com.alipay.global.api.model.ams.","com.alipay.global.api.request.ams.aba.","com.alipay.global.api.response.ams.aba.")) {
      try {return Class.forName(prefix+name);} catch (ClassNotFoundException ignored) {}
    }
    throw new ClassNotFoundException(name);
  }
  static Object schema(JavaType type) {
    Class<?> raw=type.getRawClass();
    if(raw==String.class || raw.isEnum()) return map("type","string");
    if(raw==Boolean.class || raw==boolean.class) return map("type","boolean");
    if(raw==Integer.class || raw==int.class || raw==Long.class || raw==long.class) return map("type","integer");
    if(Number.class.isAssignableFrom(raw) || raw==double.class || raw==float.class) return map("type","number");
    if(type.isCollectionLikeType() || type.isArrayType()) return map("type","array","items",schema(type.getContentType()));
    if(type.isMapLikeType()) return map("type","object","properties",map(),"additionalProperties",schema(type.getContentType()));
    return map("$ref","#/components/schemas/"+raw.getSimpleName());
  }
  static Object sample(JavaType type) throws Exception {
    Class<?> raw=type.getRawClass();
    if(raw==String.class) return "probe";
    if(raw.isEnum()) return raw.getEnumConstants()[0];
    if(raw==Integer.class || raw==int.class) return 0;
    if(raw==Long.class || raw==long.class) return 0L;
    if(raw==Boolean.class || raw==boolean.class) return false;
    if(type.isCollectionLikeType()) return Arrays.asList("HKD","USD");
    if(type.isMapLikeType()) return map("probe","value");
    return raw.getConstructor().newInstance();
  }
  public static void main(String[] args) throws Exception {
    String input=new Scanner(System.in,"UTF-8").useDelimiter("\\A").next();
    JsonNode request=mapper.readTree(input);Map<String,Object> models=map();List<String> evidence=new ArrayList<String>();
    for(JsonNode target:request.get("models")) {
      String name=target.asText();Class<?> type=cls(name);Object obj=type.getConstructor().newInstance();
      Map<String,Object> props=map();
      BeanDescription description=mapper.getSerializationConfig().introspect(mapper.constructType(type));
      for(BeanPropertyDefinition property:description.findProperties()) {
        if(!property.couldSerialize()) continue;
        AnnotatedMember mutator=property.getMutator();check(mutator!=null,name+" setter "+property.getName());mutator.fixAccess(true);
        JavaType pt=property.getPrimaryType();mutator.setValue(obj,sample(pt));props.put(property.getName(),schema(pt));
      }
      JsonNode wire=mapper.readTree(JsonUtil.toJson(obj));check(wire.size()==props.size(),name+" wire keys");
      Object round=JsonUtil.fromJson(wire.toString(),type);check(mapper.readTree(JsonUtil.toJson(round)).equals(wire),name+" wire roundtrip");
      for(Constructor<?> ctor:type.getConstructors()) {
        Object[] values=new Object[ctor.getParameterTypes().length];
        for(int i=0;i<values.length;i++) values[i]=sample(mapper.constructType(ctor.getGenericParameterTypes()[i]));
        ctor.newInstance(values);evidence.add(ctor.toGenericString());
      }
      models.put(name,map("type","object","properties",props));
    }
    for(String name:Arrays.asList("ApplyCardPaymentPreference","CardPaymentPreference")) {
      Class<?> type=cls(name);Object obj=type.getConstructor().newInstance();Method setter=type.getMethod("setCurrencyOrder",List.class);
      check(mapper.readTree(JsonUtil.toJson(obj)).size()==0,"absent preference child");
      setter.invoke(obj,new ArrayList<String>());check(mapper.readTree(JsonUtil.toJson(obj)).get("currencyOrder").isArray(),"empty array preserved");
      setter.invoke(obj,Arrays.asList("HKD","USD"));check(mapper.readTree(JsonUtil.toJson(obj)).get("currencyOrder").toString().equals("[\"HKD\",\"USD\"]"),"ordered currency array");
      setter.invoke(obj,new Object[]{null});check(mapper.readTree(JsonUtil.toJson(obj)).size()==0,"SDK null omission");
    }
    String custom="{\"authorizationControl\":{\"paymentPreference\":{\"currencyOrder\":[\"HKD\",\"USD\"]}}}";
    Object ordinary=JsonUtil.fromJson(custom,cls("AlipayInquireCardDetailResponse"));
    Object sensitive=JsonUtil.fromJson("{\"cardDetail\":"+custom+"}",cls("AlipayInquireCardSensitiveInfoResponse"));
    check(mapper.readTree(JsonUtil.toJson(ordinary)).at("/authorizationControl/paymentPreference/currencyOrder").size()==2,"ordinary parsing");
    check(mapper.readTree(JsonUtil.toJson(sensitive)).at("/cardDetail/authorizationControl/paymentPreference/currencyOrder").size()==2,"sensitive parsing");
    Object inherited=JsonUtil.fromJson("{\"paymentPreference\":{}}",cls("ApplyCardAuthorizationControl"));
    check(mapper.readTree(JsonUtil.toJson(inherited)).get("paymentPreference").isObject(),"empty inheritance object");
    List<Object> bindings=new ArrayList<Object>();
    for(String stem:Arrays.asList("ApplyCard","InquireCardDetail","InquireCardSensitiveInfo")) {
      AlipayRequest<?> req=(AlipayRequest<?>)cls("Alipay"+stem+"Request").getConstructor().newInstance();
      bindings.add(map("path",req.getPath(),"method",req.getHttpMethod(),"request",req.getClass().getSimpleName(),"response",req.getResponseClass().getSimpleName()));
    }
    evidence.add("SDK JsonUtil round trips, empty/ordered arrays, null omission, nested inquiry parsing and reflected constructors/setters");
    System.out.print(mapper.writeValueAsString(map("models",models,"bindings",bindings,"wire_checks",models.size(),"evidence",evidence)));
  }
}
