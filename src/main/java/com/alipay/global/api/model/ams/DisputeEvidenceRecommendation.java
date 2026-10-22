package com.alipay.global.api.model.ams;

import lombok.Data;

@Data
public class DisputeEvidenceRecommendation {
  /** Evidence tag. */
  private String evidenceTag;
  /** Whether Antom has already obtained this evidence. */
  private Boolean obtained;
  /** Optional contribution level: HIGH, MEDIUM or LOW. */
  private String winRateContribution;
}
