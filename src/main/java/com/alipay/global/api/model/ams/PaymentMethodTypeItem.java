package com.alipay.global.api.model.ams;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentMethodTypeItem {
    private String paymentMethodType;
    private int paymentMethodOrder;
    private boolean expressCheckout;
}
