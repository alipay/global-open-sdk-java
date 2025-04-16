package com.alipay.global.api.request.ams.notify;

import com.alipay.global.api.model.ams.Amount;
import com.alipay.global.api.model.ams.CustomizedInfo;
import com.alipay.global.api.model.ams.Quote;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AlipayRefundNotify extends AlipayNotify {

    /**
     * Indicates the refund result. SUCCESS/FAIL
     */
    private String refundStatus;

    /**
     * The unique ID assigned by a merchant to identify a refund request
     */
    private String refundRequestId;

    /**
     * The unique ID that is assigned by Alipay to identify a refund. A one-to-one correspondence between paymentId and paymentRequestId exists
     */
    private String refundId;

    /**
     * The refund amount that is initiated by the merchant
     */
    private Amount refundAmount;

    /**
     * The date and time when the refund reaches a final state of success or failure
     */
    private String refundTime;

    /**
     * The refund settlement amount, which equals the refund amount multiplied by the value of settlementQuote
     */
    private Amount grossSettlementAmount;

    /**
     * The exchange rate between the settlement currency and transaction currency
     */
    private Quote settlementQuote;

    private CustomizedInfo customizedInfo;

}
