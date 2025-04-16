package com.alipay.global.api.response.ams.pay;

import com.alipay.global.api.model.ams.Amount;
import com.alipay.global.api.response.AlipayResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AlipayCaptureResponse extends AlipayResponse {

    private String captureRequestId;
    private String captureId;
    private String paymentId;
    private Amount captureAmount;
    private String captureTime;
    private String acquirerReferenceNo;

}
