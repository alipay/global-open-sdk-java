package com.alipay.global.api.model.ams;

/**
 * @Author yanhong
 * @version $Id: Trial.java, v 0.1 2024年03月19日 3:49 PM yanhong Exp $
 **/
public class Trial {

    private Integer trialStartPeriod;

    private Amount trialAmount;

    private Integer trialEndPeriod;

    public Integer getTrialStartPeriod() {
        return trialStartPeriod;
    }

    public void setTrialStartPeriod(Integer trialStartPeriod) {
        this.trialStartPeriod = trialStartPeriod;
    }

    public Amount getTrialAmount() {
        return trialAmount;
    }

    public void setTrialAmount(Amount trialAmount) {
        this.trialAmount = trialAmount;
    }

    public Integer getTrialEndPeriod() {
        return trialEndPeriod;
    }

    public void setTrialEndPeriod(Integer trialEndPeriod) {
        this.trialEndPeriod = trialEndPeriod;
    }
}
