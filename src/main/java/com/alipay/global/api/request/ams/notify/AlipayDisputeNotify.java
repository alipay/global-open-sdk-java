package com.alipay.global.api.request.ams.notify;

import com.alipay.global.api.model.ams.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AlipayDisputeNotify extends AlipayNotify {
  private String paymentRequestId;
  private String disputeId;
  private String paymentId;
  private String disputeTime;

  /** Optional ISO 8601 cancellation time with timezone, returned for DISPUTE_CANCELLED. */
  private String disputeCancelledTime;

  /** Optional ISO 8601 defense acceptance time with timezone, returned for DEFENSE_SUPPLIED. */
  private String defenseSuppliedTime;

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
  private String captureId;
  private String autoDefendReason;
  private AcquirerInfo acquirerInfo;
  private IssuerComments issuerComments;
}
