package com.alipay.global.api.request.ams.notify;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AlipaySubscriptionPayNotify extends AlipayPayResultNotify {
  private String subscriptionRequestId;
  private String subscriptionId;
  private String periodStartTime;
  private String periodEndTime;
  private String phaseNo;
}
