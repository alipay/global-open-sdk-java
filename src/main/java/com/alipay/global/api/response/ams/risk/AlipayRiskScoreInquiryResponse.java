package com.alipay.global.api.response.ams.risk;

import com.alipay.global.api.model.ams.RiskScoreResult;
import com.alipay.global.api.response.AlipayResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Deprecated
public class AlipayRiskScoreInquiryResponse extends AlipayResponse {

    private List<RiskScoreResult> riskScoreResults;

}
