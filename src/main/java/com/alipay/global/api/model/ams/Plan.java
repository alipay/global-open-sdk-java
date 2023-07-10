package com.alipay.global.api.model.ams;

/**
 * @Author yanhong
 * @version $Id: Plan.java, v 0.1 2023年07月10日 8:43 PM yanhong Exp $
 **/
public class Plan {

    private String interestRate;

    private Amount minInstallmentAmount;

    private Amount maxInstallmentAmount;

    private String installmentNum;

    private String interval;

    private boolean enabled;

    public String getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(String interestRate) {
        this.interestRate = interestRate;
    }

    public Amount getMinInstallmentAmount() {
        return minInstallmentAmount;
    }

    public void setMinInstallmentAmount(Amount minInstallmentAmount) {
        this.minInstallmentAmount = minInstallmentAmount;
    }

    public Amount getMaxInstallmentAmount() {
        return maxInstallmentAmount;
    }

    public void setMaxInstallmentAmount(Amount maxInstallmentAmount) {
        this.maxInstallmentAmount = maxInstallmentAmount;
    }

    public String getInstallmentNum() {
        return installmentNum;
    }

    public void setInstallmentNum(String installmentNum) {
        this.installmentNum = installmentNum;
    }

    public String getInterval() {
        return interval;
    }

    public void setInterval(String interval) {
        this.interval = interval;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
