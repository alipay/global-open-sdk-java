package com.alipay.global.api.base64;

public interface Base64Encryptor {

    public String encodeToString(byte[] src);

    public byte[] decode(String src);

}
