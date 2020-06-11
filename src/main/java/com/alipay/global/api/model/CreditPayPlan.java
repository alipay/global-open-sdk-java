package com.alipay.global.api.model;

public class CreditPayPlan {

    private int installmentNum;
    private CreditPayFeeType creditPayFeeType;
    private int feePercentage ;

    public int getInstallmentNum() {
        return installmentNum;
    }

    public void setInstallmentNum(int installmentNum) {
        this.installmentNum = installmentNum;
    }

    public CreditPayFeeType getCreditPayFeeType() {
        return creditPayFeeType;
    }

    public void setCreditPayFeeType(CreditPayFeeType creditPayFeeType) {
        this.creditPayFeeType = creditPayFeeType;
    }

    public int getFeePercentage() {
        return feePercentage;
    }

    public void setFeePercentage(int feePercentage) {
        this.feePercentage = feePercentage;
    }


}
