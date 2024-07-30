package com.alipay.global.api.request.ams.pay;

import com.alipay.global.api.model.ams.*;
import com.alipay.global.api.model.ams.ProductCodeType;
import com.alipay.global.api.model.constants.AntomPathConstants;
import com.alipay.global.api.request.AlipayRequest;
import com.alipay.global.api.response.ams.pay.AlipayPayConsultResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class AlipayPayConsultRequest extends AlipayRequest<AlipayPayConsultResponse> {

    private ProductCodeType    productCode;
    private Amount             paymentAmount;
    /**
     * The country or region where the merchant operates the business. The parameter is a 2-letter country or region code that follows ISO 3166 Country Codes standard.
     *
     * Some possible values are US, SG, HK, PK, JP, CN, BR, AU, and MY.
     * value: MerchantRegionType.*.name
     */
    private String             merchantRegion;
    private List<String> allowedPaymentMethodRegions;
    private List<String>       allowedPaymentMethods;
    private List<String>       blockedPaymentMethods;
    private String             region;
    private String             customerId;
    private String             referenceUserId;
    private Env                env;
    private String             extendInfo;
    private String             userRegion;
    private PaymentFactor      paymentFactor;
    private SettlementStrategy settlementStrategy;
    private Merchant           merchant;
    private List<String>       allowedPspRegions;
    private Buyer              buyer;
    /**
     * The unique ID to identify a merchant account.
     */
    private String             merchantAccountId;

    public AlipayPayConsultRequest() {
        this.setPath(AntomPathConstants.CONSULT_PAYMENT_PATH);
    }

    @Override
    public Class<AlipayPayConsultResponse> getResponseClass() {
        return AlipayPayConsultResponse.class;
    }

}
