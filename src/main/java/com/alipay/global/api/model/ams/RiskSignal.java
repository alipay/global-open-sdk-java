package com.alipay.global.api.model.ams;

public class RiskSignal {
    /**
     * The tag assigned by a merchant to a risky transaction.
     */
    private String riskCode;

    /**
     * The reason why a transaction is identified as risky provided by a merchant.
     */
    private String riskReason;

    public String getRiskCode() {
        return riskCode;
    }

    public void setRiskCode(String riskCode) {
        this.riskCode = riskCode;
    }

    public String getRiskReason() {
        return riskReason;
    }

    public void setRiskReason(String riskReason) {
        this.riskReason = riskReason;
    }
}