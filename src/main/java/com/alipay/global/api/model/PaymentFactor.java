package com.alipay.global.api.model;

public class PaymentFactor {

    private Boolean                isPaymentEvaluation;
    private InStorePaymentScenario inStorePaymentScenario;

    public Boolean getIsPaymentEvaluation() {
        return isPaymentEvaluation;
    }

    public void setIsPaymentEvaluation(Boolean isPaymentEvaluation) {
        this.isPaymentEvaluation = isPaymentEvaluation;
    }

    public InStorePaymentScenario getInStorePaymentScenario() {
        return inStorePaymentScenario;
    }

    public void setInStorePaymentScenario(InStorePaymentScenario inStorePaymentScenario) {
        this.inStorePaymentScenario = inStorePaymentScenario;
    }

}
