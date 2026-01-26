package com.alipay.global.api.request.ams.notify;

import com.alipay.global.api.model.ams.Amount;
import com.alipay.global.api.model.ams.SubscriptionNotificationType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.OffsetDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
public class AlipaySubscriptionCancelNotify extends AlipayNotify {

    private Amount paymentAmount;
    private String paymentCreateTime;
    private String paymentTime;
    private String periodEndTime;
    private String phaseNo;
    private String subscriptionId;
    private String subscriptionOrderId;
    private SubscriptionNotificationType subscriptionOrderStatus;
    private String subscriptionRequestId;
    private String subscriptionTransId;

}