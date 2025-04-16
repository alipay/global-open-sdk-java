package com.alipay.global.api.request.ams.subscription;

import com.alipay.global.api.model.ams.Amount;
import com.alipay.global.api.model.ams.OrderInfo;
import com.alipay.global.api.model.ams.PeriodRule;
import com.alipay.global.api.model.constants.AntomPathConstants;
import com.alipay.global.api.request.AlipayRequest;
import com.alipay.global.api.response.ams.subscription.AlipaySubscriptionUpdateResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = true)
public class AlipaySubscriptionUpdateRequest extends AlipayRequest<AlipaySubscriptionUpdateResponse> {

    private String subscriptionUpdateRequestId;
    private String subscriptionId;
    private String subscriptionDescription;
    private PeriodRule periodRule;
    private Amount paymentAmount;
    private String subscriptionEndTime;
    private OrderInfo orderInfo;


    public AlipaySubscriptionUpdateRequest() {
        this.setPath(AntomPathConstants.SUBSCRIPTION_UPDATE_PATH);
    }


    @Override
    public Class<AlipaySubscriptionUpdateResponse> getResponseClass() {
        return AlipaySubscriptionUpdateResponse.class;
    }
}
