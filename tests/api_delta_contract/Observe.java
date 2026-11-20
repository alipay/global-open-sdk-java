import com.alipay.global.api.request.AlipayRequest;
import com.alipay.global.api.tools.JsonUtil;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.introspect.*;
import java.io.*;
import java.lang.reflect.*;
import java.util.*;

public class Observe {
  static final ObjectMapper mapper = new ObjectMapper();
  static boolean billing = false;

  static Map<String, String> scalarSurface(Class<?> t) {
    Map<String, String> out = new TreeMap<String, String>();
    for (BeanPropertyDefinition p :
        mapper.getSerializationConfig().introspect(mapper.constructType(t)).findProperties()) {
      Class<?> c = p.getPrimaryType().getRawClass();
      out.put(p.getName(), c.isEnum() ? String.class.getName() : c.getName());
    }
    return out;
  }

  static Map<String, Object> map(Object... args) {
    Map<String, Object> out = new LinkedHashMap<String, Object>();
    for (int i = 0; i < args.length; i += 2) out.put((String) args[i], args[i + 1]);
    return out;
  }

  static void check(boolean ok, String message) {
    if (!ok) throw new AssertionError(message);
  }

  static Class<?> cls(String name) throws Exception {
    try {
      return Class.forName("com.alipay.global.api.model.ams." + name);
    } catch (ClassNotFoundException ignored) {
    }
    java.nio.file.Path base =
        java.nio.file.Paths.get(System.getProperty("sdk.classes", "target/classes"));
    try (java.util.stream.Stream<java.nio.file.Path> files = java.nio.file.Files.walk(base)) {
      for (java.nio.file.Path p : (Iterable<java.nio.file.Path>) files::iterator) {
        if (p.getFileName().toString().equals(name + ".class")) {
          String q = base.relativize(p).toString().replace(File.separatorChar, '.');
          return Class.forName(q.substring(0, q.length() - 6));
        }
      }
    }
    throw new ClassNotFoundException(name);
  }

  static Object schema(JavaType type) {
    Class<?> raw = type.getRawClass();
    if (billing && raw.getName().equals("com.alipay.global.api.model.Result")) {
      try {
        Class<?> logical = cls("ResultInfo");
        check(scalarSurface(raw).equals(scalarSurface(logical)), "legacy result wire equivalence");
        return map("$ref", "#/components/schemas/" + logical.getSimpleName());
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    }
    if (raw == String.class) return map("type", "string");
    if (raw.isEnum()) return map("$ref", "#/components/schemas/" + raw.getSimpleName());
    if (raw == Boolean.class || raw == boolean.class) return map("type", "boolean");
    if (raw == Integer.class || raw == int.class || raw == Long.class || raw == long.class)
      return map("type", "integer");
    if (Number.class.isAssignableFrom(raw) || raw == double.class || raw == float.class)
      return map("type", "number");
    if (type.isCollectionLikeType() || type.isArrayType())
      return map("type", "array", "items", schema(type.getContentType()));
    if (type.isMapLikeType())
      return map(
          "type",
          "object",
          "properties",
          map(),
          "additionalProperties",
          schema(type.getContentType()));
    return map("$ref", "#/components/schemas/" + raw.getSimpleName());
  }

  static Object sample(JavaType type) throws Exception {
    Class<?> raw = type.getRawClass();
    if (raw == String.class) return "probe";
    if (raw.isEnum()) return raw.getEnumConstants()[0];
    if (raw == Integer.class || raw == int.class) return 0;
    if (raw == Long.class || raw == long.class) return 0L;
    if (raw == Boolean.class || raw == boolean.class) return false;
    if (type.isCollectionLikeType())
      return Arrays.asList(sample(type.getContentType()), sample(type.getContentType()));
    if (type.isMapLikeType()) return map("probe", "value");
    return raw.getConstructor().newInstance();
  }

  public static void main(String[] args) throws Exception {
    String input = new Scanner(System.in, "UTF-8").useDelimiter("\\A").next();
    JsonNode request = mapper.readTree(input);
    billing = request.get("models").toString().contains("BillingSubscription");
    Map<String, Object> models = map();
    List<String> evidence = new ArrayList<String>();
    for (JsonNode target : request.get("models")) {
      String key = target.asText();
      String name = key.substring(key.lastIndexOf('/') + 1);
      Class<?> type = cls(name);
      Object obj = type.getConstructor().newInstance();
      Map<String, Object> props = map();
      BeanDescription description =
          mapper.getSerializationConfig().introspect(mapper.constructType(type));
      for (BeanPropertyDefinition property : description.findProperties()) {
        if (!property.couldSerialize()) continue;
        AnnotatedMember mutator = property.getMutator();
        check(mutator != null, name + " setter " + property.getName());
        mutator.fixAccess(true);
        JavaType pt = property.getPrimaryType();
        com.fasterxml.jackson.databind.annotation.JsonDeserialize binding =
            property.getField() == null
                ? null
                : property
                    .getField()
                    .getAnnotation(com.fasterxml.jackson.databind.annotation.JsonDeserialize.class);
        JavaType wireType =
            binding != null && binding.as() != Void.class ? mapper.constructType(binding.as()) : pt;
        mutator.setValue(obj, sample(wireType));
        props.put(property.getName(), schema(wireType));
      }
      JsonNode wire = mapper.readTree(JsonUtil.toJson(obj));
      check(wire.size() == props.size(), name + " wire keys");
      Object round = JsonUtil.fromJson(wire.toString(), type);
      check(mapper.readTree(JsonUtil.toJson(round)).equals(wire), name + " wire roundtrip");
      for (Constructor<?> ctor : type.getConstructors()) {
        Object[] values = new Object[ctor.getParameterTypes().length];
        for (int i = 0; i < values.length; i++)
          values[i] = sample(mapper.constructType(ctor.getGenericParameterTypes()[i]));
        ctor.newInstance(values);
        evidence.add(ctor.toGenericString());
      }
      models.put(key, map("type", "object", "properties", props));
    }
    try {
      Class<?> t = cls("ConsultBuyer");
      Object buyer = t.getConstructor().newInstance();
      Method setter = t.getMethod("setBuyerAccessGroupFlag", String.class);
      for (String value : Arrays.asList("true", "false")) {
        setter.invoke(buyer, value);
        JsonNode b = mapper.readTree(JsonUtil.toJson(buyer));
        check(
            b.get("buyerAccessGroupFlag").isTextual()
                && b.get("buyerAccessGroupFlag").asText().equals(value),
            "string buyer flag");
        Object q = cls("AlipayPayConsultRequest").getConstructor().newInstance();
        q.getClass().getMethod("setBuyer", cls("Buyer")).invoke(q, buyer);
        check(
            mapper
                .readTree(JsonUtil.toJson(q))
                .at("/buyer/buyerAccessGroupFlag")
                .asText()
                .equals(value),
            "nested buyer binding");
      }
      setter.invoke(buyer, new Object[] {null});
      check(
          !mapper.readTree(JsonUtil.toJson(buyer)).has("buyerAccessGroupFlag"),
          "unset flag omission");
      com.alipay.global.api.model.ams.Buyer legacy = new com.alipay.global.api.model.ams.Buyer();
      legacy.setReferenceBuyerId("legacy_buyer");
      com.alipay.global.api.request.ams.pay.AlipayPayConsultRequest legacyRequest =
          new com.alipay.global.api.request.ams.pay.AlipayPayConsultRequest();
      legacyRequest.setBuyer(legacy);
      com.alipay.global.api.model.ams.Buyer legacyReadback = legacyRequest.getBuyer();
      check(
          legacyReadback.getReferenceBuyerId().equals("legacy_buyer"),
          "legacy Buyer setter and getter");
      evidence.add("Actual consult true/false string and omitted flag JSON");
    } catch (ClassNotFoundException ignored) {
    }
    for (JsonNode target : request.get("models")) {
      String key = target.asText();
      Class<?> t = cls(key.substring(key.lastIndexOf('/') + 1));
      try {
        t.getMethod("getCreditNoteIds");
      } catch (NoSuchMethodException ignored) {
        continue;
      }
      for (String fixture :
          Arrays.asList(
              "{}",
              "{\"creditNoteIds\":null}",
              "{\"creditNoteIds\":[]}",
              "{\"creditNoteId\":\"cn_a\",\"creditNoteIds\":[\"cn_a\"],\"creditNoteAmount\":\"3000\",\"creditNoteCurrency\":\"USD\"}",
              "{\"creditNoteIds\":[\"cn_a\",\"cn_z\"],\"creditNoteAmount\":\"8000\",\"creditNoteCurrency\":\"USD\"}")) {
        JsonNode inputNode = mapper.readTree(fixture);
        JsonNode actual = mapper.readTree(JsonUtil.toJson(JsonUtil.fromJson(fixture, t)));
        Iterator<String> names = inputNode.fieldNames();
        while (names.hasNext()) {
          String k = names.next();
          if (!inputNode.get(k).isNull())
            check(inputNode.get(k).equals(actual.get(k)), "subscription fixture " + k);
        }
      }
      evidence.add(
          "Actual subscription parser: one/many/empty/absent/null ordered IDs, retained scalar and"
              + " amount string");
    }
    Set<String> paths = new HashSet<String>();
    for (JsonNode key : request.get("operation_keys")) {
      String path =
          key.asText().split(" ", 2)[1].split("/requestBody/", 2)[0].split("/responses/", 2)[0];
      paths.add(path);
    }
    List<Object> bindings = new ArrayList<Object>();
    java.nio.file.Path base =
        java.nio.file.Paths.get(System.getProperty("sdk.classes", "target/classes"));
    try (java.util.stream.Stream<java.nio.file.Path> files = java.nio.file.Files.walk(base)) {
      for (java.nio.file.Path p : (Iterable<java.nio.file.Path>) files::iterator) {
        String file = p.getFileName().toString();
        if (!file.endsWith("Request.class") || file.equals("AlipayRequest.class")) continue;
        try {
          Class<?> t = cls(file.substring(0, file.length() - 6));
          if (!AlipayRequest.class.isAssignableFrom(t)) continue;
          AlipayRequest<?> q = (AlipayRequest<?>) t.getConstructor().newInstance();
          if (!paths.contains(q.getPath())) continue;
          bindings.add(
              map(
                  "path",
                  q.getPath(),
                  "method",
                  q.getHttpMethod(),
                  "request",
                  t.getSimpleName(),
                  "response",
                  q.getResponseClass().getSimpleName()));
        } catch (ReflectiveOperationException ignored) {
        }
      }
    }
    System.out.print(
        mapper.writeValueAsString(
            map(
                "models",
                models,
                "bindings",
                bindings,
                "wire_checks",
                models.size(),
                "evidence",
                evidence)));
  }
}
