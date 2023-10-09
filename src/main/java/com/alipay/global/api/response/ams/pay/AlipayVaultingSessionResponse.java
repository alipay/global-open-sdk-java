package com.alipay.global.api.response.ams.pay;

import com.alipay.global.api.model.Result;

public class AlipayVaultingSessionResponse {
    private Result result;
    private String vaultingSessionData;
    private String vaultingSessionId;
    private String vaultingSessionExpiryTime;

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public String getVaultingSessionData() {
        return vaultingSessionData;
    }

    public void setVaultingSessionData(String vaultingSessionData) {
        this.vaultingSessionData = vaultingSessionData;
    }

    public String getVaultingSessionId() {
        return vaultingSessionId;
    }

    public void setVaultingSessionId(String vaultingSessionId) {
        this.vaultingSessionId = vaultingSessionId;
    }

    public String getVaultingSessionExpiryTime() {
        return vaultingSessionExpiryTime;
    }

    public void setVaultingSessionExpiryTime(String vaultingSessionExpiryTime) {
        this.vaultingSessionExpiryTime = vaultingSessionExpiryTime;
    }
}