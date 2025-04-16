package com.alipay.global.api.request.ams.auth;

import com.alipay.global.api.model.ams.CustomerBelongsTo;
import com.alipay.global.api.model.ams.GrantType;
import com.alipay.global.api.model.constants.AntomPathConstants;
import com.alipay.global.api.request.AlipayRequest;
import com.alipay.global.api.response.ams.auth.AlipayAuthApplyTokenResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = true)
public class AlipayAuthApplyTokenRequest extends AlipayRequest<AlipayAuthApplyTokenResponse> {

    private GrantType grantType;
    private CustomerBelongsTo customerBelongsTo;
    private String authCode;
    private String refreshToken;
    private String extendInfo;
    private String merchantRegion;

    public AlipayAuthApplyTokenRequest() {
        this.setPath(AntomPathConstants.AUTH_APPLY_TOKEN_PATH);
    }

    @Override
    public Class<AlipayAuthApplyTokenResponse> getResponseClass() {
        return AlipayAuthApplyTokenResponse.class;
    }

}
