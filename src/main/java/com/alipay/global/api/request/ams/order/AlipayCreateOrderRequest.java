package com.alipay.global.api.request.ams.order;

import com.alipay.global.api.model.ams.Amount;
import com.alipay.global.api.model.ams.Order;
import com.alipay.global.api.model.ams.ProductCodeType;
import com.alipay.global.api.request.AlipayRequest;
import com.alipay.global.api.response.ams.order.AlipayCreateOrderResponse;

public class AlipayCreateOrderRequest extends AlipayRequest<AlipayCreateOrderResponse> {

    private ProductCodeType productCode;
    private String          paymentRequestId;
    private Order           order;
    private Amount          paymentAmount;
    private String          paymentRedirectUrl;
    private String          paymentNotifyUrl;

    public ProductCodeType getProductCode() {
        return productCode;
    }

    public void setProductCode(ProductCodeType productCode) {
        this.productCode = productCode;
    }

    public String getPaymentRequestId() {
        return paymentRequestId;
    }

    public void setPaymentRequestId(String paymentRequestId) {
        this.paymentRequestId = paymentRequestId;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Amount getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(Amount paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public String getPaymentRedirectUrl() {
        return paymentRedirectUrl;
    }

    public void setPaymentRedirectUrl(String paymentRedirectUrl) {
        this.paymentRedirectUrl = paymentRedirectUrl;
    }

    public String getPaymentNotifyUrl() {
        return paymentNotifyUrl;
    }

    public void setPaymentNotifyUrl(String paymentNotifyUrl) {
        this.paymentNotifyUrl = paymentNotifyUrl;
    }

    @Override
    public Class<AlipayCreateOrderResponse> getResponseClass() {
        return AlipayCreateOrderResponse.class;
    }

}
