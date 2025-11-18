package com.alipay.global.api.model.ams;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubscriptionInfo {
  private String subscriptionDescription;
  private String subscriptionStartTime;
  private String subscriptionEndTime;
  private PeriodRule periodRule;
  private List<Trial> trials;
  private String subscriptionNotifyUrl;
  private String subscriptionExpiryTime;
}
