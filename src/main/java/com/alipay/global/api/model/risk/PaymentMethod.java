/*
 * Ant Group
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package com.alipay.global.api.model.risk;

/**
 * The payment method used by the merchant or acquirer to collect payments.
 * 商户或收单机构用于收取款项的支付方式。
 */
public class PaymentMethod {
    /**
     * The type of payment method included in the payment method options
     * 支付方式选项中包含的支付方式类型
     */
    private String                paymentMethodType;
    /**
     * A unique ID used to identify the payment method
     * 用于识别支付方式的唯一ID
     */
    private String                paymentMethodId;
    /**
     * More information about the card.
     * 有关该卡的详细信息。
     */
    private PaymentMethodMetaData paymentMethodMetaData;

    public String getPaymentMethodType() {
        return paymentMethodType;
    }

    public void setPaymentMethodType(String paymentMethodType) {
        this.paymentMethodType = paymentMethodType;
    }

    public String getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(String paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    public PaymentMethodMetaData getPaymentMethodMetaData() {
        return paymentMethodMetaData;
    }

    public void setPaymentMethodMetaData(PaymentMethodMetaData paymentMethodMetaData) {
        this.paymentMethodMetaData = paymentMethodMetaData;
    }
}