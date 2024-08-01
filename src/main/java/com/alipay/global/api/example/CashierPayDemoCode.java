package com.alipay.global.api.example;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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

public class CashierPayDemoCode {

    /**
     * replace with your client id.
     * find your client id here: <a href="https://dashboard.alipay.com/global-payments/developers/quickStart">quickStart</a>
     */
    public static final String        CLIENT_ID            = "SANDBOX_5YBZ1G2ZHUPS06086";

    /**
     * replace with your antom public key (used to verify signature).
     * find your antom public key here: <a href="https://dashboard.alipay.com/global-payments/developers/quickStart">quickStart</a>
     */
    public static final String        ANTOM_PUBLIC_KEY     = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAkJIL3C7NSzSQxP1DNK0Grktr5G5bMEj4ndEIBnSyFv8+e6ytS+G1+ch7EdZ4Lt7KYUGoFW1wJKyTS8V5UBMJTWxAkdc2uX3GrQiWbPvReMl3sNa3SC9Kmi8ofVKQdpAt8aZZaTLxmti0YyCh8zUTddE9AOeMZi8xvzC8chcGbfx4FA5meFGkPEBeYfxZgQzCjXnMJ/A2JFeh5g2443pfAq/caoIamcnTcA9qhJCMaqDyXb2pxXmg/VOClhqhaOjj4dnxzYKln1YNJw02SaVT9zjkNJkbY2QzCjEV0NdG/QLCQ/xBkFlDvlJ+nyCiTySqVOuJXGCos1cljMoYJGZLXQIDAQAB\n";

    /**
     * replace with your private key (used to sign).
     * please ensure the secure storage of your private key to prevent leakage
     */
    public static final String        MERCHANT_PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDUQqOt3fQ/+Uo7fS9daD7Usuf0fA4iHpI93UjmUChaYko/3O7RoD1oMo9NjJDVZZYIKkH6ytzZ3m7BcldTzAFWaoTFPdC8Wvebk1R8pj9sojwdVIkxdPxoDu5BHP10vk5vivMdoiOzr5YTInKHJbnv7cTbbp+zdnzC51djUrROq9j3NklXdMIDGIjYCf7jeUSl5mP6g4WNvXBevUQV4RXhOOEo10p6RejIKxpfFeJYE+u4TVzmqjVgJWhHljqETTcJVLrHG4puFr/bOXDtMETu9cmO7i95LkSzoT7H9RMSLJIZLtFxEFtPLDkOe6hNJ2w4dOyTH4LqELZPQ5UxqtnHAgMBAAECggEATVUeFylnZKtpfTUFva1OPuYTlkJgT9P9ieaGOcr4eL2YuZ2/sMYFuWG6ThGpbuX8/GqQ8VNZlVLx3S7QXCpWTmquhsIXu5gU4CyUOnnDX8vXxQPYnBk4IJ1X9oceXJs9LmxcWO6KvVt6fOwu98gp7x0cfKnYK2U+3S+16ezm3kYZvVd9b6CdMbdp7Ktf57xztrwGqbYL7GmOIYLnvkCWudkesCg2lNAOYEmIp0ygx1fW47Vr9oDwiznG5cLgtrsS29HG3I41a78GYCiAoCtyGXMd9vqlkRP8FRYE9W5Yk58z8kEQUIDAWqmj6YtGST0Tk0x2aSfNF3eLSj38Nr7uQQKBgQD2jP8INjxrwc23rog6SfUtze0VmjM9/3Q43tKxuNBJghQeu1qmn0nsanwuQ1Z59nhBogmweSH4GepSXekPl0GY3QDXhxgpk6pVxdFICIutTrg63C5VaLZDYqUaxZsdH3A6ibdqS9kivSpZzx5thoo36keaFB/20HQe+mH3W4WTpwKBgQDcZTTVbgz1jaJlIVg0b4a7js028Qw7vFSIcniTqL714Y1gEIrRHG7Vbp0ahigdO9nC8cv7IBSNeGIRbOaTq2uwHI9ipsS1838fTyIjoeDw/eF3C5qGJjIQ+tisTulECLemjWhi9TbeYusEpH0K3byZZT0jxn+XxeP3sGMpMu3M4QKBgGrRh6UdKmxdWS2dtnPamxkvQOq9FjpkNQx2rb5GHifa7bCq54mNNc/yCr9YeVz6BfwZoi0NLkR5peXtvg39MHR0O+slIrjQu1nSDOwyl6GMm5EU2BY0+dKUKIhC2QNzHYKfLBInrnm4j2K9s6csRTIYlNESqw6aHdVWEZ/DzUm9AoGAIiB5weK8lvlLgCVdpyDt1/UQycUhg85xuH8hNJwYlbJLVei51g91Cg+KvCSx6XTvD0SptUUOaamOZexa99q/XhHOEhSIuTPvJ0X1n1bdofNuCiT91G/tqLtu+Z9ud15yMZQL6ebWjDD8+0cNJbWYEpiekv+VmkbGOgBSGnsMX4ECgYEAgjqehMDk2vwmAQVASM5wrnfMGpBgXnj58FKqqb+gZPG64+WMZCYkI2ga8mTgwqfjIjziONe+Dp81qLLAHGQlILRPw47rn63gu1Lh5jag93qyj1qXLG6+LIktLmuoaErtgaLjII6dX5Ejj7ULsxJhG06FS5Q5NdBD70aFe8LT0Dg=";

    /**
     * please replace with your endpoint.
     * find your endpoint here: <a href="https://dashboard.alipay.com/global-payments/developers/quickStart">quickStart</a>
     */
    private final static AlipayClient CLIENT               = new DefaultAlipayClient(
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
        } catch (AlipayApiException e) {
            String errorMsg = e.getMessage();
            // handle error condition
        }
    }

    /**
     *  show how to finish a payment by Card paymentMethod 
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
     *  show how to finish a payment by Blik
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
     *  show how to inquiry PaymentResult
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
