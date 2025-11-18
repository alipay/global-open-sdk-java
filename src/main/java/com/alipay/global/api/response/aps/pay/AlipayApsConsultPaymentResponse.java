package com.alipay.global.api.response.aps.pay;

import com.alipay.global.api.model.aps.PaymentOption;
import com.alipay.global.api.response.AlipayResponse;
import java.util.List;

public class AlipayApsConsultPaymentResponse extends AlipayResponse {

  private List<PaymentOption> paymentOptions;

  public List<PaymentOption> getPaymentOptions() {
    return paymentOptions;
  }

  public void setPaymentOptions(List<PaymentOption> paymentOptions) {
    this.paymentOptions = paymentOptions;
  }
}
