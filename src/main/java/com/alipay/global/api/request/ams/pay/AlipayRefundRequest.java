package com.alipay.global.api.request.ams.pay;

import com.alipay.global.api.model.ams.Amount;
import com.alipay.global.api.model.ams.CustomizedInfo;
import com.alipay.global.api.model.ams.RefundDetail;
import com.alipay.global.api.model.ams.RefundToBankInfo;
import com.alipay.global.api.model.constants.AntomPathConstants;
import com.alipay.global.api.request.AlipayRequest;
import com.alipay.global.api.response.ams.pay.AlipayRefundResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class AlipayRefundRequest extends AlipayRequest<AlipayRefundResponse> {

    /**
     * The unique ID assigned by the merchant to identify a refund request
     */
    private String refundRequestId;

    /**
     * The unique ID assigned by Alipay for the original payment to be refunded
     */
    private String paymentId;

    /**
     * The unique ID to identify a refund, which is assigned by the merchant that directly provides services or goods to the customer
     */
    private String referenceRefundId;

    /**
     * The refund amount that is initiated by the merchant
     */
    private Amount refundAmount;

    /**
     * The refund reason
     */
    private String refundReason;

    /**
     * The URL that is used to receive the refund result notification. The URL must be either specified in the request or set in Alipay Developer Center
     */
    private String refundNotifyUrl;

    private Boolean isAsyncRefund;

    private String extendInfo;

    private List<RefundDetail> refundDetails;

    private String refundSourceAccountNo;

    private RefundToBankInfo refundToBankInfo;

    private CustomizedInfo customizedInfo;


    public AlipayRefundRequest() {
        this.setPath(AntomPathConstants.REFUND_PATH);
    }

    @Override
    public Class<AlipayRefundResponse> getResponseClass() {
        return AlipayRefundResponse.class;
    }
}
