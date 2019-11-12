package com.alipay.api;

import com.alipay.api.exception.AlipayApiException;
import com.alipay.api.request.AlipayRequest;
import com.alipay.api.response.AlipayResponse;

public interface AlipayClient {

    <T extends AlipayResponse> T execute(AlipayRequest<T> alipayRequest) throws AlipayApiException;

}
