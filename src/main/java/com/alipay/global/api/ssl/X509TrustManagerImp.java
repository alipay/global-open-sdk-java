package com.alipay.global.api.ssl;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.X509TrustManager;

/**
 * @deprecated This legacy implementation does not validate certificates and must not be used
 * for HTTPS connections. Use the platform default trust managers instead.
 */
@Deprecated
public class X509TrustManagerImp implements X509TrustManager {
  @Override
  public void checkClientTrusted(X509Certificate[] x509Certificates, String s)
      throws CertificateException {}

  @Override
  public void checkServerTrusted(X509Certificate[] x509Certificates, String s)
      throws CertificateException {}

  @Override
  public X509Certificate[] getAcceptedIssuers() {
    return new X509Certificate[0];
  }
}
