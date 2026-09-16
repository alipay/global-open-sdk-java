```
Language：JAVA  
JDK version：8+
Copyright：Ant financial services group  
```

#### 1 Please use the latest version

https://mvnrepository.com/artifact/com.alipay.global.sdk/global-open-sdk-java

```xml
<dependency>
    <groupId>com.alipay.global.sdk</groupId>
    <artifactId>global-open-sdk-java</artifactId>
    <version>3.1.0</version>
</dependency>
```
   
#### Upgrading to 3.0.0

The SDK now uses Jackson for ordinary JSON requests, multipart JSON parts, and
Meter HTTP/2 bodies. Its POM imports Jackson BOM `2.18.10`. An application's BOM
can override these dependencies; check the effective dependency tree and keep
Jackson modules aligned. Jackson 3 is not supported.

Existing Client calls, model field types, and Java enum constants are retained.
Generated AMS string enums write API wire values and accept both wire values and
historical Java constant names on input; unknown input returns null, including
direct calls to `fromValue`. In particular, `TEMPLATE`/`FILE` write
`DISPUTE_EVIDENCE_TEMPLATE`/`DISPUTE_EVIDENCE_FILE`, and
`PAYMENTCODE`/`ORDERCODE`/`ENTRYCODE` write `PaymentCode`/`OrderCode`/`EntryCode`.
Authentication responses read both `isPassed` and historical `passed`, but write
only `isPassed`.

For custom JSON handling, use `com.alipay.global.api.tools.JsonUtil.toJson` and
`JsonUtil.fromJson`. Object field order and complete JSON strings may change;
assert JSON structure instead of string snapshots. The SDK no longer supplies
fastjson transitively, so applications that still use it must manage that
dependency themselves. The SDK's mapper is private and does not reuse an
application's Spring ObjectMapper.

Sign exactly the JSON body you send. For notifications, call
`WebhookTool.checkSignature` with the original HTTP body **before** parsing it
with `JsonUtil.fromJson`; do not reserialize a notification before verification.
Meter HTTP/2 continues to use only `X-Session-Id`, as described below.

#### 2 Main class file  
```java
DefaultAlipayClient.java  

public DefaultAlipayClient(String gatewayUrl, String merchantPrivateKey, String alipayPublicKey);  
public <T extends AlipayResponse> T execute(AlipayRequest<T> alipayRequest);  
  
```

#### Meter event upload

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
  
```java
SignatureTool.java 

public static String sign(String httpMethod, String path, String clientId, String reqTimeStr, String reqBody, String merchantPrivateKey);  
public static boolean verify(String httpMethod, String path, String clientId, String rspTimeStr, String rspBody, String signature, String alipayPublicKey);  
 
```
  
#### 3 The sample for ams、aps、risk   

AMS:
  
```java
AlipayClient CLIENT = new DefaultAlipayClient("https://open-na.alipay.com", merchantPrivateKey, alipayPublicKey, clientid);

AlipayPayRequest alipayPayRequest = new AlipayPayRequest();
alipayPayRequest.setClientId("clientId");
alipayPayRequest.setProductCode(ProductCodeType.CASHIER_PAYMENT);
alipayPayRequest.setPaymentRequestId("pay_test_99");

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
  
```  

APS:

```java
AlipayClient CLIENT = new DefaultAlipayClient("https://open-na.alipay.com", merchantPrivateKey, alipayPublicKey, clientid);
 
AlipayApsInquiryPaymentRequest inquiryPaymentRequest = new AlipayApsInquiryPaymentRequest();
 
inquiryPaymentRequest.setClientId("clientId");
inquiryPaymentRequest.setPaymentId("20210518190796060008A0001628707");
inquiryPaymentRequest.setPaymentRequestId("rtanhU9au6F3VEbvbVWRz");
 
AlipayApsInquiryPaymentResponse alipayResponse = CLIENT.execute(inquiryPaymentRequest);

```  

RISK:

```java
AlipayClient CLIENT = new DefaultAlipayClient("https://open-sea-global.alipay.com", merchantPrivateKey, alipayPublicKey, clientid);

RiskDecideRequest riskDecideRequest = new RiskDecideRequest();
riskDecideRequest.setClientId("clietId");
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
```
  
The execute method contains the HTTP request to the gateway. 

If you're concerned about HTTP invocation performance, you can implement HTTP invocation yourself.
 
```java
public class YourAlipayClient extends BaseAlipayClient{

    public YourAlipayClient(String gatewayUrl, String merchantPrivateKey, String alipayPublicKey ){
        super(gatewayUrl, merchantPrivateKey, alipayPublicKey);
    }

    @Override
    public Map<String, String> buildCustomHeader() {
        // TODO 
    }

    @Override
    public HttpRpcResult sendRequest(String requestUrl, String httpMethod, Map<String, String> header, String reqBody)throws AlipayApiException {  
        // HTTP Call
        // ...
        
        HttpRpcResult resp = new HttpRpcResult();
        
        int respCode = "Get from response header";
        httpRpcResult.setRspCode(respCode);
        
        String rspSignValue = "Get from response header";
        resp.setRspSign(rspSignValue);
        
        String responseTime = "Get from response header";
        resp.setResponseTime(responseTime);
        
        String rspBody = "Get from response body";
        resp.setRspBody(rspBody);
        
        return resp;
    }
    
}

AlipayClient       yourAlipayClient  = new YourAlipayClient("https://open-na.alipay.com", "merchantPrivateKey", "alipayPublicKey");  
AlipayPayResponse  alipayPayResponse = yourAlipayClient.execute(aliPayRequest);  

```
  
#### 4 If you don't care about HTTP calls,the sample for sign and verify  
```java
String httpMethod = "POST";
String path       = "/ams/sandbox/api/v1/payments/pay";
String clientId   = "T_client";
String reqTimeStr = "2019-11-01T10:00:00+08:30";

String reqBody       = "{\"key1\":\"value1\"}";
String signReqValue  = SignatureTool.sign(httpMethod, path, clientId, reqTimeStr, reqBody, merchantPrivateKey);  
  
String rspBody    = "{\"key2\":\"value2\"}";
String rspTimeStr = "2019-11-01T10:00:01+08:30";
String signature  = "get from response header";
boolean isPass    = SignatureTool.verify(httpMethod, path, clientId, rspTimeStr, rspBody, signature, alipayPublicKey);
  
```
  
#### 5 For base64  

For compatibility with lower version of Java JDK, signatureTool provided a base64 encryptor DefaultBase64Encryptor by default. 

```java
public class DefaultBase64Encryptor implements Base64Encryptor {

    @Override
    public String encodeToString(byte[] src) {
        return DatatypeConverter.printBase64Binary(src);
    }

    @Override
    public byte[] decode(String src) {
        return DatatypeConverter.parseBase64Binary(src);
    }

}

```  

For better performance, you can also customize the base64 tool. For example, jdk8 API Base64.

##### 5.1 add "MyBase64Encryptor"
```java
public class MyBase64Encryptor implements Base64Encryptor {

    @Override
    public String encodeToString(byte[] src) {
        return Base64.getEncoder().encodeToString(src);
    }

    @Override
    public byte[] decode(String src) {
        return Base64.getDecoder().decode(src);
    }
    
}
```
##### 5.2 init before invoke alipayClient
```java
static {
    Base64Provider.setBase64Encryptor(new MyBase64Encryptor());
}
```

## API Key authentication

See the [complete API Key example](src/main/java/com/alipay/global/api/example/ApiKeyExample.java).

Initialize the client with your gateway URL and API Key; existing RSA usage remains supported.
This feature is available in the current source branch and has not been published yet.

Set `ANTOM_GATEWAY_URL` to your regional HTTPS gateway (for example,
`https://open-sea-global.alipay.com` for Asia), `ANTOM_API_KEY` to your key,
`ANTOM_REDIRECT_URL` to your checkout return URL, and `ANTOM_NOTIFY_URL` to your
notification endpoint. The application reads these variables; the SDK does not load them automatically.

The example creates a CARD payment session for USD 1.00 (`100` minor units),
with USD settlement. Use a merchant configured for this combination and a key
with createPaymentSession permission. Replace the example client IP with the
buyer's IP in your application. Exceptions propagate to the caller; a normal
response must still be checked for business success.

```java
import java.util.UUID;
import com.alipay.global.api.DefaultAlipayClient;
import com.alipay.global.api.model.ams.*;
import com.alipay.global.api.request.ams.pay.AlipayPaymentSessionRequest;
import com.alipay.global.api.response.ams.pay.AlipayPaymentSessionResponse;

public class ApiKeyExample {
    public static void main(String[] args) throws Exception {
        DefaultAlipayClient client = new DefaultAlipayClient(
            System.getenv("ANTOM_GATEWAY_URL"), System.getenv("ANTOM_API_KEY"));
        Amount amount = Amount.builder().currency("USD").value("100").build();
        AlipayPaymentSessionRequest request = new AlipayPaymentSessionRequest();
        request.setProductCode(ProductCodeType.CASHIER_PAYMENT);
        request.setProductScene("CHECKOUT_PAYMENT");
        request.setPaymentRequestId(UUID.randomUUID().toString());
        request.setOrder(Order.builder()
            .referenceOrderId(UUID.randomUUID().toString())
            .orderDescription("API Key example").orderAmount(amount).build());
        request.setPaymentAmount(amount);
        request.setPaymentMethod(PaymentMethod.builder().paymentMethodType("CARD").build());
        request.setPaymentFactor(PaymentFactor.builder().isAuthorization(false).build());
        request.setSettlementStrategy(SettlementStrategy.builder().settlementCurrency("USD").build());
        request.setEnv(Env.builder().terminalType(TerminalType.WEB).clientIp("127.0.0.1").build());
        request.setPaymentRedirectUrl(System.getenv("ANTOM_REDIRECT_URL"));
        request.setPaymentNotifyUrl(System.getenv("ANTOM_NOTIFY_URL"));

        // Transport errors propagate as exceptions; also check the business result.
        AlipayPaymentSessionResponse response = client.execute(request);
        if (response.getResult() == null
                || !"S".equals(response.getResult().getResultStatus())
                || !"SUCCESS".equals(response.getResult().getResultCode())) {
            throw new IllegalStateException("Session creation was not successful: "
                + (response.getResult() == null ? "missing result" : response.getResult().getResultCode()));
        }
        if (response.getPaymentSessionId() == null || response.getPaymentSessionId().isEmpty()) {
            throw new IllegalStateException("Missing paymentSessionId");
        }
        // Use response.getPaymentSessionData() or the returned URL with your checkout.
        System.out.println("Payment session created");
    }
}
```

- Standard and Restricted keys use the same client. TEST/PROD in the key selects
  the request environment; do not add a sandbox path to the gateway URL.
- Creating a session does not mean payment is complete. Notifications still use
  the existing signature verification mechanism.
- File upload is not supported with API Key authentication.
