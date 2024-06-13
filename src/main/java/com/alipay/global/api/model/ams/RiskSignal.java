package com.alipay.global.api.model.ams;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RiskSignal {
    /**
     * The tag assigned by a merchant to a risky transaction.
     */
    private String riskCode;

    /**
     * The reason why a transaction is identified as risky provided by a merchant.
     */
    private String riskReason;

}