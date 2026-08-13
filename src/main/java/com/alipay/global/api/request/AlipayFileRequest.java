package com.alipay.global.api.request;

import com.alipay.global.api.response.AlipayResponse;
import java.io.File;
import java.io.InputStream;

/** Base type for SDK-provided file upload requests. */
public abstract class AlipayFileRequest<T extends AlipayResponse> {

  private AlipayFileContent fileContent;
  private String clientId;
  private Integer keyVersion;

  protected AlipayFileRequest() {}

  public final AlipayFileContent getFileContent() {
    return fileContent;
  }

  /**
   * Uses a local file as the upload content. The SDK opens and closes its own input stream.
   *
   * @param file a readable regular file
   */
  public final void setFile(File file) {
    this.fileContent = AlipayFileContent.fromFile(file);
  }

  /**
   * Uses a caller-owned stream as the upload content. The SDK never closes the stream. It restores
   * the original position for {@link java.io.FileInputStream} and mark-supported streams; other
   * streams remain consumed after the call.
   *
   * @param inputStream the caller-owned file content stream
   * @param fileName the multipart file name, including an extension when the API requires one
   */
  public final void setFile(InputStream inputStream, String fileName) {
    this.fileContent = AlipayFileContent.fromStream(inputStream, fileName);
  }

  /**
   * Uses a prebuilt file content wrapper.
   *
   * @param fileContent the upload content
   */
  public final void setFileContent(AlipayFileContent fileContent) {
    if (fileContent == null) {
      throw new IllegalArgumentException("fileContent can't be null");
    }
    this.fileContent = fileContent;
  }

  public final String getClientId() {
    return clientId;
  }

  public final void setClientId(String clientId) {
    this.clientId = clientId;
  }

  public final Integer getKeyVersion() {
    return keyVersion;
  }

  public final void setKeyVersion(Integer keyVersion) {
    this.keyVersion = keyVersion;
  }
}
