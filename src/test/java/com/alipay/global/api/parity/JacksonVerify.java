package com.alipay.global.api.parity;

import com.alipay.global.api.tools.JsonUtil;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 迁移到 Jackson 后，对 fastjson golden 基线做差分验证（L1 序列化 + L2 反序列化）。
 *
 * <p>读取 GoldenCapture 采集的基线，用当前 Jackson 引擎重跑同样的确定性对象，逐类比对：
 *
 * <ul>
 *   <li>L1：Jackson 序列化结果 vs golden（语义比对，忽略字段顺序）
 *   <li>L2：用 golden JSON 反序列化后引擎中立 dump vs golden dump
 * </ul>
 *
 * 输出差异类清单，人工/白名单核对是否只落在预期的枚举变更上。
 */
public class JacksonVerify {

  public static void main(String[] args) throws Exception {
    String classesDir = args.length > 0 ? args[0] : "target/classes";
    String goldenDir = args.length > 1 ? args[1] : "src/test/resources/golden";

    Map<String, String> serGolden = read(goldenDir + "/serialization.txt");
    Map<String, String> deGolden = read(goldenDir + "/deserialization.txt");

    List<String> serDiffs = new ArrayList<>();
    List<String> deDiffs = new ArrayList<>();
    int serOk = 0, deOk = 0;

    for (Map.Entry<String, String> e : serGolden.entrySet()) {
      String className = e.getKey();
      String goldenJson = e.getValue();
      Class<?> c;
      try {
        c = Class.forName(className);
      } catch (Throwable t) {
        continue;
      }
      Object obj = ParitySupport.fill(c);
      if (obj == null) {
        continue;
      }
      // L1 序列化差分
      try {
        String jacksonJson = JsonUtil.toJson(obj);
        JsonNode a = JsonUtil.mapper().readTree(goldenJson);
        JsonNode b = JsonUtil.mapper().readTree(jacksonJson);
        if (a.equals(b)) {
          serOk++;
        } else {
          serDiffs.add(className + "\n    golden : " + goldenJson + "\n    jackson: " + jacksonJson);
        }
      } catch (Throwable t) {
        serDiffs.add(className + "  SER_ERROR:" + t.getClass().getSimpleName() + ":" + t.getMessage());
      }
      // L2 反序列化差分
      String goldenDump = deGolden.get(className);
      if (goldenDump != null && !goldenDump.startsWith("PARSE_ERROR")) {
        try {
          Object parsed = JsonUtil.parse(goldenJson, c);
          String jacksonDump = ParitySupport.dump(parsed).replace("\n", "\\n");
          if (goldenDump.equals(jacksonDump)) {
            deOk++;
          } else {
            deDiffs.add(className + "\n    golden : " + goldenDump + "\n    jackson: " + jacksonDump);
          }
        } catch (Throwable t) {
          deDiffs.add(className + "  DE_ERROR:" + t.getClass().getSimpleName());
        }
      }
    }

    System.out.println("======== L1 序列化差分 ========");
    System.out.println("identical = " + serOk + " , different = " + serDiffs.size());
    for (String d : serDiffs) {
      System.out.println("[SER-DIFF] " + d);
    }
    System.out.println("======== L2 反序列化差分 ========");
    System.out.println("identical = " + deOk + " , different = " + deDiffs.size());
    for (String d : deDiffs) {
      System.out.println("[DE-DIFF] " + d);
    }
  }

  private static Map<String, String> read(String path) throws Exception {
    Map<String, String> map = new TreeMap<>();
    for (String line : Files.readAllLines(new File(path).toPath(), StandardCharsets.UTF_8)) {
      int tab = line.indexOf('\t');
      if (tab > 0) {
        map.put(line.substring(0, tab), line.substring(tab + 1));
      }
    }
    return map;
  }
}
