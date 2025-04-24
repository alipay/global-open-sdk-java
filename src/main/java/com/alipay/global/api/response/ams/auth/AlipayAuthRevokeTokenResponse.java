package com.alipay.global.api.response.ams.auth;

import com.alipay.global.api.response.AlipayResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AlipayAuthRevokeTokenResponse extends AlipayResponse {

    private String extendInfo;

}
