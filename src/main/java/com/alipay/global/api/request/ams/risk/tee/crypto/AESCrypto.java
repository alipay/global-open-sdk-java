/*
 * Ant Group
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.alipay.global.api.request.ams.risk.tee.crypto;

import com.alipay.global.api.base64.Base64Provider;
import com.alipay.global.api.request.ams.risk.tee.constants.CryptoSdkConstant;
import com.alipay.global.api.request.ams.risk.tee.enums.ErrorCodeEnum;
import com.alipay.global.api.request.ams.risk.tee.exception.CryptoException;
import java.security.Security;
import javax.crypto.spec.SecretKeySpec;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.engines.AESEngine;
import org.bouncycastle.crypto.modes.GCMBlockCipher;
import org.bouncycastle.crypto.params.AEADParameters;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

/** AES crypto implementation AES 加密实现 */
public class AESCrypto implements Crypto {
  private static volatile AESCrypto instance;

  static {
    Security.addProvider(new BouncyCastleProvider());
  }

  public static AESCrypto getInstance() {
    if (instance == null) {
      synchronized (AESCrypto.class) {
        if (instance == null) {
          instance = new AESCrypto();
        }
      }
    }
    return instance;
  }

  /**
   * encrypt by dataKey 通过 dataKey 加密数据
   *
   * @param dataKey symmetric key
   * @param data content to encrypt
   * @return encrypted data if data is not null and null if data is null
   */
  @Override
  public byte[] encrypt(byte[] dataKey, byte[] data) {
    if (dataKey == null || dataKey.length == 0) {
      throw new CryptoException(ErrorCodeEnum.PARAM_ILLEGAL, "dataKey cannot be empty");
    }
    if (data == null) {
      return null;
    }

    try {
      SecretKeySpec keySpec = new SecretKeySpec(dataKey, CryptoSdkConstant.ENCRYPT_MAIN_ALGORITHM);
      return encrypt(data, keySpec);
    } catch (Throwable t) {
      throw new CryptoException(ErrorCodeEnum.ENCRYPT_ERROR, t);
    }
  }

  /**
   * encrypt by dataKey 通过 dataKey 加密数据
   *
   * @param dataKeyBase64 symmetric key encoded by base64
   * @param data content to encrypt
   * @return encrypted data if data is not null and null if data is null
   */
  @Override
  public byte[] encrypt(String dataKeyBase64, byte[] data) {
    if (dataKeyBase64 == null || dataKeyBase64.length() == 0) {
      throw new CryptoException(ErrorCodeEnum.PARAM_ILLEGAL, "dataKey cannot be empty");
    }
    return encrypt(Base64Provider.getBase64Encryptor().decode(dataKeyBase64), data);
  }

  private byte[] encrypt(byte[] data, SecretKeySpec keySpec) throws Exception {

    GCMBlockCipher gcmEngine = new GCMBlockCipher(new AESEngine());

    CipherParameters params =
        new AEADParameters(
            new KeyParameter(keySpec.getEncoded()),
            CryptoSdkConstant.TAG_LENGTH,
            CryptoSdkConstant.COMMON_IV);

    gcmEngine.init(true, params);
    int encryptedDataLength = gcmEngine.getOutputSize(data.length);
    byte[] encryptedData = new byte[encryptedDataLength];
    int outputLen = gcmEngine.processBytes(data, 0, data.length, encryptedData, 0);
    gcmEngine.doFinal(encryptedData, outputLen);
    byte[] result = new byte[CryptoSdkConstant.COMMON_IV.length + encryptedData.length];
    System.arraycopy(encryptedData, 0, result, 0, encryptedData.length);
    System.arraycopy(
        CryptoSdkConstant.COMMON_IV,
        0,
        result,
        encryptedData.length,
        CryptoSdkConstant.COMMON_IV.length);
    return result;
  }
}
