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
     * replace with your client id.
     * find your client id here: <a href="https://dashboard.alipay.com/global-payments/developers/quickStart">quickStart</a>
     */
    public static final String CLIENT_ID = "SANDBOX_5YBZ1G2ZHUPS06086";

    /**
     * replace with your antom public key (used to verify signature).
     * find your antom public key here: <a href="https://dashboard.alipay.com/global-payments/developers/quickStart">quickStart</a>
     */
    public static final String ANTOM_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAkJIL3C7NSzSQxP1DNK0Grktr5G5bMEj4ndEIBnSyFv8+e6ytS+G1+ch7EdZ4Lt7KYUGoFW1wJKyTS8V5UBMJTWxAkdc2uX3GrQiWbPvReMl3sNa3SC9Kmi8ofVKQdpAt8aZZaTLxmti0YyCh8zUTddE9AOeMZi8xvzC8chcGbfx4FA5meFGkPEBeYfxZgQzCjXnMJ/A2JFeh5g2443pfAq/caoIamcnTcA9qhJCMaqDyXb2pxXmg/VOClhqhaOjj4dnxzYKln1YNJw02SaVT9zjkNJkbY2QzCjEV0NdG/QLCQ/xBkFlDvlJ+nyCiTySqVOuJXGCos1cljMoYJGZLXQIDAQAB";

    /**
     * replace with your private key (used to sign).
     * please ensure the secure storage of your private key to prevent leakage
     */
    public static final String MERCHANT_PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC/PqnrI3zdRMIAqIFzOlNkop2o/jMVva22j05W7/Sv78PU7SMmwQGObmw9COcm2nX0N1ix0dlqH2lmXRmoE69hElXcWnDEzcSHpNe1HtM0eWgOLozrwpa0PXue0O0cxvEhZxuWqKUMrwf3cV9dziTzbmmK70wP5PcIstjqy8SK/LyMZ/W5q1xRrBlRmXQvAOnx79A9ok0m8H99ziwOye9aDr8Tb0LEVBxeUVB+XwniiHYDbxRjY1awsYOaK66W+gZcx4ORXx8xDjDPX6q45RNIuJRgSXdqsQQxEk5tKSHpSj8LSqz67Dr/Jyq8MVJn30VXL+uX+yHyeCNKe5tf+2IHAgMBAAECggEATw+DxU5tbzfej9EZet5Q3ViQnu0/hyxb5Q3HUA9w807GgX7rOjkuAIjLvEuy65AClUxQIWrkW4fS1duFIMPKi/G9hxPobKO4LG9MMXclzxqllr9NyKUwEiEcuuIaM/xWcP2kHRto6B5vx66ZwzjWc8BgZ2xX4HZCXdk57Y8BmIb9IejKgIYtyUWrYuGkvQsh+SHWX5CZVEgGlCy3uYOMDj0BkSJOrLc3yW6octGuVQLEgFx3EEB0ZJMThUB+gxInj/pl06iwCuWbZXxkRkXHrllz1gOvfGwhtccwPHrKGJDHDY+OIIgsd4eEDJjySdMjx2hydmCbxbRBxEIK5iW/kQKBgQD10ReS5F6T+QDgc2VdscZo/64PLldXtH71sTJoIat8XK0oPNSbkzJzPpuWwj6hpSKO1/8Hsq52gM2IjJBSx2q7nD9azVrBevl/aLhAmM4OX7ahSDaQZx3yHwL40onXAZIVDECyi1zRLtBDgKaGgLJdIZ2lzgo7Jhcw8J3S/Np52QKBgQDHKtTAl43dKuTV5QZ7PwItrgcjOvm6LzhuI+xbM5YnAOLcp7g9UP6LLI5kB00UkB9fDnoePVbwEgl0ShIKHCWBU++F4mjPoayoTQCKBzNuvK5wKklVPoVDsnwhGXIS8fkBmU5VtjArB0kCAoIpR5HEzIaCiaZZGWMiZ0kfBdBu3wKBgQC+ZRJ2Qw4CXMZSEu87b/u2zfq6ZXFfTD1d/b6GKzYQ4BN6bAtc6NkVrDOExLUQLMCklSZChyJcRQ1tKzqJ8013POFRamdWHvLqvWihF/nZ5kalizJADK6EH4MEyMXc06mbRd9Cq3Db0P+cmSPiYAJG4keh6gHAqJMj4+rKRfDOmQKBgCbdp9jRemCffzpyT/p7CDzLyh7I4nS/xD5SCkyd235PAPZYUG6+wH1+O2cvuY36tfSBybje9Xkxu+CSl8SbS4JaU9KHpTZncV8Cb8l/sDy62zuONPNKmQzl5q063vTtfU8fkJbPT8UFzexzetz9V2fVFaahn/GhL6RGDZHdO5h3AoGALV6PDVsB6VjJtE8RA8Bpsmwl6ytLDiEljFImkzXQzq4/gTz2NJXjKGnUC70pae1Z1OWMHoZWqFgg1YAxVHeFZBqLQ88HwmCwwRjLkUyFxPRlJ7y4V8olBsDY57E1j03MNENaal88bRpvKUoZRTh8HCbk6BV8e6o+2vMf3HUe2Ss=";

    /**
     * please replace with your endpoint.
     * find your endpoint here: <a href="https://dashboard.alipay.com/global-payments/developers/quickStart">quickStart</a>
     */
    private final static AlipayClient CLIENT = new DefaultAlipayClient(
            EndPointConstants.SG, MERCHANT_PRIVATE_KEY, ANTOM_PUBLIC_KEY, CLIENT_ID);

    public static void main(String[] args) {
//        executeConsult();
                executePayWithCard();
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
        } catch (AlipayApiException e) {
            String errorMsg = e.getMessage();
            // handle error condition
        }
    }

    /**
     * show how to finish a payment by Card paymentMethod
     */
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
        paymentMethodMetaData.put("cardNo", "4294097400915107");
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
        PaymentMethod paymentMethod = PaymentMethod.builder().paymentMethodType("CARD")
                .paymentMethodMetaData(paymentMethodMetaData).build();
        alipayPayRequest.setPaymentMethod(paymentMethod);

        // replace with your orderId
        String orderId = UUID.randomUUID().toString();

        // set buyer info
        Buyer buyer = Buyer.builder().referenceBuyerId("yourBuyerId").build();

        // set order info
        Order order = Order.builder().referenceOrderId(orderId)
                .orderDescription("antom testing order").orderAmount(amount).buyer(buyer).build();
        alipayPayRequest.setOrder(order);

        // set env info
        Env env = Env.builder().terminalType(TerminalType.WEB)
                .clientIp("1.2.3.4").build();
        alipayPayRequest.setEnv(env);

        // set auth capture payment mode
        PaymentFactor paymentFactor = PaymentFactor.builder().isAuthorization(true).build();
        alipayPayRequest.setPaymentFactor(paymentFactor);

        // replace with your notify url
        alipayPayRequest.setPaymentNotifyUrl("http://www.yourNotifyUrl.com");

        // replace with your redirect url
        alipayPayRequest.setPaymentRedirectUrl("http://www.yourRedirectUrl.com");

        alipayPayRequest.setSettlementStrategy(new SettlementStrategy("USD"));

        // do payment
        AlipayPayResponse alipayPayResponse = null;
        try {
            //开始时间
            long startTime = System.currentTimeMillis();
            alipayPayResponse = CLIENT.execute(alipayPayRequest);
            //结束时间
            long endTime = System.currentTimeMillis();
            System.out.println("耗时：" + (endTime - startTime) + "ms");
        } catch (AlipayApiException e) {
            String errorMsg = e.getMessage();
            e.printStackTrace();
            // handle error condition
        }
//        System.out.println(alipayPayResponse.getResult().getResultMessage());
        System.out.println(alipayPayResponse);
    }

    /**
     * show how to finish a payment by Blik
     */
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
        Order order = Order.builder().referenceOrderId(orderId)
                .orderDescription("antom testing order").orderAmount(amount).buyer(buyer).build();
        alipayPayRequest.setOrder(order);

        // set env info
        Env env = Env.builder().terminalType(TerminalType.WEB)
                .clientIp("1.2.3.4").build();
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

    /**
     * show how to card payment Session(need to finish payment by Antom SDK)
     */
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
        Order order = Order.builder().referenceOrderId(orderId)
                .orderDescription("antom testing order").orderAmount(amount).buyer(buyer).build();
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

    /**
     * show how to inquiry PaymentResult
     */
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
