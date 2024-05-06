package com.alipay.global.api.request.ams.risk.tee.crypto;

/**
 * @version global-open-sdk-java 2024/4/26 18:01
 * @author: zhiwen.lw
 */
public interface Crypto {
    /**
     * encrypt by dataKey
     *
     * @param dataKey symmetric key
     * @param data content to encrypt
     * @return encrypted data if data is not null and null if data is null
     */
    byte[] encrypt(byte[] dataKey, byte[] data);

    /**
     * encrypt by dataKey
     *
     * @param dataKeyBase64 symmetric key encoded by base64
     * @param data content to encrypt
     * @return encrypted data if data is not null and null if data is null
     */
    byte[] encrypt(String dataKeyBase64, byte[] data);
}
