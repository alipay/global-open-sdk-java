package com.alipay.global.api.request.ams.pay;

import com.alipay.global.api.model.ams.CardPaymentMethodDetail;
import com.alipay.global.api.model.constants.AntomPathConstants;
import com.alipay.global.api.request.AlipayRequest;
import com.alipay.global.api.response.ams.pay.AlipayFetchNonceResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AlipayFetchNonceRequest extends AlipayRequest<AlipayFetchNonceResponse> {
  private CardPaymentMethodDetail card;

  public AlipayFetchNonceRequest() {
    this.setPath(AntomPathConstants.PAYMENT_FETCH_NONCE_PATH);
  }

  @Override
  public Class<AlipayFetchNonceResponse> getResponseClass() {
    return AlipayFetchNonceResponse.class;
  }
}
