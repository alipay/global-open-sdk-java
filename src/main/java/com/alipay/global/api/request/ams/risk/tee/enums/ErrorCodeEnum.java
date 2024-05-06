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
package com.alipay.global.api.request.ams.risk.tee.enums;

public enum ErrorCodeEnum {

    UNKNOWN_ERROR("UNKNOWN_ERROR", "unknown error"),

    PARAM_ILLEGAL("PARAM_ILLEGAL", "param illegal"),

    ENCRYPT_ERROR("ENCRYPT_ERROR", "encrypt error"),

    UNKNOWN_ENCRYPT_KEY("UNKNOWN_ENCRYPT_KEY", "unknown encrypt key"),

    MISMATCH_ENCRYPT_STRATEGY("MISMATCH_ENCRYPT_STRATEGY","mismatch encrypt strategy"),

    ;

    private final String code;

    private final String description;

    ErrorCodeEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public static ErrorCodeEnum getEnumByCode(String code) {

        for (ErrorCodeEnum codeEnum : values()) {
            if (codeEnum.getCode().equals(code)) {
                return codeEnum;
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() { return description; }
}