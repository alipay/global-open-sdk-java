/*
 * Ant Group
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package com.alipay.global.api.model.ams;

public class AcquirerInfo {

    private String acquirerName;

    private String referenceRequestId;

    private String acquirerTransactionId;

    private String acquirerMerchantId;

    private String acquirerResultCode;

    private String acquirerResultMessage;

    public String getAcquirerName() {
        return acquirerName;
    }

    public void setAcquirerName(String acquirerName) {
        this.acquirerName = acquirerName;
    }

    public String getReferenceRequestId() {
        return referenceRequestId;
    }

    public void setReferenceRequestId(String referenceRequestId) {
        this.referenceRequestId = referenceRequestId;
    }

    public String getAcquirerTransactionId() {
        return acquirerTransactionId;
    }

    public void setAcquirerTransactionId(String acquirerTransactionId) {
        this.acquirerTransactionId = acquirerTransactionId;
    }

    public String getAcquirerMerchantId() {
        return acquirerMerchantId;
    }

    public void setAcquirerMerchantId(String acquirerMerchantId) {
        this.acquirerMerchantId = acquirerMerchantId;
    }

    public String getAcquirerResultCode() {
        return acquirerResultCode;
    }

    public void setAcquirerResultCode(String acquirerResultCode) {
        this.acquirerResultCode = acquirerResultCode;
    }

    public String getAcquirerResultMessage() {
        return acquirerResultMessage;
    }

    public void setAcquirerResultMessage(String acquirerResultMessage) {
        this.acquirerResultMessage = acquirerResultMessage;
    }
}