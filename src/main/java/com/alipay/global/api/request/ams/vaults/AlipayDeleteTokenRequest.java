package com.alipay.global.api.request.ams.vaults;

import com.alipay.global.api.model.constants.AntomPathConstants;
import com.alipay.global.api.request.AlipayRequest;
import com.alipay.global.api.response.ams.vaults.AlipayDeleteTokenResponse;
import com.alipay.global.api.response.ams.vaults.AlipayUpdateTokenResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class AlipayDeleteTokenRequest  extends
        AlipayRequest<AlipayDeleteTokenResponse> {
    private String merchantAccountId;
    private String token;

    public AlipayDeleteTokenRequest() {
        this.setPath(AntomPathConstants.DELETE_TOKEN_VAULTING_PATH);
    }

    @Override
    public Class<AlipayDeleteTokenResponse> getResponseClass() {
        return AlipayDeleteTokenResponse.class;
    }
}
