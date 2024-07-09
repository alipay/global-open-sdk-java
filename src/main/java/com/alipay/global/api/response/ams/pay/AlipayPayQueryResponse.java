package com.alipay.global.api.response.ams.pay;

import java.util.List;

import com.alipay.global.api.model.ams.*;
import com.alipay.global.api.response.AlipayResponse;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class AlipayPayQueryResponse extends AlipayResponse {

    private TransactionStatusType paymentStatus;
    private String                paymentResultCode;
    private String                paymentResultMessage;
    private String                paymentRequestId;
    private String                paymentId;
    private String                authPaymentId;
    private Amount                paymentAmount;
    private Amount                actualPaymentAmount;
    private Quote                 paymentQuote;
    private String                authExpiryTime;
    private String                paymentCreateTime;
    private String                paymentTime;
    private Amount                nonGuaranteeCouponAmount;
    private PspCustomerInfo       pspCustomerInfo;
    private RedirectActionForm    redirectActionForm;

    private CardInfo              cardInfo;

    private String                acquirerReferenceNo;
    private String                extendInfo;
    private List<Transaction>     transactions;
    private Amount                customsDeclarationAmount;
    private Amount                grossSettlementAmount;
    private Quote                 settlementQuote;
    private PaymentResultInfo     paymentResultInfo;

    private AcquirerInfo          acquirerInfo;

}
