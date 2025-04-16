package com.alipay.global.api.request.ams.customs;

import com.alipay.global.api.model.constants.AntomPathConstants;
import com.alipay.global.api.request.AlipayRequest;
import com.alipay.global.api.response.ams.customs.AlipayCustomsQueryResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class AlipayCustomsQueryRequest extends AlipayRequest<AlipayCustomsQueryResponse> {

    private List<String> declarationRequestIds;

    public AlipayCustomsQueryRequest() {
        this.setPath(AntomPathConstants.INQUIRY_DECLARE_PATH);
    }

    @Override
    public Class<AlipayCustomsQueryResponse> getResponseClass() {
        return AlipayCustomsQueryResponse.class;
    }

}
