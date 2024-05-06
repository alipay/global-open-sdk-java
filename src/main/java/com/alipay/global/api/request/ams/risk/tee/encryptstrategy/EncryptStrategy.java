package com.alipay.global.api.request.ams.risk.tee.encryptstrategy;

import com.alipay.global.api.request.AlipayRequest;
import com.alipay.global.api.request.ams.risk.tee.enums.EncryptKeyEnum;

import java.util.List;

public interface EncryptStrategy {
    /**
     * encrypt method
     * 加密方法
     * @param data_key merchant DK
     * @param request plaintext request
     * @param encryptKeyList list of keys to encrypt
     */
    void encrypt(byte[] data_key, AlipayRequest<?> request, List<EncryptKeyEnum> encryptKeyList);
}
