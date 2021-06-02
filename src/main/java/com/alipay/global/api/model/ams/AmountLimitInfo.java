package com.alipay.global.api.model.ams;

public class AmountLimitInfo {

    private AmountLimit singleLimit;
    private AmountLimit dayLimit;
    private AmountLimit monthLimit;

    public AmountLimit getSingleLimit() {
        return singleLimit;
    }

    public void setSingleLimit(AmountLimit singleLimit) {
        this.singleLimit = singleLimit;
    }

    public AmountLimit getDayLimit() {
        return dayLimit;
    }

    public void setDayLimit(AmountLimit dayLimit) {
        this.dayLimit = dayLimit;
    }

    public AmountLimit getMonthLimit() {
        return monthLimit;
    }

    public void setMonthLimit(AmountLimit monthLimit) {
        this.monthLimit = monthLimit;
    }


}
