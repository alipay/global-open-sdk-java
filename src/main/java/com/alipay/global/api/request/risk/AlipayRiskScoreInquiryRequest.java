package com.alipay.global.api.request.risk;

import com.alipay.global.api.model.CustomerBelongsTo;
import com.alipay.global.api.model.CustomerIdType;
import com.alipay.global.api.model.RiskScoreType;
import com.alipay.global.api.request.AlipayRequest;
import com.alipay.global.api.response.risk.AlipayRiskScoreInquiryResponse;

import java.util.List;

public class AlipayRiskScoreInquiryRequest extends AlipayRequest<AlipayRiskScoreInquiryResponse> {

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
    public Class<AlipayRiskScoreInquiryResponse> getResponseClass() {
        return AlipayRiskScoreInquiryResponse.class;
    }

}
