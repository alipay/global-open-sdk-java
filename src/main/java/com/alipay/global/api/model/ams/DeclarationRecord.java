package com.alipay.global.api.model.ams;


public class DeclarationRecord {

    private String              declarationRequestId;
    private String              customsPaymentId;
    private String              customsOrderId;
    private CustomsInfo         customs;
    private MerchantCustomsInfo merchantCustomsInfo;
    private Amount              declarationAmount;
    private Boolean             splitOrder;
    private String              declarationRequestStatus;
    private String              lastModifiedTime;
    private String              customsDeclarationResultCode;
    private String              customsDeclarationResultDesc;
    private String              customsDeclarationReturnTime;

    public String getDeclarationRequestId() {
        return declarationRequestId;
    }

    public void setDeclarationRequestId(String declarationRequestId) {
        this.declarationRequestId = declarationRequestId;
    }

    public String getCustomsPaymentId() {
        return customsPaymentId;
    }

    public void setCustomsPaymentId(String customsPaymentId) {
        this.customsPaymentId = customsPaymentId;
    }

    public String getCustomsOrderId() {
        return customsOrderId;
    }

    public void setCustomsOrderId(String customsOrderId) {
        this.customsOrderId = customsOrderId;
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

    public Amount getDeclarationAmount() {
        return declarationAmount;
    }

    public void setDeclarationAmount(Amount declarationAmount) {
        this.declarationAmount = declarationAmount;
    }

    public Boolean getSplitOrder() {
        return splitOrder;
    }

    public void setSplitOrder(Boolean splitOrder) {
        this.splitOrder = splitOrder;
    }

    public String getDeclarationRequestStatus() {
        return declarationRequestStatus;
    }

    public void setDeclarationRequestStatus(String declarationRequestStatus) {
        this.declarationRequestStatus = declarationRequestStatus;
    }

    public String getLastModifiedTime() {
        return lastModifiedTime;
    }

    public void setLastModifiedTime(String lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }

    public String getCustomsDeclarationResultCode() {
        return customsDeclarationResultCode;
    }

    public void setCustomsDeclarationResultCode(String customsDeclarationResultCode) {
        this.customsDeclarationResultCode = customsDeclarationResultCode;
    }

    public String getCustomsDeclarationResultDesc() {
        return customsDeclarationResultDesc;
    }

    public void setCustomsDeclarationResultDesc(String customsDeclarationResultDesc) {
        this.customsDeclarationResultDesc = customsDeclarationResultDesc;
    }

    public String getCustomsDeclarationReturnTime() {
        return customsDeclarationReturnTime;
    }

    public void setCustomsDeclarationReturnTime(String customsDeclarationReturnTime) {
        this.customsDeclarationReturnTime = customsDeclarationReturnTime;
    }
}
