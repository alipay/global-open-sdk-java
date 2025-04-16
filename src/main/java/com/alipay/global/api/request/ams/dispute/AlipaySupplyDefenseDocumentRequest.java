package com.alipay.global.api.request.ams.dispute;

import com.alipay.global.api.model.constants.AntomPathConstants;
import com.alipay.global.api.request.AlipayRequest;
import com.alipay.global.api.response.ams.dispute.AlipaySupplyDefenseDocumentResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AlipaySupplyDefenseDocumentRequest extends
        AlipayRequest<AlipaySupplyDefenseDocumentResponse> {

    private String disputeId;

    private String disputeEvidence;

    public AlipaySupplyDefenseDocumentRequest() {
        this.setPath(AntomPathConstants.SUPPLY_DEFENCE_DOC_PATH);
    }

    @Override
    public Class<AlipaySupplyDefenseDocumentResponse> getResponseClass() {
        return AlipaySupplyDefenseDocumentResponse.class;
    }
}
