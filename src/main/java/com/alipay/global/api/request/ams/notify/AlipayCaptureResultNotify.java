package com.alipay.global.api.request.ams.notify;

import com.alipay.global.api.model.ams.AcquirerInfo;
import com.alipay.global.api.model.ams.Amount;

public class AlipayCaptureResultNotify extends AlipayNotify{

    /**
     * The unique ID that is assigned by the merchant to identify a capture request
     */
    private String captureRequestId;

    /**
     * The unique ID that is assigned by Alipay to identify a payment
     */
    private String paymentId;

    /**
     * The unique ID assigned by Alipay to identify a capture
     */
    private String captureId;

    /**
     * The capture amount that the merchant requests to receive in the transaction currency
     */
    private Amount captureAmount;

    /**
     * The time when Alipay captures the payment
     */
    private String captureTime;

    /**
     * The unique ID assigned by the non-Alipay acquirer for the transaction
     */
    private String acquirerReferenceNo;

    /**
     * The information of the acquirer that processes the payment.
     */
    private AcquirerInfo acquirerInfo;


    public String getCaptureRequestId() {
        return captureRequestId;
    }

    public void setCaptureRequestId(String captureRequestId) {
        this.captureRequestId = captureRequestId;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public Amount getCaptureAmount() {
        return captureAmount;
    }

    public void setCaptureAmount(Amount captureAmount) {
        this.captureAmount = captureAmount;
    }

    public String getCaptureId() {
        return captureId;
    }

    public void setCaptureId(String captureId) {
        this.captureId = captureId;
    }

    public String getCaptureTime() {
        return captureTime;
    }

    public void setCaptureTime(String captureTime) {
        this.captureTime = captureTime;
    }

    public String getAcquirerReferenceNo() {
        return acquirerReferenceNo;
    }

    public void setAcquirerReferenceNo(String acquirerReferenceNo) {
        this.acquirerReferenceNo = acquirerReferenceNo;
    }

    public AcquirerInfo getAcquirerInfo() {
        return acquirerInfo;
    }

    public void setAcquirerInfo(AcquirerInfo acquirerInfo) {
        this.acquirerInfo = acquirerInfo;
    }
}
