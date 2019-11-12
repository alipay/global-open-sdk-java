package com.alipay.api.model;

public class DiscountPaymentMethodDetail {

    private String    discountId;
    private Amount    availableAmount;
    private String    discountName;
    private String    discountDescription;
    private String    paymentMethodDetailMetadata;

    public String getDiscountId() {
        return discountId;
    }

    public void setDiscountId(String discountId) {
        this.discountId = discountId;
    }

    public Amount getAvailableAmount() {
        return availableAmount;
    }

    public void setAvailableAmount(Amount availableAmount) {
        this.availableAmount = availableAmount;
    }

    public String getDiscountName() {
        return discountName;
    }

    public void setDiscountName(String discountName) {
        this.discountName = discountName;
    }

    public String getDiscountDescription() {
        return discountDescription;
    }

    public void setDiscountDescription(String discountDescription) {
        this.discountDescription = discountDescription;
    }

    public String getPaymentMethodDetailMetadata() {
        return paymentMethodDetailMetadata;
    }

    public void setPaymentMethodDetailMetadata(String paymentMethodDetailMetadata) {
        this.paymentMethodDetailMetadata = paymentMethodDetailMetadata;
    }

}
