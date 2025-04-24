package com.alipay.global.api.response.ams.auth;

import com.alipay.global.api.model.ams.AuthCodeForm;
import com.alipay.global.api.response.AlipayResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AlipayAuthConsultResponse extends AlipayResponse {

    private String authUrl;
    private String extendInfo;
    private String normalUrl;
    private String schemeUrl;
    private String applinkUrl;
    private String appIdentifier;
    private AuthCodeForm authCodeForm;

}
