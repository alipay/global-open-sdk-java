package com.alipay.global.api.request.ams.notify;

import com.alipay.global.api.model.ams.Amount;
import com.alipay.global.api.model.ams.PeriodRule;
import com.alipay.global.api.model.ams.SubscriptionNotificationType;
import com.alipay.global.api.model.ams.SubscriptionStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AlipaySubscriptionNotify extends AlipayNotify{
    private String subscriptionRequestId;
    private String subscriptionId;
    private SubscriptionStatus subscriptionStatus ;
    private SubscriptionNotificationType subscriptionNotificationType;
    private String subscriptionStartTime;
    private String subscriptionEndTime;
    private PeriodRule periodRule;
    private Boolean allowAccumulate;
    private Amount maxAccumulateAmount;
}
