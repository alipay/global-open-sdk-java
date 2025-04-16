package com.alipay.global.api.request.ams.risk;

import com.alipay.global.api.model.ams.CustomerBelongsTo;
import com.alipay.global.api.model.ams.CustomerIdType;
import com.alipay.global.api.model.ams.RiskScoreType;
import com.alipay.global.api.request.AlipayRequest;
import com.alipay.global.api.response.ams.risk.AlipayRiskScoreInquiryResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Deprecated
public class AlipayRiskScoreInquiryRequest extends AlipayRequest<AlipayRiskScoreInquiryResponse> {

    private CustomerBelongsTo customerBelongsTo;
    private CustomerIdType customerIdType;
    private String customerId;
    private List<RiskScoreType> riskScoreTypes;

    @Override
    public Class<AlipayRiskScoreInquiryResponse> getResponseClass() {
        return AlipayRiskScoreInquiryResponse.class;
    }

}
