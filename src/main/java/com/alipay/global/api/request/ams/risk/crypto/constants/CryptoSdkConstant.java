/**
 * Copyright (c) 2023 International Business Group, Ant Group. All rights reserved. Permission is hereby granted, free of charge, to any
 * person obtaining a copy of this software and associated documentation files (the "Software"), the rights to use, copy, modify, merge,
 * and/or distribute the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions: 1.
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR
 * ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH
 * THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE; and 2. If applicable, the use of the Software is also subject to the terms and
 * conditions of any non-disclosure agreement signed by you and the relevant Ant Group entity.
 */
package com.alipay.global.api.request.ams.risk.crypto.constants;

import java.nio.charset.StandardCharsets;

public class CryptoSdkConstant {
    public static final int    TAG_LENGTH             = 96;

    //to ensure that the same plaintext has the same ciphertext, we use a fixed initialization vector
    public static final byte[] COMMON_IV              = "i".getBytes(StandardCharsets.UTF_8);

    public static final String ENCRYPT_ALGORITHM      = "AES/GCM/NoPadding";

    public static final String ENCRYPT_MAIN_ALGORITHM = "AES";

}
