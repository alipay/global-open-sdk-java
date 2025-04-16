package com.alipay.global.api.request.ams.pay;

import com.alipay.global.api.model.ams.*;
import com.alipay.global.api.model.constants.AntomPathConstants;
import com.alipay.global.api.request.AlipayRequest;
import com.alipay.global.api.response.ams.pay.AlipayPayResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AlipayPayRequest extends AlipayRequest<AlipayPayResponse> {

    /**
     * Represents the payment product that is being used.
     */
    private ProductCodeType productCode;

    /**
     * The unique ID assigned by a merchant to identify a payment request
     */
    private String paymentRequestId;

    /**
     * The order information
     */
    private Order order;

    /**
     * The payment amount that the merchant requests to receive in the order currency
     */
    private Amount paymentAmount;

    /**
     * The payment method that is used to collect the payment by the merchant or acquirer
     */
    private PaymentMethod paymentMethod;

    /**
     * The specific time after which the payment will expire
     */
    private String paymentExpiryTime;

    /**
     * The merchant page URL that the user is redirected to after the payment is completed
     */
    private String paymentRedirectUrl;

    /**
     * The URL that is used to receive the payment result notification
     */
    private String paymentNotifyUrl;

    /**
     * Factors that impact the payment. This field is used to indicate the payment scenario
     */
    private PaymentFactor paymentFactor;

    /**
     * The settlement strategy for the payment request.
     */
    private SettlementStrategy settlementStrategy;

    /**
     * The credit payment plan information for this payment
     */
    private CreditPayPlan creditPayPlan;

    /**
     * The unique ID that is assigned by Alipay to identify the mini program
     */
    private String appId;

    /**
     * The country or region where the merchant operates the business
     */
    private String merchantRegion;

    /**
     * A 2-letter country or region code based on the standard of ISO 3166 Country Codes. This parameter is used to sort Alipay+ payment methods according to the user's region
     */
    private String userRegion;

    /**
     * Information about the environment where the order is placed, such as the device information
     */
    private Env env;

    private PaymentMethod payToMethod;

    private Boolean isAuthorization;

    private Merchant merchant;

    private PaymentVerificationData paymentVerificationData;

    private String extendInfo;

    /**
     * The unique ID to identify a merchant account.
     */
    private String merchantAccountId;

    private Boolean dualOfflinePayment;

    private CustomizedInfo customizedInfo;

    private Quote paymentQuote;

    private Amount processingAmount;

    public AlipayPayRequest() {
        this.setPath(AntomPathConstants.PAYMENT_PATH);
    }

    @Override
    public Class<AlipayPayResponse> getResponseClass() {
        return AlipayPayResponse.class;
    }

}
