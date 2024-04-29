package com.alipay.global.api.request.ams.risk.crypto.encryptstrategy;

import com.alipay.global.api.request.AlipayRequest;
import com.alipay.global.api.request.ams.risk.SendPaymentResultRequest;
import com.alipay.global.api.request.ams.risk.crypto.enums.EncryptKeyEnum;
import com.alipay.global.api.request.ams.risk.crypto.enums.ErrorCodeEnum;
import com.alipay.global.api.request.ams.risk.crypto.exception.CryptoException;

import java.util.List;

public class SendPaymentResultEncryptStrategy implements EncryptStrategy{
    @Override
    public void encrypt(byte[] data_key, AlipayRequest<?> request, List<EncryptKeyEnum> encryptKeyList) {
        if (!(request instanceof SendPaymentResultRequest)) {
            throw new CryptoException(ErrorCodeEnum.MISMATCH_ENCRYPT_STRATEGY, "request is not instance of SendPaymentResultRequest");
        }
        SendPaymentResultRequest sendPaymentResultRequest = (SendPaymentResultRequest) request;
        // TODO: implement me!
    }
}
