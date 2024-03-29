package com.alipay.global.api.model.ams;

import com.alipay.global.api.model.Result;

public class Transaction {

    private Result transactionResult;
    private String                transactionId;
    private TransactionType       transactionType;
    private TransactionStatusType transactionStatus;
    private Amount transactionAmount;
    private String                transactionRequestId;
    private String                transactionTime;

    private AcquirerInfo          acquirerInfo;

    public Result getTransactionResult() {
        return transactionResult;
    }

    public void setTransactionResult(Result transactionResult) {
        this.transactionResult = transactionResult;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public TransactionStatusType getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(TransactionStatusType transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public Amount getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(Amount transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public String getTransactionRequestId() {
        return transactionRequestId;
    }

    public void setTransactionRequestId(String transactionRequestId) {
        this.transactionRequestId = transactionRequestId;
    }

    public String getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(String transactionTime) {
        this.transactionTime = transactionTime;
    }

    public AcquirerInfo getAcquirerInfo() {
        return acquirerInfo;
    }

    public void setAcquirerInfo(AcquirerInfo acquirerInfo) {
        this.acquirerInfo = acquirerInfo;
    }
}
