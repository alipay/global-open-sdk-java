package com.alipay.global.api.model.ams;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AvailablePaymentMethod {
    private List<PaymentMethodTypeItem> paymentMethodTypeList;
}
