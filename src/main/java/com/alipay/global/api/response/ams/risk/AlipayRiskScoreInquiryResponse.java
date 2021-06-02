package com.alipay.global.api.response.ams.risk;

import com.alipay.global.api.model.ams.RiskScoreResult;
import com.alipay.global.api.response.AlipayResponse;

import java.util.List;

public class AlipayRiskScoreInquiryResponse extends AlipayResponse {

    private List<RiskScoreResult> riskScoreResults;

    public List<RiskScoreResult> getRiskScoreResults() {
        return riskScoreResults;
    }

    public void setRiskScoreResults(List<RiskScoreResult> riskScoreResults) {
        this.riskScoreResults = riskScoreResults;
    }

}
