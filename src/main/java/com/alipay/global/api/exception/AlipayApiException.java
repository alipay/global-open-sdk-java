package com.alipay.global.api.exception;

public class AlipayApiException extends Exception {

    private static final long serialVersionUID = 6341441272448887609L;

    private String errCode;
    private String errMsg;

    public AlipayApiException() {
        super();
    }

    public AlipayApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public AlipayApiException(String message) {
        super(message);
    }

    public AlipayApiException(Throwable cause) {
        super(cause);
    }

    public AlipayApiException(String errCode, String errMsg) {
        super(errCode + ":" + errMsg);
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public String getErrCode() {
        return this.errCode;
    }

    public String getErrMsg() {
        return this.errMsg;
    }

}
