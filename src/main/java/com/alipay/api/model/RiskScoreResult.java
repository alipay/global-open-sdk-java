package com.alipay.api.model;

import java.math.BigDecimal;
import java.util.List;

public class RiskScoreResult {

    private RiskScoreType         riskScoreType;
    private BigDecimal            riskScore;
    private List<RiskScoreDetail> riskScoreDetails;

    public RiskScoreType getRiskScoreType() {
        return riskScoreType;
    }

    public void setRiskScoreType(RiskScoreType riskScoreType) {
        this.riskScoreType = riskScoreType;
    }

    public BigDecimal getRiskScore() {
        return riskScore;
    }

    public void setRiskScore(BigDecimal riskScore) {
        this.riskScore = riskScore;
    }

    public List<RiskScoreDetail> getRiskScoreDetails() {
        return riskScoreDetails;
    }

    public void setRiskScoreDetails(List<RiskScoreDetail> riskScoreDetails) {
        this.riskScoreDetails = riskScoreDetails;
    }

}
