package com.alipay.global.api.model.ams;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VaultingPaymentMethodDetail {
    private String paymentMethodType;
    private VaultingCard card;
    private Wallet wallet;
}