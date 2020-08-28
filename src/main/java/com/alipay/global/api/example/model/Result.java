package com.alipay.global.api.example.model;

import com.alipay.global.api.model.ResultStatusType;

public class Result {

    /**
     * 结果码ID
     */
    private String resultCodeId;

    /**
     * 结果码
     */
    private String resultCode;

    /**
     * 状态(S/F/U)
     */
    private ResultStatusType resultStatus;

    /**
     * 返回结果描述
     */
    private String resultMessage;

    public String getResultCodeId() {
        return resultCodeId;
    }

    public void setResultCodeId(String resultCodeId) {
        this.resultCodeId = resultCodeId;
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

    public static Result ofResult(String resultCode, String resultMessage, ResultStatusType resultStatus){
        Result result = new Result();
        result.setResultCode(resultCode);
        result.setResultMessage(resultMessage);
        result.setResultStatus(resultStatus);

        return result;
    }

}
