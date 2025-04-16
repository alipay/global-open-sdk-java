package com.alipay.global.api.response.ams.pay;

import com.alipay.global.api.model.ams.*;
import com.alipay.global.api.response.AlipayResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class AlipayPayResponse extends AlipayResponse {

    private String paymentRequestId;
    private String paymentId;
    private Amount paymentAmount;
    private String paymentData;
    private Amount actualPaymentAmount;
    private Quote paymentQuote;
    private String paymentTime;
    private String paymentCreateTime;
    private String authExpiryTime;
    private Amount nonGuaranteeCouponValue;
    private String paymentActionForm;
    private PspCustomerInfo pspCustomerInfo;
    private ChallengeActionForm challengeActionForm;
    private RedirectActionForm redirectActionForm;
    private OrderCodeForm orderCodeForm;
    private Amount grossSettlementAmount;
    private Quote settlementQuote;
    private String extendInfo;
    private String normalUrl;
    private String schemeUrl;
    private String applinkUrl;
    private String appIdentifier;
    private PaymentResultInfo paymentResultInfo;
    private AcquirerInfo acquirerInfo;
    private List<PromotionResult> promotionResult;
    private Amount processingAmount;

}
