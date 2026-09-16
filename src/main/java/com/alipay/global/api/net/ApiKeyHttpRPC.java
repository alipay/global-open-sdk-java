package com.alipay.global.api.net;

import com.alipay.global.api.exception.AlipayApiException;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;

/** API Key transport: runtime CA trust, hostname verification and no redirects. */
public final class ApiKeyHttpRPC {
  private ApiKeyHttpRPC() {}

  public static HttpRpcResult doPost(String url, Map<String, String> headers, String body)
      throws Exception {
    URL target = new URL(url);
    if (!"https".equals(target.getProtocol())) {
      throw new AlipayApiException("API Key requires HTTPS");
    }
    HttpsURLConnection connection = (HttpsURLConnection) target.openConnection();
    connection.setInstanceFollowRedirects(false);
    connection.setConnectTimeout(15000);
    connection.setReadTimeout(15000);
    connection.setRequestMethod("POST");
    connection.setDoOutput(true);
    byte[] payload = body.getBytes(StandardCharsets.UTF_8);
    // Streaming prevents HttpURLConnection from automatically replaying a POST.
    connection.setFixedLengthStreamingMode(payload.length);
    for (Map.Entry<String, String> entry : headers.entrySet()) {
      connection.setRequestProperty(entry.getKey(), entry.getValue());
    }
    try {
      try (OutputStream output = connection.getOutputStream()) {
        output.write(payload);
      }
      HttpRpcResult result = new HttpRpcResult();
      int status = connection.getResponseCode();
      result.setRspCode(status);
      InputStream stream = status >= 400 ? connection.getErrorStream() : connection.getInputStream();
      ByteArrayOutputStream bytes = new ByteArrayOutputStream();
      if (stream != null) {
        try (InputStream input = stream) {
          byte[] buffer = new byte[4096];
          int count;
          while ((count = input.read(buffer)) != -1) { bytes.write(buffer, 0, count); }
        }
      }
      result.setRspBody(new String(bytes.toByteArray(), StandardCharsets.UTF_8));
      return result;
    } finally {
      connection.disconnect();
    }
  }
}
