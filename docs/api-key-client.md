# API Key client

> API Key access is not yet available to all merchants. Use this client only if
> API Key access has been enabled for your account; otherwise, use RSA.

SDK support was added in 3.1.0. The client accepts
a regional HTTPS gateway and a complete API Key. TEST/PROD paths are selected from
the key without modifying the request or decoding a ClientId.

## Run the sandbox example

From this repository root, configure:

| Environment variable | Value |
|---|---|
| `ANTOM_GATEWAY_URL` | Your regional HTTPS gateway, without an API path |
| `ANTOM_API_KEY` | A Restricted TEST key authorized for createPaymentSession |
| `ANTOM_NOTIFY_URL` | Your HTTPS notification endpoint |

The [example](../src/main/java/com/alipay/global/api/example/ApiKeyPaymentSessionDemo.java) creates one USD 1.00 CARD checkout session. It rejects
non-Restricted-TEST keys, generates a new request ID per run, prints the response
and exits unsuccessfully if the business result is not `SUCCESS / S`. For brevity,
it also uses the notification URL as the redirect URL; use your checkout return
page as the redirect URL in a real integration.

```sh
mvn -q compile exec:java -Dexec.mainClass=com.alipay.global.api.example.ApiKeyPaymentSessionDemo
```

The printed response contains session credentials: print it only for local debugging.
Read API Keys from server-side configuration; never log them.

## Configuration and boundaries

Optional timeouts: `ApiKeyAlipayClient.builder(gatewayUrl, apiKey).connectTimeoutMillis(15000).readTimeoutMillis(30000).build()`. Each ordinary request releases its connections.

Ordinary requests verify TLS certificates and hostnames, reject redirects and do
not add automatic retries. Custom headers cannot override authentication headers.

- Existing RSA clients and business models remain available. Switching clients does not change the business request/response model.
- File uploads still require a separate RSA client.
- Session `uploadEvent` uses the existing HTTP/2 executor with only `X-Session-Id`, without Bearer or path rewriting. This example does not establish Billing/Meter permissions.
- Notifications still use the existing RSA verification tools and separately configured ClientId/public key. API Key is not a notification signing key.

The API Key Client and authentication/transport helpers are hand-maintained; preserve
them when regenerating business models.
