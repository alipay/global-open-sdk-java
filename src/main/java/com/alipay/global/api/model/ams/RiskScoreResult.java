package com.alipay.global.api.model.ams;

import java.math.BigDecimal;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RiskScoreResult {

  private RiskScoreType riskScoreType;
  private BigDecimal riskScore;
  private List<RiskScoreDetail> riskScoreDetails;
}
