package com.alipay.global.api;

import java.net.URI;
import java.nio.ByteBuffer;
import java.nio.charset.CodingErrorAction;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/** Immutable authentication for ordinary OpenAPI v2 requests. */
final class ApiKeyAuth {
  private final String key;
  private final boolean sandbox;
  private final String clientId;

  ApiKeyAuth(String gatewayUrl, String apiKey) {
    try {
      URI gateway = new URI(gatewayUrl);
      if (!"https".equals(gateway.getScheme()) || gateway.getHost() == null
          || gateway.getRawUserInfo() != null || gateway.getRawQuery() != null
          || gateway.getRawFragment() != null
          || !(gateway.getRawPath().isEmpty() || "/".equals(gateway.getRawPath()))) {
        throw new IllegalArgumentException();
      }
    } catch (Exception e) {
      throw new IllegalArgumentException("API Key requires an absolute HTTPS gateway without credentials, path, query or fragment");
    }
    try {
      String[] parts = apiKey.split("_", 4);
      if (parts.length != 4 || !("isak".equals(parts[0]) || "irak".equals(parts[0]))
          || !("TEST".equals(parts[1]) || "PROD".equals(parts[1]))
          || !parts[2].matches("[A-Za-z0-9+/]+") || !parts[3].matches("[A-Za-z0-9_-]+")) {
        throw new IllegalArgumentException();
      }
      byte[] decoded = Base64.getDecoder().decode(parts[2]);
      if (decoded.length == 0
          || !Base64.getEncoder().withoutPadding().encodeToString(decoded).equals(parts[2])) {
        throw new IllegalArgumentException();
      }
      clientId = StandardCharsets.UTF_8.newDecoder()
          .onMalformedInput(CodingErrorAction.REPORT).onUnmappableCharacter(CodingErrorAction.REPORT)
          .decode(ByteBuffer.wrap(decoded)).toString();
      sandbox = "TEST".equals(parts[1]);
      key = apiKey;
    } catch (Exception e) {
      throw new IllegalArgumentException("Invalid API Key format; expected Standard or Restricted TEST/PROD key");
    }
  }

  String path(String path) {
    if (path != null && path.startsWith("/ams/sandbox/api/")) {
      path = "/ams/api/" + path.substring("/ams/sandbox/api/".length());
    }
    if (path == null || !path.startsWith("/ams/api/") || path.contains("?") || path.contains("#")) {
      throw new IllegalArgumentException("API Key requires an ordinary /ams/api/ request path");
    }
    return sandbox ? "/ams/sandbox/api/" + path.substring("/ams/api/".length()) : path;
  }

  String clientId() { return clientId; }
  String authorization() { return "Bearer " + key; }
  String redact(String text) { return text == null ? null : text.replace(key, "[REDACTED]"); }
}
