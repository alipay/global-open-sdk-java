package com.alipay.global.api.request.ams.auth;

import com.alipay.global.api.model.ams.AgreementInfo;
import com.alipay.global.api.model.ams.PaymentMethod;
import com.alipay.global.api.model.ams.ProductCodeType;
import com.alipay.global.api.model.ams.ScopeType;
import com.alipay.global.api.model.constants.AntomPathConstants;
import com.alipay.global.api.request.AlipayRequest;
import com.alipay.global.api.response.ams.auth.AlipayAuthCreateSessionResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AlipayAuthCreateSessionRequest extends AlipayRequest<AlipayAuthCreateSessionResponse> {

  private ProductCodeType productCode;
  private AgreementInfo agreementInfo;
  private ScopeType[] scopes;
  private PaymentMethod paymentMethod;
  private String paymentRedirectUrl;

  public AlipayAuthCreateSessionRequest() {
    this.setPath(AntomPathConstants.CREATE_SESSION_PATH);
    this.setProductCode(ProductCodeType.AGREEMENT_PAYMENT);
  }

  @Override
  public Class<AlipayAuthCreateSessionResponse> getResponseClass() {
    return AlipayAuthCreateSessionResponse.class;
  }
}
