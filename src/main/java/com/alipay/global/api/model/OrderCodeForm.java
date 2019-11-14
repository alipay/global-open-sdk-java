package com.alipay.global.api.model;

import java.util.List;

public class OrderCodeForm {

    private String           paymentMethodType;
    private String           expireTime;
    private List<CodeDetail> codeDetails;
    private String           extendInfo;

    public String getPaymentMethodType() {
        return paymentMethodType;
    }

    public void setPaymentMethodType(String paymentMethodType) {
        this.paymentMethodType = paymentMethodType;
    }

    public String getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(String expireTime) {
        this.expireTime = expireTime;
    }

    public List<CodeDetail> getCodeDetails() {
        return codeDetails;
    }

    public void setCodeDetails(List<CodeDetail> codeDetails) {
        this.codeDetails = codeDetails;
    }

    public String getExtendInfo() {
        return extendInfo;
    }

    public void setExtendInfo(String extendInfo) {
        this.extendInfo = extendInfo;
    }
}
