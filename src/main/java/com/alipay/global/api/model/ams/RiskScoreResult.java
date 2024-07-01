package com.alipay.global.api.model.ams;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RiskScoreResult {

    private RiskScoreType riskScoreType;
    private BigDecimal riskScore;
    private List<RiskScoreDetail> riskScoreDetails;

}
