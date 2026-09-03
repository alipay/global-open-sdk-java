package com.alipay.global.api;

import com.alipay.global.api.exception.AlipayApiException;
import com.alipay.global.api.model.ResultStatusType;
import com.alipay.global.api.net.HttpRpcResult;
import com.alipay.global.api.request.AlipayFileContent;
import com.alipay.global.api.request.AlipayFileRequest;
import com.alipay.global.api.response.AlipayResponse;
import com.alipay.global.api.tools.Constants;
import com.alipay.global.api.tools.DateTool;
import com.alipay.global.api.tools.JsonUtil;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

/** Internal execution path for SDK-provided file upload requests. */
final class FileUploadExecutor {

  private FileUploadExecutor() {}

  static <T extends AlipayResponse> T execute(
      BaseAlipayClient.FileUploadClientContext clientContext,
      String explicitUploadGatewayUrl,
      AlipayFileRequest<T> request)
      throws AlipayApiException {
    if (request == null) {
      throw new AlipayApiException("alipayFileRequest can't be null");
    }

    FileUploadOperation<T> operation = FileUploadOperationRegistry.resolve(request);
    operation.validate(request);
    PreparedFile preparedFile = prepareFile(request.getFileContent(), operation.getMaxFileSize());
    String requestBody = operation.buildBody(request, sha256Hex(preparedFile.content));
    if (StringUtils.isBlank(requestBody)) {
      throw new AlipayApiException("file request body can't be blank");
    }

    String uploadGatewayUrl =
        UploadGatewayResolver.resolve(
            clientContext.getConfiguredGatewayUrl(), explicitUploadGatewayUrl);
    String clientId =
        StringUtils.isBlank(request.getClientId())
            ? clientContext.getConfiguredClientId()
            : request.getClientId();
    if (StringUtils.isBlank(clientId)) {
      throw new AlipayApiException("clientId can't be blank");
    }
    String path =
        clientContext.resolveSandboxPath(
            operation.getPath(), operation.isUseSandboxUrl(), clientId);

    String requestTime = DateTool.getCurrentTimeMillis();
    String signature =
        clientContext.sign(operation.getHttpMethod(), path, clientId, requestTime, requestBody);
    Map<String, String> headers =
        clientContext.buildHeaders(requestTime, clientId, request.getKeyVersion(), signature);

    HttpRpcResult response;
    try {
      response =
          MultipartFileTransport.doPost(
              uploadGatewayUrl + path,
              headers,
              requestBody,
              operation.getFilePartNames(),
              preparedFile.fileName,
              preparedFile.contentType,
              preparedFile.content);
    } catch (AlipayApiException e) {
      throw e;
    } catch (Exception e) {
      throw new AlipayApiException("File upload request failed", e);
    }
    return parseResponse(clientContext, operation, path, clientId, response);
  }

  private static <T extends AlipayResponse> T parseResponse(
      BaseAlipayClient.FileUploadClientContext clientContext,
      FileUploadOperation<T> operation,
      String path,
      String clientId,
      HttpRpcResult response)
      throws AlipayApiException {
    if (response == null) {
      throw new AlipayApiException("HttpRpcResult is null");
    }
    String responseBody = response.getRspBody();
    if (response.getRspCode() != Constants.HTTP_SUCCESS_CODE) {
      throw new AlipayApiException(
          "Response data error, HTTP status="
              + response.getRspCode()
              + ", rspBody="
              + responseBody);
    }

    final T parsedResponse;
    try {
      parsedResponse = JsonUtil.fromJson(responseBody, operation.getResponseClass());
    } catch (AlipayApiException e) {
      throw new AlipayApiException("Response body is not valid JSON", e);
    }
    if (parsedResponse == null || parsedResponse.getResult() == null) {
      throw new AlipayApiException(
          "Response data error, result field is null, rspBody=" + responseBody);
    }

    boolean signatureMissing = StringUtils.isBlank(response.getRspSign());
    boolean responseTimeMissing = StringUtils.isBlank(response.getResponseTime());
    if (signatureMissing && responseTimeMissing) {
      if (ResultStatusType.F.equals(parsedResponse.getResult().getResultStatus())) {
        return parsedResponse;
      }
      throw new AlipayApiException("Unsigned file upload response is not a failure response");
    }
    if (signatureMissing || responseTimeMissing) {
      throw new AlipayApiException(
          "File upload response must contain both Signature and Response-Time");
    }
    if (!clientContext.verifyResponse(
        operation.getHttpMethod(),
        path,
        clientId,
        response.getResponseTime(),
        responseBody,
        response.getRspSign())) {
      throw new AlipayApiException("Response signature verify fail");
    }
    return parsedResponse;
  }

  private static PreparedFile prepareFile(AlipayFileContent fileContent, long maxFileSize)
      throws AlipayApiException {
    if (fileContent == null) {
      throw new AlipayApiException("file can't be null");
    }
    byte[] content;
    if (fileContent.isFile()) {
      content = readLocalFile(fileContent.getFile(), maxFileSize);
    } else {
      content = readCallerStream(fileContent.getInputStream(), maxFileSize);
    }
    String fileName = fileContent.getFileName();
    if (StringUtils.isBlank(fileName)) {
      throw new AlipayApiException("fileName can't be blank");
    }
    String contentType = URLConnection.guessContentTypeFromName(fileName);
    if (StringUtils.isBlank(contentType)) {
      contentType = "application/octet-stream";
    }
    return new PreparedFile(content, fileName, contentType);
  }

  private static byte[] readLocalFile(File file, long maxFileSize) throws AlipayApiException {
    if (file == null || !file.isFile() || !file.canRead()) {
      throw new AlipayApiException("file must be a readable regular file");
    }
    if (file.length() == 0) {
      throw new AlipayApiException("file can't be empty");
    }
    if (file.length() > maxFileSize) {
      throw new AlipayApiException("file size can't exceed " + maxFileSize + " bytes");
    }
    try (InputStream inputStream = new FileInputStream(file)) {
      return readBounded(inputStream, maxFileSize);
    } catch (IOException e) {
      throw new AlipayApiException("Unable to read file", e);
    }
  }

  private static byte[] readCallerStream(InputStream inputStream, long maxFileSize)
      throws AlipayApiException {
    if (inputStream == null) {
      throw new AlipayApiException("file stream can't be null");
    }

    FileChannel fileChannel = null;
    long originalPosition = -1;
    boolean marked = false;
    try {
      if (inputStream instanceof FileInputStream) {
        fileChannel = ((FileInputStream) inputStream).getChannel();
        originalPosition = fileChannel.position();
      } else if (inputStream.markSupported()) {
        inputStream.mark((int) maxFileSize + 1);
        marked = true;
      }
    } catch (IOException e) {
      throw new AlipayApiException("Unable to read file stream position", e);
    }

    byte[] content = null;
    Exception failure = null;
    try {
      content = readBounded(inputStream, maxFileSize);
    } catch (Exception e) {
      failure = e;
    }
    try {
      if (fileChannel != null) {
        fileChannel.position(originalPosition);
      } else if (marked) {
        inputStream.reset();
      }
    } catch (IOException e) {
      if (failure == null) {
        failure = e;
      } else {
        failure.addSuppressed(e);
      }
    }
    if (failure != null) {
      if (failure instanceof AlipayApiException) {
        throw (AlipayApiException) failure;
      }
      throw new AlipayApiException("Unable to read or restore file stream", failure);
    }
    return content;
  }

  private static byte[] readBounded(InputStream inputStream, long maxFileSize)
      throws IOException, AlipayApiException {
    int limit = (int) maxFileSize + 1;
    ByteArrayOutputStream buffer = new ByteArrayOutputStream(Math.min(limit, 8192));
    byte[] chunk = new byte[Math.min(limit, 8192)];
    while (buffer.size() < limit) {
      int remaining = limit - buffer.size();
      int count = inputStream.read(chunk, 0, Math.min(chunk.length, remaining));
      if (count < 0) {
        break;
      }
      if (count == 0) {
        continue;
      }
      buffer.write(chunk, 0, count);
    }
    if (buffer.size() == 0) {
      throw new AlipayApiException("file can't be empty");
    }
    if (buffer.size() > maxFileSize) {
      throw new AlipayApiException("file size can't exceed " + maxFileSize + " bytes");
    }
    return buffer.toByteArray();
  }

  private static String sha256Hex(byte[] content) throws AlipayApiException {
    try {
      byte[] digest = MessageDigest.getInstance("SHA-256").digest(content);
      StringBuilder hex = new StringBuilder(digest.length * 2);
      for (byte value : digest) {
        hex.append(String.format("%02x", value & 0xff));
      }
      return hex.toString();
    } catch (NoSuchAlgorithmException e) {
      throw new AlipayApiException("SHA-256 is not available", e);
    }
  }

  private static final class PreparedFile {
    private final byte[] content;
    private final String fileName;
    private final String contentType;

    private PreparedFile(byte[] content, String fileName, String contentType) {
      this.content = content;
      this.fileName = fileName;
      this.contentType = contentType;
    }
  }
}
