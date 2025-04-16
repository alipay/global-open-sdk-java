package com.alipay.global.api.response.ams.auth;

import com.alipay.global.api.model.ams.PspCustomerInfo;
import com.alipay.global.api.response.AlipayResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AlipayAuthApplyTokenResponse extends AlipayResponse {

    private String accessToken;
    private String accessTokenExpiryTime;
    private String refreshToken;
    private String refreshTokenExpiryTime;
    private String extendInfo;
    private String userLoginId;
    private PspCustomerInfo pspCustomerInfo;

}
