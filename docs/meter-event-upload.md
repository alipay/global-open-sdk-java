# Meter event upload

`meter/createSession` uses the regular signed AMS transport. Use its session ID
to call `meter/uploadEvent` through `executeWithHeaders`:

```java
AlipayMeterUploadEventRequest request = new AlipayMeterUploadEventRequest();
request.setMeters(meters);

Map<String, String> headers = new HashMap<>();
headers.put("X-Session-Id", sessionId);
AlipayMeterUploadEventResponse response = CLIENT.executeWithHeaders(request, headers);
```

The SDK sends `meter/uploadEvent` to the gateway URL configured on the client,
without sandbox path rewriting, request signing, response signature verification,
or automatic retries. This API requires HTTP/2.
