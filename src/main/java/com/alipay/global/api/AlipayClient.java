package com.alipay.global.api;

import com.alipay.global.api.exception.AlipayApiException;
import com.alipay.global.api.request.AlipayRequest;
import com.alipay.global.api.response.AlipayResponse;

public interface AlipayClient {

    <T extends AlipayResponse> T execute(AlipayRequest<T> alipayRequest) throws AlipayApiException;

}
