package com.alipay.global.api.request.ams.pay;


import com.alipay.global.api.request.AlipayRequest;
import com.alipay.global.api.response.ams.pay.AlipayInquiryRefundResponse;

public class AlipayInquiryRefundRequest extends AlipayRequest<AlipayInquiryRefundResponse> {

    private String refundRequestId;

    private String refundId;

    public String getRefundRequestId() {
        return refundRequestId;
    }

    public void setRefundRequestId(String refundRequestId) {
        this.refundRequestId = refundRequestId;
    }

    public String getRefundId() {
        return refundId;
    }

    public void setRefundId(String refundId) {
        this.refundId = refundId;
    }

    @Override
    public Class getResponseClass() {
        return AlipayInquiryRefundResponse.class;
    }
}
