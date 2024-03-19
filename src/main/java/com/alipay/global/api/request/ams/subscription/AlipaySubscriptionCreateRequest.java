package com.alipay.global.api.request.ams.subscription;

import com.alipay.global.api.model.ams.Amount;
import com.alipay.global.api.model.ams.Env;
import com.alipay.global.api.model.ams.OrderInfo;
import com.alipay.global.api.model.ams.PaymentMethod;
import com.alipay.global.api.model.ams.PeriodRule;
import com.alipay.global.api.model.ams.SettlementStrategy;
import com.alipay.global.api.model.ams.Trial;
import com.alipay.global.api.request.AlipayRequest;
import com.alipay.global.api.response.ams.subscription.AlipaySubscriptionCreateResponse;

import java.util.List;


public class AlipaySubscriptionCreateRequest extends AlipayRequest<AlipaySubscriptionCreateResponse> {

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

    public String getSubscriptionRequestId() {
        return subscriptionRequestId;
    }

    public void setSubscriptionRequestId(String subscriptionRequestId) {
        this.subscriptionRequestId = subscriptionRequestId;
    }

    public String getSubscriptionDescription() {
        return subscriptionDescription;
    }

    public void setSubscriptionDescription(String subscriptionDescription) {
        this.subscriptionDescription = subscriptionDescription;
    }

    public String getSubscriptionRedirectUrl() {
        return subscriptionRedirectUrl;
    }

    public void setSubscriptionRedirectUrl(String subscriptionRedirectUrl) {
        this.subscriptionRedirectUrl = subscriptionRedirectUrl;
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

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getSubscriptionNotificationUrl() {
        return subscriptionNotificationUrl;
    }

    public void setSubscriptionNotificationUrl(String subscriptionNotificationUrl) {
        this.subscriptionNotificationUrl = subscriptionNotificationUrl;
    }

    public OrderInfo getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(OrderInfo orderInfo) {
        this.orderInfo = orderInfo;
    }

    public SettlementStrategy getSettlementStrategy() {
        return settlementStrategy;
    }

    public void setSettlementStrategy(SettlementStrategy settlementStrategy) {
        this.settlementStrategy = settlementStrategy;
    }

    public Env getEnv() {
        return env;
    }

    public void setEnv(Env env) {
        this.env = env;
    }

    public List<Trial> getTrials() {
        return trials;
    }

    public void setTrials(List<Trial> trials) {
        this.trials = trials;
    }

    public Amount getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(Amount paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public String getPaymentNotificationUrl() {
        return paymentNotificationUrl;
    }

    public void setPaymentNotificationUrl(String paymentNotificationUrl) {
        this.paymentNotificationUrl = paymentNotificationUrl;
    }

    @Override
    public Class<AlipaySubscriptionCreateResponse> getResponseClass() {
        return AlipaySubscriptionCreateResponse.class;
    }
}
