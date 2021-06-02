package com.alipay.global.api.response.ams.users;

import com.alipay.global.api.response.AlipayResponse;

public class AlipayUserQueryInfoResponse extends AlipayResponse {

    private String userId;
    private String userLoginId;
    private String hashUserLoginId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserLoginId() {
        return userLoginId;
    }

    public void setUserLoginId(String userLoginId) {
        this.userLoginId = userLoginId;
    }

    public String getHashUserLoginId() {
        return hashUserLoginId;
    }

    public void setHashUserLoginId(String hashUserLoginId) {
        this.hashUserLoginId = hashUserLoginId;
    }

}
