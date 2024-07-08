package com.alipay.global.api.model.ams;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreditPayPlan {

    private int installmentNum;
    private String interval;
    private CreditPayFeeType creditPayFeeType;
    private int feePercentage;

}
