package com.alipay.global.api.response.ams.pay;

import com.alipay.global.api.model.ams.*;
import com.alipay.global.api.response.AlipayResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AlipayInquiryRefundResponse extends AlipayResponse {

    private String refundId;
    private String refundRequestId;
    private Amount refundAmount;
    /**
     * refundStatus
     * -SUCCESS
     * -PROCESSING
     */
    private TransactionStatusType refundStatus;
    private String refundTime;
    private Amount grossSettlementAmount;
    private Quote settlementQuote;

    private AcquirerInfo acquirerInfo;

    private CustomizedInfo customizedInfo;

}
