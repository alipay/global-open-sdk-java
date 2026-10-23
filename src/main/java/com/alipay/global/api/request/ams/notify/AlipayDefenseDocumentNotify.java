package com.alipay.global.api.request.ams.notify;

import com.alipay.global.api.model.ams.*;
import java.util.Map;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AlipayDefenseDocumentNotify extends AlipayNotify {
  /** Dispute ID. */
  private String disputeId;

  /** Notification time as an ISO 8601 string with timezone. */
  private String notifyTime;

  /** Metadata of the generated material; the file body is not included. */
  private DisputeDefenseFile defenseFile;

  /** Optional predicted win-rate level of this material. */
  private String winRateLevel;

  /** Optional evidence tags mapped to requirement descriptions. */
  private Map<String, String> recommendedEvidence;

  /** Optional suggestions for improving the defense material. */
  private String suggestionsForImprovement;
}
