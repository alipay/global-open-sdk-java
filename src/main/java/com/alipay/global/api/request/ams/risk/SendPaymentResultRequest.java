/*
 * Ant Group
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package com.alipay.global.api.request.ams.risk;

import com.alipay.global.api.model.ams.CardVerificationResult;
import com.alipay.global.api.request.AlipayRequest;
import com.alipay.global.api.response.ams.risk.SendPaymentResultResponse;

/**
 * The request of Ant Group's send payment result API.
 * 调用蚂蚁集团支付结果通知接口的请求参数。
 */
public class SendPaymentResultRequest extends AlipayRequest<SendPaymentResultResponse> {
    /**
     * A unique ID assigned to a merchant who provides a service or product directly to a customer and is used to identify the transaction.
     * 直接向买家提供服务或商品的商户分配的唯一ID，用于识别交易。
     */
    private String                 referenceTransactionId;
    /**
     * Payment result status. Valid values are as follows:
     * SUCCESS: indicates that the payment is successful.
     * FAIL: indicates that the payment fails.
     * 支付结果状态。有效值为：
     * SUCCESS：表示支付成功。
     * FAIL：表示支付失败。
     */
    private String                 paymentStatus;
    /**
     * The reason why the card payment authorization provided by the payment method provider failed.
     * 支付方式提供商提供的卡支付授权失败的原因。
     */
    private Error                  authorizationError;
    /**
     * The result of the verification of the card payment method.
     * 卡支付方式的验证结果。
     */
    private CardVerificationResult cardVerificationResult;
    /**
     * Payment method providers.
     * 支付方式提供商。
     */
    private String                 paymentMethodProvider;

    public String getReferenceTransactionId() {
        return referenceTransactionId;
    }

    public void setReferenceTransactionId(String referenceTransactionId) {
        this.referenceTransactionId = referenceTransactionId;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Error getAuthorizationError() {
        return authorizationError;
    }

    public void setAuthorizationError(Error authorizationError) {
        this.authorizationError = authorizationError;
    }

    public CardVerificationResult getCardVerificationResult() {
        return cardVerificationResult;
    }

    public void setCardVerificationResult(CardVerificationResult cardVerificationResult) {
        this.cardVerificationResult = cardVerificationResult;
    }

    public String getPaymentMethodProvider() {
        return paymentMethodProvider;
    }

    public void setPaymentMethodProvider(String paymentMethodProvider) {
        this.paymentMethodProvider = paymentMethodProvider;
    }

    @Override
    public Class<SendPaymentResultResponse> getResponseClass() {
        return SendPaymentResultResponse.class;
    }
}