package com.alipay.global.api.response.ams.customs;

import com.alipay.global.api.model.ams.ClearingChannel;
import com.alipay.global.api.model.ams.IdentityCheckResult;
import com.alipay.global.api.response.AlipayResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AlipayCustomsDeclareResponse extends AlipayResponse {

  private String customsPaymentId;
  private String customsOrderId;
  private IdentityCheckResult identityCheckResult;
  private ClearingChannel clearingChannel;
  private String clearingTransactionId;
  private String customsProviderRegistrationId;
}
