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
    public static final String CLIENT_ID = "";

    /**
     * replace with your antom public key (used to verify signature).
     * find your antom public key here: <a href="https://dashboard.alipay.com/global-payments/developers/quickStart">quickStart</a>
     */
    public static final String ANTOM_PUBLIC_KEY = "";

    /**
     * replace with your private key (used to sign).
     * please ensure the secure storage of your private key to prevent leakage
     */
    public static final String MERCHANT_PRIVATE_KEY = "";

    /**
     * please replace with your endpoint.
     * find your endpoint here: <a href="https://dashboard.alipay.com/global-payments/developers/quickStart">quickStart</a>
     */
    private final static AlipayClient CLIENT = new DefaultAlipayClient(
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
            System.out.println(JSONObject.toJSON(alipayPayResponse));
        } catch (AlipayApiException e) {
            String errorMsg = e.getMessage();
            // handle error condition
        }
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
