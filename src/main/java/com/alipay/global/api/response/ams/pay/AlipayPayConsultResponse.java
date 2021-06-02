package com.alipay.global.api.response.ams.pay;

import com.alipay.global.api.model.ams.PaymentMethodInfo;
import com.alipay.global.api.model.ams.PaymentOption;
import com.alipay.global.api.response.AlipayResponse;

import java.util.List;

public class AlipayPayConsultResponse extends AlipayResponse {

    private List<PaymentOption>      paymentOptions;
    private List<PaymentMethodInfo>  paymentMethodInfos;
    private String                   extendInfo;

    public List<PaymentOption> getPaymentOptions() {
        return paymentOptions;
    }

    public void setPaymentOptions(List<PaymentOption> paymentOptions) {
        this.paymentOptions = paymentOptions;
    }

    public List<PaymentMethodInfo> getPaymentMethodInfos() {
        return paymentMethodInfos;
    }

    public void setPaymentMethodInfos(List<PaymentMethodInfo> paymentMethodInfos) {
        this.paymentMethodInfos = paymentMethodInfos;
    }

    public String getExtendInfo() {
        return extendInfo;
    }

    public void setExtendInfo(String extendInfo) {
        this.extendInfo = extendInfo;
    }

}
