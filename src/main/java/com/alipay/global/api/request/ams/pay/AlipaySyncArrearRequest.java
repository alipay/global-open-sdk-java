package com.alipay.global.api.request.ams.pay;

import com.alipay.global.api.model.constants.AntomPathConstants;
import com.alipay.global.api.request.AlipayRequest;
import com.alipay.global.api.response.ams.pay.AlipaySyncArrearResponse;
import lombok.Data;

@Data
public class AlipaySyncArrearRequest extends AlipayRequest<AlipaySyncArrearResponse> {

  private String paymentId;
  private String paymentRequestId;

  public AlipaySyncArrearRequest() {
    this.setPath(AntomPathConstants.SYNC_ARREAR_PATH);
  }

  @Override
  public Class<AlipaySyncArrearResponse> getResponseClass() {
    return AlipaySyncArrearResponse.class;
  }
}
