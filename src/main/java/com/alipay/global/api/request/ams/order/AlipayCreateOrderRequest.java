package com.alipay.global.api.request.ams.order;

import com.alipay.global.api.model.ams.Amount;
import com.alipay.global.api.model.ams.Order;
import com.alipay.global.api.model.ams.ProductCodeType;
import com.alipay.global.api.request.AlipayRequest;
import com.alipay.global.api.response.ams.order.AlipayCreateOrderResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Deprecated
@Data
@EqualsAndHashCode(callSuper = true)
public class AlipayCreateOrderRequest extends AlipayRequest<AlipayCreateOrderResponse> {

    private ProductCodeType productCode;
    private String paymentRequestId;
    private Order order;
    private Amount paymentAmount;
    private String paymentRedirectUrl;
    private String paymentNotifyUrl;

    public AlipayCreateOrderRequest() {
    }

    @Override
    public Class<AlipayCreateOrderResponse> getResponseClass() {
        return AlipayCreateOrderResponse.class;
    }

}
