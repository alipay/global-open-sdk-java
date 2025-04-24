package com.alipay.global.api.model.ams;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AvailablePaymentMethod {
    private List<PaymentMethodTypeItem> paymentMethodTypeList;
    private Map<String,Object> paymentMethodMetaData;
}
