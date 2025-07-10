package com.alipay.global.api.model.ams;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
