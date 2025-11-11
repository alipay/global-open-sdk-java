package com.alipay.global.api.base64;


import java.util.Base64;

/** 为兼容低版本的java，默认使用javax.xml.bind.DatatypeConverter */
public class DefaultBase64Encryptor implements Base64Encryptor {

    @Override
    public String encodeToString(byte[] src) {
        return Base64.getEncoder().encodeToString(src);
    }

    @Override
    public byte[] decode(String src) {
        return Base64.getDecoder().decode(src);
    }

}
