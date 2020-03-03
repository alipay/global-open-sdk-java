package com.alipay.global.api.request;

import com.alibaba.fastjson.annotation.JSONField;
import com.alipay.global.api.response.AlipayResponse;

public abstract class AlipayRequest <T extends AlipayResponse>{
    /**
     * client id
     */
    @JSONField(serialize = false)
    private String clientId;
    @JSONField(serialize = false)
    private String path;
    @JSONField(serialize = false)
    private Integer keyVersion;
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

    public Integer getKeyVersion() {
        return keyVersion;
    }

    public void setKeyVersion(Integer keyVersion) {
        this.keyVersion = keyVersion;
    }

    public abstract String getHttpMethod();

    /**
     * 得到当前API的响应结果类型
     *
     * @return 响应类型
     */
    public abstract Class<T> getResponseClass();

}
