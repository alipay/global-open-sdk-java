/*
 * Ant Group
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package com.alipay.global.api.model.risk;

/**
 * The reason why the card payment authorization provided by the payment method provider failed.
 * 支付方式提供商提供的卡支付授权失败的原因。
 */
public class AuthorizationError {
    /**
     * Authorization error code.
     * 授权错误码
     */
    private String errorCode;
    /**
     * Description of the Authorization error code.
     * 授权错误码的描述
     */
    private String errorMessage;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}