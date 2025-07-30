package com.alipay.global.api.model.ams;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubscriptionPlan {

  private Boolean allowAccumulate;
  private Amount maxAccumulateAmount;
  private PeriodRule periodRule;
  private String subscriptionStartTime;
  private String subscriptionNotificationUrl;
}
