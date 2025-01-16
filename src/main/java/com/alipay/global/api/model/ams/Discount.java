package com.alipay.global.api.model.ams;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Discount {

    private String discountTag;
    private String discountName;
    private Amount savingsAmount;
    private Amount estimateSavingsAmount;

}
