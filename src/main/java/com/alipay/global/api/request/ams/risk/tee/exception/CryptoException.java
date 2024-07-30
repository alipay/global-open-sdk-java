/*
 * Ant Group
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.alipay.global.api.request.ams.risk.tee.exception;

import com.alipay.global.api.request.ams.risk.tee.enums.ErrorCodeEnum;
import org.apache.commons.lang3.StringUtils;

/**
 * CryptoException that may occur during crypto operations
 * 加密操作期间可能发生的 CryptoException
 */
public class CryptoException extends RuntimeException {

    private static final long serialVersionUID = 4032315964590816437L;

    private ErrorCodeEnum errorCode;

    public CryptoException(ErrorCodeEnum errorCode, Throwable e) {
        super(getMessage(e), e);
        setErrorCode(errorCode);
    }

    public CryptoException(ErrorCodeEnum errorCode, String message) {
        super(message);
        setErrorCode(errorCode);
    }

    public CryptoException(ErrorCodeEnum errorCode, String message, Throwable throwable) {
        super(message, throwable);
        setErrorCode(errorCode);
    }

    private static String getMessage(Throwable e) {
        if (null == e) {
            return StringUtils.EMPTY;
        }
        return String.format("%s: %s", e.getClass().getSimpleName(), e.getMessage());
    }

    public ErrorCodeEnum getErrorCode() {
        return errorCode;
    }

    private void setErrorCode(ErrorCodeEnum errorCode) {
        this.errorCode = errorCode;
    }
}