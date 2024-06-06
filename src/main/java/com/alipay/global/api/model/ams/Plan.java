package com.alipay.global.api.model.ams;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Plan {

    private String interestRate;

    private Amount minInstallmentAmount;

    private Amount maxInstallmentAmount;

    private String installmentNum;

    private String interval;

    private boolean enabled;

}
