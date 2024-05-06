package com.alipay.global.api.request.ams.risk.tee.crypto;

import com.alipay.global.api.request.ams.risk.tee.constants.CryptoSdkConstant;
import com.alipay.global.api.request.ams.risk.tee.enums.ErrorCodeEnum;
import com.alipay.global.api.request.ams.risk.tee.exception.CryptoException;

import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

/**
 * AES crypto implementation
 * AES 加密实现
 */
public class AESCrypto implements Crypto {
    private static volatile AESCrypto instance;

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
     * encrypt by dataKey
     * 通过 dataKey 加密数据
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
            SecretKeySpec keySpec = new SecretKeySpec(dataKey,
                    CryptoSdkConstant.ENCRYPT_MAIN_ALGORITHM);
            return encrypt(data, keySpec);
        } catch (Throwable t) {
            throw new CryptoException(ErrorCodeEnum.ENCRYPT_ERROR, t);
        }
    }

    /**
     * encrypt by dataKey
     * 通过 dataKey 加密数据
     * @param dataKeyBase64 symmetric key encoded by base64
     * @param data content to encrypt
     * @return encrypted data if data is not null and null if data is null
     */
    @Override
    public byte[] encrypt(String dataKeyBase64, byte[] data) {
        if (dataKeyBase64 == null || dataKeyBase64.length() == 0) {
            throw new CryptoException(ErrorCodeEnum.PARAM_ILLEGAL, "dataKey cannot be empty");
        }
        return encrypt(Base64.getDecoder().decode(dataKeyBase64), data);
    }

    private byte[] encrypt(byte[] data, SecretKeySpec keySpec) throws Exception {
        GCMParameterSpec parameterSpec = new GCMParameterSpec(CryptoSdkConstant.TAG_LENGTH,
                CryptoSdkConstant.COMMON_IV);
        Cipher cipher = Cipher.getInstance(CryptoSdkConstant.ENCRYPT_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, parameterSpec);
        byte[] encrypted = cipher.doFinal(data);
        byte[] result = new byte[CryptoSdkConstant.COMMON_IV.length + encrypted.length];
        System.arraycopy(CryptoSdkConstant.COMMON_IV, 0, result, encrypted.length,
                CryptoSdkConstant.COMMON_IV.length);
        System.arraycopy(encrypted, 0, result, 0, encrypted.length);
        return result;
    }
}
