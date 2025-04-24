package com.alipay.global.api.request.ams.pay;

import com.alipay.global.api.model.ams.Amount;
import com.alipay.global.api.model.constants.AntomPathConstants;
import com.alipay.global.api.request.AlipayRequest;
import com.alipay.global.api.response.ams.pay.AlipayCaptureResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AlipayCaptureRequest extends AlipayRequest<AlipayCaptureResponse> {

    /**
     * The unique ID that is assigned by the merchant to identify a capture request. Alipay uses this field for idempotence control
     */
    private String captureRequestId;

    /**
     * The unique ID that is assigned by Alipay to identify a payment
     */
    private String paymentId;

    /**
     * The capture amount that the merchant requests to receive in the transaction currency
     */
    private Amount captureAmount;

    private Boolean isLastCapture;

    public AlipayCaptureRequest() {
        this.setPath(AntomPathConstants.CAPTURE_PATH);
    }

    @Override
    public Class<AlipayCaptureResponse> getResponseClass() {
        return AlipayCaptureResponse.class;
    }
}
