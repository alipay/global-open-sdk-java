package com.alipay.global.api.model.ams;

import java.util.Map;

public class PaymentMethod {
    private String             paymentMethodType;
    private String             paymentMethodId;
    private Map<String,Object> paymentMethodMetaData;
    private String             customerId;
    private String             extendInfo;

    public String getPaymentMethodType() {
        return paymentMethodType;
    }

    public void setPaymentMethodType(String paymentMethodType) {
        this.paymentMethodType = paymentMethodType;
    }

    public String getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(String paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    public Map<String, Object> getPaymentMethodMetaData() {
        return paymentMethodMetaData;
    }

    public void setPaymentMethodMetaData(Map<String, Object> paymentMethodMetaData) {
        this.paymentMethodMetaData = paymentMethodMetaData;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getExtendInfo() {
        return extendInfo;
    }

    public void setExtendInfo(String extendInfo) {
        this.extendInfo = extendInfo;
    }
}
