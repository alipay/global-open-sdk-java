package com.alipay.global.api.request.ams.dispute;

import com.alipay.global.api.model.constants.AntomPathConstants;
import com.alipay.global.api.request.AlipayRequest;
import com.alipay.global.api.response.ams.dispute.AlipayAcceptDisputeResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AlipayAcceptDisputeRequest extends AlipayRequest<AlipayAcceptDisputeResponse> {

    private String disputeId;

    public AlipayAcceptDisputeRequest() {
        this.setPath(AntomPathConstants.ACCEPT_DISPUTE_PATH);
    }

    @Override
    public Class<AlipayAcceptDisputeResponse> getResponseClass() {
        return AlipayAcceptDisputeResponse.class;
    }
}
