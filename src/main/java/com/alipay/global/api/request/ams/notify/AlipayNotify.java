package com.alipay.global.api.request.ams.notify;

import com.alipay.global.api.model.Result;

/**
 * @Author yanhong
 * @version $Id: AlipayNotify.java, v 0.1 2023年07月05日 2:45 PM yanhong Exp $
 **/
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
