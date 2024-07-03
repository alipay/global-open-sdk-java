package com.alipay.global.api.request.ams.pay;


import com.alipay.global.api.request.AlipayRequest;
import com.alipay.global.api.response.ams.pay.AlipayInquiryRefundResponse;

public class AlipayInquiryRefundRequest extends AlipayRequest<AlipayInquiryRefundResponse> {

    /**
     * The unique ID assigned by a merchant to identify a refund request
     */
    private String refundRequestId;

    /**
     * The unique ID assigned by Alipay to identify a refund
     */
    private String refundId;

    /**
     * The unique ID to identify a merchant account.
     */
    private String merchantAccountId;

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

    public String getMerchantAccountId() { return merchantAccountId; }

    public void setMerchantAccountId(String merchantAccountId) { this.merchantAccountId = merchantAccountId; }

    @Override
    public Class getResponseClass() {
        return AlipayInquiryRefundResponse.class;
    }
}
