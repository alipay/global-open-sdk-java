/*
 * Ant Group
 * Copyright (c) 2004-2024 All Rights Reserved.
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
import com.alipay.global.api.request.ams.risk.tee.encryptstrategy.RiskDecideEncryptStrategy;
import com.alipay.global.api.request.ams.risk.tee.enums.EncryptKeyEnum;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;


public class RiskDecideTeeDemoCode {
    private static final String       CLIENT_ID           = "5J5YBT2Y2Y5MSG04004";
    private static final String       GATE_WAY_URL        = "https://open-id-pre.alipay.com";
    private static final String       merchantPrivateKey  = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCEvkX4gF0D/1moCefOF3UsPVULyjUp7uY/hPlrTbO9eqLv+vFwK5Su/sxl2H6xH1T0/LnCt9kp022DzwKq2klF2yOyDR5qngVsSRQKajKA2iVF9/N795TNtw/gb7dzP0J1orjyrNGIKfdCjzcs1kBYgEfg4RAWrehC/EH9jxDFmbIE1q6ctv4cfkAv7hS8bqceiobFiJv7m76MFTCgUTXQckCPR64lRiol037Fen+b0BB6crp1nThNYWbXhiRuyIfBbgiyrZH7RKoVwYqmrgubiPEDrM+lO3P+u31nvVdE5DYNpr+yISWOhAjABQdMuYdoOShh/Kp4bVPBmdrneeMJAgMBAAECggEAcmu8zo5/HxH9i042hLxNoPuhEk+TcfjeeYQ6ppbMxxgt2OQbN5ApUoqJT4TE+BJE3gP2b3Sf+WCo9sP6zaDpJhDV/QU34bkifYukfbG6pLzlAiHPvFzV5FjWk6RYoju2IsZH287BsjhSTQo8CicIQgEZdCsr/hCbsxnUHCc00s4B/b7J2vcp6FR1M/07fxRmBZjooNVp5G/hSAHYCqO1qYdOZSI1BY3NFkp7+q/yQzEHFSH1jv2jELibb9J9lpmpWIPWpPVjplW9SUJAxEUMBLsJrZw3jKXvXTTlo8j0c2Pqym87BlR2SCSD7sVX6EKiNbSmMTmmut6ttE0jC1yBgQKBgQC7scpQw/dDE+IljCtOcADV8a1Kl3cDEh8MZp/RaDgtCDTjmTfoUpJPhPTScO4wVS2/6lKOMnJQwSj6eUxBzjy6fIDINCcFBNNrFxy37sRFbO7yYpzF5jk58voJ2MYWQS15TV97HeYKVrEkXZaI+gQxZXKUfEWFg656P5oAG2I92QKBgQC1DQ2AVql53HAMjZuh1KEqTya896W9W0wQITGPN3boMZVo55yhd2KaM2wY2sJVDZ6VeKIX7ctKPMSGRPCIi7HT2TUg4KVMymNTkGdDs0TfnpRYpyK2ZAc3UTPlQW9VcRt2ZRm/3HtmNLBUG4zUf+Vdg4fmep5aBjxqcMntCiIgsQKBgBj3C+70MdZFNcZCv5veP+41CBSs0tKgvWp71/QVhatPtSE0xv/qw7U5/QpkpPneyogFVde+FABzwMyYB7GVCfTQDJ0UesArJSfQWiyI4KhPVfT9ctbLyX7Qt7tJjwd0weSpivKoRmnHoCYzIIu1nYsocDYZ1XFt025FOWmcOtP5AoGAFPWs4XWm6w+k/R8LrGF0QvSk1G2LB2/hT2C4Xw8Zuem7lUhy7pJmnNllIz94DeakvEMZ6NFDnpWIXs7uVbBuZssXhnXbTReFBjCKc+D2ETlHjTfUIe9UkofZDSpVHDwa5Mrxfy+kLYHeA+6okBU6VWgbvIfnex6LsfratZFdwBECgYAX4txY+kPBMV1dYyTDIRun2Uj1fnsY/P4kZxt37dfDiCj2wYGgEK9i2YphuyDPWVMWTx6xOJdKsypOiYMAjgsKRgCpOlSbHVyaG23GqYAYzaNMP/8fEIcMlEcWR2kdkJe+sAZohn4tm9JlHtvl1EpXFmNaNXoXj+MvgTiQvTppWg==";
    private static final String       alipayPublicKey     = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAhL5F+IBdA/9ZqAnnzhd1LD1VC8o1Ke7mP4T5a02zvXqi7/rxcCuUrv7MZdh+sR9U9Py5wrfZKdNtg88CqtpJRdsjsg0eap4FbEkUCmoygNolRffze/eUzbcP4G+3cz9CdaK48qzRiCn3Qo83LNZAWIBH4OEQFq3oQvxB/Y8QxZmyBNaunLb+HH5AL+4UvG6nHoqGxYib+5u+jBUwoFE10HJAj0euJUYqJdN+xXp/m9AQenK6dZ04TWFm14YkbsiHwW4Isq2R+0SqFcGKpq4Lm4jxA6zPpTtz/rt9Z71XROQ2Daa/siEljoQIwAUHTLmHaDkoYfyqeG1TwZna53njCQIDAQAB";
    private static final AlipayClient defaultAlipayClient = new DefaultAlipayClient(GATE_WAY_URL, merchantPrivateKey, alipayPublicKey);
    private static final String       DATA_KEY = "w7QkIZhPyXqoZTflcQvTVg==";
    public static void main(String[] args) {
        preAuthDecide();
    }

    public static RiskDecideResponse preAuthDecide() {
        RiskDecideRequest request = new RiskDecideRequest();
        request.setClientId(CLIENT_ID);
        request.setPath("/ams/api/v1/risk/privacy/payments/decide");
        request.setReferenceTransactionId("test_20231012091493242");
        request.setAuthorizationPhase(AuthorizationPhase.PRE_AUTHORIZATION);
        // 1. build plaintext request
        buildRiskDecideRequest(request);
        // 2. encrypt request
        encryptRequest(request);
        // 3. send request
        RiskDecideResponse response = null;
        try {
            response = defaultAlipayClient.execute(request);
        } catch (AlipayApiException e) {
            // TODO Handle AlipayApiException and log
            e.printStackTrace();
        }
        return response;
    }

    public static RiskDecideResponse postAuthDecide() {
        RiskDecideRequest request = new RiskDecideRequest();
        request.setClientId(CLIENT_ID);
        request.setPath("/ams/api/v1/risk/privacy/payments/decide");
        request.setReferenceTransactionId("test_20231012091493242");
        request.setAuthorizationPhase(AuthorizationPhase.POST_AUTHORIZATION);
        // 1. build plaintext request
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
        // 2. encrypt request
        encryptRequest(request);
        // 3. send request
        RiskDecideResponse response = null;
        try {
            response = defaultAlipayClient.execute(request);
        } catch (AlipayApiException e) {
            // TODO Handle AlipayApiException and log
        }
        return response;
    }

    public static void encryptRequest(RiskDecideRequest request) {
        // 2.1. build encryptList
        List<EncryptKeyEnum> encryptList = Arrays.asList(
                EncryptKeyEnum.BUYER_EMAIL,
                EncryptKeyEnum.BUYER_PHONE_NO,
                EncryptKeyEnum.BUYER_REGISTRATION_TIME,
                EncryptKeyEnum.BILL_TO_NAME,
                EncryptKeyEnum.SHIPPING_ADDR1,
                EncryptKeyEnum.SHIPPING_ADDR2,
                EncryptKeyEnum.SHIPPING_NAME,
                EncryptKeyEnum.SHIPPING_EMAIL,
                EncryptKeyEnum.SHIPPING_PHONE_NO
        );
        // 2.2. encrypt request by using RiskDecideEncryptStrategy
        RiskDecideEncryptStrategy strategy = new RiskDecideEncryptStrategy();
        strategy.encrypt(DATA_KEY, request, encryptList);
    }

    public static SendPaymentResultResponse sendPaymentResult() {
        SendPaymentResultRequest request = new SendPaymentResultRequest();
        request.setClientId(CLIENT_ID);
        request.setPath("/ams/api/v1/risk/privacy/payments/sendPaymentResult");
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
            e.printStackTrace();
        }
        return response;
    }

    public static SendRefundResultResponse sendPaymentRefund() {
        SendRefundResultRequest request = new SendRefundResultRequest();
        request.setClientId(CLIENT_ID);
        request.setPath("/ams/api/v1/risk/privacy/payments/sendRefundResult");
        request.setReferenceTransactionId("test_20231012091493242");

        SendRefundResultResponse response = null;
        try {
            response = defaultAlipayClient.execute(request);
        } catch (AlipayApiException e) {
            // TODO Handle AlipayApiException and log
            e.printStackTrace();
        }
        return response;
    }

    public static RiskReportResponse reportRisk() {
        RiskReportRequest request = new RiskReportRequest();
        request.setClientId(CLIENT_ID);
        request.setPath("/ams/api/v1/risk/privacy/payments/reportRisk");
        request.setReferenceTransactionId("test_20231012091493242");
        request.setReportReason("test");
        request.setRiskType("FRAUD");
        request.setRiskOccurrenceTime(new Date());

        RiskReportResponse response = null;
        try {
            response = defaultAlipayClient.execute(request);
        } catch (AlipayApiException e) {
            // TODO Handle AlipayApiException and log
            e.printStackTrace();
        }
        return response;
    }

    private static void buildRiskDecideRequest(RiskDecideRequest request) {
        Order order = new Order();
        order.setReferenceOrderId("test_202310120914932421");
        Amount orderAmount = new Amount();
        orderAmount.setCurrency("BRL");
        orderAmount.setValue("30000");
        order.setOrderAmount(orderAmount);
        order.setOrderDescription("Cappuccino #grande (Mika's coffee shop)");
        Merchant merchant = new Merchant();
        merchant.setReferenceMerchantId("SM_001");
        merchant.setMerchantEmail("12345678@foxmail.com");
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
        buyer.setBuyerRegistrationTime("2023-01-01T12:08:56+08:00");
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
        paymentMethodMetaData.setCardHolderName(cardHolderName);
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