/*
 * Ant Group
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package com.alipay.global.api.model.risk;

import com.alipay.global.api.model.ams.Amount;

/**
 * Payment method details, including payment method type, card information, etc.
 * 支付详细信息，包括支付方式，卡信息等
 */
public class PaymentDetail {
    /**
     * The amount paid using this payment method.
     * 使用此支付方式的支付金额
     */
    private Amount        amount;
    /**
     * The payment method used by the merchant or acquirer to collect payments.
     * 商户或收单机构用于收取款项的支付方式。
     */
    private PaymentMethod paymentMethod;

    public Amount getAmount() {
        return amount;
    }

    public void setAmount(Amount amount) {
        this.amount = amount;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}