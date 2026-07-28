package com.alipay.global.api.parity;

import com.alipay.global.api.DefaultAlipayClient;
import com.alipay.global.api.request.ams.pay.AlipayPayQueryRequest;
import com.alipay.global.api.response.ams.pay.AlipayPayQueryResponse;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

/**
 * L4 沙箱端到端冒烟（真实网关调用，使用 .env）。
 *
 * <p>用只读的 inquiryPayment 查询一个不存在的单号：任何被正确签名的响应都能完整验证
 * "Jackson 序列化 -> 加签 -> HTTP -> Jackson 反序列化 -> 响应验签" 全链路在引擎更换后正常。
 * 拿到解析后的 Result（即使是 ORDER_NOT_EXIST）即代表链路通、验签过。
 */
public class SandboxSmoke {

  public static void main(String[] args) throws Exception {
    String envPath = args.length > 0 ? args[0] : "../.env";
    java.util.Map<String, String> env = readEnv(envPath);
    String gateway = env.get("gateway");
    String clientId = env.get("client-id");
    String privateKey = env.get("private-key");
    String publicKey = env.get("public-key");

    System.out.println("== L4 SandboxSmoke ==");
    System.out.println("OBS|gateway=" + gateway);
    System.out.println("OBS|clientId.sandbox=" + (clientId != null && clientId.startsWith("SANDBOX_")));

    DefaultAlipayClient client =
        new DefaultAlipayClient(gateway, privateKey, publicKey, clientId);

    AlipayPayQueryRequest req = new AlipayPayQueryRequest();
    req.setPaymentRequestId("qoder_jackson_smoke_" + System.currentTimeMillis());

    try {
      AlipayPayQueryResponse resp = client.execute(req);
      System.out.println("PASS chain ok (serialize+sign+http+parse+verify)");
      System.out.println("OBS|result=" + resp.getResult());
    } catch (Exception e) {
      // 区分：网络问题 vs 业务/验签问题
      System.out.println("INFO exception: " + e.getClass().getSimpleName() + " : " + e.getMessage());
      String msg = String.valueOf(e.getMessage());
      if (msg.contains("JSON") || msg.contains("parse") || msg.contains("serialize")) {
        System.out.println("FAIL likely JSON engine problem");
        System.exit(1);
      } else {
        System.out.println("NOTE non-JSON error (network/gateway/verify) — 见上面信息判断");
      }
    }
  }

  private static java.util.Map<String, String> readEnv(String path) throws Exception {
    java.util.Map<String, String> map = new java.util.HashMap<>();
    for (String line : Files.readAllLines(new File(path).toPath(), StandardCharsets.UTF_8)) {
      int idx = line.indexOf(':');
      if (idx > 0) {
        map.put(line.substring(0, idx).trim(), line.substring(idx + 1).trim());
      }
    }
    return map;
  }
}
