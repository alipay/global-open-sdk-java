package com.alipay.global.api.example.excutable;

import java.util.UUID;

import com.alipay.global.api.AlipayClient;
import com.alipay.global.api.DefaultAlipayClient;
import com.alipay.global.api.exception.AlipayApiException;
import com.alipay.global.api.model.ams.*;
import com.alipay.global.api.model.constants.EndPointConstants;
import com.alipay.global.api.model.constants.ProductSceneConstants;
import com.alipay.global.api.request.ams.pay.AlipayPaymentSessionRequest;
import com.alipay.global.api.response.ams.pay.AlipayPaymentSessionResponse;

public class EasyPayExecutableDemoCode {

    private static final String merchantPrivateKey = "";

    private static final String alipayPublicKey    = "";

    private static final String CLIENT_ID          = "";

    public static void main(String[] args) {

    }

    public static void easyPaySession() {

        AlipayClient defaultAlipayClient = new DefaultAlipayClient(EndPointConstants.SG,
            merchantPrivateKey, alipayPublicKey);

        AlipayPaymentSessionRequest alipayPaymentSessionRequest = new AlipayPaymentSessionRequest();
        alipayPaymentSessionRequest.setClientId(CLIENT_ID);

        alipayPaymentSessionRequest.setProductScene(ProductSceneConstants.EASY_PAY);
        alipayPaymentSessionRequest.setProductCode(ProductCodeType.CASHIER_PAYMENT);

        // replace to your paymentRequestId
        String paymentRequestId = UUID.randomUUID().toString();
        alipayPaymentSessionRequest.setPaymentRequestId(paymentRequestId);

        // set amount
        Amount amount = new Amount();
        amount.setCurrency("HKD");
        amount.setValue("4200");
        alipayPaymentSessionRequest.setPaymentAmount(amount);

        //set settlement currency
        SettlementStrategy settlementStrategy = new SettlementStrategy();
        settlementStrategy.setSettlementCurrency("USD");
        alipayPaymentSessionRequest.setSettlementStrategy(settlementStrategy);

        // set paymentMethod
        PaymentMethod paymentMethod = new PaymentMethod();
        paymentMethod.setPaymentMethodType("CARD");
        alipayPaymentSessionRequest.setPaymentMethod(paymentMethod);

        // replace to your orderId
        String orderId = UUID.randomUUID().toString();

        // set order Info
        Order order = new Order();
        order.setReferenceOrderId(orderId);
        order.setOrderDescription("antom test order");
        order.setOrderAmount(amount);

        AlipayPaymentSessionResponse alipayPaymentSessionResponse = null;

        try {
            alipayPaymentSessionResponse = defaultAlipayClient.execute(alipayPaymentSessionRequest);
        } catch (AlipayApiException e) {
            String errorMsg = e.getMessage();
            // handle error condition
        }

        System.out.println(alipayPaymentSessionResponse);

    }

}
