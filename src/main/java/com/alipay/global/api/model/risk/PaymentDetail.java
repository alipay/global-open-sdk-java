/*
 * Ant Group
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package com.alipay.global.api.model.risk;

import com.alipay.global.api.model.ams.Amount;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDetail {
    /**
     * The amount paid using this payment method.
     * 使用此支付方式的支付金额
     */
    private Amount amount;
    /**
     * The payment method used by the merchant or acquirer to collect payments.
     * 商户或收单机构用于收取款项的支付方式。
     */
    private PaymentMethod paymentMethod;

}