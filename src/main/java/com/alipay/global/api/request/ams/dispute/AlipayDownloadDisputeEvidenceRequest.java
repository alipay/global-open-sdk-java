package com.alipay.global.api.request.ams.dispute;

import com.alipay.global.api.model.ams.DisputeEvidenceType;
import com.alipay.global.api.model.constants.AntomPathConstants;
import com.alipay.global.api.request.AlipayRequest;
import com.alipay.global.api.response.ams.dispute.AlipayDownloadDisputeEvidenceResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AlipayDownloadDisputeEvidenceRequest extends
        AlipayRequest<AlipayDownloadDisputeEvidenceResponse> {

    private String disputeId;
    private DisputeEvidenceType disputeEvidenceType;

    public AlipayDownloadDisputeEvidenceRequest() {
        this.setPath(AntomPathConstants.DOWNLOAD_DISPUTE_EVIDENCE_PATH);
    }

    @Override
    public Class<AlipayDownloadDisputeEvidenceResponse> getResponseClass() {
        return AlipayDownloadDisputeEvidenceResponse.class;
    }
}
