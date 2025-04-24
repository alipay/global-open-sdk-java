package com.alipay.global.api.request.ams.pay;

import com.alipay.global.api.model.ams.*;
import com.alipay.global.api.model.constants.AntomPathConstants;
import com.alipay.global.api.request.AlipayRequest;
import com.alipay.global.api.response.ams.pay.AlipayPaymentSessionResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class AlipayPaymentSessionRequest extends AlipayRequest<AlipayPaymentSessionResponse> {

    /**
     * Represents the payment product that is being used. The fixed value is CASHIER_PAYMENT
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
     * The expiry time of paymentSession
     */
    private String paymentSessionExpiryTime;

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
     * The settlement strategy for the payment request
     */
    private SettlementStrategy settlementStrategy;

    /**
     * Indicates whether Antom collects the installment information for the payment.
     */
    private Boolean enableInstallmentCollection;

    /**
     * The credit payment plan information for this payment
     */
    private CreditPayPlan creditPayPlan;

    /**
     * The country or region where the merchant operates the business
     */
    private String merchantRegion;

    /**
     * Information about the environment where the order is placed
     */
    private Env env;

    /**
     * Authorization information about Easy Pay payments
     */
    private AgreementInfo agreementInfo;

    /**
     * The data used by Ant Group for risk control purposes.
     */
    private RiskData riskData;

    /**
     * This param is used for Easy pay payments,set its value to EASY_PAY
     */
    private String productScene;

    private List<PaymentMethod> savedPaymentMethods;

    private String locale;

    private AvailablePaymentMethod availablePaymentMethod;

    private List<String> allowedPaymentMethodRegions;

    private CustomizedInfo customizedInfo;

    private Quote paymentQuote;

    private Amount processingAmount;

    private SubscriptionPlan subscriptionPlan;


    public AlipayPaymentSessionRequest() {
        this.setPath(AntomPathConstants.CREATE_SESSION_PATH);
    }

    @Override
    public Class<AlipayPaymentSessionResponse> getResponseClass() {
        return AlipayPaymentSessionResponse.class;
    }

}
