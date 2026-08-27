package com.alipay.global.api.model.ams;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** Issuer and cardholder comments returned in a dispute notification. */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IssuerComments {

  /** Cardholder-provided supplementary comments. Maximum length: 1024 characters. */
  private String cardholderComments;

  /** Issuer-provided reason for invalid authorization. Maximum length: 1024 characters. */
  private String reasonOfInvalidAuthorization;

  /** Explanation of a previously presented credit. Maximum length: 1024 characters. */
  private String explanationOfCreditPresented;

  /** Issuer-provided explanation of the dispute judgment. Maximum length: 1024 characters. */
  private String judgeReason;
}
