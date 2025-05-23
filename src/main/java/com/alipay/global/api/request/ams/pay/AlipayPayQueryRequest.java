package com.alipay.global.api.request.ams.pay;

import com.alipay.global.api.model.constants.AntomPathConstants;
import com.alipay.global.api.request.AlipayRequest;
import com.alipay.global.api.response.ams.pay.AlipayPayQueryResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AlipayPayQueryRequest extends AlipayRequest<AlipayPayQueryResponse> {

    /**
     * The original payment request ID of the payment request to be canceled, generated by the merchant to uniquely identify a payment request
     */
    private String paymentRequestId;

    /**
     * The original payment ID of the payment request to be canceled, generated by Alipay to uniquely identify the payment when the merchant initiates the original payment
     */
    private String paymentId;

    /**
     * The unique ID to identify a merchant account.
     */
    private String merchantAccountId;


    public AlipayPayQueryRequest() {
        this.setPath(AntomPathConstants.INQUIRY_PAYMENT_PATH);
    }

    @Override
    public Class<AlipayPayQueryResponse> getResponseClass() {
        return AlipayPayQueryResponse.class;
    }
}
