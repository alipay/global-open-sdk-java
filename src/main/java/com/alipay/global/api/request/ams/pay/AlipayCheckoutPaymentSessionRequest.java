package com.alipay.global.api.request.ams.pay;


import com.alipay.global.api.model.ams.ProductCodeType;
import com.alipay.global.api.model.constants.AntomPathConstants;
import com.alipay.global.api.model.constants.ProductSceneConstants;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AlipayCheckoutPaymentSessionRequest extends AlipayPaymentSessionRequest {

  public AlipayCheckoutPaymentSessionRequest() {
    this.setPath(AntomPathConstants.CREATE_SESSION_PATH);
    this.setProductCode(ProductCodeType.CASHIER_PAYMENT);
    this.setProductScene(ProductSceneConstants.CHECKOUT_PAYMENT);
  }
}
