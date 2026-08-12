package com.alipay.global.api;

import com.alipay.global.api.exception.AlipayApiException;
import com.alipay.global.api.net.DefaultHttpRPC;
import com.alipay.global.api.net.HttpMethod;
import com.alipay.global.api.net.HttpRpcResult;
import com.alipay.global.api.tools.Constants;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.net.ssl.HttpsURLConnection;

/** Internal HTTPS transport for bounded multipart file uploads. */
final class MultipartFileTransport {

  private static final int READ_TIMEOUT = 15000;
  private static final int CONNECT_TIMEOUT = 15000;
  private static final String CRLF = "\r\n";
  private static final Charset UTF_8 = Charset.forName(Constants.DEFAULT_CHARSET);

  private MultipartFileTransport() {}

  static HttpRpcResult doPost(
      String url,
      Map<String, String> headers,
      String requestBody,
      List<String> filePartNames,
      String fileName,
      String fileContentType,
      byte[] fileContent)
      throws IOException, AlipayApiException {
    String boundary = "----AntomBoundary" + UUID.randomUUID().toString().replace("-", "");
    byte[] bodyPrefix = createBodyPrefix(boundary);
    byte[] requestBodyBytes = requestBody.getBytes(UTF_8);
    byte[] closingBoundary = ("--" + boundary + "--" + CRLF).getBytes(UTF_8);
    byte[][] filePrefixes = new byte[filePartNames.size()][];
    long contentLength = bodyPrefix.length + requestBodyBytes.length + CRLF.length();
    for (int i = 0; i < filePartNames.size(); i++) {
      filePrefixes[i] =
          createFilePrefix(
              boundary, filePartNames.get(i), sanitizeFileName(fileName), fileContentType);
      contentLength += filePrefixes[i].length + fileContent.length + CRLF.length();
    }
    contentLength += closingBoundary.length;

    String contentType = "multipart/form-data; boundary=" + boundary;
    HttpsURLConnection connection = openConnection(url, contentType);
    connection.setFixedLengthStreamingMode(contentLength);
    if (headers != null) {
      for (Map.Entry<String, String> entry : headers.entrySet()) {
        if (!Constants.CONTENT_TYPE_HEADER.equalsIgnoreCase(entry.getKey())
            && entry.getValue() != null) {
          connection.setRequestProperty(entry.getKey(), entry.getValue());
        }
      }
    }
    connection.setRequestProperty(Constants.CONTENT_TYPE_HEADER, contentType);
    connection.setRequestProperty(Constants.ACCEPT_HEADER, "application/json");

    try {
      connection.connect();
      try (DataOutputStream output = new DataOutputStream(connection.getOutputStream())) {
        output.write(bodyPrefix);
        output.write(requestBodyBytes);
        writeUtf8(output, CRLF);
        for (byte[] filePrefix : filePrefixes) {
          output.write(filePrefix);
          output.write(fileContent);
          writeUtf8(output, CRLF);
        }
        output.write(closingBoundary);
        output.flush();
      }

      HttpRpcResult result = new HttpRpcResult();
      result.setRspSign(DefaultHttpRPC.getResponseSignature(connection));
      result.setResponseTime(DefaultHttpRPC.getResponseTime(connection));
      result.setRspCode(connection.getResponseCode());
      result.setRspBody(DefaultHttpRPC.getResponseAsString(connection));
      return result;
    } finally {
      connection.disconnect();
    }
  }

  private static HttpsURLConnection openConnection(String url, String contentType)
      throws IOException, AlipayApiException {
    URLConnection rawConnection = new URL(url).openConnection();
    if (!(rawConnection instanceof HttpsURLConnection)) {
      throw new AlipayApiException("Only supports HTTPS.");
    }
    HttpsURLConnection connection = (HttpsURLConnection) rawConnection;
    connection.setRequestMethod(HttpMethod.POST.name());
    connection.setDoInput(true);
    connection.setDoOutput(true);
    connection.setConnectTimeout(CONNECT_TIMEOUT);
    connection.setReadTimeout(READ_TIMEOUT);
    connection.setRequestProperty(Constants.CONTENT_TYPE_HEADER, contentType);
    connection.setRequestProperty(Constants.CONNECTION_HEADER, "keep-alive");
    return connection;
  }

  private static byte[] createBodyPrefix(String boundary) {
    String prefix =
        "--"
            + boundary
            + CRLF
            + "Content-Disposition: form-data; name=\"body\""
            + CRLF
            + "Content-Type: application/json; charset=UTF-8"
            + CRLF
            + CRLF;
    return prefix.getBytes(UTF_8);
  }

  private static byte[] createFilePrefix(
      String boundary, String partName, String fileName, String contentType) {
    String prefix =
        "--"
            + boundary
            + CRLF
            + "Content-Disposition: form-data; name=\""
            + partName
            + "\"; filename=\""
            + fileName
            + "\""
            + CRLF
            + "Content-Type: "
            + contentType
            + CRLF
            + "Content-Transfer-Encoding: binary"
            + CRLF
            + CRLF;
    return prefix.getBytes(UTF_8);
  }

  private static void writeUtf8(DataOutputStream output, String value) throws IOException {
    output.write(value.getBytes(UTF_8));
  }

  private static String sanitizeFileName(String fileName) {
    String baseName = fileName;
    int slash = Math.max(baseName.lastIndexOf('/'), baseName.lastIndexOf('\\'));
    if (slash >= 0) {
      baseName = baseName.substring(slash + 1);
    }
    StringBuilder safeName = new StringBuilder(baseName.length());
    for (int i = 0; i < baseName.length(); i++) {
      char value = baseName.charAt(i);
      if (value == '"' || value == '\r' || value == '\n' || value == 0 || value < 0x20) {
        safeName.append('_');
      } else {
        safeName.append(value);
      }
    }
    return safeName.length() == 0 ? "file" : safeName.toString();
  }
}
