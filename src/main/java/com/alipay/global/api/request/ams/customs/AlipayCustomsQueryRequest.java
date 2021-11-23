package com.alipay.global.api.request.ams.customs;


import com.alipay.global.api.request.AlipayRequest;
import com.alipay.global.api.response.ams.customs.AlipayCustomsQueryResponse;

import java.util.List;

public class AlipayCustomsQueryRequest extends AlipayRequest<AlipayCustomsQueryResponse> {

    private List<String> declarationRequestIds;


    public List<String> getDeclarationRequestIds() {
        return declarationRequestIds;
    }

    public void setDeclarationRequestIds(List<String> declarationRequestIds) {
        this.declarationRequestIds = declarationRequestIds;
    }

    @Override
    public Class<AlipayCustomsQueryResponse> getResponseClass() {
        return AlipayCustomsQueryResponse.class;
    }
}
