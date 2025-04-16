package com.alipay.global.api.request.ams.subscription;

import com.alipay.global.api.model.ams.*;
import com.alipay.global.api.model.constants.AntomPathConstants;
import com.alipay.global.api.request.AlipayRequest;
import com.alipay.global.api.response.ams.subscription.AlipaySubscriptionCreateResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class AlipaySubscriptionCreateRequest extends
        AlipayRequest<AlipaySubscriptionCreateResponse> {

    /**
     * The unique ID assigned by a merchant to identify a subscription request. Alipay uses this field for idempotency control.
     */
    private String subscriptionRequestId;

    /**
     * The description of the subscription, used for displaying user consumption records and other actions.
     */
    private String subscriptionDescription;

    /**
     * The merchant page URL that the user is redirected to after authorizing the subscription.
     */
    private String subscriptionRedirectUrl;

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
     * The duration of subscription preparation process should be less than 48 hours
     */
    private String subscriptionExpiryTime;

    /**
     * The payment method that is used to collect the payment by the merchant or acquirer.
     */
    private PaymentMethod paymentMethod;

    /**
     * The URL that is used to receive the subscription result notification.
     */
    private String subscriptionNotificationUrl;

    /**
     * The URL that is used to receive the subscription result notification.
     */
    private String paymentNotificationUrl;

    /**
     * The order information for the subscription.
     */
    private OrderInfo orderInfo;

    /**
     * The payment amount charged to the user per subscription period.
     */
    private Amount paymentAmount;

    /**
     * The settlement strategy for the payment request.
     */
    private SettlementStrategy settlementStrategy;

    /**
     * Information about the environment where the order is placed, such as the device information.
     */
    private Env env;

    /**
     * The list of trial information of a subscription.
     */
    private List<Trial> trials;

    private String merchantAccountId;
    private Boolean allowAccumulate;
    private Amount maxAccumulateAmount;
    private CustomizedInfo customizedInfo;

    public AlipaySubscriptionCreateRequest() {
        this.setPath(AntomPathConstants.SUBSCRIPTION_CREATE_PATH);
    }

    @Override
    public Class<AlipaySubscriptionCreateResponse> getResponseClass() {
        return AlipaySubscriptionCreateResponse.class;
    }
}
