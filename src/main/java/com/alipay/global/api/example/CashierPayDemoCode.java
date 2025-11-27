package com.alipay.global.api.example;

import com.alibaba.fastjson.JSONObject;
import com.alipay.global.api.AlipayClient;
import com.alipay.global.api.DefaultAlipayClient;
import com.alipay.global.api.exception.AlipayApiException;
import com.alipay.global.api.model.ams.*;
import com.alipay.global.api.model.constants.EndPointConstants;
import com.alipay.global.api.request.ams.pay.AlipayPayConsultRequest;
import com.alipay.global.api.request.ams.pay.AlipayPayQueryRequest;
import com.alipay.global.api.request.ams.pay.AlipayPayRequest;
import com.alipay.global.api.request.ams.pay.AlipayPaymentSessionRequest;
import com.alipay.global.api.response.ams.pay.AlipayPayConsultResponse;
import com.alipay.global.api.response.ams.pay.AlipayPayQueryResponse;
import com.alipay.global.api.response.ams.pay.AlipayPayResponse;
import com.alipay.global.api.response.ams.pay.AlipayPaymentSessionResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CashierPayDemoCode {

  /**
   * replace with your client id. find your client id here: <a
   * href="https://dashboard.alipay.com/global-payments/developers/quickStart">quickStart</a>
   */
  public static final String CLIENT_ID = "SANDBOX_5YC31G2ZNMQK07357";

  /**
   * replace with your antom public key (used to verify signature). find your antom public key here:
   * <a href="https://dashboard.alipay.com/global-payments/developers/quickStart">quickStart</a>
   */
  public static final String ANTOM_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAq7zEydi4Q2VvUIb1Mjpm/I2R3NVWcSMddlhvHYJADZ07YOGjvlQPbH3iixhLMnk1Y0tT7Sw7B1Ov1kXDGUhnui/YmGQBDbz9vg4iPDXA8OU7TaSsIk2BbP4+uZoortx2AZu/ABTGBDvyhLyJBkNplJ7196Np+IMaxi2RlT2NEAV4vFIurUcfFl5vvMliyV1SacvIyONkurzixSLirlKBl35t1mGm44xqh7M40tcMScgdF8pIdkzVz0nAnBcGb0aTeD3YLQmYFFmbQhWIe7MAa0BPEK7sxTJ1x1PbRUCHEXiZURnPjZTD7FBsTfLlcGOlOe0DXB7mrWm+AP+fVBjbAwIDAQAB";

  /**
   * replace with your private key (used to sign). please ensure the secure storage of your private
   * key to prevent leakage
   */
  public static final String MERCHANT_PRIVATE_KEY = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDhnOLYh3Tte5ELNzD6kr6TSN+F4oXaNlnKgx2aGf/xSSHIh1k+wihv6HbwPAexdtjpDQAgwEv4YXpdH3erQLuy3oBIBVsdbXWg09TRttyBeM8FzbMru6qR1TceypEPhR9W4/hP9DZEmn9XZmR7xR9KStDKJdnqSNr578IVFvp3hXUt2HfwoHbUwwOPbu8a66th9b1PyNJ5DOdSoTj52tvFMOyfCmKOn9U/bwtcT/EGEJFlIj1QjBSwlEeCBDUVwwKlo2ttMP5Omy7i4Lxi5NKAMdw+Cru49FqGEf9B/JKfovd6EcwrnUeDfXVltNrPJjdr19WzatDh0k0wE/9QT6EnAgMBAAECggEAZsbQdDFo69KRpZlT36I/3NqisNwbe+esidum/Y+Aj8tv72jxF+zc/PXaUOAX5RkuASSh/Ul8kj7dvlRacJJBr1868xQ1iLdXkZdOaOazluuQ66TkTTTlpB+MR6Oh538OYsfhU5L9sczr28XSWqvW8EIa0SvjFJ5x2tAFCxR3r0AqXFrRteHSPYI01sle9ynCq3frBQwX481N/T0YvDQNFiRw+YlzJwJsZqPooFA2H/o+AL+LQED7eevnLYvevNS4GGVkWNO7gfKFHJA3RCMJgRqsXfxs2SG2cBx6YBYCQ7JurP8veMr3NBf/OOGCZln4zY4Vl5bTXe5vxeT5gzm18QKBgQD/jCH38x0AIjx0zwpZyvcp6C/2PohVjb6B/TbAiMmaIjpei06DCXHrDiObTLoxguZfmA+ypiPTZBOwFEDo7wDJ8khQwRMx9ydPMiaWoFCvl5iSke2vs/ONxdw02zRGj2uivgqjDf+94eTS8aSTJ+7kt1KLq4ZQf80Efywv+0xVnwKBgQDiAy5MMU8oDiSun8FoSBX3SomjdOX/tg4hMZ2PKYnXTJFUJ5bkjLhgdsPO5WGcFGsdReuweTzKteIRmS0zvdekVIpWFchflyeIM3+OuI1ZJJG6t28Xg3e8VOXCD917fjLnOOmH3f7PV/rj59wVM0yPvGStlAbPP0kwrcci8Wo3eQKBgQDE/ujctGwhw0KppUVMbRtWEeiPQitlEGzQ1jtT9t661DH82hT/DNPlqLOoL2DFdCxVeup3BH5PojFPJn3XUw9fnkdDAWPju6xw768xpIouooV6T8ZUETvqiaG0mVrWHg+SmD+o7My+OxpjxuXgjwMpC201wFc9TRflpIeSwX1Z7wKBgQCpUgy7VC2jKoVctZ6ly2t5akQXSxqMKg4H3C3X9RypSVmPHGG1M59l1VP4imxIDBv7QEjEWu+qRfzphkIRA2asXBGPUJ5eztT0+u/TMnvijr0GjyoRCZMIaun+KviY7gCgrUh3W17sY1M4rpl44Ie5H0ClscIwPY9NgsMvcIFMsQKBgG/XoSq4KjB+/SFdLTH4ITLb6Q8rvWOOyu6OvTBCgfxhq4R+rBP/40bvd1Ax5Eawfwq/GDfUL5jpzoaJGXGXDI90eXdeDOHZB7rq/+un9De1jPLGc1Ty7YT3SctYAvFw8jo0K65eckL7AaiGHk39eOXrWmJVVchOVlkX8TayiTgk";

  /**
   * please replace with your endpoint. find your endpoint here: <a
   * href="https://dashboard.alipay.com/global-payments/developers/quickStart">quickStart</a>
   */
  private static final AlipayClient CLIENT =
      new DefaultAlipayClient(
          EndPointConstants.SG, MERCHANT_PRIVATE_KEY, ANTOM_PUBLIC_KEY, CLIENT_ID);

  public static void main(String[] args) {
    executeConsult();
    //        executePayWithCard();
    //        executePayWithBlik();
    //        executePaymentSessionCreateWithCard();
    //        executeInquiry();
  }

  public static void executeConsult() {
    AlipayPayConsultRequest alipayPayConsultRequest = new AlipayPayConsultRequest();
    alipayPayConsultRequest.setProductCode(ProductCodeType.CASHIER_PAYMENT);

    // set amount
    Amount amount = Amount.builder().value("4200").currency("BRL").build();
    alipayPayConsultRequest.setPaymentAmount(amount);

    // set env Info
    Env env = Env.builder().terminalType(TerminalType.WEB).build();
    alipayPayConsultRequest.setEnv(env);

    AlipayPayConsultResponse alipayPayConsultResponse = null;

    try {
      alipayPayConsultResponse = CLIENT.execute(alipayPayConsultRequest);
      System.out.println(JSONObject.toJSON(alipayPayConsultResponse));
    } catch (AlipayApiException e) {
      String errorMsg = e.getMessage();
      // handle error condition
    }
  }

  /** show how to finish a payment by Card paymentMethod */
  public static void executePayWithCard() {
    AlipayPayRequest alipayPayRequest = new AlipayPayRequest();
    alipayPayRequest.setProductCode(ProductCodeType.CASHIER_PAYMENT);

    // replace with your paymentRequestId
    String paymentRequestId = UUID.randomUUID().toString();
    alipayPayRequest.setPaymentRequestId(paymentRequestId);

    // set amount
    Amount amount = Amount.builder().value("4200").currency("BRL").build();
    alipayPayRequest.setPaymentAmount(amount);

    // if merchant collect card info, set card info in this paymentMethodMetaData
    Map<String, Object> paymentMethodMetaData = new HashMap<String, Object>();
    paymentMethodMetaData.put("cardNo", "0255187751531899");
    paymentMethodMetaData.put("cvv", "712");
    paymentMethodMetaData.put("expiryMonth", "06");
    paymentMethodMetaData.put("expiryYear", "28");
    paymentMethodMetaData.put("tokenize", false);
    paymentMethodMetaData.put("cpf", "671.998.112-31");
    JSONObject cardholderName = new JSONObject();
    cardholderName.put("firstName", "Alan");
    cardholderName.put("lastName", "Wallex");
    paymentMethodMetaData.put("cardholderName", cardholderName);

    // set paymentMethod
    PaymentMethod paymentMethod =
        PaymentMethod.builder()
            .paymentMethodType("CARD")
            .paymentMethodMetaData(paymentMethodMetaData)
            .build();
    alipayPayRequest.setPaymentMethod(paymentMethod);

    // replace with your orderId
    String orderId = UUID.randomUUID().toString();

    // set buyer info
    Buyer buyer = Buyer.builder().referenceBuyerId("yourBuyerId").build();

    // set order info
    Order order =
        Order.builder()
            .referenceOrderId(orderId)
            .orderDescription("antom testing order")
            .orderAmount(amount)
            .buyer(buyer)
            .build();
    alipayPayRequest.setOrder(order);

    // set env info
    Env env = Env.builder().terminalType(TerminalType.WEB).clientIp("1.2.3.4").build();
    alipayPayRequest.setEnv(env);

    // set auth capture payment mode
    PaymentFactor paymentFactor = PaymentFactor.builder().isAuthorization(true).build();
    alipayPayRequest.setPaymentFactor(paymentFactor);

    // replace with your notify url
    alipayPayRequest.setPaymentNotifyUrl("http://www.yourNotifyUrl.com");

    // replace with your redirect url
    alipayPayRequest.setPaymentRedirectUrl("http://www.yourRedirectUrl.com");

    // do payment
    AlipayPayResponse alipayPayResponse = null;
    try {
      alipayPayResponse = CLIENT.execute(alipayPayRequest);
      System.out.println(JSONObject.toJSON(alipayPayResponse));
    } catch (AlipayApiException e) {
      String errorMsg = e.getMessage();
      // handle error condition
    }
  }

  /** show how to finish a payment by Blik */
  public static void executePayWithBlik() {
    AlipayPayRequest alipayPayRequest = new AlipayPayRequest();
    alipayPayRequest.setProductCode(ProductCodeType.CASHIER_PAYMENT);

    // replace with your paymentRequestId
    String paymentRequestId = UUID.randomUUID().toString();
    alipayPayRequest.setPaymentRequestId(paymentRequestId);

    // set amount
    Amount amount = Amount.builder().value("4200").currency("PLN").build();
    alipayPayRequest.setPaymentAmount(amount);

    // set paymentMethod
    PaymentMethod paymentMethod = PaymentMethod.builder().paymentMethodType("BLIK").build();
    alipayPayRequest.setPaymentMethod(paymentMethod);

    // replace with your orderId
    String orderId = UUID.randomUUID().toString();

    // set buyer info
    Buyer buyer = Buyer.builder().referenceBuyerId("yourBuyerId").build();

    // set order info
    Order order =
        Order.builder()
            .referenceOrderId(orderId)
            .orderDescription("antom testing order")
            .orderAmount(amount)
            .buyer(buyer)
            .build();
    alipayPayRequest.setOrder(order);

    // set env info
    Env env = Env.builder().terminalType(TerminalType.WEB).clientIp("1.2.3.4").build();
    alipayPayRequest.setEnv(env);

    // replace with your notify url
    alipayPayRequest.setPaymentNotifyUrl("http://www.yourNotifyUrl.com");

    // replace with your redirect url
    alipayPayRequest.setPaymentRedirectUrl("http://www.yourRedirectUrl.com");

    // do payment
    AlipayPayResponse alipayPayResponse = null;
    try {
      alipayPayResponse = CLIENT.execute(alipayPayRequest);
    } catch (AlipayApiException e) {
      String errorMsg = e.getMessage();
      // handle error condition
    }
  }

  /** show how to card payment Session(need to finish payment by Antom SDK) */
  public static void executePaymentSessionCreateWithCard() {
    AlipayPaymentSessionRequest alipayPaymentSessionRequest = new AlipayPaymentSessionRequest();
    alipayPaymentSessionRequest.setProductCode(ProductCodeType.CASHIER_PAYMENT);

    // replace with your paymentRequestId
    String paymentRequestId = UUID.randomUUID().toString();
    alipayPaymentSessionRequest.setPaymentRequestId(paymentRequestId);

    // set amount
    Amount amount = Amount.builder().value("4200").currency("BRL").build();
    alipayPaymentSessionRequest.setPaymentAmount(amount);

    // set paymentMethod
    PaymentMethod paymentMethod = PaymentMethod.builder().paymentMethodType("CARD").build();
    alipayPaymentSessionRequest.setPaymentMethod(paymentMethod);

    // set auth capture payment mode
    PaymentFactor paymentFactor = PaymentFactor.builder().isAuthorization(true).build();
    alipayPaymentSessionRequest.setPaymentFactor(paymentFactor);

    // replace with your orderId
    String orderId = UUID.randomUUID().toString();

    // set buyer info
    Buyer buyer = Buyer.builder().referenceBuyerId("yourBuyerId").build();

    // set order info
    Order order =
        Order.builder()
            .referenceOrderId(orderId)
            .orderDescription("antom testing order")
            .orderAmount(amount)
            .buyer(buyer)
            .build();
    alipayPaymentSessionRequest.setOrder(order);

    // replace with your notify url
    alipayPaymentSessionRequest.setPaymentNotifyUrl("http://www.yourNotifyUrl.com");

    // replace with your redirect url
    alipayPaymentSessionRequest.setPaymentRedirectUrl("http://www.yourRedirectUrl.com");

    // do payment
    AlipayPaymentSessionResponse alipayPaymentSessionResponse = null;
    try {
      alipayPaymentSessionResponse = CLIENT.execute(alipayPaymentSessionRequest);
    } catch (AlipayApiException e) {
      String errorMsg = e.getMessage();
      // handle error condition
    }
  }

  /** show how to inquiry PaymentResult */
  public static void executeInquiry() {
    AlipayPayQueryRequest alipayPayQueryRequest = new AlipayPayQueryRequest();
    alipayPayQueryRequest.setPaymentRequestId("yourPaymentRequestId");

    AlipayPayQueryResponse alipayPayQueryResponse = null;
    try {
      alipayPayQueryResponse = CLIENT.execute(alipayPayQueryRequest);
    } catch (AlipayApiException e) {
      String errorMsg = e.getMessage();
      // handle error condition
    }
  }
}
