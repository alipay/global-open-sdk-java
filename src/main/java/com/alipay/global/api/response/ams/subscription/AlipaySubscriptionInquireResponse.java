package com.alipay.global.api.response.ams.subscription;

import com.alipay.global.api.model.ams.Amount;
import com.alipay.global.api.model.ams.PeriodRule;
import com.alipay.global.api.response.AlipayResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = true)
public class AlipaySubscriptionInquireResponse extends AlipayResponse {
    private String subscriptionId;
    private String subscriptionRequestId;
    private String subscriptionStatus;
    private Amount paymentAmount;
    private Boolean allowAccumulate;
    private Amount maxAccumulateAmount;
    private PeriodRule periodRule;
    private String phaseNo;
    private String subscriptionStartTime;
    private String subscriptionEndTime;
}
