/*
 * Ant Group
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.alipay.global.api.request.ams.risk.tee.encryptstrategy;

import com.alibaba.fastjson.JSONObject;
import com.alipay.global.api.AlipayClient;
import com.alipay.global.api.DefaultAlipayClient;
import com.alipay.global.api.model.ams.*;
import com.alipay.global.api.model.risk.Merchant;
import com.alipay.global.api.model.risk.Order;
import com.alipay.global.api.model.risk.PaymentMethod;
import com.alipay.global.api.model.risk.*;
import com.alipay.global.api.request.ams.risk.RiskDecideRequest;
import com.alipay.global.api.request.ams.risk.tee.enums.EncryptKeyEnum;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class EncryptStrategyTest {
    private static final String       CLIENT_ID           = "SANDBOX_5YBW7N2ZFQKM01671";
    private static final String       GATE_WAY_URL        = "https://open-sea-global.alipay.com";
    private static final String       merchantPrivateKey  = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCvmKKTqh8LycwRsKgHbSjaVC1UIc2FLzZoXwDjyje/RGIgXfnQiMTQtbr8PzhK+syD+Lk809QxAWxKJilfysBsMwYHnPrq3bd76cESCht8aEXOlhP3Y6U3Q/ErMCbDLmRGt6KL4EkQXVW/hBDRQfnqn9523n7Vf+QYB8VkdwUmCQoLOAmUa/b0tN20FZiMLOkKoyoR3sfyZkq0tJoc8H5VEp4JCPTePhLHErm0ydPALRATpwe6r5gBTr7afLYp3p4H39pnhAaOLoNgsTRl9iNrZLKW058T0xGN8l6eNQjh4D5Bb4m4cXOYW7EsBMXbv9/zOCSIE3cerUMUapGa+EwNAgMBAAECggEALXn9WPrtMXNg25AB5HJ76xdz6d8bSAvzLlK0e0oq5+lA9JsCEOTq/Bakx6Fj5d9QSjmHV96QjOSOdCGwA3QSI7OPMzePsHYdiAUBELf3aF/k/FzX7PEsn8SD2CiPUWtZYws6GLnMkmVpyMDeTiOlIwOmbOiwtoB5xVN15tAAUFoG2cDEilIluL74bMKWNzym76AC3KFoUG7JHQudGC3li3/tvbiKqdEkVv4KkpxO5L2HoOJcOQ1wbXrXbBr7owCJ1hgOLulWS+I1HUru96+vPnheVuSMOmGwe6Ocs2mz3I9E2yBJS2Et3IaU5blgj2v+mCB+Ef1GhT2P3MbgKcWFDQKBgQDj2UA8PlglClM2SYCuPLI6v8y3CLd45SZNeZJ624VFSKdH5BVn8jJ0s0cQ7tdGVztRIhv9aklZAJoUke6u8dTMcqfP02rDIwrAy4k04RZtaT0fMMLD1UIjNZGjHSNA3RaTALvsOW3a9FrQ0ADq2CGH1AE9UeA3cvYTcCdcqVvnKwKBgQDFSqoLwzS+u1umAKuVxi7EP6mCqrs2G3TYmPPJItDmZ2vvqYJ4FlsCyHOJgwrEiWlBmXHdkoMnDXJVJFW+P6cLQ9V4n3p0Ie9z+o6aMtvlZWuSYltwXm/ycVp/hQPbFFIxJtUfcHeURWXGGIAaaPtE3AEZlLf/6He/EuIx/i39pwKBgHUylFXog5JZ4z9zQ1tcIRkS+wvS0fy/cZo8RFopfD2NB251JYWYENfyN0XAdL7bs8kh0F/jQeMj6h1Wiv01qw2WlDbUOoAxECDnEKZAeTbCAQyAGk587U4LCeRq4m1Ey43FvOPfDmCne+cYdsBVd7nUFOcjrD8ZRPXupHCHNJyNAoGAF4UoMpDKB/cEHQ01Z3yh9Kl2RuqduPya+Ht7jL4G04D+4CqAMFrR7MoC5CDoNrf/AWAOsBCYbB5xQ+85/Z1PB8vFdkTk71VgW55CQ+XOS1HE+kWZixLnG6sh09QCuqp4hf2QKNhamSWQ1YX0N7HOSs5Dn04YhXysUe4pYzZfOz8CgYEAlLvAJ7oqCgCt+d3XjcGW76l+k8seIGjnpyWN5rbCalXnRuFsaNf6RTVskmcVzsZlWLon1fK8IH4xFDrqgXbvERemuaHWnaadZD6i/6tkbyOROAFGbkOSf5Wwe4NS7xzxGM0WSmPArg1nfd/D/8TIgAKQZyIpwPeer51HczR4AX8=";
    private static final String       alipayPublicKey     = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAqWxuPBXkI+9uMFT8gM/jdkNyVVbDix3A5y2mhRMiPPCMh5ArPkrcHGTRQKntQI6flAc6LNuEvB+4i4Eu+jLs3qjmlM6J1Hvhpw/mu9L+V+bypyONx35AtJjEoajD3d+kCtoWqYFylZpXXP4VCGxhVi0N9fI9AIszUNECcbYV9iu7T7cCT388dklvmmOQvT0CvdxBfrlpfVcyCOuShhYTJvsxofYLFbWo7j+ghciVPthvPQ2z8X9ZJcECWG60sr1qvNBbM26F20b59UE79HCjbCV+3KNNztP/PJNQFDkSYeSdvW+f3Uu+k3yrFhUWIR65VwE2L+QIwkQPvFt6mIx8mwIDAQAB";
    private static final AlipayClient defaultAlipayClient = new DefaultAlipayClient(GATE_WAY_URL, merchantPrivateKey, alipayPublicKey);

    public static void main(String[] args) throws Exception {
        RiskDecideRequest request = new RiskDecideRequest();
        request.setClientId(CLIENT_ID);
        request.setPath("/ams/api/v1/risk/payments/decide");
        request.setReferenceTransactionId("test_20231012091493242");
        request.setAuthorizationPhase(AuthorizationPhase.PRE_AUTHORIZATION);
        // 1. 构建明文报文
        buildRiskDecideRequest(request);
        // 2. 生成 DK
        KeyGenerator aesKeyGen = KeyGenerator.getInstance("AES");
        SecretKey key = aesKeyGen.generateKey();
        byte[] data_key = key.getEncoded();
        // 3. 加密策略
        RiskDecideEncryptStrategy strategy = new RiskDecideEncryptStrategy();
        // 4. 测试
        testRiskDecideEncryptStrategy(data_key, request, strategy);
        timeCostTest(data_key, request, strategy);
    }

    private static void timeCostTest(byte[] data_key, RiskDecideRequest request,
                                     RiskDecideEncryptStrategy strategy) throws Exception {
        // 1. 构建 encryptKeyLists
        List<EncryptKeyEnum> encryptKeyList = Arrays.asList(
                EncryptKeyEnum.MERCHANT_EMAIL,
                EncryptKeyEnum.SHIPPING_ADDR1,
                EncryptKeyEnum.SHIPPING_ADDR2,
                EncryptKeyEnum.SHIPPING_NAME,
                EncryptKeyEnum.SHIPPING_EMAIL,
                EncryptKeyEnum.SHIPPING_PHONE_NO,
                EncryptKeyEnum.BUYER_REGISTRATION_TIME,
                EncryptKeyEnum.CARD_HOLDER_NAME
        );
        long start = System.currentTimeMillis();
        strategy.encrypt(data_key, request, encryptKeyList);
        long end = System.currentTimeMillis();
        System.out.println("encrypt time cost:" + (end - start));
    }

    private static void testRiskDecideEncryptStrategy(byte[] data_key, RiskDecideRequest request,
                                                      RiskDecideEncryptStrategy strategy) throws Exception {
        System.out.println("BEGIN TEST:");
        testOrders(data_key, request, strategy);
        String data_keyStr = Base64.getEncoder().encodeToString(data_key);
        testBuyerRegistrationTime(data_keyStr, request, strategy);
        testCardHolderName(data_key, request, strategy);
    }

    private static void testOrders(byte[] data_key, RiskDecideRequest request,
                                       RiskDecideEncryptStrategy strategy) throws Exception {
        List<Order> orders = request.getOrders();
        // 0. 加密前的数据
        String merchantEmailBeforeEncrypt = orders.stream().map(Order::getMerchant).map(Merchant::getMerchantEmail).collect(Collectors.joining(","));
        String shippingAddr1BeforeEncrypt = orders.stream().map(Order::getShipping).map(Shipping::getShippingAddress).map(Address::getAddress1).collect(Collectors.joining(","));
        String shippingAddr2BeforeEncrypt = orders.stream().map(Order::getShipping).map(Shipping::getShippingAddress).map(Address::getAddress2).collect(Collectors.joining(","));
        String shippingEmailBeforeEncrypt = orders.stream().map(Order::getShipping).map(Shipping::getShipToEmail).collect(Collectors.joining(","));
        String shippingPhoneNoBeforeEncrypt = orders.stream().map(Order::getShipping).map(Shipping::getShippingPhoneNo).collect(Collectors.joining(","));
        String shippingNamesBeforeEncrypt = orders.stream()
                .map(Order::getShipping)
                .map(Shipping::getShippingName)
                .map(userName -> {
                    String firstName = userName.getFirstName() != null ? userName.getFirstName() : "";
                    String middleName = userName.getMiddleName() != null ? userName.getMiddleName() : "";
                    String lastName = userName.getLastName() != null ? userName.getLastName() : "";
                    return firstName + " " + middleName + " " + lastName;
                })
                .collect(Collectors.joining(", "));

        // 1. 构建 encryptKeyLists
        List<EncryptKeyEnum> encryptKeyList = Arrays.asList(
                EncryptKeyEnum.MERCHANT_EMAIL,
                EncryptKeyEnum.SHIPPING_ADDR1,
                EncryptKeyEnum.SHIPPING_ADDR2,
                EncryptKeyEnum.SHIPPING_NAME,
                EncryptKeyEnum.SHIPPING_EMAIL,
                EncryptKeyEnum.SHIPPING_PHONE_NO
        );
        // 2. 调用加密策略进行加密
        strategy.encrypt(data_key, request, encryptKeyList);
        // 3. 调用解密方法，验证加解密是否正确
        for (Order order : orders) {
            // 3.1 MERCHANT_EMAIL
            if (order.getMerchant().getMerchantEmail() != null) {
                order.getMerchant().setMerchantEmail(
                        new String(decrypt(data_key, Base64.getDecoder().decode(order.getMerchant().getMerchantEmail()))));
            }
            // 3.2. SHIPPING_ADDR1
            if (order.getShipping().getShippingAddress().getAddress1() != null) {
                order.getShipping().getShippingAddress().setAddress1(
                        new String(decrypt(data_key, Base64.getDecoder().decode(order.getShipping().getShippingAddress().getAddress1()))));
            }
            // 3.3. SHIPPING_ADDR2
            if (order.getShipping().getShippingAddress().getAddress2() != null) {
                order.getShipping().getShippingAddress().setAddress2(
                        new String(decrypt(data_key, Base64.getDecoder().decode(order.getShipping().getShippingAddress().getAddress2()))));
            }
            // 3.4. SHIPPING_NAME
            if (order.getShipping().getShippingName() != null) {
                decryptName(data_key, order.getShipping().getShippingName());
            }
            // 3.5. SHIPPING_EMAIL
            if (order.getShipping().getShipToEmail() != null) {
                order.getShipping().setShipToEmail(
                        new String(decrypt(data_key, Base64.getDecoder().decode(order.getShipping().getShipToEmail()))));
            }
            // 3.6. SHIPPING_PHONE_NO
            if (order.getShipping().getShippingPhoneNo() != null) {
                order.getShipping().setShippingPhoneNo(
                        new String(decrypt(data_key, Base64.getDecoder().decode(order.getShipping().getShippingPhoneNo()))));
            }
        }
        // 4. 比较结果
        String merchantEmailAfterEncrypt = orders.stream().map(Order::getMerchant).map(Merchant::getMerchantEmail).collect(Collectors.joining(","));
        String shippingAddr1AfterEncrypt = orders.stream().map(Order::getShipping).map(Shipping::getShippingAddress).map(Address::getAddress1).collect(Collectors.joining(","));
        String shippingAddr2AfterEncrypt = orders.stream().map(Order::getShipping).map(Shipping::getShippingAddress).map(Address::getAddress2).collect(Collectors.joining(","));
        String shippingEmailAfterEncrypt = orders.stream().map(Order::getShipping).map(Shipping::getShipToEmail).collect(Collectors.joining(","));
        String shippingPhoneNoAfterEncrypt = orders.stream().map(Order::getShipping).map(Shipping::getShippingPhoneNo).collect(Collectors.joining(","));
        String shippingNamesAfterEncrypt = orders.stream()
                .map(Order::getShipping)
                .map(Shipping::getShippingName)
                .map(userName -> {
                    String firstName = userName.getFirstName() != null ? userName.getFirstName() : "";
                    String middleName = userName.getMiddleName() != null ? userName.getMiddleName() : "";
                    String lastName = userName.getLastName() != null ? userName.getLastName() : "";
                    return firstName + " " + middleName + " " + lastName;
                })
                .collect(Collectors.joining(", "));
        System.out.println("+ TEST MERCHANT_EMAIL RESULT: " + merchantEmailAfterEncrypt.equals(merchantEmailBeforeEncrypt));
        System.out.println("+ TEST SHIPPING_ADDR1 RESULT: " + shippingAddr1AfterEncrypt.equals(shippingAddr1BeforeEncrypt));
        System.out.println("+ TEST SHIPPING_ADDR2 RESULT: " + shippingAddr2AfterEncrypt.equals(shippingAddr2BeforeEncrypt));
        System.out.println("+ TEST SHIPPING_NAME RESULT: " + shippingNamesAfterEncrypt.equals(shippingNamesBeforeEncrypt));
        System.out.println("+ TEST MERCHANT_EMAIL RESULT: " + shippingEmailAfterEncrypt.equals(shippingEmailBeforeEncrypt));
        System.out.println("+ TEST SHIPPING_PHONE_NO RESULT: " + shippingPhoneNoAfterEncrypt.equals(shippingPhoneNoBeforeEncrypt));
    }

    private static void testBuyerRegistrationTime(String data_key, RiskDecideRequest request,
                                          RiskDecideEncryptStrategy strategy) throws Exception {
        String buyerRegistrationTime = request.getBuyer().getBuyerRegistrationTime();
        if (buyerRegistrationTime == null) {
            return;
        }
        // 1. 构建 encryptKeyLists
        List<EncryptKeyEnum> encryptKeyList = Arrays.asList(
                EncryptKeyEnum.BUYER_REGISTRATION_TIME
        );
        // 2. 调用加密策略进行加密
        strategy.encrypt(data_key, request, encryptKeyList);
        // 3. 调用解密方法，验证加解密是否正确
        request.getBuyer().setBuyerRegistrationTime(
                new String(decrypt(Base64.getDecoder().decode(data_key), Base64.getDecoder().decode(request.getBuyer().getBuyerRegistrationTime()))));
        System.out.println("+ TEST BUYER_REGISTRATION_TIME RESULT: " + buyerRegistrationTime.equals(request.getBuyer().getBuyerRegistrationTime()));
    }

    private static void testCardHolderName(byte[] data_key, RiskDecideRequest request,
                                           RiskDecideEncryptStrategy strategy) throws Exception {
        List<PaymentDetail> paymentDetails = request.getPaymentDetails();
        String beforeEncrypt = JSONObject.toJSONString(paymentDetails);
        // 1. 构建 encryptKeyLists
        List<EncryptKeyEnum> encryptKeyList = Arrays.asList(
                EncryptKeyEnum.CARD_HOLDER_NAME
        );
        // 2. 调用加密策略进行加密
        strategy.encrypt(data_key, request, encryptKeyList);
        // 3. 调用解密方法，验证加解密是否正确
        for (PaymentDetail paymentDetail : paymentDetails) {
            UserName cardHolderName = paymentDetail.getPaymentMethod().getPaymentMethodMetaData().getCardholderName();
            if (cardHolderName != null) {
                decryptName(data_key, cardHolderName);
            }
        }
        String afterDecrypt = JSONObject.toJSONString(paymentDetails);
        System.out.println("+ TEST CARD_HOLDER_NAME RESULT: " + afterDecrypt.equals(beforeEncrypt));
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

    private static byte[] decrypt(byte[] dataKey, byte[] ciphertext) throws Exception {
        SecretKeySpec keySpec = new SecretKeySpec(dataKey, "AES");

        GCMParameterSpec parameterSpec = new GCMParameterSpec(96, ciphertext, ciphertext.length - 1, 1);

        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");

        cipher.init(Cipher.DECRYPT_MODE, keySpec, parameterSpec);
        return cipher.doFinal(ciphertext, 0, ciphertext.length - 1);
    }

    private static void decryptName(byte[] dataKey, UserName userName) throws Exception {
        if (userName.getFirstName() != null) {
            userName.setFirstName(new String(decrypt(dataKey, Base64.getDecoder().decode(userName.getFirstName()))));
        }
        if (userName.getMiddleName() != null) {
            userName.setMiddleName(new String(decrypt(dataKey, Base64.getDecoder().decode(userName.getMiddleName()))));
        }
        if (userName.getLastName() != null) {
            userName.setLastName(new String(decrypt(dataKey, Base64.getDecoder().decode(userName.getLastName()))));
        }
        if (userName.getFullName() != null) {
            userName.setFullName(new String(decrypt(dataKey, Base64.getDecoder().decode(userName.getFullName()))));
        }
    }
}