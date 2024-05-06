package com.alipay.global.api.request.ams.risk.tee.crypto;

import com.alipay.global.api.request.ams.risk.tee.crypto.AESCrypto;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class AESCryptoTest {
    public static void main(String[] args) throws Exception{
        testString1();
    }

    private static void testString1() throws Exception{
        //prepare
        KeyGenerator aesKeyGen = KeyGenerator.getInstance("AES");
        SecretKey key = aesKeyGen.generateKey();
        byte[] keyBytes = key.getEncoded();
        String plaintext = "E;,c(=YD#|";

        //invoke
        byte[] encrypt1 = AESCrypto.getInstance().encrypt(keyBytes,
                plaintext.getBytes(StandardCharsets.UTF_8));
        byte[] encrypt2 = AESCrypto.getInstance().encrypt(keyBytes,
                plaintext.getBytes(StandardCharsets.UTF_8));
        String plaintext1 = new String(decrypt(keyBytes, encrypt1));
        String plaintext2 = new String(decrypt(keyBytes, encrypt2));

        //check
        System.out.println(Base64.getEncoder().encodeToString(encrypt1)
                .equals(Base64.getEncoder().encodeToString(encrypt2)));
        System.out.println(plaintext1.equals(plaintext));
        System.out.println(plaintext2.equals(plaintext));
        System.out.println(plaintext1.equals(plaintext2));
    }

    private static void testString2() throws Exception{
        //prepare
        KeyGenerator aesKeyGen = KeyGenerator.getInstance("AES");
        SecretKey key = aesKeyGen.generateKey();
        byte[] keyBytes = key.getEncoded();
        String plaintext = "E;,c(=YD#|";

        // invoke encrypt
        byte[] encrypt = AESCrypto.getInstance().encrypt(keyBytes,
                plaintext.getBytes(StandardCharsets.UTF_8));
        String encryptString = Base64.getEncoder().encodeToString(encrypt);

        // decrypt from byte[]
        String plaintext1 = new String(decrypt(keyBytes, encrypt));
        System.out.println(plaintext1.equals(plaintext));

        // decrypt from String
        byte[] encryptBytes = Base64.getDecoder().decode(encryptString);
        String plaintext3 = new String(decrypt(keyBytes, encryptBytes));
        System.out.println(plaintext3.equals(plaintext));
    }

    private static void testInteger() throws Exception{
        //prepare
        KeyGenerator aesKeyGen = KeyGenerator.getInstance("AES");
        SecretKey key = aesKeyGen.generateKey();
        byte[] keyBytes = key.getEncoded();
        Integer plaintext = 1234567890;

        //invoke
        byte[] encrypt1 = AESCrypto.getInstance().encrypt(keyBytes,
                plaintext.toString().getBytes(StandardCharsets.UTF_8));
        byte[] encrypt2 = AESCrypto.getInstance().encrypt(keyBytes,
                plaintext.toString().getBytes(StandardCharsets.UTF_8));
        Integer plaintext1 = Integer.parseInt(new String(decrypt(keyBytes, encrypt1)));
        Integer plaintext2 = Integer.parseInt(new String(decrypt(keyBytes, encrypt2)));

        // check
        System.out.println(Base64.getEncoder().encodeToString(encrypt1)
                .equals(Base64.getEncoder().encodeToString(encrypt2)));
        System.out.println(plaintext1.equals(plaintext));
        System.out.println(plaintext2.equals(plaintext));
        System.out.println(plaintext1.equals(plaintext2));
        }

    private static byte[] decrypt(byte[] dataKey, byte[] ciphertext) throws Exception {
        SecretKeySpec keySpec = new SecretKeySpec(dataKey, "AES");

        GCMParameterSpec parameterSpec = new GCMParameterSpec(96, ciphertext, ciphertext.length - 1, 1);

        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");

        cipher.init(Cipher.DECRYPT_MODE, keySpec, parameterSpec);
        return cipher.doFinal(ciphertext, 0, ciphertext.length - 1);
    }
}
