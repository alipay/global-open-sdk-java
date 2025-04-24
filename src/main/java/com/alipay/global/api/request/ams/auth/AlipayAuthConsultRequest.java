package com.alipay.global.api.request.ams.auth;

import com.alipay.global.api.model.ams.*;
import com.alipay.global.api.model.constants.AntomPathConstants;
import com.alipay.global.api.request.AlipayRequest;
import com.alipay.global.api.response.ams.auth.AlipayAuthConsultResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = true)
public class AlipayAuthConsultRequest extends AlipayRequest<AlipayAuthConsultResponse> {

    private CustomerBelongsTo customerBelongsTo;
    private String authClientId;
    private String authRedirectUrl;
    private ScopeType[] scopes;
    private String authState;
    private TerminalType terminalType;
    private OsType osType;
    private String osVersion;
    private String extendInfo;
    private String merchantRegion;
    private Boolean recurringPayment;
    private AuthMetaData authMetaData;

    public AlipayAuthConsultRequest() {
        this.setPath(AntomPathConstants.AUTH_CONSULT_PATH);
    }

    @Override
    public Class<AlipayAuthConsultResponse> getResponseClass() {
        return AlipayAuthConsultResponse.class;
    }

}
