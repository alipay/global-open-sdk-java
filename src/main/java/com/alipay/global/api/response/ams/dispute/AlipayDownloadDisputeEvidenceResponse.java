package com.alipay.global.api.response.ams.dispute;

import com.alipay.global.api.model.ams.DisputeEvidenceFormatType;
import com.alipay.global.api.response.AlipayResponse;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AlipayDownloadDisputeEvidenceResponse extends AlipayResponse {

    private String disputeEvidence;

    private DisputeEvidenceFormatType disputeEvidenceFormat;

}
