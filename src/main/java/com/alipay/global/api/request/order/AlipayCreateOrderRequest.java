package com.alipay.global.api.request.order;

import com.alibaba.fastjson.annotation.JSONField;
import com.alipay.global.api.model.Amount;
import com.alipay.global.api.model.Order;
import com.alipay.global.api.model.ProductCodeType;
import com.alipay.global.api.net.HttpMethod;
import com.alipay.global.api.request.AlipayRequest;
import com.alipay.global.api.response.order.AlipayCreateOrderResponse;

public class AlipayCreateOrderRequest extends AlipayRequest<AlipayCreateOrderResponse> {

    @JSONField(serialize = false)
    private final static String httpMethod =  HttpMethod.POST.name();

    private ProductCodeType productCode;
    private String          paymentRequestId;
    private Order           order;
    private Amount          paymentAmount;
    private String          paymentRedirectUrl;
    private String          paymentNotifyUrl;
    private String          extendInfo;

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

    public String getExtendInfo() {
        return extendInfo;
    }

    public void setExtendInfo(String extendInfo) {
        this.extendInfo = extendInfo;
    }

    @Override
    public String getHttpMethod() {
        return httpMethod;
    }

    @Override
    public Class<AlipayCreateOrderResponse> getResponseClass() {
        return AlipayCreateOrderResponse.class;
    }

}
