package com.alipay.global.api.request.ams.subscription;

import com.alipay.global.api.request.AlipayRequest;
import com.alipay.global.api.response.ams.subscription.AlipaySubscriptionCancelResponse;

/**
 * @Author yanhong
 * @version $Id: AlipaySubscriptionCancelRequest.java, v 0.1 2024年03月19日 4:06 PM yanhong Exp $
 **/
public class AlipaySubscriptionCancelRequest extends AlipayRequest<AlipaySubscriptionCancelResponse> {

    /**
     * The unique ID assigned by Alipay to identify a subscription.
     */
    private String subscriptionId;

    /**
     * The unique ID assigned by a merchant to identify a subscription request.
     */
    private String subscriptionRequestId;

    /**
     * The cancellation type for the subscription. Valid values are:
     *
     * CANCEL: indicates canceling the subscription. The subscription service is not provided to the user after the current subscription period ends.
     * TERMINATE: indicates terminating the subscription. The subscription service is ceased immediately.
     */
    private String cancellationType;

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public String getSubscriptionRequestId() {
        return subscriptionRequestId;
    }

    public void setSubscriptionRequestId(String subscriptionRequestId) {
        this.subscriptionRequestId = subscriptionRequestId;
    }

    public String getCancellationType() {
        return cancellationType;
    }

    public void setCancellationType(String cancellationType) {
        this.cancellationType = cancellationType;
    }

    @Override
    public Class<AlipaySubscriptionCancelResponse> getResponseClass() {
        return AlipaySubscriptionCancelResponse.class;
    }
}
