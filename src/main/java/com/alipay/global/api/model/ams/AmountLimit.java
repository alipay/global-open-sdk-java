package com.alipay.global.api.model.ams;

public class AmountLimit {

    private Amount maxAmount;
    private Amount minAmount;
    private Amount remainAmount;

    public Amount getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(Amount maxAmount) {
        this.maxAmount = maxAmount;
    }

    public Amount getMinAmount() {
        return minAmount;
    }

    public void setMinAmount(Amount minAmount) {
        this.minAmount = minAmount;
    }

    public Amount getRemainAmount() {
        return remainAmount;
    }

    public void setRemainAmount(Amount remainAmount) {
        this.remainAmount = remainAmount;
    }


}
