package com.alipay.global.api.response.ams.risk;

import java.util.List;

import com.alipay.global.api.model.ams.RiskScoreResult;
import com.alipay.global.api.response.AlipayResponse;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Deprecated
public class AlipayRiskScoreInquiryResponse extends AlipayResponse {

    private List<RiskScoreResult> riskScoreResults;

}
