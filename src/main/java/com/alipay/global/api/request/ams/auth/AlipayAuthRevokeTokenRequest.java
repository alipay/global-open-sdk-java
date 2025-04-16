package com.alipay.global.api.request.ams.auth;

import com.alipay.global.api.model.constants.AntomPathConstants;
import com.alipay.global.api.request.AlipayRequest;
import com.alipay.global.api.response.ams.auth.AlipayAuthRevokeTokenResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AlipayAuthRevokeTokenRequest extends AlipayRequest<AlipayAuthRevokeTokenResponse> {

    private String accessToken;
    private String extendInfo;

    public AlipayAuthRevokeTokenRequest() {
        this.setPath(AntomPathConstants.AUTH_REVOKE_PATH);
    }

    @Override
    public Class<AlipayAuthRevokeTokenResponse> getResponseClass() {
        return AlipayAuthRevokeTokenResponse.class;
    }

}
