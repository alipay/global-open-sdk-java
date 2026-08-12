package com.alipay.global.api.request;

import java.io.File;
import java.io.InputStream;
import org.apache.commons.lang3.StringUtils;

/** A file or caller-owned stream used by a file upload request. */
public final class AlipayFileContent {

  private final File file;
  private final InputStream inputStream;
  private final String fileName;

  private AlipayFileContent(File file, InputStream inputStream, String fileName) {
    this.file = file;
    this.inputStream = inputStream;
    this.fileName = fileName;
  }

  /**
   * Creates upload content backed by a local file.
   *
   * @param file a readable regular file
   * @return file-backed upload content
   */
  public static AlipayFileContent fromFile(File file) {
    if (file == null) {
      throw new IllegalArgumentException("file can't be null");
    }
    return new AlipayFileContent(file, null, file.getName());
  }

  /**
   * Creates upload content backed by a caller-owned stream. The SDK does not close the stream.
   *
   * @param inputStream the file content stream
   * @param fileName the multipart file name
   * @return stream-backed upload content
   */
  public static AlipayFileContent fromStream(InputStream inputStream, String fileName) {
    if (inputStream == null) {
      throw new IllegalArgumentException("inputStream can't be null");
    }
    if (StringUtils.isBlank(fileName)) {
      throw new IllegalArgumentException("fileName is required for an InputStream");
    }
    return new AlipayFileContent(null, inputStream, fileName);
  }

  public File getFile() {
    return file;
  }

  public InputStream getInputStream() {
    return inputStream;
  }

  public String getFileName() {
    return fileName;
  }

  public boolean isFile() {
    return file != null;
  }
}
