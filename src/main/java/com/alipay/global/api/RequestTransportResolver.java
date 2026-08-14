package com.alipay.global.api;

import com.alipay.global.api.net.HttpMethod;
import com.alipay.global.api.request.AlipayRequest;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

final class RequestTransportResolver {

  private static final Set<String> SESSION_HTTP2_ROUTES =
      Collections.unmodifiableSet(
          new HashSet<>(
              Arrays.asList(routeKey(HttpMethod.POST.name(), "/ams/api/v1/meter/uploadEvent"))));

  private RequestTransportResolver() {}

  static boolean requiresSessionHttp2(AlipayRequest<?> request) {
    return request != null
        && SESSION_HTTP2_ROUTES.contains(routeKey(request.getHttpMethod(), request.getPath()));
  }

  private static String routeKey(String httpMethod, String path) {
    return (httpMethod == null ? "" : httpMethod.toUpperCase(Locale.ROOT)) + " " + path;
  }
}
