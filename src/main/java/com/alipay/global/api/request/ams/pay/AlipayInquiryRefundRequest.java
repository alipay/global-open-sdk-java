package com.alipay.global.api.request.ams.pay;

import com.alipay.global.api.model.constants.AntomPathConstants;
import com.alipay.global.api.request.AlipayRequest;
import com.alipay.global.api.response.ams.pay.AlipayInquiryRefundResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AlipayInquiryRefundRequest extends AlipayRequest<AlipayInquiryRefundResponse> {

    /**
     * The unique ID assigned by a merchant to identify a refund request
     */
    private String refundRequestId;

    /**
     * The unique ID assigned by Alipay to identify a refund
     */
    private String refundId;

    /**
     * The unique ID to identify a merchant account.
     */
    private String merchantAccountId;

    public AlipayInquiryRefundRequest() {
        this.setPath(AntomPathConstants.INQUIRY_REFUND_PATH);
    }

    @Override
    public Class getResponseClass() {
        return AlipayInquiryRefundResponse.class;
    }
}
