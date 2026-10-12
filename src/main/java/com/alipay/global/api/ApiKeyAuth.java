package com.alipay.global.api;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URI;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

/** Internal API Key configuration; identity fragments are deliberately opaque. */
final class ApiKeyAuth {
  private static final Set<String> RESERVED_HEADERS =
      new HashSet<>(
          Arrays.asList(
              "authorization",
              "signature",
              "client-id",
              "request-time",
              "key-version",
              "keyversion",
              "agent-token",
              "content-type",
              "user-agent",
              "x-sdkversion",
              "sdk-version",
              "host",
              "content-length",
              "transfer-encoding",
              "connection",
              "proxy-authorization"));
  final String gatewayUrl;
  private final String apiKey;
  private final boolean sandbox;

  ApiKeyAuth(String gatewayUrl, String apiKey) {
    if (apiKey == null
        || !apiKey.matches("(?:isak|irak)_(?:TEST|PROD)_[^_\\s\\p{Cntrl}]+_[^\\s\\p{Cntrl}]+")) {
      throw new IllegalArgumentException("apiKey must be a Standard or Restricted TEST/PROD key");
    }
    URI uri;
    try {
      uri = URI.create(gatewayUrl);
    } catch (Exception e) {
      throw new IllegalArgumentException("gatewayUrl must be an HTTPS base URL");
    }
    if (!"https".equalsIgnoreCase(uri.getScheme())
        || uri.getHost() == null
        || uri.getRawUserInfo() != null
        || uri.getRawQuery() != null
        || uri.getRawFragment() != null
        || !(uri.getRawPath().isEmpty() || "/".equals(uri.getRawPath()))) {
      throw new IllegalArgumentException("gatewayUrl must be an HTTPS base URL");
    }
    this.gatewayUrl =
        gatewayUrl.endsWith("/") ? gatewayUrl.substring(0, gatewayUrl.length() - 1) : gatewayUrl;
    this.apiKey = apiKey;
    this.sandbox = apiKey.split("_", 3)[1].equals("TEST");
  }

  String authorization() {
    return "Bearer " + apiKey;
  }

  String path(String path) {
    if (path == null
        || !path.matches("/ams/(?:sandbox/)?api/[A-Za-z0-9_/-]+")
        || path.contains("//")) {
      throw new IllegalArgumentException("request path must be an ordinary /ams/api/ path");
    }
    String normal = path.replaceFirst("^/ams/sandbox/api/", "/ams/api/");
    return sandbox ? normal.replaceFirst("^/ams/api/", "/ams/sandbox/api/") : normal;
  }

  static boolean reserved(String name) {
    return RESERVED_HEADERS.contains(name.toLowerCase(Locale.ROOT));
  }

  static void validateHeader(String name, String value) {
    if (name == null
        || !name.matches("[!#$%&'*+.^_`|~0-9A-Za-z-]+")
        || value == null
        || value.indexOf('\r') >= 0
        || value.indexOf('\n') >= 0) {
      throw new IllegalArgumentException("Invalid custom header");
    }
  }

  Throwable safeCause(Throwable error) {
    StringWriter trace = new StringWriter();
    error.printStackTrace(new PrintWriter(trace));
    return trace.toString().contains(apiKey) ? null : error;
  }

  String redact(String message) {
    return message == null ? null : message.replace(apiKey, "[REDACTED]");
  }
}
