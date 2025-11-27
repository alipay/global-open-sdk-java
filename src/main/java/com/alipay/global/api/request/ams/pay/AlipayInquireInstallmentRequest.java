package com.alipay.global.api.request.ams.pay;

import com.alipay.global.api.model.constants.AntomPathConstants;
import com.alipay.global.api.request.AlipayRequest;
import com.alipay.global.api.response.ams.pay.AlipayInquireInstallmentResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AlipayInquireInstallmentRequest
    extends AlipayRequest<AlipayInquireInstallmentResponse> {
  private String merchantAccountId;

  public AlipayInquireInstallmentRequest() {
    this.setPath(AntomPathConstants.PAYMENT_INQUIRE_INSTALLMENT_PATH);
  }

  @Override
  public Class<AlipayInquireInstallmentResponse> getResponseClass() {
    return AlipayInquireInstallmentResponse.class;
  }
}
