package com.alipay.global.api.parity;

import com.alipay.global.api.tools.JsonUtil;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 枚举契约一致性 / 往返一致性测试（无需网络，替代端到端验证枚举行为）。
 *
 * <ul>
 *   <li>往返：对每个枚举的每个常量，Jackson 序列化再反序列化必须得回同一常量（证明 @JsonValue/@JsonCreator 一致）
 *   <li>契约：关键的 name!=value 枚举，其 wire 值必须等于 OpenAPI 契约声明值
 * </ul>
 */
public class EnumContractCheck {

  private static int fail = 0;

  public static void main(String[] args) throws Exception {
    String classesDir = args.length > 0 ? args[0] : "target/classes";
    List<Class<?>> enums = listEnums(classesDir);
    System.out.println("== EnumContractCheck: " + enums.size() + " enums ==");

    int roundTripConstants = 0;
    for (Class<?> e : enums) {
      for (Object c : e.getEnumConstants()) {
        roundTripConstants++;
        try {
          String json = JsonUtil.toJson(c);
          Object back = JsonUtil.parse(json, e);
          if (back != c) {
            fail++;
            System.out.println("[ROUNDTRIP-FAIL] " + e.getSimpleName() + "." + c + " -> " + json + " -> " + back);
          }
        } catch (Throwable t) {
          fail++;
          System.out.println("[ROUNDTRIP-ERROR] " + e.getSimpleName() + "." + c + " : " + t.getClass().getSimpleName());
        }
      }
    }
    System.out.println("round-trip constants checked = " + roundTripConstants + " , failures = " + fail);

    // 契约值断言（对照 OpenAPI 契约）
    assertWire("com.alipay.global.api.model.ams.DisputeEvidenceType", "FILE", "\"DISPUTE_EVIDENCE_FILE\"");
    assertWire("com.alipay.global.api.model.ams.DisputeEvidenceType", "TEMPLATE", "\"DISPUTE_EVIDENCE_TEMPLATE\"");
    assertWire("com.alipay.global.api.model.ams.InStorePaymentScenario", "PAYMENTCODE", "\"PaymentCode\"");
    assertWire("com.alipay.global.api.model.ams.InStorePaymentScenario", "ORDERCODE", "\"OrderCode\"");
    assertWire("com.alipay.global.api.model.ams.InStorePaymentScenario", "ENTRYCODE", "\"EntryCode\"");

    System.out.println("== total failures = " + fail + " ==");
    if (fail > 0) {
      System.exit(1);
    }
  }

  @SuppressWarnings({"unchecked", "rawtypes"})
  private static void assertWire(String enumClass, String constant, String expectedJson) {
    try {
      Class e = Class.forName(enumClass);
      Object c = Enum.valueOf(e, constant);
      String json = JsonUtil.toJson(c);
      boolean ok = expectedJson.equals(json);
      System.out.println(
          (ok ? "PASS " : "FAIL ") + enumClass.substring(enumClass.lastIndexOf('.') + 1) + "." + constant
              + " -> " + json + (ok ? "" : "  (expected " + expectedJson + ")"));
      if (!ok) {
        fail++;
      }
    } catch (Throwable t) {
      fail++;
      System.out.println("FAIL assertWire " + enumClass + "." + constant + " : " + t.getClass().getSimpleName());
    }
  }

  private static List<Class<?>> listEnums(String classesDir) throws Exception {
    List<Class<?>> out = new ArrayList<>();
    File root = new File(classesDir);
    walk(root, root, out);
    return out;
  }

  private static void walk(File root, File dir, List<Class<?>> out) throws Exception {
    File[] fs = dir.listFiles();
    if (fs == null) {
      return;
    }
    for (File f : fs) {
      if (f.isDirectory()) {
        walk(root, f, out);
      } else if (f.getName().endsWith(".class") && !f.getName().contains("$")) {
        String rel = root.toURI().relativize(f.toURI()).getPath();
        String cn = rel.substring(0, rel.length() - 6).replace('/', '.');
        if (!cn.startsWith("com.alipay.global.api.model")) {
          continue;
        }
        try {
          Class<?> c = Class.forName(cn);
          if (c.isEnum()) {
            out.add(c);
          }
        } catch (Throwable ignore) {
        }
      }
    }
  }
}
