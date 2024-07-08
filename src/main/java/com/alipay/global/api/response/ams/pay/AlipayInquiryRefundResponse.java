package com.alipay.global.api.response.ams.pay;

import com.alipay.global.api.model.ams.AcquirerInfo;
import com.alipay.global.api.model.ams.Amount;
import com.alipay.global.api.model.ams.Quote;
import com.alipay.global.api.model.ams.TransactionStatusType;
import com.alipay.global.api.response.AlipayResponse;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class AlipayInquiryRefundResponse extends AlipayResponse {

    private String                refundId;
    private String                refundRequestId;
    private Amount                refundAmount;
    /**
     * refundStatus
     * -SUCCESS
     * -PROCESSING
     */
    private TransactionStatusType refundStatus;
    private String                refundTime;
    private Amount                grossSettlementAmount;
    private Quote                 settlementQuote;

    private AcquirerInfo          acquirerInfo;

}
