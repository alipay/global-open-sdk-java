package com.alipay.global.api.model.ams;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentMethodInfo {

    private String paymentMethodType;
    private String paymentMethodDetail;
    private boolean enabled;
    private boolean preferred;
    private String extendInfo;

}
