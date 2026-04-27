package com.alipay.global.api.model.ams;

import lombok.*;

/** RefundFromMethod */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RefundFromMethod {

  /** The grant token for refund */
  private String grantToken;

  /** The refund from method type, e.g., ANTOM_BIZ_ACCOUNT_DEPOSIT */
  private String refundFromMethodType;

  /** The customer ID */
  private String customerId;
}
