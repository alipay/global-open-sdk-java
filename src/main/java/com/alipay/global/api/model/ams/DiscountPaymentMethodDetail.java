package com.alipay.global.api.model.ams;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DiscountPaymentMethodDetail {

    private String discountId;
    private Amount availableAmount;
    private String discountName;
    private String discountDescription;
    private String paymentMethodDetailMetadata;

}
