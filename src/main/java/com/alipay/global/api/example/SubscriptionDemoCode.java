package com.alipay.global.api.example;

import com.alibaba.fastjson.JSON;
import com.alipay.global.api.AlipayClient;
import com.alipay.global.api.DefaultAlipayClient;
import com.alipay.global.api.exception.AlipayApiException;
import com.alipay.global.api.model.ams.*;
import com.alipay.global.api.model.constants.EndPointConstants;
import com.alipay.global.api.request.ams.subscription.AlipaySubscriptionCreateRequest;
import com.alipay.global.api.response.ams.subscription.AlipaySubscriptionCreateResponse;

import java.util.UUID;

public class SubscriptionDemoCode {

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
        createSubscription();
    }

    public static void createSubscription() {
        AlipaySubscriptionCreateRequest alipaySubscriptionCreateRequest = new AlipaySubscriptionCreateRequest();

        String subscriptionRequestId = UUID.randomUUID().toString();
        alipaySubscriptionCreateRequest.setSubscriptionRequestId(subscriptionRequestId);
        alipaySubscriptionCreateRequest.setSubscriptionDescription("desc");
        alipaySubscriptionCreateRequest.setSubscriptionStartTime("2024-03-19T12:01:01+08:00");
        alipaySubscriptionCreateRequest.setSubscriptionEndTime("2024-06-27T12:01:01+08:00");
        // The duration of subscription preparation process should be less than 48 hours
        alipaySubscriptionCreateRequest.setSubscriptionExpiryTime("2024-03-20T18:20:06+08:00");

        PeriodRule periodRule = PeriodRule.builder().periodType("MONTH").periodCount(1).build();
        alipaySubscriptionCreateRequest.setPeriodRule(periodRule);

        PaymentMethod paymentMethod = PaymentMethod.builder().paymentMethodType("GCASH").build();
        alipaySubscriptionCreateRequest.setPaymentMethod(paymentMethod);

        OrderInfo orderInfo = new OrderInfo();
        alipaySubscriptionCreateRequest.setOrderInfo(orderInfo);

        Amount amount = Amount.builder().value("5000").currency("PHP").build();
        orderInfo.setOrderAmount(amount);

        alipaySubscriptionCreateRequest.setPaymentAmount(amount);

        alipaySubscriptionCreateRequest
                .setSubscriptionRedirectUrl("https://www.yourRedirectUrl.com");
        alipaySubscriptionCreateRequest
                .setSubscriptionNotificationUrl("https://www.yourNotify.com");
        alipaySubscriptionCreateRequest.setPaymentNotificationUrl("https://www.yourNotify.com");

        Env env = Env.builder().terminalType(TerminalType.APP).osType(OsType.ANDROID).build();
        alipaySubscriptionCreateRequest.setEnv(env);

        AlipaySubscriptionCreateResponse alipaySubscriptionCreateResponse = null;

        System.out.println(JSON.toJSON(alipaySubscriptionCreateRequest));

        try {
            alipaySubscriptionCreateResponse = CLIENT.execute(alipaySubscriptionCreateRequest);
        } catch (AlipayApiException e) {
            String errorMsg = e.getMessage();
            System.out.println(e);
            // handle error condition
        }

        System.out.println(JSON.toJSON(alipaySubscriptionCreateResponse));

    }

}
