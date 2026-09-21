# Antom SDK for Java

Latest release: **3.1.1**

## Installation

```xml
<dependency>
    <groupId>com.alipay.global.sdk</groupId>
    <artifactId>global-open-sdk-java</artifactId>
    <version>3.1.1</version>
</dependency>
```

Requires JDK 8+.

## Quick start

- **API Key:** follow the [setup guide](docs/api-key-client.md) and run the [sandbox example](src/main/java/com/alipay/global/api/example/ApiKeyPaymentSessionDemo.java).
- **RSA:** start with the [payment example](src/main/java/com/alipay/global/api/example/CashierPayDemoCode.java).
- Browse [more examples](src/main/java/com/alipay/global/api/example) and the [API documentation](https://global.alipay.com/docs/).

API Key and RSA clients share request/response models. File uploads and notification
verification still require RSA credentials.

## Upgrade notes

Gateway responses with `resultStatus S` must be signed; responses with only one
of the signature and response-time headers are rejected.

Billing integrations: `availableAmount` now uses `Amount`; the `AvailableAmount`
model has been removed.

Upgrading from 2.x? See the [Jackson migration guide](docs/java-migration.md).

## Meter event upload

`meter/uploadEvent` requires HTTP/2 and `X-Session-Id`. See the
[usage and requirements](docs/meter-event-upload.md).

## Support

For integration questions, contact overseas_support@service.alibaba.com.
