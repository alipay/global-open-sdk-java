package com.alipay.global.api.request.ams.customs;

import com.alipay.global.api.model.ams.Amount;
import com.alipay.global.api.model.ams.Certificate;
import com.alipay.global.api.model.ams.CustomsInfo;
import com.alipay.global.api.model.ams.MerchantCustomsInfo;
import com.alipay.global.api.model.constants.AntomPathConstants;
import com.alipay.global.api.request.AlipayRequest;
import com.alipay.global.api.response.ams.customs.AlipayCustomsDeclareResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AlipayCustomsDeclareRequest extends AlipayRequest<AlipayCustomsDeclareResponse> {

  private String declarationRequestId;
  private String paymentId;
  private Amount declarationAmount;
  private CustomsInfo customs;
  private MerchantCustomsInfo merchantCustomsInfo;
  private Boolean splitOrder;
  private String subOrderId;
  private Certificate buyerCertificate;

  public AlipayCustomsDeclareRequest() {
    this.setPath(AntomPathConstants.DECLARE_PATH);
  }

  @Override
  public Class<AlipayCustomsDeclareResponse> getResponseClass() {
    return AlipayCustomsDeclareResponse.class;
  }
}
