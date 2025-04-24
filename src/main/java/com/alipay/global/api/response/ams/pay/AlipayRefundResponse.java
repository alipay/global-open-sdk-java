package com.alipay.global.api.response.ams.pay;

import com.alipay.global.api.model.ams.AcquirerInfo;
import com.alipay.global.api.model.ams.Amount;
import com.alipay.global.api.model.ams.Quote;
import com.alipay.global.api.response.AlipayResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AlipayRefundResponse extends AlipayResponse {

    private String refundRequestId;
    private String refundId;
    private String paymentId;
    private Amount refundAmount;
    private String refundTime;
    private Amount refundNonGuaranteeCouponAmount;
    private Amount grossSettlementAmount;
    private Quote settlementQuote;
    private AcquirerInfo acquirerInfo;
    private String acquirerReferenceNo;

}
