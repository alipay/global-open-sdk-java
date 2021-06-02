package com.alipay.global.api.response.aps.pay;

import com.alipay.global.api.response.AlipayResponse;

public class AlipayApsCancelPaymentResponse extends AlipayResponse {

    private String pspId;
    private String acquirerId;

    public String getPspId() {
        return pspId;
    }

    public void setPspId(String pspId) {
        this.pspId = pspId;
    }

    public String getAcquirerId() {
        return acquirerId;
    }

    public void setAcquirerId(String acquirerId) {
        this.acquirerId = acquirerId;
    }

}
