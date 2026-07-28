package com.alipay.global.api.parity;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 引擎中立的反射工具：确定性填充对象 + 规范化 dump 对象图 + 枚举 SDK 目标类。
 *
 * <p>刻意不 import 任何 JSON 库（fastjson/jackson），以便 GoldenCapture(fastjson) 与
 * JacksonVerify(jackson) 共用，且在移除 fastjson 后仍可编译。用于 Jackson 迁移的行为 parity 回归。
 */
public final class ParitySupport {

  private static final long FIXED_MILLIS = 1700000000000L;
  private static final int MAX_DEPTH = 6;
  private static final String PKG = "com.alipay.global.api.";

  private ParitySupport() {}

  /** 扫描 target/classes 下 request/response/model 包中可实例化的具体类（排除枚举/抽象/内部类）。 */
  public static List<Class<?>> listTargetClasses(String classesDir) throws Exception {
    List<Class<?>> result = new ArrayList<>();
    File root = new File(classesDir);
    collect(root, root, result);
    result.sort((a, b) -> a.getName().compareTo(b.getName()));
    return result;
  }

  private static void collect(File root, File dir, List<Class<?>> out) throws Exception {
    File[] files = dir.listFiles();
    if (files == null) {
      return;
    }
    for (File f : files) {
      if (f.isDirectory()) {
        collect(root, f, out);
        continue;
      }
      if (!f.getName().endsWith(".class") || f.getName().contains("$")) {
        continue;
      }
      String rel = root.toURI().relativize(f.toURI()).getPath();
      String className = rel.substring(0, rel.length() - ".class".length()).replace('/', '.');
      if (!className.startsWith(PKG)) {
        continue;
      }
      boolean target =
          className.contains(".request.") || className.contains(".response.") || className.contains(".model.");
      if (!target) {
        continue;
      }
      Class<?> c;
      try {
        c = Class.forName(className);
      } catch (Throwable t) {
        continue;
      }
      if (c.isEnum() || c.isInterface() || Modifier.isAbstract(c.getModifiers())) {
        continue;
      }
      // 只收有无参构造的具体类
      try {
        c.getDeclaredConstructor();
      } catch (NoSuchMethodException e) {
        continue;
      }
      out.add(c);
    }
  }

  /** 确定性填充一个实例（含继承字段），返回填好的对象；失败返回 null。 */
  public static Object fill(Class<?> type) {
    try {
      return fillValue(type, null, 0, new ArrayList<Class<?>>());
    } catch (Throwable t) {
      return null;
    }
  }

  @SuppressWarnings({"unchecked", "rawtypes"})
  private static Object fillValue(Class<?> type, Type generic, int depth, List<Class<?>> path)
      throws Exception {
    if (type == null) {
      return null;
    }
    // 基础类型
    if (type == String.class) {
      return "v";
    }
    if (type == Integer.class || type == int.class) {
      return 1;
    }
    if (type == Long.class || type == long.class) {
      return 1L;
    }
    if (type == Boolean.class || type == boolean.class) {
      return Boolean.TRUE;
    }
    if (type == Double.class || type == double.class) {
      return 1.0d;
    }
    if (type == Float.class || type == float.class) {
      return 1.0f;
    }
    if (type == BigDecimal.class) {
      return new BigDecimal("1.00");
    }
    if (type == Date.class) {
      return new Date(FIXED_MILLIS);
    }
    if (type.isEnum()) {
      Object[] cs = type.getEnumConstants();
      return cs != null && cs.length > 0 ? cs[0] : null;
    }
    if (Class.class.isAssignableFrom(type)) {
      return null; // responseClass 等，序列化时被忽略
    }
    if (List.class.isAssignableFrom(type)) {
      List list = new ArrayList();
      Class<?> elem = generic != null ? firstTypeArg(generic) : String.class;
      if (elem == null) {
        elem = String.class;
      }
      if (depth < MAX_DEPTH) {
        Object e = fillValue(elem, null, depth + 1, path);
        if (e != null) {
          list.add(e);
        }
      }
      return list;
    }
    if (Map.class.isAssignableFrom(type)) {
      Map map = new LinkedHashMap();
      map.put("k", "v");
      return map;
    }
    // 复杂 POJO：递归，带环/深度保护
    if (!type.getName().startsWith(PKG)) {
      return null;
    }
    if (depth >= MAX_DEPTH || path.contains(type)) {
      return null;
    }
    Object instance;
    try {
      instance = type.getDeclaredConstructor().newInstance();
    } catch (Throwable t) {
      return null;
    }
    path.add(type);
    for (Field field : allFields(type)) {
      if (Modifier.isStatic(field.getModifiers()) || Modifier.isFinal(field.getModifiers())) {
        continue;
      }
      field.setAccessible(true);
      Object val = fillValue(field.getType(), field.getGenericType(), depth + 1, path);
      if (val != null) {
        try {
          field.set(instance, val);
        } catch (Throwable ignore) {
        }
      }
    }
    path.remove(type);
    return instance;
  }

  private static Class<?> firstTypeArg(Type generic) {
    if (generic instanceof ParameterizedType) {
      Type[] args = ((ParameterizedType) generic).getActualTypeArguments();
      if (args.length > 0 && args[0] instanceof Class) {
        return (Class<?>) args[0];
      }
    }
    return null;
  }

  private static List<Field> allFields(Class<?> type) {
    List<Field> fields = new ArrayList<>();
    for (Class<?> c = type; c != null && c != Object.class; c = c.getSuperclass()) {
      for (Field f : c.getDeclaredFields()) {
        fields.add(f);
      }
    }
    return fields;
  }

  /** 引擎中立地把对象图 dump 成排序后的 path=value 文本，用于跨引擎比对反序列化结果。 */
  public static String dump(Object obj) {
    TreeMap<String, String> flat = new TreeMap<>();
    try {
      flatten("", obj, flat, 0, new ArrayList<Object>());
    } catch (Throwable t) {
      return "DUMP_ERROR:" + t.getClass().getSimpleName();
    }
    StringBuilder sb = new StringBuilder();
    for (Map.Entry<String, String> e : flat.entrySet()) {
      sb.append(e.getKey()).append('=').append(e.getValue()).append('\n');
    }
    return sb.toString();
  }

  private static void flatten(String prefix, Object obj, Map<String, String> out, int depth, List<Object> seen)
      throws Exception {
    if (obj == null || depth > MAX_DEPTH + 2) {
      return;
    }
    Class<?> type = obj.getClass();
    if (type == String.class
        || Number.class.isAssignableFrom(type)
        || type == Boolean.class
        || type.isEnum()) {
      out.put(prefix, String.valueOf(obj));
      return;
    }
    if (obj instanceof Date) {
      out.put(prefix, String.valueOf(((Date) obj).getTime()));
      return;
    }
    if (obj instanceof Map) {
      for (Map.Entry<?, ?> e : ((Map<?, ?>) obj).entrySet()) {
        flatten(prefix + "." + e.getKey(), e.getValue(), out, depth + 1, seen);
      }
      return;
    }
    if (obj instanceof List) {
      List<?> list = (List<?>) obj;
      for (int i = 0; i < list.size(); i++) {
        flatten(prefix + "[" + i + "]", list.get(i), out, depth + 1, seen);
      }
      return;
    }
    if (!type.getName().startsWith(PKG)) {
      out.put(prefix, String.valueOf(obj));
      return;
    }
    for (Object s : seen) {
      if (s == obj) {
        return;
      }
    }
    seen.add(obj);
    for (Field field : allFields(type)) {
      if (Modifier.isStatic(field.getModifiers())) {
        continue;
      }
      field.setAccessible(true);
      Object val = field.get(obj);
      if (val != null) {
        flatten(prefix.isEmpty() ? field.getName() : prefix + "." + field.getName(), val, out, depth + 1, seen);
      }
    }
  }
}
