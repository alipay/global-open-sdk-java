package com.alipay.global.api.request.pay;

import com.alibaba.fastjson.annotation.JSONField;
import com.alipay.global.api.model.Amount;
import com.alipay.global.api.net.HttpMethod;
import com.alipay.global.api.request.AlipayRequest;
import com.alipay.global.api.response.pay.AlipayCaptureResponse;

public class AlipayCaptureRequest extends AlipayRequest<AlipayCaptureResponse> {

    @JSONField(serialize = false)
    private final static String httpMethod = HttpMethod.POST.name();

    private String  paymentRequestId;
    private String  authPaymentId;
    private String  referenceCaptureId;
    private Amount  paymentAmount;
    private Boolean isLastCapture;
    private String  extendInfo;

    public String getPaymentRequestId() {
        return paymentRequestId;
    }

    public void setPaymentRequestId(String paymentRequestId) {
        this.paymentRequestId = paymentRequestId;
    }

    public String getAuthPaymentId() {
        return authPaymentId;
    }

    public void setAuthPaymentId(String authPaymentId) {
        this.authPaymentId = authPaymentId;
    }

    public String getReferenceCaptureId() {
        return referenceCaptureId;
    }

    public void setReferenceCaptureId(String referenceCaptureId) {
        this.referenceCaptureId = referenceCaptureId;
    }

    public Amount getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(Amount paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public Boolean getLastCapture() {
        return isLastCapture;
    }

    public void setLastCapture(Boolean lastCapture) {
        isLastCapture = lastCapture;
    }

    public String getExtendInfo() {
        return extendInfo;
    }

    public void setExtendInfo(String extendInfo) {
        this.extendInfo = extendInfo;
    }

    @Override
    public String getHttpMethod() {
        return httpMethod;
    }

    @Override
    public Class<AlipayCaptureResponse> getResponseClass() {
        return AlipayCaptureResponse.class;
    }
}
