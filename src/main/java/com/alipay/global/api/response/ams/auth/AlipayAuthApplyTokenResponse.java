package com.alipay.global.api.response.ams.auth;

import com.alipay.global.api.response.AlipayResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class AlipayAuthApplyTokenResponse extends AlipayResponse {

    private String accessToken;
    private String accessTokenExpiryTime;
    private String refreshToken;
    private String refreshTokenExpiryTime;
    private String extendInfo;
    private String userLoginId;

}
