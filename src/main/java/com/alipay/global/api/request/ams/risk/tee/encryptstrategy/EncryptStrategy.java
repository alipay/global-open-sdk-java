package com.alipay.global.api.request.ams.risk.tee.encryptstrategy;

import com.alipay.global.api.request.AlipayRequest;
import com.alipay.global.api.request.ams.risk.tee.enums.EncryptKeyEnum;

import java.util.List;

/**
 * interface of encryption strategy, different requests need to implement their own encryption strategy
 * 加密策略接口，不同请求需要各自实现加密策略
 */
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
