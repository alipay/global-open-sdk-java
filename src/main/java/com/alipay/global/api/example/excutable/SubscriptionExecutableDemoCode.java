package com.alipay.global.api.example.excutable;

import com.alibaba.fastjson.JSON;
import com.alipay.global.api.AlipayClient;
import com.alipay.global.api.DefaultAlipayClient;
import com.alipay.global.api.exception.AlipayApiException;
import com.alipay.global.api.model.ams.*;
import com.alipay.global.api.model.constants.AntomPathConstants;
import com.alipay.global.api.model.constants.EndPointConstants;
import com.alipay.global.api.request.ams.subscription.AlipaySubscriptionCreateRequest;
import com.alipay.global.api.response.ams.subscription.AlipaySubscriptionCreateResponse;

import java.util.UUID;

public class SubscriptionExecutableDemoCode {


    private static final String merchantPrivateKey = "";

    private static final String alipayPublicKey = "";

    private static final String CLIENT_ID = "";

    public static void main(String[] args) {

        createSubScription();

    }

    public static void createSubScription() {

        AlipayClient defaultAlipayClient = new DefaultAlipayClient(EndPointConstants.SG,
                merchantPrivateKey, alipayPublicKey);

        AlipaySubscriptionCreateRequest alipaySubscriptionCreateRequest = new AlipaySubscriptionCreateRequest();
        alipaySubscriptionCreateRequest.setPath(AntomPathConstants.SUBSCRIPTION_CREATE_PATH);
        alipaySubscriptionCreateRequest.setClientId(CLIENT_ID);

        String subscriptionRequestId = UUID.randomUUID().toString();
        alipaySubscriptionCreateRequest.setSubscriptionRequestId(subscriptionRequestId);
        alipaySubscriptionCreateRequest.setSubscriptionDescription("desc");
        alipaySubscriptionCreateRequest.setSubscriptionStartTime("2024-03-19T12:01:01+08:00");
        alipaySubscriptionCreateRequest.setSubscriptionEndTime("2024-06-27T12:01:01+08:00");
        alipaySubscriptionCreateRequest.setSubscriptionExpiryTime("2024-03-20T18:20:06+08:00");

        PeriodRule periodRule = new PeriodRule();
        periodRule.setPeriodType("MONTH");
        periodRule.setPeriodCount(1);
        alipaySubscriptionCreateRequest.setPeriodRule(periodRule);

        PaymentMethod paymentMethod = new PaymentMethod();
        paymentMethod.setPaymentMethodType("GCASH");
        alipaySubscriptionCreateRequest.setPaymentMethod(paymentMethod);

        OrderInfo orderInfo = new OrderInfo();
        alipaySubscriptionCreateRequest.setOrderInfo(orderInfo);

        Amount amount = new Amount();
        amount.setCurrency("PHP");
        amount.setValue("5000");
        orderInfo.setOrderAmount(amount);

        alipaySubscriptionCreateRequest.setPaymentAmount(amount);

        SettlementStrategy settlementStrategy = new SettlementStrategy();
        settlementStrategy.setSettlementCurrency("USD");
        alipaySubscriptionCreateRequest.setSettlementStrategy(settlementStrategy);

        alipaySubscriptionCreateRequest.setSubscriptionRedirectUrl("https://www.yourRedirectUrl.com");
        alipaySubscriptionCreateRequest.setSubscriptionNotificationUrl("https://www.yourNotify.com");
        alipaySubscriptionCreateRequest.setPaymentNotificationUrl("https://www.yourNotify.com");

        Env env = new Env();
        env.setTerminalType(TerminalType.APP);
        env.setOsType(OsType.ANDROID);
        alipaySubscriptionCreateRequest.setEnv(env);

        AlipaySubscriptionCreateResponse alipaySubscriptionCreateResponse = null;

        System.out.println(JSON.toJSON(alipaySubscriptionCreateRequest));

        try {
            alipaySubscriptionCreateResponse = defaultAlipayClient.execute(alipaySubscriptionCreateRequest);
        } catch (AlipayApiException e) {
            String errorMsg = e.getMessage();
            System.out.println(e);
            // handle error condition
        }

        System.out.println(JSON.toJSON(alipaySubscriptionCreateResponse));

    }


}
