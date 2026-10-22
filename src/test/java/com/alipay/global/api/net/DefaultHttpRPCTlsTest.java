package com.alipay.global.api.net;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import org.junit.Test;
import com.alipay.global.api.exception.AlipayApiException;

public class DefaultHttpRPCTlsTest {
  @Test
  public void preservesPlatformCertificateAndHostnameVerification() throws Exception {
    Method method = DefaultHttpRPC.class.getDeclaredMethod(
        "getConnection", URL.class, String.class, String.class);
    method.setAccessible(true);
    HttpsURLConnection connection = (HttpsURLConnection) method.invoke(
        null, new URL("https://localhost/"), "POST", "application/json");
    assertSame(HttpsURLConnection.getDefaultSSLSocketFactory(), connection.getSSLSocketFactory());
    assertSame(HttpsURLConnection.getDefaultHostnameVerifier(), connection.getHostnameVerifier());
    connection.disconnect();
  }

  @Test
  public void stillRejectsPlainHttp() throws Exception {
    Method method = DefaultHttpRPC.class.getDeclaredMethod(
        "getConnection", URL.class, String.class, String.class);
    method.setAccessible(true);
    try {
      method.invoke(null, new URL("http://localhost/"), "POST", "application/json");
      throw new AssertionError("Plain HTTP was accepted");
    } catch (InvocationTargetException error) {
      assertTrue(error.getCause() instanceof AlipayApiException);
    }
  }
}
