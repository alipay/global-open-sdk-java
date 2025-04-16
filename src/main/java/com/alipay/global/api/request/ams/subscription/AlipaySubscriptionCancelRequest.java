package com.alipay.global.api.request.ams.subscription;

import com.alipay.global.api.model.constants.AntomPathConstants;
import com.alipay.global.api.request.AlipayRequest;
import com.alipay.global.api.response.ams.subscription.AlipaySubscriptionCancelResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AlipaySubscriptionCancelRequest extends
        AlipayRequest<AlipaySubscriptionCancelResponse> {

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
     * <p>
     * CANCEL: indicates canceling the subscription. The subscription service is not provided to the user after the current subscription period ends.
     * TERMINATE: indicates terminating the subscription. The subscription service is ceased immediately.
     */
    private String cancellationType;

    public AlipaySubscriptionCancelRequest() {
        this.setPath(AntomPathConstants.SUBSCRIPTION_CANCEL_PATH);
    }

    @Override
    public Class<AlipaySubscriptionCancelResponse> getResponseClass() {
        return AlipaySubscriptionCancelResponse.class;
    }
}
