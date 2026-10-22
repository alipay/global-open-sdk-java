package com.alipay.global.api.request.ams.notify;

import lombok.Data;
import lombok.EqualsAndHashCode;
import com.alipay.global.api.model.ams.*;
import java.util.List;
import java.util.Map;

@Data
@EqualsAndHashCode(callSuper = true)
public class AlipayDefenseStrategySuggestionNotify extends AlipayNotify {
  /** Dispute ID. */
  private String disputeId;
  /** Notification time as an ISO 8601 string with timezone. */
  private String notifyTime;
  /** Predicted win-rate level: HIGH, MEDIUM or LOW. */
  private String winRateLevel;
  /** Recommended evidence items, from 1 to 32 entries. */
  private List<DisputeEvidenceRecommendation> evidenceList;
  /** Optional cardholder dispute reason. */
  private String cardholderDisputeReason;
  /** Suggested defense strategy. */
  private String defenseStrategyDescription;
}
