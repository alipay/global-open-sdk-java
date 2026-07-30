package com.alipay.global.api.tools;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class SdkVersion {

  private static final String SDK_NAME = "global-open-sdk-java";
  private static final String VERSION = loadVersion();

  private SdkVersion() {}

  public static String getVersion() {
    return VERSION;
  }

  public static String getUserAgent() {
    return SDK_NAME + "/" + VERSION;
  }

  private static String loadVersion() {
    InputStream input = SdkVersion.class.getResourceAsStream("sdk-version.properties");
    if (input == null) {
      return "unknown";
    }

    try {
      Properties properties = new Properties();
      properties.load(input);
      String version = properties.getProperty("sdk.version");
      if (version == null || version.trim().isEmpty() || version.contains("${")) {
        return "unknown";
      }
      return version.trim();
    } catch (IOException ignored) {
      return "unknown";
    } finally {
      try {
        input.close();
      } catch (IOException ignored) {
        // Nothing to do.
      }
    }
  }
}
