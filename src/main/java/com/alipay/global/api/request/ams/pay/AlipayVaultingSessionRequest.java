package com.alipay.global.api.request.ams.pay;

import com.alipay.global.api.model.constants.AntomPathConstants;
import com.alipay.global.api.request.AlipayRequest;
import com.alipay.global.api.response.ams.pay.AlipayVaultingSessionResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class AlipayVaultingSessionRequest extends AlipayRequest<AlipayVaultingSessionResponse> {

    private String paymentMethodType;

    private String vaultingRequestId;

    private String vaultingNotificationUrl;

    private String redirectUrl;

    private String merchantRegion;

    public AlipayVaultingSessionRequest() {
        this.setPath(AntomPathConstants.CREATE_VAULTING_SESSION_PATH);
    }

    @Override
    public Class<AlipayVaultingSessionResponse> getResponseClass() {
        return AlipayVaultingSessionResponse.class;
    }
}