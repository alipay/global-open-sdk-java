package com.alipay.global.api.request.ams.notify;

import com.alipay.global.api.model.ams.AcquirerInfo;
import com.alipay.global.api.model.ams.CustomizedInfo;
import com.alipay.global.api.model.ams.VaultingPaymentMethodDetail;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AlipayVaultingNotify extends AlipayNotify {

  /** The unique ID that is assigned by a merchant to identify a card vaulting request. */
  private String vaultingRequestId;

  /** The details about the card payment method. */
  private VaultingPaymentMethodDetail paymentMethodDetail;

  private String vaultingCreateTime;

  private AcquirerInfo acquirerInfo;

  private CustomizedInfo customizedInfo;
}
