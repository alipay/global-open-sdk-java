package com.alipay.global.api.request.ams.risk.crypto.encryptstrategy;

import com.alipay.global.api.model.risk.Order;
import com.alipay.global.api.request.AlipayRequest;
import com.alipay.global.api.request.ams.risk.RiskDecideRequest;
import com.alipay.global.api.request.ams.risk.crypto.AESCrypto;
import com.alipay.global.api.request.ams.risk.crypto.enums.EncryptKeyEnum;
import com.alipay.global.api.request.ams.risk.crypto.enums.ErrorCodeEnum;
import com.alipay.global.api.request.ams.risk.crypto.exception.CryptoException;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;

public class RiskDecideEncryptStrategy implements EncryptStrategy{
    @Override
    public void encrypt(byte[] data_key, AlipayRequest<?> request, List<EncryptKeyEnum> encryptKeyList) {
        if (!(request instanceof RiskDecideRequest)) {
            throw new CryptoException(ErrorCodeEnum.MISMATCH_ENCRYPT_STRATEGY, "request is not instance of RiskDecideRequest");
        }
        RiskDecideRequest riskDecideRequest = (RiskDecideRequest) request;
        // TODO: implement me!
        AESCrypto crypto = AESCrypto.getInstance();
        List<Order> orders = riskDecideRequest.getOrders();
        for (EncryptKeyEnum key : encryptKeyList) {
            if (key == null || key.getCode() == null) {
                continue;
            }
            switch (key) {
                case ORDERS_SHIPPING_SHIPPINGADDR_ADDR1:
                    for (Order order : orders) {
                        byte[] encrypt = crypto.encrypt(data_key,
                                order.getShipping().getShippingAddress().getAddress1().getBytes(StandardCharsets.UTF_8));
                        order.getShipping().getShippingAddress().setAddress1(Base64.getEncoder().encodeToString(encrypt));
                    }
                    break;
                case ORDERS_SHIPPING_SHIPPINGADDR_ADDR2:
                    for (Order order : orders) {
                        byte[] encrypt = crypto.encrypt(data_key,
                                order.getShipping().getShippingAddress().getAddress2().getBytes(StandardCharsets.UTF_8));
                        order.getShipping().getShippingAddress().setAddress2(Base64.getEncoder().encodeToString(encrypt));
                    }
                    break;
                default:
                    throw new CryptoException(ErrorCodeEnum.UNKNOWN_ENCRYPT_KEY, "unknown encrypt key");
            }
        }
    }
}

