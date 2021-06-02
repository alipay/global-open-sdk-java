package com.alipay.global.api.model.aps;

public class PaymentFactor {

    private Boolean                isInStorePayment;
    private Boolean                isCashierPayment;
    private PresentmentMode        presentmentMode;
    private InStorePaymentScenario inStorePaymentScenario;

    public Boolean getIsInStorePayment() {
        return isInStorePayment;
    }

    public void setIsInStorePayment(Boolean inStorePayment) {
        isInStorePayment = inStorePayment;
    }

    public Boolean getIsCashierPayment() {
        return isCashierPayment;
    }

    public void setIsCashierPayment(Boolean cashierPayment) {
        isCashierPayment = cashierPayment;
    }

    public PresentmentMode getPresentmentMode() {
        return presentmentMode;
    }

    public void setPresentmentMode(PresentmentMode presentmentMode) {
        this.presentmentMode = presentmentMode;
    }

    public InStorePaymentScenario getInStorePaymentScenario() {
        return inStorePaymentScenario;
    }

    public void setInStorePaymentScenario(InStorePaymentScenario inStorePaymentScenario) {
        this.inStorePaymentScenario = inStorePaymentScenario;
    }

}
