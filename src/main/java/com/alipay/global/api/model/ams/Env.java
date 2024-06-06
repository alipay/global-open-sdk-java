package com.alipay.global.api.model.ams;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Env {

    private TerminalType terminalType;
    private OsType osType;
    private String userAgent;
    private String deviceTokenId;
    private String clientIp;
    private String cookieId;
    private String extendInfo;
    private String storeTerminalId;
    private String storeTerminalRequestTime;
    private BrowserInfo browserInfo;
    private String colorDepth;
    private String screenHeight;
    private String screenWidth;
    private Integer timeZoneOffset;
    private String deviceBrand;
    private String deviceModel;
    private String deviceLanguage;
    private String deviceId;

}
