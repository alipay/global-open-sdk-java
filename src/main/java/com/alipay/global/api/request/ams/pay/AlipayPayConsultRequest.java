package com.alipay.global.api.request.ams.pay;

import com.alipay.global.api.model.ams.*;
import com.alipay.global.api.model.constants.AntomPathConstants;
import com.alipay.global.api.request.AlipayRequest;
import com.alipay.global.api.response.ams.pay.AlipayPayConsultResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class AlipayPayConsultRequest extends AlipayRequest<AlipayPayConsultResponse> {

    private ProductCodeType productCode;
    private Amount paymentAmount;
    private String merchantRegion;
    private List<String> allowedPaymentMethodRegions;
    private List<String> allowedPaymentMethods;
    private List<String> blockedPaymentMethods;
    private String region;
    private String customerId;
    private String referenceUserId;
    private Env env;
    private String extendInfo;
    private String userRegion;
    private PaymentFactor paymentFactor;
    private SettlementStrategy settlementStrategy;
    private Merchant merchant;
    private List<String> allowedPspRegions;
    private Buyer buyer;
    /**
     * The unique ID to identify a merchant account.
     */
    private String merchantAccountId;
    private PaymentMethodCategoryType paymentMethodCategory;

    public AlipayPayConsultRequest() {
        this.setPath(AntomPathConstants.CONSULT_PAYMENT_PATH);
    }

    @Override
    public Class<AlipayPayConsultResponse> getResponseClass() {
        return AlipayPayConsultResponse.class;
    }

}
