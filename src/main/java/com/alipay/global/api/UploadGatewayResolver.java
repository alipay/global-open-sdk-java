package com.alipay.global.api;

import com.alipay.global.api.exception.AlipayApiException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

/** Resolves the regional upload gateway without changing normal API routing. */
final class UploadGatewayResolver {

  private static final Map<String, String> DEFAULT_GATEWAYS;

  static {
    Map<String, String> gateways = new HashMap<String, String>();
    gateways.put("open-sea-global.alipay.com", "https://open-big-sea.alipay.com");
    gateways.put("open-sea.alipay.com", "https://open-big-sea.alipay.com");
    gateways.put("open-na-global.alipay.com", "https://open-big-na.alipay.com");
    gateways.put("open-na.alipay.com", "https://open-big-na.alipay.com");
    gateways.put("open-de-global.alipay.com", "https://open-big-de-global.alipay.com");
    DEFAULT_GATEWAYS = Collections.unmodifiableMap(gateways);
  }

  private UploadGatewayResolver() {}

  static String resolve(String normalGatewayUrl, String explicitUploadGatewayUrl)
      throws AlipayApiException {
    if (StringUtils.isNotBlank(explicitUploadGatewayUrl)) {
      return explicitUploadGatewayUrl;
    }

    URI normalGateway = parseNormalGateway(normalGatewayUrl);
    String mappedGateway = DEFAULT_GATEWAYS.get(normalGateway.getHost().toLowerCase(Locale.ROOT));
    if (mappedGateway == null) {
      throw new AlipayApiException(
          "No default file gateway mapping exists for "
              + normalGateway.getHost()
              + ". Configure uploadGatewayUrl explicitly.");
    }
    return mappedGateway;
  }

  static String normalizeExplicit(String uploadGatewayUrl) {
    if (StringUtils.isBlank(uploadGatewayUrl)) {
      throw new IllegalArgumentException("uploadGatewayUrl can't be blank");
    }
    URI gateway = parseAbsoluteHttps(uploadGatewayUrl.trim(), "uploadGatewayUrl");
    if (gateway.getRawUserInfo() != null
        || gateway.getRawQuery() != null
        || gateway.getRawFragment() != null
        || (StringUtils.isNotEmpty(gateway.getRawPath()) && !"/".equals(gateway.getRawPath()))) {
      throw new IllegalArgumentException(
          "uploadGatewayUrl must not include user info, path, query, or fragment");
    }
    String authority = gateway.getRawAuthority().toLowerCase(Locale.ROOT);
    return "https://" + authority;
  }

  private static URI parseNormalGateway(String normalGatewayUrl) throws AlipayApiException {
    if (StringUtils.isBlank(normalGatewayUrl)) {
      throw new AlipayApiException(
          "gatewayUrl can't be blank. Configure uploadGatewayUrl explicitly.");
    }
    String candidate = normalGatewayUrl.trim();
    if (!candidate.contains("://")) {
      candidate = "https://" + candidate;
    }
    final URI gateway;
    try {
      gateway = parseAbsoluteHttps(candidate, "gatewayUrl");
    } catch (IllegalArgumentException e) {
      throw new AlipayApiException(
          "gatewayUrl can't be mapped to a file gateway. Configure uploadGatewayUrl explicitly.",
          e);
    }
    if (gateway.getRawUserInfo() != null
        || gateway.getRawQuery() != null
        || gateway.getRawFragment() != null
        || (StringUtils.isNotEmpty(gateway.getRawPath()) && !"/".equals(gateway.getRawPath()))
        || (gateway.getPort() >= 0 && gateway.getPort() != 443)) {
      throw new AlipayApiException(
          "gatewayUrl can't be mapped to a file gateway. Configure uploadGatewayUrl explicitly.");
    }
    return gateway;
  }

  private static URI parseAbsoluteHttps(String url, String parameterName) {
    try {
      URI parsed = new URI(url);
      if (!parsed.isAbsolute()
          || !"https".equalsIgnoreCase(parsed.getScheme())
          || StringUtils.isBlank(parsed.getHost())) {
        throw new IllegalArgumentException(parameterName + " must be an absolute HTTPS base URL");
      }
      return parsed;
    } catch (URISyntaxException e) {
      throw new IllegalArgumentException(parameterName + " is invalid", e);
    }
  }
}
