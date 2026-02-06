package com.alipay.global.api.model.ams;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** RetryInfo 类的描述 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RetryInfo {
  private Integer availableRetries;
  private List<PaymentAttempt> paymentAttempts;
}
