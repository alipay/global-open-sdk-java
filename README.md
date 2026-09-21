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
- **RSA:** follow the [configuration](#rsa-configuration) and [inline example](#payment) below.
- Browse [more examples](src/main/java/com/alipay/global/api/example) and the [API documentation](https://global.alipay.com/docs/).

API Key and RSA clients share request/response models. File uploads and notification
verification still require RSA credentials.

### API Key client

Set `ANTOM_GATEWAY_URL` and `ANTOM_API_KEY` in your server environment.
This initializes the client; see the [setup guide](docs/api-key-client.md) for a
complete sandbox request and its additional configuration. In Java, place the
import at file scope and the initialization statements inside a method.

```java
import com.alipay.global.api.ApiKeyAlipayClient;

ApiKeyAlipayClient client = new ApiKeyAlipayClient(
    System.getenv("ANTOM_GATEWAY_URL"), System.getenv("ANTOM_API_KEY"));
```

### RSA configuration

Before using the RSA client, prepare these values from your Antom integration:

```properties
gatewayUrl=your_regional_https_gateway
clientId=your_client_id
merchantPrivateKey=your_merchant_private_key
alipayPublicKey=your_antom_public_key
```

The examples read them from `ANTOM_GATEWAY_URL`, `ANTOM_CLIENT_ID`,
`ANTOM_MERCHANT_PRIVATE_KEY`, and `ANTOM_PUBLIC_KEY`, respectively.
Keep keys in server-side configuration. Replace sample order data and callback
URLs with your own values before sending a request.

### Payment

Save as `ReadmePayment.java`. These are separate examples; APS and Risk require
the corresponding product permissions.

```java
import com.alipay.global.api.*;
import com.alipay.global.api.model.ams.*;
import com.alipay.global.api.request.ams.pay.AlipayPayRequest;
import com.alipay.global.api.response.ams.pay.AlipayPayResponse;
import com.alipay.global.api.tools.JsonUtil;
import java.util.*;

public class ReadmePayment {
    public static void main(String[] args) throws Exception {
        String gatewayUrl = System.getenv("ANTOM_GATEWAY_URL");
        String clientId = System.getenv("ANTOM_CLIENT_ID");
        String merchantPrivateKey = System.getenv("ANTOM_MERCHANT_PRIVATE_KEY");
        String alipayPublicKey = System.getenv("ANTOM_PUBLIC_KEY");

        AlipayClient CLIENT = new DefaultAlipayClient(gatewayUrl, merchantPrivateKey, alipayPublicKey, clientId);

        AlipayPayRequest alipayPayRequest = new AlipayPayRequest();
        alipayPayRequest.setClientId(clientId);
        alipayPayRequest.setProductCode(ProductCodeType.CASHIER_PAYMENT);
        alipayPayRequest.setPaymentRequestId(UUID.randomUUID().toString());

        Amount paymentAmount = new Amount();
        paymentAmount.setCurrency("USD");
        paymentAmount.setValue("30000");
        alipayPayRequest.setPaymentAmount(paymentAmount);

        Order order = new Order();
        order.setReferenceOrderId("102775765075669");
        order.setOrderDescription("Mi Band 3 Wrist Strap Metal Screwless Stainless Steel For Xiaomi Mi Band 3");

        ChinaExtraTransInfo chinaExtraTransInfo = new ChinaExtraTransInfo();
        chinaExtraTransInfo.setBusinessType(BusinessType.HOTEL);
        chinaExtraTransInfo.setHotelName("hotelName");
        chinaExtraTransInfo.setCheckinTime("2020-06-26T10:00:00+08:00");
        chinaExtraTransInfo.setCheckoutTime("2020-06-26T10:00:00+08:00");
        Map<String, Object> extendInfo = new HashMap<String, Object>();
        extendInfo.put("chinaExtraTransInfo",chinaExtraTransInfo);
        order.setExtendInfo(JsonUtil.toJson(extendInfo));

        Merchant merchant = new Merchant();
        merchant.setMerchantMCC("testMcc");
        merchant.setReferenceMerchantId("referenceMerchantId");
        order.setMerchant(merchant);

        Amount orderAmount = new Amount();
        orderAmount.setCurrency("USD");
        orderAmount.setValue("30000");
        order.setOrderAmount(orderAmount);

        Env env = new Env();
        env.setTerminalType(TerminalType.APP);
        env.setOsType(OsType.IOS);
        alipayPayRequest.setEnv(env);

        alipayPayRequest.setOrder(order);

        PaymentMethod paymentMethod = new PaymentMethod();
        paymentMethod.setPaymentMethodType(WalletPaymentMethodType.ALIPAY_CN.name());
        alipayPayRequest.setPaymentMethod(paymentMethod);

        alipayPayRequest.setPaymentNotifyUrl("https://global.alipay.com/notify");
        alipayPayRequest.setPaymentRedirectUrl("https://global.alipay.com?param1=v1");

        SettlementStrategy settlementStrategy  = new SettlementStrategy();
        settlementStrategy.setSettlementCurrency("USD");
        alipayPayRequest.setSettlementStrategy(settlementStrategy);

        AlipayPayResponse alipayPayResponse = CLIENT.execute(alipayPayRequest);
    }
}
```

### APS inquiry

Save as `ReadmeAps.java`. These are separate examples; APS and Risk require
the corresponding product permissions.

```java
import com.alipay.global.api.*;
import com.alipay.global.api.request.aps.pay.AlipayApsInquiryPaymentRequest;
import com.alipay.global.api.response.aps.pay.AlipayApsInquiryPaymentResponse;

public class ReadmeAps {
    public static void main(String[] args) throws Exception {
        String gatewayUrl = System.getenv("ANTOM_GATEWAY_URL");
        String clientId = System.getenv("ANTOM_CLIENT_ID");
        String merchantPrivateKey = System.getenv("ANTOM_MERCHANT_PRIVATE_KEY");
        String alipayPublicKey = System.getenv("ANTOM_PUBLIC_KEY");

        AlipayClient CLIENT = new DefaultAlipayClient(gatewayUrl, merchantPrivateKey, alipayPublicKey, clientId);

        AlipayApsInquiryPaymentRequest inquiryPaymentRequest = new AlipayApsInquiryPaymentRequest();

        inquiryPaymentRequest.setClientId(clientId);
        inquiryPaymentRequest.setPaymentId("20210518190796060008A0001628707");
        inquiryPaymentRequest.setPaymentRequestId("rtanhU9au6F3VEbvbVWRz");

        AlipayApsInquiryPaymentResponse alipayResponse = CLIENT.execute(inquiryPaymentRequest);
    }
}
```

### Risk decision

Save as `ReadmeRisk.java`. These are separate examples; APS and Risk require
the corresponding product permissions.

```java
import com.alipay.global.api.*;
import com.alipay.global.api.model.ams.*;
import com.alipay.global.api.model.risk.*;
import com.alipay.global.api.model.risk.Merchant;
import com.alipay.global.api.model.risk.Order;
import com.alipay.global.api.model.risk.PaymentMethod;
import com.alipay.global.api.request.ams.risk.RiskDecideRequest;
import com.alipay.global.api.response.ams.risk.RiskDecideResponse;
import java.util.*;

public class ReadmeRisk {
    public static void main(String[] args) throws Exception {
        String gatewayUrl = System.getenv("ANTOM_GATEWAY_URL");
        String clientId = System.getenv("ANTOM_CLIENT_ID");
        String merchantPrivateKey = System.getenv("ANTOM_MERCHANT_PRIVATE_KEY");
        String alipayPublicKey = System.getenv("ANTOM_PUBLIC_KEY");

        AlipayClient CLIENT = new DefaultAlipayClient(gatewayUrl, merchantPrivateKey, alipayPublicKey, clientId);

        RiskDecideRequest riskDecideRequest = new RiskDecideRequest();
        riskDecideRequest.setClientId(clientId);
        riskDecideRequest.setReferenceTransactionId("test_referenceTransactionId");
        riskDecideRequest.setAuthorizationPhase(AuthorizationPhase.PRE_AUTHORIZATION);

        Order order = new Order();
        order.setReferenceOrderId("test_orderId");
        order.setOrderDescription("test_orderDesc");

        Amount orderAmount = new Amount();
        orderAmount.setCurrency("BRL");
        orderAmount.setValue("30000");
        order.setOrderAmount(orderAmount);

        Goods goods = new Goods();
        goods.setReferenceGoodsId("test_referenceGoodId");
        order.setGoods(Collections.singletonList(goods));

        Merchant merchant = new Merchant();
        merchant.setMerchantMCC("test_merchantMcc");
        merchant.setReferenceMerchantId("test_referenceMerchantId");
        order.setMerchant(merchant);
        riskDecideRequest.setOrders(Collections.singletonList(order));

        Buyer buyer = new Buyer();
        buyer.setReferenceBuyerId("test_reference_buyerId");
        buyer.setBuyerPhoneNo("test_phoneNo");
        buyer.setBuyerEmail("test@alipay.com");
        riskDecideRequest.setBuyer(buyer);

        Amount actualPaymentAmount = new Amount();
        actualPaymentAmount.setCurrency("BRL");
        actualPaymentAmount.setValue("300000");
        riskDecideRequest.setActualPaymentAmount(actualPaymentAmount);

        PaymentDetail paymentDetail = new PaymentDetail();
        Amount paymentAmount = new Amount();
        paymentAmount.setCurrency("BRL");
        paymentAmount.setValue("300000");
        paymentDetail.setAmount(paymentAmount);
        PaymentMethod paymentMethod = new PaymentMethod();
        paymentMethod.setPaymentMethodType("CARD");
        PaymentMethodMetaData paymentMethodMetaData = new PaymentMethodMetaData();
        paymentMethodMetaData.setCardNo("risk_testCardNo");
        paymentMethod.setPaymentMethodMetaData(paymentMethodMetaData);
        paymentDetail.setPaymentMethod(paymentMethod);
        riskDecideRequest.setPaymentDetails(Collections.singletonList(paymentDetail));

        Env env = new Env();
        env.setTerminalType(TerminalType.APP);
        env.setOsType(OsType.IOS);
        riskDecideRequest.setEnv(env);

        RiskDecideResponse response = CLIENT.execute(riskDecideRequest);
    }
}
```

## Upgrade notes

Gateway responses with `resultStatus S` must be signed; responses with only one
of the signature and response-time headers are rejected.

Billing integrations: `availableAmount` now uses `Amount`; the `AvailableAmount`
model has been removed.

Upgrading from 2.x? See the [Jackson migration guide](docs/java-migration.md).

## Meter event upload

`meter/createSession` uses the regular signed AMS transport. Use its session ID
to call `meter/uploadEvent` through `executeWithHeaders`:

The fragment below initializes a `DefaultAlipayClient` and assumes a valid session ID from
`meter/createSession`, and a populated collection of meter event batches.

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

## Advanced usage

### Custom HTTP transport

Subclass `BaseAlipayClient` to customize ordinary JSON requests. This working
starting point delegates to the SDK's HTTP transport. If you replace it, return
the original response body, HTTP status, signature, and response-time fields in
`HttpRpcResult` so the SDK can verify the response.

```java
import com.alipay.global.api.BaseAlipayClient;
import com.alipay.global.api.exception.AlipayApiException;
import com.alipay.global.api.net.DefaultHttpRPC;
import com.alipay.global.api.net.HttpRpcResult;
import java.util.Collections;
import java.util.Map;

public class YourAlipayClient extends BaseAlipayClient {
    public YourAlipayClient(String gatewayUrl, String merchantPrivateKey,
                            String alipayPublicKey, String clientId) {
        super(gatewayUrl, merchantPrivateKey, alipayPublicKey, clientId);
    }

    @Override
    public Map<String, String> buildCustomHeader() {
        return Collections.emptyMap();
    }

    @Override
    public HttpRpcResult sendRequest(String requestUrl, String httpMethod,
                                    Map<String, String> headers, String reqBody)
            throws AlipayApiException {
        if (!"POST".equals(httpMethod)) {
            throw new AlipayApiException("This example supports POST only");
        }
        try {
            return DefaultHttpRPC.doPost(requestUrl, headers, reqBody);
        } catch (Exception e) {
            throw new AlipayApiException(e);
        }
    }
}
```

### Sign and verify without the HTTP client

The following method-body fragment assumes you have the request/response values
and keys shown below. Use the exact transmitted path, timestamp, and JSON body.
Verify a notification's original body before parsing it; do not reserialize it.

```java
String requestSignature = com.alipay.global.api.tools.SignatureTool.sign(
    httpMethod, path, clientId, requestTime, requestBody, merchantPrivateKey);
boolean verified = com.alipay.global.api.tools.SignatureTool.verify(
    httpMethod, path, clientId, responseTime, responseBody, responseSignature, alipayPublicKey);
```

### Custom Base64 implementation

The default implementation uses Apache Commons Codec. To use the JDK implementation,
configure the provider once at application startup, before any client calls:

```java
com.alipay.global.api.base64.Base64Provider.setBase64Encryptor(
    new com.alipay.global.api.base64.Base64Encryptor() {
        @Override
        public String encodeToString(byte[] src) {
            return java.util.Base64.getEncoder().encodeToString(src);
        }

        @Override
        public byte[] decode(String src) {
            return java.util.Base64.getDecoder().decode(src);
        }
    });
```

## Support

For integration questions, contact overseas_support@service.alibaba.com.
