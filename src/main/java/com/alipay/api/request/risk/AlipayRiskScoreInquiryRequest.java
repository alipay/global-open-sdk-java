package com.alipay.api.request.risk;

import com.alibaba.fastjson.annotation.JSONField;
import com.alipay.api.model.CustomerBelongsTo;
import com.alipay.api.model.CustomerIdType;
import com.alipay.api.model.RiskScoreType;
import com.alipay.api.net.HttpMethod;
import com.alipay.api.request.AlipayRequest;
import com.alipay.api.response.risk.AlipayRiskScoreInquiryResponse;

import java.util.List;

public class AlipayRiskScoreInquiryRequest extends AlipayRequest<AlipayRiskScoreInquiryResponse> {

    @JSONField(serialize = false)
    private final static String httpMethod = HttpMethod.POST.name();

    private CustomerBelongsTo   customerBelongsTo;
    private CustomerIdType      customerIdType;
    private String              customerId;
    private List<RiskScoreType> riskScoreTypes;

    public CustomerBelongsTo getCustomerBelongsTo() {
        return customerBelongsTo;
    }

    public void setCustomerBelongsTo(CustomerBelongsTo customerBelongsTo) {
        this.customerBelongsTo = customerBelongsTo;
    }

    public CustomerIdType getCustomerIdType() {
        return customerIdType;
    }

    public void setCustomerIdType(CustomerIdType customerIdType) {
        this.customerIdType = customerIdType;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public List<RiskScoreType> getRiskScoreTypes() {
        return riskScoreTypes;
    }

    public void setRiskScoreTypes(List<RiskScoreType> riskScoreTypes) {
        this.riskScoreTypes = riskScoreTypes;
    }

    @Override
    public String getHttpMethod() {
        return httpMethod;
    }

    @Override
    public Class<AlipayRiskScoreInquiryResponse> getResponseClass() {
        return AlipayRiskScoreInquiryResponse.class;
    }

}
