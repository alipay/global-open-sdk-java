package com.alipay.global.api.base64;

import org.apache.commons.codec.binary.Base64;

/**
 * Base64 encoding and decoding using Apache Commons Codec You can also customize the base64 tool.
 * See the end of the README. compatible JDK 6, 8, 11+
 */
public class DefaultBase64Encryptor implements Base64Encryptor {

  @Override
  public String encodeToString(byte[] src) {
    return Base64.encodeBase64String(src);
  }

  @Override
  public byte[] decode(String src) {
    return Base64.decodeBase64(src);
  }
}
