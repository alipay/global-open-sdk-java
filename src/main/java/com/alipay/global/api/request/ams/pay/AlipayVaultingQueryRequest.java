package com.alipay.global.api.request.ams.pay;

import com.alipay.global.api.model.constants.AntomPathConstants;
import com.alipay.global.api.request.AlipayRequest;
import com.alipay.global.api.response.ams.pay.AlipayVaultingQueryResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AlipayVaultingQueryRequest extends AlipayRequest<AlipayVaultingQueryResponse> {

    private String vaultingRequestId;

    public AlipayVaultingQueryRequest() {
        this.setPath(AntomPathConstants.INQUIRE_VAULTING_PATH);
    }

    @Override
    public Class<AlipayVaultingQueryResponse> getResponseClass() {
        return AlipayVaultingQueryResponse.class;
    }
}
