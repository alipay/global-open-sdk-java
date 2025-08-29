package com.alipay.global.api.response.ams.pay;

import com.alipay.global.api.model.ams.CardCategoryType;
import com.alipay.global.api.model.ams.FundingType;
import com.alipay.global.api.model.ams.Installment;
import com.alipay.global.api.response.AlipayResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AlipayGetCardInfoResponse  extends AlipayResponse {
    private String cardBrand;
    private String localCardBrand;
    private String coBagedCardBrand;
    private String issuingRegion;
    private FundingType funding;
    private CardCategoryType cardCategory;
    private Boolean supportInstallment;
    private Installment installment;
}
