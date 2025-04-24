package com.alipay.global.api.model.ams;

import com.alipay.global.api.model.aps.Logo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentOption {

    private String paymentMethodType;
    private PaymentMethodCategoryType paymentMethodCategory;
    private List<String> paymentMethodRegion;
    private boolean enabled;
    private boolean preferred;
    private String disableReason;
    private Map<String, AmountLimitInfo> amountLimitInfoMap;
    private List<String> supportedCurrencies;
    private PaymentOptionDetail paymentOptionDetail;
    private String extendInfo;
    private Logo logo;
    private List<String> promoNames;

    private Installment installment;
    private List<PromotionInfo> promotionInfos;

    private InteractionType interactionType;
    private String bankIdentifierCode;

}
