package com.alipay.global.api.parity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;
import java.util.TreeMap;

/**
 * 在【当前 fastjson 引擎】上采集 golden 基线（迁移前一次性运行）。产出两份基线到 src/test/resources/golden/：
 *
 * <ul>
 *   <li>serialization.txt：每个可实例化类 -> fastjson 序列化后的 JSON（出向请求体基线）
 *   <li>deserialization.txt：用该 JSON 反序列化回对象后、引擎中立 dump 的对象图（入向解析基线）
 * </ul>
 *
 * 迁移到 Jackson 后由 JacksonVerify 读取这两份基线做差分。移除 fastjson 前必须先跑本类。
 */
public class GoldenCapture {

  private static final SerializerFeature F = SerializerFeature.DisableCircularReferenceDetect;

  public static void main(String[] args) throws Exception {
    String classesDir = args.length > 0 ? args[0] : "target/classes";
    String outDir = args.length > 1 ? args[1] : "src/test/resources/golden";
    new File(outDir).mkdirs();

    List<Class<?>> classes = ParitySupport.listTargetClasses(classesDir);

    TreeMap<String, String> ser = new TreeMap<>();
    TreeMap<String, String> de = new TreeMap<>();
    int okSer = 0, okDe = 0, skip = 0;

    for (Class<?> c : classes) {
      Object obj = ParitySupport.fill(c);
      if (obj == null) {
        skip++;
        continue;
      }
      String json;
      try {
        json = JSON.toJSONString(obj, F);
      } catch (Throwable t) {
        skip++;
        continue;
      }
      ser.put(c.getName(), json);
      okSer++;

      try {
        Object parsed = JSON.parseObject(json, c);
        de.put(c.getName(), oneLine(ParitySupport.dump(parsed)));
        okDe++;
      } catch (Throwable t) {
        de.put(c.getName(), "PARSE_ERROR:" + t.getClass().getSimpleName());
      }
    }

    write(outDir + "/serialization.txt", ser);
    write(outDir + "/deserialization.txt", de);

    System.out.println("== GoldenCapture (fastjson " + JSON.VERSION + ") ==");
    System.out.println("classes scanned = " + classes.size());
    System.out.println("serialization golden = " + okSer);
    System.out.println("deserialization golden = " + okDe);
    System.out.println("skipped (uninstantiable) = " + skip);
    System.out.println("written to " + outDir);
  }

  private static String oneLine(String s) {
    return s.replace("\n", "\\n");
  }

  private static void write(String path, TreeMap<String, String> map) throws Exception {
    StringBuilder sb = new StringBuilder();
    for (java.util.Map.Entry<String, String> e : map.entrySet()) {
      sb.append(e.getKey()).append('\t').append(e.getValue()).append('\n');
    }
    Files.write(new File(path).toPath(), sb.toString().getBytes(StandardCharsets.UTF_8));
  }
}
