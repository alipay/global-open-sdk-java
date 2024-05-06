package com.alipay.global.api.request.ams.risk.tee.encryptstrategy;

import com.alipay.global.api.request.AlipayRequest;
import com.alipay.global.api.request.ams.risk.tee.enums.EncryptKeyEnum;

import java.util.List;

public interface EncryptStrategy {
    void encrypt(byte[] data_key, AlipayRequest<?> request, List<EncryptKeyEnum> encryptKeyList);
}
