package com.alipay.global.api.request.ams.subscription;

import com.alipay.global.api.model.constants.AntomPathConstants;
import com.alipay.global.api.request.AlipayRequest;
import com.alipay.global.api.response.ams.subscription.AlipaySubscriptionInquireResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AlipaySubscriptionInquireRequest
    extends AlipayRequest<AlipaySubscriptionInquireResponse> {
  private String subscriptionId;
  private String merchantSubscriptionId;

  public AlipaySubscriptionInquireRequest() {
    this.setPath(AntomPathConstants.SUBSCRIPTION_INQUIRE_PATH);
  }

  @Override
  public Class<AlipaySubscriptionInquireResponse> getResponseClass() {
    return AlipaySubscriptionInquireResponse.class;
  }
}
