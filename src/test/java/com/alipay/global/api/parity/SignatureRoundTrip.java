package com.alipay.global.api.parity;

import com.alipay.global.api.request.ams.pay.AlipayPayConsultRequest;
import com.alipay.global.api.tools.JsonUtil;
import com.alipay.global.api.tools.SignatureTool;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.util.Base64;

/**
 * L3 签名往返（离线，使用 .env 真实商户私钥）。
 *
 * <p>验证：换 Jackson 引擎后，用真实商户私钥对 Jackson 序列化的请求 body 加签，并用从该私钥派生出的公钥验签成功。
 * 证明"序列化 -> 加签 -> 验签"链路在引擎更换后正常工作（签名作用于序列化字符串，与引擎无关）。
 *
 * <p>注意：.env 里的 public-key 是 alipay 响应验签公钥，与商户私钥非同一对，故此处用派生公钥自验。
 */
public class SignatureRoundTrip {

  public static void main(String[] args) throws Exception {
    String envPath = args.length > 0 ? args[0] : "../.env";
    java.util.Map<String, String> env = readEnv(envPath);
    String clientId = env.get("client-id");
    String privateKey = env.get("private-key");
    if (privateKey == null || privateKey.isEmpty()) {
      System.out.println("FAIL: private-key not found in " + envPath);
      System.exit(1);
    }

    // 构造真实请求并用 Jackson 序列化
    AlipayPayConsultRequest req = new AlipayPayConsultRequest();
    req.setClientId(clientId);
    String body = JsonUtil.toJson(req);
    String path = req.getPath();
    String time = String.valueOf(System.currentTimeMillis());
    String content = SignatureTool.genSignContent("POST", path, clientId, time, body);

    // 用真实商户私钥加签
    String signature = SignatureTool.sign(content, privateKey);

    // 从私钥派生公钥并验签
    String derivedPub = derivePublicKeyBase64(privateKey);
    boolean ok = SignatureTool.verify(content, signature, derivedPub);

    System.out.println("== L3 SignatureRoundTrip (Jackson body + real merchant key) ==");
    System.out.println("OBS|path=" + path);
    System.out.println("OBS|body=" + body);
    System.out.println("OBS|signature.len=" + signature.length());
    System.out.println((ok ? "PASS " : "FAIL ") + "sign-then-verify roundtrip");
    if (!ok) {
      System.exit(1);
    }
  }

  private static String derivePublicKeyBase64(String privateKeyBase64) throws Exception {
    byte[] der = Base64.getDecoder().decode(privateKeyBase64.replaceAll("\\s", ""));
    KeyFactory kf = KeyFactory.getInstance("RSA");
    RSAPrivateCrtKey priv =
        (RSAPrivateCrtKey) kf.generatePrivate(new PKCS8EncodedKeySpec(der));
    PublicKey pub =
        kf.generatePublic(new RSAPublicKeySpec(priv.getModulus(), priv.getPublicExponent()));
    return Base64.getEncoder().encodeToString(pub.getEncoded());
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
