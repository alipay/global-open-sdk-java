package com.alipay.global.api;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alipay.global.api.exception.AlipayApiException;
import com.alipay.global.api.model.Result;
import com.alipay.global.api.net.HttpRpcResult;
import com.alipay.global.api.request.AlipayRequest;
import com.alipay.global.api.response.AlipayResponse;
import com.alipay.global.api.tools.Constants;
import com.alipay.global.api.tools.DateTool;
import com.alipay.global.api.tools.SignatureTool;

public abstract class BaseAlipayClient implements AlipayClient {

    private static final Integer DEFAULT_KEY_VERSION = 1;
    /**
     * eg: https://open-na.alipay.com
     */
    private String gatewayUrl;
    /**
     * merchants private key
     */
    private String merchantPrivateKey;
    /**
     * alipay public key
     */
    private String alipayPublicKey;
    /**
     * client id
     */
    private String clientId;
    /**
     * is sandbox mode
     */
    private boolean isSandboxMode = false;

    private String agentToken;

    public BaseAlipayClient() {
    }

    public BaseAlipayClient(String gatewayUrl, String merchantPrivateKey, String alipayPublicKey) {
        this.gatewayUrl = gatewayUrl;
        this.merchantPrivateKey = merchantPrivateKey;
        this.alipayPublicKey = alipayPublicKey;
    }

    public BaseAlipayClient(String gatewayUrl, String merchantPrivateKey, String alipayPublicKey, String clientId) {
        this.gatewayUrl = gatewayUrl;
        this.merchantPrivateKey = merchantPrivateKey;
        this.alipayPublicKey = alipayPublicKey;
        this.clientId = clientId;

        // if client id starts with SANDBOX_, set to sandbox mode
        if (clientId.startsWith("SANDBOX_")) {
            this.isSandboxMode = true;
        }
    }

    public BaseAlipayClient(String gatewayUrl, String merchantPrivateKey, String alipayPublicKey, String clientId, String agentToken) {
        this.gatewayUrl = gatewayUrl;
        this.merchantPrivateKey = merchantPrivateKey;
        this.alipayPublicKey = alipayPublicKey;
        this.clientId = clientId;
        this.agentToken = agentToken;

        // if client id starts with SANDBOX_, set to sandbox mode
        if (clientId.startsWith("SANDBOX_")) {
            this.isSandboxMode = true;
        }
    }

    public <T extends AlipayResponse> T execute(AlipayRequest<T> alipayRequest) throws AlipayApiException {

        // compatible with old version which clientId does not exist in BaseAlipayClient
        alipayRequest.setClientId(alipayRequest.getClientId() == null ? this.clientId : alipayRequest.getClientId());

        // replace with sandbox url if needed
        adjustSandboxUrl(alipayRequest);

        // check request params
        checkRequestParams(alipayRequest);

        String clientId = alipayRequest.getClientId();
        String httpMethod = alipayRequest.getHttpMethod();
        String path = alipayRequest.getPath();
        Integer keyVersion = alipayRequest.getKeyVersion();
        String reqTime = DateTool.getCurrentTimeMillis();
        String reqBody = JSON.toJSONString(alipayRequest, SerializerFeature.DisableCircularReferenceDetect);

        /**
         * 对内容加签(Sign the content)
         */
        String signValue = genSignValue(httpMethod, path, clientId, reqTime, reqBody);

        /**
         *  生成必要header(Generate required headers)
         */
        Map<String, String> header = buildBaseHeader(reqTime, clientId, keyVersion, signValue);
        Map<String, String> customHeader = buildCustomHeader();
        if (customHeader != null && !customHeader.isEmpty()) {
            header.putAll(customHeader);
        }

        String requestUrl = genRequestUrl(path);
        /**
         * 向网关发起http请求(Make an HTTP request to the gateway)
         */
        HttpRpcResult rsp = sendRequest(requestUrl, httpMethod, header, reqBody);

        if (rsp == null) {
            throw new AlipayApiException("HttpRpcResult is null.");
        }

        int httpRespCode = rsp.getRspCode();
        String rspBody = rsp.getRspBody();
        if (httpRespCode != Constants.HTTP_SUCCESS_CODE) {
            throw new AlipayApiException("Response data error, rspBody:" + rspBody);
        }
        Class<T> responseClass = alipayRequest.getResponseClass();
        T alipayResponse = JSON.parseObject(rspBody, responseClass);
        Result result = alipayResponse.getResult();
        if (result == null) {
            throw new AlipayApiException("Response data error, result field is null, rspBody:" + rspBody);
        }

        String rspSignValue = rsp.getRspSign();
        String rspTime = rsp.getResponseTime();
        if (null == rspSignValue || rspSignValue.isEmpty() || null == rspTime || rspTime.isEmpty()) {
            return alipayResponse;
        }

        /**
         * 对返回结果验签(Verify the result signature)
         */
        boolean isVerifySuccess = checkRspSign(httpMethod, path, clientId, rspTime, rspBody, rspSignValue);
        if (!isVerifySuccess) {
            throw new AlipayApiException("Response signature verify fail.");
        }

        return alipayResponse;
    }

    private String genSignValue(String httpMethod, String path, String clientId, String requestTime, String reqBody) throws AlipayApiException {
        String signatureValue;
        try {
            signatureValue = SignatureTool.sign(httpMethod, path, clientId, requestTime, reqBody, merchantPrivateKey);
        } catch (Exception e) {
            throw new AlipayApiException("generate signature error", e);
        }
        return signatureValue;
    }

    private boolean checkRspSign(String httpMethod, String path, String clientId, String responseTime, String rspBody, String rspSignValue) throws AlipayApiException {
        try {
            return SignatureTool.verify(httpMethod, path, clientId, responseTime, rspBody, rspSignValue, alipayPublicKey);
        } catch (Exception e) {
            throw new AlipayApiException("verify signature error", e);
        }
    }

    private void checkRequestParams(AlipayRequest alipayRequest) throws AlipayApiException {
        if (alipayRequest == null) {
            throw new AlipayApiException("alipayRequest can't null");
        }

        String clientId = alipayRequest.getClientId();
        String httpMethod = alipayRequest.getHttpMethod();
        String path = alipayRequest.getPath();

        if (StringUtils.isBlank(gatewayUrl)) {
            throw new AlipayApiException("gatewayUrl can't null");
        }

        if (StringUtils.isBlank(clientId)) {
            throw new AlipayApiException("clientId can't null");
        }

        if (StringUtils.isBlank(httpMethod)) {
            throw new AlipayApiException("httpMethod can't null");
        }

        if (StringUtils.isBlank(path)) {
            throw new AlipayApiException("path can't null");
        }

        if (!path.startsWith("/")) {
            throw new AlipayApiException("path must start with /");
        }

    }

    private String genRequestUrl(String path) {
        if (!gatewayUrl.startsWith("http://") && !gatewayUrl.startsWith("https://")) {
            gatewayUrl = "https://" + gatewayUrl;
        }
        if (gatewayUrl.endsWith("/")) {
            int len = gatewayUrl.length();
            gatewayUrl = gatewayUrl.substring(0, len - 1);
        }
        return gatewayUrl + path;

    }

    /**
     * If is sandbox mode, modify the path
     *
     * @param alipayRequest
     */
    private void adjustSandboxUrl(AlipayRequest alipayRequest) {
        if (isSandboxMode && alipayRequest.usingSandboxUrl()) {
            String originPath = alipayRequest.getPath();
            alipayRequest.setPath(originPath.replaceFirst("/ams/api", "/ams/sandbox/api"));
        }
    }

    /**
     * Generate required headers
     *
     * @param requestTime
     * @param clientId
     * @param keyVersion
     * @param signatureValue
     * @return
     */
    private Map<String, String> buildBaseHeader(String requestTime, String clientId, Integer keyVersion, String signatureValue) {
        Map<String, String> header = new HashMap<String, String>();
        header.put(Constants.CONTENT_TYPE_HEADER, "application/json; charset=UTF-8");
        header.put(Constants.REQ_TIME_HEADER, requestTime);
        header.put(Constants.CLIENT_ID_HEADER, clientId);
        if (keyVersion == null) {
            keyVersion = DEFAULT_KEY_VERSION;
        }
        String signatureHeader = "algorithm=RSA256,keyVersion=" + keyVersion + ",signature=" + signatureValue;
        header.put(Constants.REQ_SIGN_HEADER, signatureHeader);
        if (StringUtils.isNotBlank(agentToken)) {
            header.put(Constants.AGENT_TOKEN_HEADER, agentToken);
        }
        return header;
    }

    public abstract Map<String, String> buildCustomHeader();

    public abstract HttpRpcResult sendRequest(String requestUrl, String httpMethod, Map<String, String> header, String reqBody) throws AlipayApiException;

}
