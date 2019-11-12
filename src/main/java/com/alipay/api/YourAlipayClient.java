package com.alipay.api;

import com.alipay.api.exception.AlipayApiException;
import com.alipay.api.net.HttpRpcResult;

import java.util.Map;

public class YourAlipayClient extends BaseAlipayClient{

    public YourAlipayClient(String merchantPrivateKey, String alipayPublicKey){
        super(merchantPrivateKey, alipayPublicKey);
    }

    public YourAlipayClient(String gatewayUrl, String merchantPrivateKey, String alipayPublicKey ){
        super(gatewayUrl, merchantPrivateKey, alipayPublicKey);
    }

    @Override
    public Map<String, String> buildCustomHeader() {
        // TODO

        return null;
    }

    @Override
    public HttpRpcResult sendRequest(String requestUrl, String httpMethod, Map<String, String> header, String reqBody)throws AlipayApiException {
        // TODO
        // HTTP Call

        return null;
    }

}