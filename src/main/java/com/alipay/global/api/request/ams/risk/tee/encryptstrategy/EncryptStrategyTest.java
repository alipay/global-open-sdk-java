/*
 * Ant Group
 * Copyright (c) 2004-2023 All Rights Reserved.
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

public class EncryptStrategyTest {
    private static final String       CLIENT_ID           = "SANDBOX_5YBW7N2ZFQKM01671";
    private static final String       GATE_WAY_URL        = "https://open-sea-global.alipay.com";
    private static final String       merchantPrivateKey  = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCvmKKTqh8LycwRsKgHbSjaVC1UIc2FLzZoXwDjyje/RGIgXfnQiMTQtbr8PzhK+syD+Lk809QxAWxKJilfysBsMwYHnPrq3bd76cESCht8aEXOlhP3Y6U3Q/ErMCbDLmRGt6KL4EkQXVW/hBDRQfnqn9523n7Vf+QYB8VkdwUmCQoLOAmUa/b0tN20FZiMLOkKoyoR3sfyZkq0tJoc8H5VEp4JCPTePhLHErm0ydPALRATpwe6r5gBTr7afLYp3p4H39pnhAaOLoNgsTRl9iNrZLKW058T0xGN8l6eNQjh4D5Bb4m4cXOYW7EsBMXbv9/zOCSIE3cerUMUapGa+EwNAgMBAAECggEALXn9WPrtMXNg25AB5HJ76xdz6d8bSAvzLlK0e0oq5+lA9JsCEOTq/Bakx6Fj5d9QSjmHV96QjOSOdCGwA3QSI7OPMzePsHYdiAUBELf3aF/k/FzX7PEsn8SD2CiPUWtZYws6GLnMkmVpyMDeTiOlIwOmbOiwtoB5xVN15tAAUFoG2cDEilIluL74bMKWNzym76AC3KFoUG7JHQudGC3li3/tvbiKqdEkVv4KkpxO5L2HoOJcOQ1wbXrXbBr7owCJ1hgOLulWS+I1HUru96+vPnheVuSMOmGwe6Ocs2mz3I9E2yBJS2Et3IaU5blgj2v+mCB+Ef1GhT2P3MbgKcWFDQKBgQDj2UA8PlglClM2SYCuPLI6v8y3CLd45SZNeZJ624VFSKdH5BVn8jJ0s0cQ7tdGVztRIhv9aklZAJoUke6u8dTMcqfP02rDIwrAy4k04RZtaT0fMMLD1UIjNZGjHSNA3RaTALvsOW3a9FrQ0ADq2CGH1AE9UeA3cvYTcCdcqVvnKwKBgQDFSqoLwzS+u1umAKuVxi7EP6mCqrs2G3TYmPPJItDmZ2vvqYJ4FlsCyHOJgwrEiWlBmXHdkoMnDXJVJFW+P6cLQ9V4n3p0Ie9z+o6aMtvlZWuSYltwXm/ycVp/hQPbFFIxJtUfcHeURWXGGIAaaPtE3AEZlLf/6He/EuIx/i39pwKBgHUylFXog5JZ4z9zQ1tcIRkS+wvS0fy/cZo8RFopfD2NB251JYWYENfyN0XAdL7bs8kh0F/jQeMj6h1Wiv01qw2WlDbUOoAxECDnEKZAeTbCAQyAGk587U4LCeRq4m1Ey43FvOPfDmCne+cYdsBVd7nUFOcjrD8ZRPXupHCHNJyNAoGAF4UoMpDKB/cEHQ01Z3yh9Kl2RuqduPya+Ht7jL4G04D+4CqAMFrR7MoC5CDoNrf/AWAOsBCYbB5xQ+85/Z1PB8vFdkTk71VgW55CQ+XOS1HE+kWZixLnG6sh09QCuqp4hf2QKNhamSWQ1YX0N7HOSs5Dn04YhXysUe4pYzZfOz8CgYEAlLvAJ7oqCgCt+d3XjcGW76l+k8seIGjnpyWN5rbCalXnRuFsaNf6RTVskmcVzsZlWLon1fK8IH4xFDrqgXbvERemuaHWnaadZD6i/6tkbyOROAFGbkOSf5Wwe4NS7xzxGM0WSmPArg1nfd/D/8TIgAKQZyIpwPeer51HczR4AX8=";
    private static final String       alipayPublicKey     = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAqWxuPBXkI+9uMFT8gM/jdkNyVVbDix3A5y2mhRMiPPCMh5ArPkrcHGTRQKntQI6flAc6LNuEvB+4i4Eu+jLs3qjmlM6J1Hvhpw/mu9L+V+bypyONx35AtJjEoajD3d+kCtoWqYFylZpXXP4VCGxhVi0N9fI9AIszUNECcbYV9iu7T7cCT388dklvmmOQvT0CvdxBfrlpfVcyCOuShhYTJvsxofYLFbWo7j+ghciVPthvPQ2z8X9ZJcECWG60sr1qvNBbM26F20b59UE79HCjbCV+3KNNztP/PJNQFDkSYeSdvW+f3Uu+k3yrFhUWIR65VwE2L+QIwkQPvFt6mIx8mwIDAQAB";
    private static final AlipayClient defaultAlipayClient = new DefaultAlipayClient(GATE_WAY_URL, merchantPrivateKey, alipayPublicKey);

    public static void main(String[] args) throws Exception {
        testRiskDecideEncryptStrategy();
    }

    public static void testRiskDecideEncryptStrategy() throws Exception {
        RiskDecideRequest request = new RiskDecideRequest();
        request.setClientId(CLIENT_ID);
        request.setPath("/ams/api/v1/risk/payments/decide");
        request.setReferenceTransactionId("test_20231012091493242");
        request.setAuthorizationPhase(AuthorizationPhase.PRE_AUTHORIZATION);
        // 1. 构建明文报文
        buildRiskDecideRequest(request);
        // 2. 构建 encryptKeyLists
        List<EncryptKeyEnum> encryptKeyList = Arrays.asList(
                EncryptKeyEnum.ORDERS_SHIPPING_SHIPPINGADDR_ADDR1,
                EncryptKeyEnum.ORDERS_SHIPPING_SHIPPINGADDR_ADDR2
        );
        // 3. 生成 DK
        KeyGenerator aesKeyGen = KeyGenerator.getInstance("AES");
        SecretKey key = aesKeyGen.generateKey();
        byte[] keyBytes = key.getEncoded();
        // 4. 调用加密方法
        RiskDecideEncryptStrategy strategy = new RiskDecideEncryptStrategy();
        strategy.encrypt(keyBytes, request, encryptKeyList);
        // 5. 调用解密方法，验证加解密是否正确
        List<Order> orders = request.getOrders();
        for (Order order : orders) {
            String encryptAddr1 = order.getShipping().getShippingAddress().getAddress1();
            String encryptAddr2 = order.getShipping().getShippingAddress().getAddress2();
            order.getShipping().getShippingAddress().setAddress1(
                    new String(decrypt(keyBytes, Base64.getDecoder().decode(encryptAddr1))));
            order.getShipping().getShippingAddress().setAddress2(
                    new String(decrypt(keyBytes, Base64.getDecoder().decode(encryptAddr2))));
        }
        System.out.println(JSONObject.toJSONString(orders));
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

    private static byte[] decrypt(byte[] dataKey, byte[] ciphertext) throws Exception {
        SecretKeySpec keySpec = new SecretKeySpec(dataKey, "AES");

        GCMParameterSpec parameterSpec = new GCMParameterSpec(96, ciphertext, ciphertext.length - 1, 1);

        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");

        cipher.init(Cipher.DECRYPT_MODE, keySpec, parameterSpec);
        return cipher.doFinal(ciphertext, 0, ciphertext.length - 1);
    }
}