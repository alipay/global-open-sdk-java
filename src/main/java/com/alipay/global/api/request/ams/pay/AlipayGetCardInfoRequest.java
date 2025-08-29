package com.alipay.global.api.request.ams.pay;


import com.alipay.global.api.model.constants.AntomPathConstants;
import com.alipay.global.api.request.AlipayRequest;
import com.alipay.global.api.response.ams.pay.AlipayGetCardInfoResponse;
import com.alipay.global.api.response.ams.pay.AlipayPayResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AlipayGetCardInfoRequest  extends AlipayRequest<AlipayGetCardInfoResponse> {

    private String cardNo;

    private String paymentMethodRegion;

    private String cardBin;


    public AlipayGetCardInfoRequest() {
        this.setPath(AntomPathConstants.CARD_INFO_PATH);
    }



    @Override
    public Class<AlipayGetCardInfoResponse> getResponseClass() {
        return AlipayGetCardInfoResponse.class;
    }
}
