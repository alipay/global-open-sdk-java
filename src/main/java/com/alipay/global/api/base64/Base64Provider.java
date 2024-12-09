package com.alipay.global.api.base64;

import lombok.Getter;

/**
 * 可以根据需求定制base64实现
 */
public class Base64Provider {

    @Getter
    private static Base64Encryptor base64Encryptor = new DefaultBase64Encryptor();

    public static void setBase64Encryptor(Base64Encryptor base64Encryptor) {
        Base64Provider.base64Encryptor = base64Encryptor;
    }

}