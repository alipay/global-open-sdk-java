package com.alipay.global.api.response.ams.risk;

import com.alipay.global.api.model.ams.RiskScoreResult;
import com.alipay.global.api.response.AlipayResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Deprecated
public class AlipayRiskScoreInquiryResponse extends AlipayResponse {

    private List<RiskScoreResult> riskScoreResults;

}
