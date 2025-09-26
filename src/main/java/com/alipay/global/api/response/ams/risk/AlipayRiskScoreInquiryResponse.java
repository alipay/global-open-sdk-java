package com.alipay.global.api.response.ams.risk;

import com.alipay.global.api.model.ams.RiskScoreResult;
import com.alipay.global.api.response.AlipayResponse;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Deprecated
public class AlipayRiskScoreInquiryResponse extends AlipayResponse {

  private List<RiskScoreResult> riskScoreResults;
}
