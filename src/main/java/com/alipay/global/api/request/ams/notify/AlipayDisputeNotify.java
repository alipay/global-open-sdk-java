package com.alipay.global.api.request.ams.notify;

import com.alipay.global.api.model.ams.Amount;
import com.alipay.global.api.model.ams.DisputeAcceptReasonType;
import com.alipay.global.api.model.ams.DisputeJudgedResult;
import com.alipay.global.api.model.ams.DisputeNotificationType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AlipayDisputeNotify extends AlipayNotify {
    private String paymentRequestId;
    private String disputeId;
    private String paymentId;
    private String disputeTime;
    private Amount disputeAmount;
    private DisputeNotificationType disputeNotificationType;
    private String disputeReasonMsg;
    private String disputeJudgedTime;
    private Amount disputeJudgedAmount;
    private DisputeJudgedResult disputeJudgedResult;
    private String defenseDueTime;
    private String disputeReasonCode;
    private String disputeSource;
    private String arn;
    private DisputeAcceptReasonType disputeAcceptReason;
    private String disputeAcceptTime;
    private String disputeType;
    private Boolean defendable;
}
