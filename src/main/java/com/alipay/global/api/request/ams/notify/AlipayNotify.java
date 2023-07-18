package com.alipay.global.api.request.ams.notify;

import com.alipay.global.api.model.Result;
public class AlipayNotify {

    private String notifyType;

    private Result result;

    public String getNotifyType() {
        return notifyType;
    }

    public void setNotifyType(String notifyType) {
        this.notifyType = notifyType;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }
}
