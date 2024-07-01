package com.alipay.global.api.model.aps;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentMethod {

    private String paymentMethodType;
    private String paymentMethodId;
    private String customerId;
    private PaymentMethodMetaData paymentMethodMetaData;

}
