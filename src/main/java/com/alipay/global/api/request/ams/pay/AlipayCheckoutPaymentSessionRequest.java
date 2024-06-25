package com.alipay.global.api.request.ams.pay;

import com.alipay.global.api.model.ams.ProductCodeType;
import com.alipay.global.api.model.constants.ProductSceneConstants;

public class AlipayCheckoutPaymentSessionRequest extends AlipayPaymentSessionRequest {

    public AlipayCheckoutPaymentSessionRequest() {
        this.setPath("/ams/api/v1/payments/createPaymentSession");
        this.setProductCode(ProductCodeType.CASHIER_PAYMENT);
        this.setProductScene(ProductSceneConstants.CHECKOUT_PAYMENT);
    }

}
