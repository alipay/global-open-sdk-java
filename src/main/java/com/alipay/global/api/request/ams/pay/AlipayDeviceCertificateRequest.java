package com.alipay.global.api.request.ams.pay;

import com.alipay.global.api.model.constants.AntomPathConstants;
import com.alipay.global.api.request.AlipayRequest;
import com.alipay.global.api.response.ams.pay.AlipayDeviceCertificateResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AlipayDeviceCertificateRequest extends AlipayRequest<AlipayDeviceCertificateResponse> {

  private String devicePublicKey;

  private String deviceRequestId;

  public AlipayDeviceCertificateRequest() {
    this.setPath(AntomPathConstants.CREATE_DEVICE_CERTIFICATE_PATH);
  }

  @Override
  public Class<AlipayDeviceCertificateResponse> getResponseClass() {
    return AlipayDeviceCertificateResponse.class;
  }
}
