package com.alipay.global.api.model.ams;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Trial {

    private Integer trialStartPeriod;

    private Amount trialAmount;

    private Integer trialEndPeriod;

}
