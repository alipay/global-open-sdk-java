package com.alipay.global.api.request.ams.subscription;

import com.alipay.global.api.model.ams.Amount;
import com.alipay.global.api.model.ams.OrderInfo;
import com.alipay.global.api.model.ams.PeriodRule;
import com.alipay.global.api.request.AlipayRequest;
import com.alipay.global.api.response.ams.subscription.AlipaySubscriptionChangeResponse;

public class AlipaySubscriptionChangeRequest extends AlipayRequest<AlipaySubscriptionChangeResponse> {

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

    public String getSubscriptionChangeRequestId() {
        return subscriptionChangeRequestId;
    }

    public void setSubscriptionChangeRequestId(String subscriptionChangeRequestId) {
        this.subscriptionChangeRequestId = subscriptionChangeRequestId;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public String getSubscriptionDescription() {
        return subscriptionDescription;
    }

    public void setSubscriptionDescription(String subscriptionDescription) {
        this.subscriptionDescription = subscriptionDescription;
    }

    public String getSubscriptionStartTime() {
        return subscriptionStartTime;
    }

    public void setSubscriptionStartTime(String subscriptionStartTime) {
        this.subscriptionStartTime = subscriptionStartTime;
    }

    public String getSubscriptionEndTime() {
        return subscriptionEndTime;
    }

    public void setSubscriptionEndTime(String subscriptionEndTime) {
        this.subscriptionEndTime = subscriptionEndTime;
    }

    public PeriodRule getPeriodRule() {
        return periodRule;
    }

    public void setPeriodRule(PeriodRule periodRule) {
        this.periodRule = periodRule;
    }

    public String getSubscriptionExpiryTime() {
        return subscriptionExpiryTime;
    }

    public void setSubscriptionExpiryTime(String subscriptionExpiryTime) {
        this.subscriptionExpiryTime = subscriptionExpiryTime;
    }

    public OrderInfo getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(OrderInfo orderInfo) {
        this.orderInfo = orderInfo;
    }

    public Amount getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(Amount paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public Amount getPaymentAmountDifference() {
        return paymentAmountDifference;
    }

    public void setPaymentAmountDifference(Amount paymentAmountDifference) {
        this.paymentAmountDifference = paymentAmountDifference;
    }

    @Override
    public Class<AlipaySubscriptionChangeResponse> getResponseClass() {
        return AlipaySubscriptionChangeResponse.class;
    }


}
