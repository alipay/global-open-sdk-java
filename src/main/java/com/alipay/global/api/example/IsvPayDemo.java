package com.alipay.global.api.example;

import com.alibaba.fastjson.JSONObject;
import com.alipay.global.api.AlipayClient;
import com.alipay.global.api.DefaultAlipayClient;
import com.alipay.global.api.exception.AlipayApiException;
import com.alipay.global.api.model.ams.*;
import com.alipay.global.api.model.constants.EndPointConstants;
import com.alipay.global.api.request.ams.pay.AlipayPayConsultRequest;
import com.alipay.global.api.request.ams.pay.AlipayPayRequest;
import com.alipay.global.api.response.ams.pay.AlipayPayConsultResponse;
import com.alipay.global.api.response.ams.pay.AlipayPayResponse;

import java.util.UUID;

public class IsvPayDemo {

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
     * ISV agentToken
     */
    public static final String AGENT_TOKEN = "";

    /**
     * please replace with your endpoint.
     * find your endpoint here: <a href="https://dashboard.alipay.com/global-payments/developers/quickStart">quickStart</a>
     */
    private final static AlipayClient CLIENT = new DefaultAlipayClient(
            EndPointConstants.SG, MERCHANT_PRIVATE_KEY, ANTOM_PUBLIC_KEY, CLIENT_ID, AGENT_TOKEN);

    public static void main(String[] args) {
        executeConsult();
//        executePayWithBlik();
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
            e.printStackTrace();
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
            System.out.println(JSONObject.toJSON(alipayPayResponse));
        } catch (AlipayApiException e) {
            String errorMsg = e.getMessage();
            System.out.println(errorMsg);
        }
    }


}
