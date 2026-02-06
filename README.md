```
Language：JAVA  
JDK version：1.6+  
Copyright：Ant financial services group  
```

#### 1 Please use the latest version

https://mvnrepository.com/artifact/com.alipay.global.sdk/global-open-sdk-java

```xml
<dependency>
    <groupId>com.alipay.global.sdk</groupId>
    <artifactId>global-open-sdk-java</artifactId>
    <version>2.1.11</version>
</dependency>
```
   
#### 2 Main class file  
```java
DefaultAlipayClient.java  

public DefaultAlipayClient(String gatewayUrl, String merchantPrivateKey, String alipayPublicKey);  
public <T extends AlipayResponse> T execute(AlipayRequest<T> alipayRequest);  
  
```
  
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
JSONObject extendInfo = new JSONObject();
extendInfo.put("chinaExtraTransInfo",chinaExtraTransInfo);
order.setExtendInfo(extendInfo.toJSONString());

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
