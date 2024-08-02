package com.alipay.global.api.model.ams;

public enum CaptureModeType {
    /**
     * AUTOMATIC: indicates that Alipay+ automatically captures the funds after the authorization. The same applies when the value is empty or you do not pass in this parameter.
     * MANUAL: indicates that you manually capture the funds by calling the capture API.
     */
    AUTOMATIC, MANUAL;
}
