package com.alipay.global.api.tools;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.util.Base64;
import org.junit.Test;

public class SignatureSecurityRegressionTest {
  @Test
  public void signsAndVerifiesWithoutStoredCredentials() throws Exception {
    KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
    generator.initialize(2048);
    KeyPair pair = generator.generateKeyPair();
    String privateKey = Base64.getEncoder().encodeToString(pair.getPrivate().getEncoded());
    String publicKey = Base64.getEncoder().encodeToString(pair.getPublic().getEncoded());
    for (String path : new String[] {"/ams/api/v1/payments/pay", "/ams/api/v1/payments/createPaymentSession"}) {
      String body = "{\"result\":{\"resultStatus\":\"S\"}}";
      String time = "2026-10-22T00:00:00Z";
      String signature = SignatureTool.sign("POST", path, "local-test", time, body, privateKey);
      assertTrue(SignatureTool.verify("POST", path, "local-test", time, body, signature, publicKey));
      assertFalse(SignatureTool.verify("POST", path, "local-test", time, body + " ", signature, publicKey));
    }
  }
}
