package com.alipay.global.api.request.ams.pay;

import com.alipay.global.api.model.constants.AntomPathConstants;
import com.alipay.global.api.request.AlipayRequest;
import com.alipay.global.api.response.ams.pay.AlipayInquireExchangeRateResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AlipayInquireExchangeRateRequest
    extends AlipayRequest<AlipayInquireExchangeRateResponse> {

  private String merchantAccountId;
  private String paymentCurrency;

  public AlipayInquireExchangeRateRequest() {
    this.setPath(AntomPathConstants.PAYMENT_INQUIRE_EXCHANGE_RATE_PATH);
  }

  @Override
  public Class<AlipayInquireExchangeRateResponse> getResponseClass() {
    return AlipayInquireExchangeRateResponse.class;
  }
}
