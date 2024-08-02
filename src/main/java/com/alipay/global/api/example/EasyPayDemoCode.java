package com.alipay.global.api.example;

import com.alipay.global.api.AlipayClient;
import com.alipay.global.api.DefaultAlipayClient;
import com.alipay.global.api.exception.AlipayApiException;
import com.alipay.global.api.model.ams.Amount;
import com.alipay.global.api.model.ams.Order;
import com.alipay.global.api.model.ams.PaymentMethod;
import com.alipay.global.api.model.ams.ProductCodeType;
import com.alipay.global.api.model.constants.EndPointConstants;
import com.alipay.global.api.model.constants.ProductSceneConstants;
import com.alipay.global.api.request.ams.pay.AlipayPaymentSessionRequest;
import com.alipay.global.api.response.ams.pay.AlipayPaymentSessionResponse;

import java.util.UUID;

public class EasyPayDemoCode {

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
        easyPaySession();
    }

    public static void easyPaySession() {
        AlipayPaymentSessionRequest alipayPaymentSessionRequest = new AlipayPaymentSessionRequest();
        alipayPaymentSessionRequest.setProductScene(ProductSceneConstants.EASY_PAY);
        alipayPaymentSessionRequest.setProductCode(ProductCodeType.CASHIER_PAYMENT);

        // replace with your paymentRequestId
        String paymentRequestId = UUID.randomUUID().toString();
        alipayPaymentSessionRequest.setPaymentRequestId(paymentRequestId);

        // set amount
        Amount amount = Amount.builder().value("4200").currency("HKD").build();
        alipayPaymentSessionRequest.setPaymentAmount(amount);

        // set paymentMethod
        PaymentMethod paymentMethod = PaymentMethod.builder().paymentMethodType("CARD").build();
        alipayPaymentSessionRequest.setPaymentMethod(paymentMethod);

        // replace with your orderId
        String orderId = UUID.randomUUID().toString();

        // set order info
        Order order = Order.builder().referenceOrderId(orderId)
                .orderDescription("antom testing order").orderAmount(amount).build();
        alipayPaymentSessionRequest.setOrder(order);

        AlipayPaymentSessionResponse alipayPaymentSessionResponse = null;

        try {
            alipayPaymentSessionResponse = CLIENT.execute(alipayPaymentSessionRequest);
        } catch (AlipayApiException e) {
            String errorMsg = e.getMessage();
            // handle error condition
        }

        System.out.println(alipayPaymentSessionResponse);

    }

}
