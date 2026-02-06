package com.alipay.global.api.model.ams;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** PaymentAttempt 类的描述 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentAttempt {
  private String attemptAt;
  private String attemptResponse;
}
