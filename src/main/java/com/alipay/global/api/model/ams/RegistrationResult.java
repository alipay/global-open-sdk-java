package com.alipay.global.api.model.ams;


public class RegistrationResult {

    private String registrationStatus;
    private String rejectReasons;


    public String getRegistrationStatus() {
        return registrationStatus;
    }

    public void setRegistrationStatus(String registrationStatus) {
        this.registrationStatus = registrationStatus;
    }

    public String getRejectReasons() {
        return rejectReasons;
    }

    public void setRejectReasons(String rejectReasons) {
        this.rejectReasons = rejectReasons;
    }
}
