/*
 * Ant Group
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package com.alipay.global.api.example;

import com.alipay.global.api.AlipayClient;
import com.alipay.global.api.DefaultAlipayClient;
import com.alipay.global.api.exception.AlipayApiException;
import com.alipay.global.api.model.ams.Address;
import com.alipay.global.api.model.ams.Amount;
import com.alipay.global.api.model.ams.Buyer;
import com.alipay.global.api.model.ams.CardVerificationResult;
import com.alipay.global.api.model.ams.Env;
import com.alipay.global.api.model.ams.Goods;
import com.alipay.global.api.model.ams.OsType;
import com.alipay.global.api.model.ams.RiskThreeDSResult;
import com.alipay.global.api.model.ams.Shipping;
import com.alipay.global.api.model.ams.TerminalType;
import com.alipay.global.api.model.ams.UserName;
import com.alipay.global.api.model.risk.AuthorizationPhase;
import com.alipay.global.api.model.risk.Order;
import com.alipay.global.api.model.risk.PaymentMethodMetaData;
import com.alipay.global.api.model.risk.Merchant;
import com.alipay.global.api.model.risk.PaymentDetail;
import com.alipay.global.api.model.risk.PaymentMethod;
import com.alipay.global.api.request.ams.risk.RiskDecideRequest;
import com.alipay.global.api.request.ams.risk.RiskReportRequest;
import com.alipay.global.api.request.ams.risk.SendPaymentResultRequest;
import com.alipay.global.api.request.ams.risk.SendRefundResultRequest;
import com.alipay.global.api.response.ams.risk.RiskDecideResponse;
import com.alipay.global.api.response.ams.risk.RiskReportResponse;
import com.alipay.global.api.response.ams.risk.SendPaymentResultResponse;
import com.alipay.global.api.response.ams.risk.SendRefundResultResponse;

import java.util.Collections;
import java.util.Date;

public class RiskDecideDemoCode {
    private static final String       CLIENT_ID           = "SANDBOX_5YBW7N2ZFQKM01671";
    private static final String       GATE_WAY_URL        = "https://open-sea-global.alipay.com";
    private static final String       merchantPrivateKey  = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCvmKKTqh8LycwRsKgHbSjaVC1UIc2FLzZoXwDjyje/RGIgXfnQiMTQtbr8PzhK+syD+Lk809QxAWxKJilfysBsMwYHnPrq3bd76cESCht8aEXOlhP3Y6U3Q/ErMCbDLmRGt6KL4EkQXVW/hBDRQfnqn9523n7Vf+QYB8VkdwUmCQoLOAmUa/b0tN20FZiMLOkKoyoR3sfyZkq0tJoc8H5VEp4JCPTePhLHErm0ydPALRATpwe6r5gBTr7afLYp3p4H39pnhAaOLoNgsTRl9iNrZLKW058T0xGN8l6eNQjh4D5Bb4m4cXOYW7EsBMXbv9/zOCSIE3cerUMUapGa+EwNAgMBAAECggEALXn9WPrtMXNg25AB5HJ76xdz6d8bSAvzLlK0e0oq5+lA9JsCEOTq/Bakx6Fj5d9QSjmHV96QjOSOdCGwA3QSI7OPMzePsHYdiAUBELf3aF/k/FzX7PEsn8SD2CiPUWtZYws6GLnMkmVpyMDeTiOlIwOmbOiwtoB5xVN15tAAUFoG2cDEilIluL74bMKWNzym76AC3KFoUG7JHQudGC3li3/tvbiKqdEkVv4KkpxO5L2HoOJcOQ1wbXrXbBr7owCJ1hgOLulWS+I1HUru96+vPnheVuSMOmGwe6Ocs2mz3I9E2yBJS2Et3IaU5blgj2v+mCB+Ef1GhT2P3MbgKcWFDQKBgQDj2UA8PlglClM2SYCuPLI6v8y3CLd45SZNeZJ624VFSKdH5BVn8jJ0s0cQ7tdGVztRIhv9aklZAJoUke6u8dTMcqfP02rDIwrAy4k04RZtaT0fMMLD1UIjNZGjHSNA3RaTALvsOW3a9FrQ0ADq2CGH1AE9UeA3cvYTcCdcqVvnKwKBgQDFSqoLwzS+u1umAKuVxi7EP6mCqrs2G3TYmPPJItDmZ2vvqYJ4FlsCyHOJgwrEiWlBmXHdkoMnDXJVJFW+P6cLQ9V4n3p0Ie9z+o6aMtvlZWuSYltwXm/ycVp/hQPbFFIxJtUfcHeURWXGGIAaaPtE3AEZlLf/6He/EuIx/i39pwKBgHUylFXog5JZ4z9zQ1tcIRkS+wvS0fy/cZo8RFopfD2NB251JYWYENfyN0XAdL7bs8kh0F/jQeMj6h1Wiv01qw2WlDbUOoAxECDnEKZAeTbCAQyAGk587U4LCeRq4m1Ey43FvOPfDmCne+cYdsBVd7nUFOcjrD8ZRPXupHCHNJyNAoGAF4UoMpDKB/cEHQ01Z3yh9Kl2RuqduPya+Ht7jL4G04D+4CqAMFrR7MoC5CDoNrf/AWAOsBCYbB5xQ+85/Z1PB8vFdkTk71VgW55CQ+XOS1HE+kWZixLnG6sh09QCuqp4hf2QKNhamSWQ1YX0N7HOSs5Dn04YhXysUe4pYzZfOz8CgYEAlLvAJ7oqCgCt+d3XjcGW76l+k8seIGjnpyWN5rbCalXnRuFsaNf6RTVskmcVzsZlWLon1fK8IH4xFDrqgXbvERemuaHWnaadZD6i/6tkbyOROAFGbkOSf5Wwe4NS7xzxGM0WSmPArg1nfd/D/8TIgAKQZyIpwPeer51HczR4AX8=";
    private static final String       alipayPublicKey     = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAqWxuPBXkI+9uMFT8gM/jdkNyVVbDix3A5y2mhRMiPPCMh5ArPkrcHGTRQKntQI6flAc6LNuEvB+4i4Eu+jLs3qjmlM6J1Hvhpw/mu9L+V+bypyONx35AtJjEoajD3d+kCtoWqYFylZpXXP4VCGxhVi0N9fI9AIszUNECcbYV9iu7T7cCT388dklvmmOQvT0CvdxBfrlpfVcyCOuShhYTJvsxofYLFbWo7j+ghciVPthvPQ2z8X9ZJcECWG60sr1qvNBbM26F20b59UE79HCjbCV+3KNNztP/PJNQFDkSYeSdvW+f3Uu+k3yrFhUWIR65VwE2L+QIwkQPvFt6mIx8mwIDAQAB";
    private static final AlipayClient defaultAlipayClient = new DefaultAlipayClient(GATE_WAY_URL, merchantPrivateKey, alipayPublicKey);

    public static void main(String[] args) {
//        preAuthDecide();
        postAuthDecide();
    }

    public static RiskDecideResponse preAuthDecide() {
        RiskDecideRequest request = new RiskDecideRequest();
        request.setClientId(CLIENT_ID);
        request.setPath("/ams/api/v1/risk/payments/decide");
        request.setReferenceTransactionId("test_20231012091493242");
        request.setAuthorizationPhase(AuthorizationPhase.PRE_AUTHORIZATION);
        buildRiskDecideRequest(request);

        RiskDecideResponse response = null;
        try {
            response = defaultAlipayClient.execute(request);
        } catch (AlipayApiException e) {
            e.printStackTrace();
            // TODO Handle AlipayApiException and log
        }
        return response;
    }

    public static RiskDecideResponse postAuthDecide() {
        RiskDecideRequest request = new RiskDecideRequest();
        request.setClientId(CLIENT_ID);
        request.setPath("/ams/api/v1/risk/payments/decide");
        request.setReferenceTransactionId("test_20231012091493242");
        request.setAuthorizationPhase(AuthorizationPhase.POST_AUTHORIZATION);
        buildRiskDecideRequest(request);
        PaymentDetail paymentDetail = request.getPaymentDetails().get(0);
        PaymentMethodMetaData paymentMethodMetaData = paymentDetail.getPaymentMethod().getPaymentMethodMetaData();
        CardVerificationResult cardVerificationResult = new CardVerificationResult();
        cardVerificationResult.setAuthenticationType("3D");
        cardVerificationResult.setAuthorizationCode("10000");
        RiskThreeDSResult riskThreeDSResult = new RiskThreeDSResult();
        riskThreeDSResult.setEci("00");
        riskThreeDSResult.setThreeDSVersion("2.0");
        riskThreeDSResult.setCavv("0");
        riskThreeDSResult.setThreeDSInteractionMode("CHALLENGED");
        cardVerificationResult.setThreeDSResult(riskThreeDSResult);
        paymentMethodMetaData.setCardVerificationResult(cardVerificationResult);

        RiskDecideResponse response = null;
        try {
            response = defaultAlipayClient.execute(request);
        } catch (AlipayApiException e) {
            // TODO Handle AlipayApiException and log
        }
        return response;
    }

    public static SendPaymentResultResponse sendPaymentResult() {
        SendPaymentResultRequest request = new SendPaymentResultRequest();
        request.setClientId(CLIENT_ID);
        request.setPath("/ams/api/v1/risk/payments/sendPaymentResult");
        request.setReferenceTransactionId("test_20231012091493242");

        request.setPaymentStatus("SUCCESS");
        request.setReferenceTransactionId("test_20231012091493242");
        CardVerificationResult cardVerificationResult = new CardVerificationResult();
        cardVerificationResult.setAuthenticationType("3D");
        cardVerificationResult.setAuthorizationCode("10000");
        RiskThreeDSResult threeDSResult = new RiskThreeDSResult();
        threeDSResult.setEci("05");
        threeDSResult.setThreeDSVersion("2.0");
        threeDSResult.setCavv("0");
        threeDSResult.setThreeDSInteractionMode("CHALLENGED");
        cardVerificationResult.setThreeDSResult(threeDSResult);
        request.setCardVerificationResult(cardVerificationResult);
        SendPaymentResultResponse response = null;
        try {
            response = defaultAlipayClient.execute(request);
        } catch (AlipayApiException e) {
            // TODO Handle AlipayApiException and log
        }
        return response;
    }

    public static SendRefundResultResponse sendPaymentRefund() {
        SendRefundResultRequest request = new SendRefundResultRequest();
        request.setClientId(CLIENT_ID);
        request.setPath("/ams/api/v1/risk/payments/sendRefundResult");
        request.setReferenceTransactionId("test_20231012091493242");

        SendRefundResultResponse response = null;
        try {
            response = defaultAlipayClient.execute(request);
        } catch (AlipayApiException e) {
            // TODO Handle AlipayApiException and log
        }
        return response;
    }

    public static RiskReportResponse reportRisk() {
        RiskReportRequest request = new RiskReportRequest();
        request.setClientId(CLIENT_ID);
        request.setPath("/ams/api/v1/risk/payments/reportRisk");
        request.setReferenceTransactionId("test_20231012091493242");
        request.setReportReason("test");
        request.setRiskType("FRAUD");
        request.setRiskOccurrenceTime(new Date());

        RiskReportResponse response = null;
        try {
            response = defaultAlipayClient.execute(request);
        } catch (AlipayApiException e) {
            // TODO Handle AlipayApiException and log
        }
        return response;
    }

    public static void buildRiskDecideRequest(RiskDecideRequest request) {
        Order order = new Order();
        order.setReferenceOrderId("test_202310120914932421");
        Amount orderAmount = new Amount();
        orderAmount.setCurrency("BRL");
        orderAmount.setValue("30000");
        order.setOrderAmount(orderAmount);
        order.setOrderDescription("Cappuccino #grande (Mika's coffee shop)");
        Merchant merchant = new Merchant();
        merchant.setReferenceMerchantId("SM_001");
        order.setMerchant(merchant);
        Goods goods = new Goods();
        goods.setReferenceGoodsId("383382011_SGAMZ-904520356");
        goods.setGoodsName("[3 Boxes] Starbucks Cappuccino Milk Coffee Pods / Coffee Capsules by Nescafe Dolce Gusto");
        goods.setGoodsCategory("Digital  Goods/Digital  Vouchers/Food  and Beverages");
        goods.setDeliveryMethodType("DIGITAL");
        goods.setGoodsQuantity("1");
        Amount goodsAmount = new Amount();
        goodsAmount.setValue("30000");
        goodsAmount.setCurrency("BRL");
        goods.setGoodsUnitAmount(goodsAmount);
        order.setGoods(Collections.singletonList(goods));
        Shipping shipping = new Shipping();
        shipping.setShippingCarrier("FedEx");
        shipping.setShippingPhoneNo("12345678912");
        shipping.setShipToEmail("fenghoureject@163.com");
        UserName shippingName = new UserName();
        shippingName.setFirstName("Dehua");
        shippingName.setLastName("Liu");
        shippingName.setMiddleName("Skr");
        shippingName.setFullName("Dehua Skr Liu");
        shipping.setShippingName(shippingName);
        Address shippingAddress = new Address();
        shippingAddress.setRegion("CN");
        shippingAddress.setState("Zhejiang");
        shippingAddress.setCity("Hangzhou");
        shippingAddress.setAddress1("Wuchang road");
        shippingAddress.setAddress2("Xihu");
        shippingAddress.setZipCode("310000");
        shipping.setShippingAddress(shippingAddress);
        order.setShipping(shipping);
        request.setOrders(Collections.singletonList(order));

        Buyer buyer = new Buyer();
        buyer.setReferenceBuyerId("test12345678");
        buyer.setBuyerPhoneNo("12345678912");
        buyer.setBuyerEmail("alipay@alipay.com");
        buyer.setAccountVerified(true);
        buyer.setSuccessfulOrderCount(100);
        UserName buyerName = new UserName();
        buyerName.setFirstName("Dehua");
        buyerName.setLastName("Liu");
        buyerName.setMiddleName("Skr");
        buyerName.setFullName("Dehua Skr Liu");
        buyer.setBuyerName(buyerName);
        request.setBuyer(buyer);

        Amount actualPaymentAmount = new Amount();
        actualPaymentAmount.setCurrency("BRL");
        actualPaymentAmount.setValue("300000");
        request.setActualPaymentAmount(actualPaymentAmount);

        PaymentDetail paymentDetail = new PaymentDetail();
        Amount paymentAmount = new Amount();
        paymentAmount.setCurrency("BRL");
        paymentAmount.setValue("300000");
        paymentDetail.setAmount(paymentAmount);
        PaymentMethod paymentMethod = new PaymentMethod();
        paymentMethod.setPaymentMethodId("0656XXXXXXX0001");
        paymentMethod.setPaymentMethodType("CARD");
        PaymentMethodMetaData paymentMethodMetaData = new PaymentMethodMetaData();
        paymentMethodMetaData.setCpf("02987654320");
        paymentMethodMetaData.setExpiryMonth("12");
        paymentMethodMetaData.setExpiryYear("29");
        paymentMethodMetaData.setCardNo("4117347806156383");
        UserName cardHolderName = new UserName();
        cardHolderName.setFirstName("Tom");
        cardHolderName.setLastName("Jay");
        paymentMethodMetaData.setCardholderName(cardHolderName);
        Address billingAddress = new Address();
        billingAddress.setRegion("CN");
        billingAddress.setState("Zhejiang");
        billingAddress.setCity("Hangzhou");
        billingAddress.setAddress1("test_address1");
        billingAddress.setAddress2("test_address2");
        billingAddress.setZipCode("310000");
        paymentMethodMetaData.setBillingAddress(billingAddress);
        paymentMethod.setPaymentMethodMetaData(paymentMethodMetaData);
        paymentDetail.setPaymentMethod(paymentMethod);
        request.setPaymentDetails(Collections.singletonList(paymentDetail));

        Env env = new Env();
        env.setTerminalType(TerminalType.APP);
        env.setClientIp("00.00.00.00");
        env.setOsType(OsType.IOS);
        env.setDeviceLanguage("zh_CN");
        env.setDeviceId("test_deviceId");
        request.setEnv(env);

        Amount discountAmount = new Amount();
        discountAmount.setCurrency("BRL");
        discountAmount.setValue("0");
        request.setDiscountAmount(discountAmount);
    }
}