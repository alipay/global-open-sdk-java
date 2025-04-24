package com.alipay.global.api.request.ams.notify;

import com.alipay.global.api.model.ams.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class AlipayPayResultNotify extends AlipayNotify {

    /**
     * The unique ID that is assigned by a merchant to identify a payment request.
     */
    private String paymentRequestId;

    /**
     * unique id generated from ipay for this payment
     */
    private String paymentId;

    /**
     * amount of this payment
     */
    private Amount paymentAmount;

    /**
     * create time of this payment
     */
    private String paymentCreateTime;

    /**
     * the time of payment finish
     */
    private String paymentTime;

    /**
     * The total amount for customs declaration
     */
    private Amount customsDeclarationAmount;

    /**
     * The value of this field equals to transaction amount multiplied by the value of settlementQuote. This field is returned when the currency exchange is predetermined and the exchange rate is locked at the time of transaction
     */
    private Amount grossSettlementAmount;

    /**
     * The exchange rate between the settlement currency and transaction currency. This field is returned when grossSettlementAmount is returned
     */
    private Quote settlementQuote;

    /**
     * Information about the customer of Alipay+ Mobile Payment Provider (Alipay+ MPP)
     */
    private PspCustomerInfo pspCustomerInfo;

    /**
     * The unique ID assigned by the non-Alipay acquirer for the transaction
     */
    private String acquirerReferenceNo;

    /**
     * The payment result information
     */
    private PaymentResultInfo paymentResultInfo;

    /**
     * The information of the acquirer that processes the payment.
     */
    private AcquirerInfo acquirerInfo;

    private List<PromotionResult> promotionResult;

    private String paymentMethodType;

    private CustomizedInfo customizedInfo;
    private Quote paymentQuote;
    private Amount processingAmount;

}
