/*
 * Ant Group
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.alipay.global.api.request.ams.risk.tee.crypto;

public interface Crypto {
    /**
     * encrypt by dataKey
     * 通过 dataKey 加密数据
     *
     * @param dataKey symmetric key
     * @param data    content to encrypt
     * @return encrypted data if data is not null and null if data is null
     */
    byte[] encrypt(byte[] dataKey, byte[] data);

    /*
     * encrypt by dataKey
     * 通过 dataKey 加密数据
     * @param dataKeyBase64 symmetric key String encoded by base64
     * @param data content to encrypt
     * @return encrypted data if data is not null and null if data is null
     */
    byte[] encrypt(String dataKeyBase64, byte[] data);
}
