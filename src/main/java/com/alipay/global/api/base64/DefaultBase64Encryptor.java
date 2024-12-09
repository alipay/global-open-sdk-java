package com.alipay.global.api.base64;


import org.apache.commons.codec.binary.Base64;

public class DefaultBase64Encryptor implements Base64Encryptor {

    @Override
    public String encodeToString(byte[] src) {
        return new String(Base64.encodeBase64(src));
    }

    @Override
    public byte[] decode(String src) {
        return Base64.decodeBase64(src);
    }
}
