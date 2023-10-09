package com.alipay.global.api.model.ams;

public class RiskData {
    /**
     * The order information used for risk control purposes.
     */
    private RiskOrder order;

    /**
     * The buyer information used for risk control purposes.
     */
    private RiskBuyer buyer;

    /**
     * The environment information used for risk control purposes.
     */
    private RiskEnv env;

    /**
     * The information provided by a merchant to identify a risky transaction.
     */
    private RiskSignal riskSignal;

    /**
     * The address information used for risk control purposes.
     */
    private RiskAddress address;

    /**
     * The verification method that a merchant uses for a card payment.
     */
    private CardVerificationResult cardVerificationResult;

    public RiskOrder getOrder() {
        return order;
    }

    public void setOrder(RiskOrder order) {
        this.order = order;
    }

    public RiskBuyer getBuyer() {
        return buyer;
    }

    public void setBuyer(RiskBuyer buyer) {
        this.buyer = buyer;
    }

    public RiskEnv getEnv() {
        return env;
    }

    public void setEnv(RiskEnv env) {
        this.env = env;
    }

    public RiskSignal getRiskSignal() {
        return riskSignal;
    }

    public void setRiskSignal(RiskSignal riskSignal) {
        this.riskSignal = riskSignal;
    }

    public RiskAddress getAddress() {
        return address;
    }

    public void setAddress(RiskAddress address) {
        this.address = address;
    }

    public CardVerificationResult getCardVerificationResult() {
        return cardVerificationResult;
    }

    public void setCardVerificationResult(CardVerificationResult cardVerificationResult) {
        this.cardVerificationResult = cardVerificationResult;
    }
}