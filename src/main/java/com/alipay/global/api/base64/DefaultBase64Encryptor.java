package com.alipay.global.api.base64;

import javax.xml.bind.DatatypeConverter;

/**
 * 为兼容低版本的java，默认使用javax.xml.bind.DatatypeConverter
 */
public class DefaultBase64Encryptor implements Base64Encryptor {

    @Override
    public String encodeToString(byte[] src) {
        return DatatypeConverter.printBase64Binary(src);
    }

    @Override
    public byte[] decode(String src) {
        return DatatypeConverter.parseBase64Binary(src);
    }

}
