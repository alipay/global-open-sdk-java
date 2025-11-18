package com.alipay.global.api.request.ams.merchant;

import com.alipay.global.api.request.AlipayRequest;
import com.alipay.global.api.response.ams.merchant.AlipayMerchantRegistrationStatusQueryResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AlipayMerchantRegistrationStatusQueryRequest
    extends AlipayRequest<AlipayMerchantRegistrationStatusQueryResponse> {

  private String registrationRequestId;
  private String referenceMerchantId;

  public AlipayMerchantRegistrationStatusQueryRequest() {
    this.setPath("/ams/api/v1/merchants/inquiryRegistrationStatus");
  }

  @Override
  public Class<AlipayMerchantRegistrationStatusQueryResponse> getResponseClass() {
    return AlipayMerchantRegistrationStatusQueryResponse.class;
  }
}
