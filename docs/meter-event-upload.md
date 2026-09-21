# Meter event upload

`meter/createSession` uses the regular signed AMS transport. Use its session ID
to call `meter/uploadEvent` through `executeWithHeaders`:

```java
DefaultAlipayClient client = new DefaultAlipayClient(
    System.getenv("ANTOM_GATEWAY_URL"), System.getenv("ANTOM_MERCHANT_PRIVATE_KEY"),
    System.getenv("ANTOM_PUBLIC_KEY"), System.getenv("ANTOM_CLIENT_ID"));
AlipayMeterUploadEventRequest request = new AlipayMeterUploadEventRequest();
request.setMeters(meters);

Map<String, String> headers = new HashMap<>();
headers.put("X-Session-Id", sessionId);
AlipayMeterUploadEventResponse response = client.executeWithHeaders(request, headers);
```

The SDK sends `meter/uploadEvent` to the gateway URL configured on the client,
without sandbox path rewriting, request signing, response signature verification,
or automatic retries. This API requires HTTP/2.
