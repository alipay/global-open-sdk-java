package com.alipay.global.api.model.ams;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * RetryInfo 类的描述
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RetryInfo {
    private Integer availableRetries;
    private List<PaymentAttempt> paymentAttempts;
}
