package com.alipay.global.api.request.ams.subscription;

import com.alipay.global.api.model.ams.Amount;
import com.alipay.global.api.model.ams.OrderInfo;
import com.alipay.global.api.model.ams.PeriodRule;
import com.alipay.global.api.model.constants.AntomPathConstants;
import com.alipay.global.api.request.AlipayRequest;
import com.alipay.global.api.response.ams.subscription.AlipaySubscriptionChangeResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AlipaySubscriptionChangeRequest extends
        AlipayRequest<AlipaySubscriptionChangeResponse> {

    /**
     * The unique ID assigned by a merchant to identify a subscription change request. Alipay uses this field for idempotency control.
     */
    private String subscriptionChangeRequestId;

    /**
     * The unique ID assigned by Alipay to identify a subscription.
     */
    private String subscriptionId;

    /**
     * The description of the subscription, used for displaying user consumption records and other actions.
     */
    private String subscriptionDescription;

    /**
     * The date and time when the subscription becomes active.
     */
    private String subscriptionStartTime;

    /**
     * The date and time when the subscription ends.
     */
    private String subscriptionEndTime;

    /**
     * The subscription period rule, used to define a subscription's billing period.
     */
    private PeriodRule periodRule;

    /**
     * A specific date and time after which the created subscription expires.
     */
    private String subscriptionExpiryTime;

    /**
     * The order information of the subscription.
     */
    private OrderInfo orderInfo;

    /**
     * The payment amount charged to the user per subscription period.
     */
    private Amount paymentAmount;

    /**
     * The payment amount for the initial subscription period after changing the payment amount for subsequent subscription periods.
     */
    private Amount paymentAmountDifference;

    private Boolean allowAccumulate;

    private Amount maxAccumulateAmount;

    public AlipaySubscriptionChangeRequest() {
        this.setPath(AntomPathConstants.SUBSCRIPTION_CHANGE_PATH);
    }

    @Override
    public Class<AlipaySubscriptionChangeResponse> getResponseClass() {
        return AlipaySubscriptionChangeResponse.class;
    }

}
