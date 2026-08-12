package com.alipay.global.api;

import com.alipay.global.api.exception.AlipayApiException;
import com.alipay.global.api.net.HttpMethod;
import com.alipay.global.api.request.AlipayFileRequest;
import com.alipay.global.api.response.AlipayResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/** Immutable transport contract for one SDK-provided file upload API. */
final class FileUploadOperation<T extends AlipayResponse> {

  private final String path;
  private final String httpMethod;
  private final long maxFileSize;
  private final List<String> filePartNames;
  private final Class<T> responseClass;
  private final boolean useSandboxUrl;
  private final RequestAdapter requestAdapter;

  FileUploadOperation(
      String path,
      long maxFileSize,
      List<String> filePartNames,
      Class<T> responseClass,
      boolean useSandboxUrl,
      RequestAdapter requestAdapter) {
    if (StringUtils.isBlank(path) || !path.startsWith("/")) {
      throw new IllegalArgumentException("path must start with /");
    }
    if (maxFileSize <= 0 || maxFileSize >= Integer.MAX_VALUE) {
      throw new IllegalArgumentException("maxFileSize must be between 1 and Integer.MAX_VALUE - 1");
    }
    if (filePartNames == null || filePartNames.isEmpty()) {
      throw new IllegalArgumentException("filePartNames can't be empty");
    }
    if (responseClass == null || requestAdapter == null) {
      throw new IllegalArgumentException("responseClass and requestAdapter can't be null");
    }
    List<String> validatedPartNames = new ArrayList<String>(filePartNames.size());
    for (String filePartName : filePartNames) {
      if (StringUtils.isBlank(filePartName)) {
        throw new IllegalArgumentException("filePartName can't be blank");
      }
      validatedPartNames.add(filePartName);
    }
    this.path = path;
    this.httpMethod = HttpMethod.POST.name();
    this.maxFileSize = maxFileSize;
    this.filePartNames = Collections.unmodifiableList(validatedPartNames);
    this.responseClass = responseClass;
    this.useSandboxUrl = useSandboxUrl;
    this.requestAdapter = requestAdapter;
  }

  String getPath() {
    return path;
  }

  String getHttpMethod() {
    return httpMethod;
  }

  long getMaxFileSize() {
    return maxFileSize;
  }

  List<String> getFilePartNames() {
    return filePartNames;
  }

  Class<T> getResponseClass() {
    return responseClass;
  }

  boolean isUseSandboxUrl() {
    return useSandboxUrl;
  }

  void validate(AlipayFileRequest<?> request) throws AlipayApiException {
    requestAdapter.validate(request);
  }

  String buildBody(AlipayFileRequest<?> request, String fileSha256) throws AlipayApiException {
    return requestAdapter.buildBody(request, fileSha256);
  }

  interface RequestAdapter {
    void validate(AlipayFileRequest<?> request) throws AlipayApiException;

    String buildBody(AlipayFileRequest<?> request, String fileSha256) throws AlipayApiException;
  }
}
