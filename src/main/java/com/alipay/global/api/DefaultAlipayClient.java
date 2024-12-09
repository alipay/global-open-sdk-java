package com.alipay.global.api;

import com.alipay.global.api.exception.AlipayApiException;
import com.alipay.global.api.net.DefaultHttpRPC;
import com.alipay.global.api.net.HttpClientRpc;
import com.alipay.global.api.net.HttpRpcResult;
import okhttp3.OkHttpClient;
import org.apache.http.impl.client.CloseableHttpClient;

import javax.net.ssl.HttpsURLConnection;
import java.util.Map;

public class DefaultAlipayClient extends BaseAlipayClient {

    public DefaultAlipayClient(String gatewayUrl, String merchantPrivateKey, String alipayPublicKey) {
        super(gatewayUrl, merchantPrivateKey, alipayPublicKey);
    }

    public DefaultAlipayClient(String gatewayUrl, String merchantPrivateKey, String alipayPublicKey, String clientId) {
        super(gatewayUrl, merchantPrivateKey, alipayPublicKey, clientId);
    }

    public DefaultAlipayClient(String gatewayUrl, String merchantPrivateKey, String alipayPublicKey, String clientId, CloseableHttpClient httpClient) {
        super(gatewayUrl, merchantPrivateKey, alipayPublicKey, clientId,httpClient);
    }

    @Override
    public Map<String, String> buildCustomHeader() {
        return null;
    }

    public HttpRpcResult sendRequest(String requestUrl, String httpMethod, Map<String, String> header, String reqBody) throws AlipayApiException {
        HttpRpcResult httpRpcResult;
        try {
            if (this.getHttpClient() != null) {
                    httpRpcResult = HttpClientRpc.doPost((CloseableHttpClient)this.getHttpClient(), requestUrl, header, reqBody);

            } else {
                httpRpcResult = DefaultHttpRPC.doPost(requestUrl, header, reqBody);
            }
        } catch (Exception e) {
            throw new AlipayApiException(e);
        }
        return httpRpcResult;
    }

}
