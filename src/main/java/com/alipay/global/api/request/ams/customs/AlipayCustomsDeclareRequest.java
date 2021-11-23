package com.alipay.global.api.request.ams.customs;


import com.alipay.global.api.model.ams.Amount;
import com.alipay.global.api.model.ams.Certificate;
import com.alipay.global.api.model.ams.CustomsInfo;
import com.alipay.global.api.model.ams.MerchantCustomsInfo;
import com.alipay.global.api.request.AlipayRequest;
import com.alipay.global.api.response.ams.customs.AlipayCustomsDeclareResponse;

public class AlipayCustomsDeclareRequest extends AlipayRequest<AlipayCustomsDeclareResponse> {



    private String              declarationRequestId;
    private String              paymentId;
    private Amount              declarationAmount;
    private CustomsInfo         customs;
    private MerchantCustomsInfo merchantCustomsInfo;
    private Boolean             splitOrder;
    private String              subOrderId;
    private Certificate         buyerCertificate;

    public String getDeclarationRequestId() {
        return declarationRequestId;
    }

    public void setDeclarationRequestId(String declarationRequestId) {
        this.declarationRequestId = declarationRequestId;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public Amount getDeclarationAmount() {
        return declarationAmount;
    }

    public void setDeclarationAmount(Amount declarationAmount) {
        this.declarationAmount = declarationAmount;
    }

    public CustomsInfo getCustoms() {
        return customs;
    }

    public void setCustoms(CustomsInfo customs) {
        this.customs = customs;
    }

    public MerchantCustomsInfo getMerchantCustomsInfo() {
        return merchantCustomsInfo;
    }

    public void setMerchantCustomsInfo(MerchantCustomsInfo merchantCustomsInfo) {
        this.merchantCustomsInfo = merchantCustomsInfo;
    }

    public Boolean getSplitOrder() {
        return splitOrder;
    }

    public void setSplitOrder(Boolean splitOrder) {
        this.splitOrder = splitOrder;
    }

    public String getSubOrderId() {
        return subOrderId;
    }

    public void setSubOrderId(String subOrderId) {
        this.subOrderId = subOrderId;
    }

    public Certificate getBuyerCertificate() {
        return buyerCertificate;
    }

    public void setBuyerCertificate(Certificate buyerCertificate) {
        this.buyerCertificate = buyerCertificate;
    }

    @Override
    public Class<AlipayCustomsDeclareResponse> getResponseClass() {
        return AlipayCustomsDeclareResponse.class;
    }
}
