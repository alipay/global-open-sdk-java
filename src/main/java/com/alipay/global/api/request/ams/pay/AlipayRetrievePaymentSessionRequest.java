package com.alipay.global.api.request.ams.pay;

import com.alipay.global.api.model.constants.AntomPathConstants;
import com.alipay.global.api.request.AlipayRequest;
import com.alipay.global.api.response.ams.pay.AlipayRefundResponse;
import com.alipay.global.api.response.ams.pay.AlipayRetrievePaymentSessionResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AlipayRetrievePaymentSessionRequest  extends AlipayRequest<AlipayRetrievePaymentSessionResponse> {

    private String paymentRequestId;


    public AlipayRetrievePaymentSessionRequest() {
        this.setPath(AntomPathConstants.RETRIEVE_PATH);
    }

    @Override
    public Class<AlipayRetrievePaymentSessionResponse> getResponseClass() {
        return AlipayRetrievePaymentSessionResponse.class;
    }
}
