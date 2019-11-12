package com.alipay.api.request;

import com.alibaba.fastjson.annotation.JSONField;
import com.alipay.api.net.HttpMethod;
import com.alipay.api.response.AlipayResponse;

public abstract class AlipayRequest <T extends AlipayResponse>{
    /**
     * client id
     */
    @JSONField(serialize = false)
    private String clientId;
    @JSONField(serialize = false)
    private String path;
    @JSONField(serialize = false)
    private  Class<T> responseClass;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getPath(){
        return this.path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public abstract String getHttpMethod();

    /**
     * 得到当前API的响应结果类型
     *
     * @return 响应类型
     */
    public abstract Class<T> getResponseClass();

}
