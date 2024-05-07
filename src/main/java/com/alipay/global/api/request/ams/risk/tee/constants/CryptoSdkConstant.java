/*
 * Ant Group
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.alipay.global.api.request.ams.risk.tee.constants;

import java.nio.charset.StandardCharsets;

/**
 * necessary constants for encrypt sdk
 * ���� SDK �ı��볣��
 */
public class CryptoSdkConstant {
    public static final int    TAG_LENGTH             = 96;

    //to ensure that the same plaintext has the same ciphertext, we use a fixed initialization vector
    public static final byte[] COMMON_IV              = "i".getBytes(StandardCharsets.UTF_8);

    public static final String ENCRYPT_ALGORITHM      = "AES/GCM/NoPadding";

    public static final String ENCRYPT_MAIN_ALGORITHM = "AES";

}
