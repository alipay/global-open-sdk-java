package com.alipay.global.api.model;

public class Result {

    private String           resultCode;
    private ResultStatusType resultStatus;
    private String           resultMessage;

    public Result(String resultCode, String resultMessage,ResultStatusType resultStatus){
        this.resultCode = resultCode;
        this.resultMessage = resultMessage;
        this.resultStatus = resultStatus;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public ResultStatusType getResultStatus() {
        return resultStatus;
    }

    public void setResultStatus(ResultStatusType resultStatus) {
        this.resultStatus = resultStatus;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

}
