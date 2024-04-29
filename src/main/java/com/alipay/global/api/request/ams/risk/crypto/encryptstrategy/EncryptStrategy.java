package com.alipay.global.api.request.ams.risk.crypto.encryptstrategy;

import com.alipay.global.api.request.AlipayRequest;
import com.alipay.global.api.request.ams.risk.crypto.enums.EncryptKeyEnum;

import java.util.List;

public interface EncryptStrategy {
    void encrypt(byte[] data_key, AlipayRequest<?> request, List<EncryptKeyEnum> encryptKeyList);
}
