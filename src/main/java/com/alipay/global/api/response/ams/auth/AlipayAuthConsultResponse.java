package com.alipay.global.api.response.ams.auth;

import com.alipay.global.api.response.AlipayResponse;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class AlipayAuthConsultResponse extends AlipayResponse {

    private String authUrl;
    private String extendInfo;
    private String normalUrl;
    private String schemeUrl;
    private String applinkUrl;
    private String appIdentifier;

}
