package com.alipay.global.api;

import com.alibaba.fastjson.JSON;
import com.alipay.global.api.exception.AlipayApiException;
import com.alipay.global.api.model.Result;
import com.alipay.global.api.model.ResultStatusType;
import com.alipay.global.api.net.HttpRpcResult;
import com.alipay.global.api.request.AlipayRequest;
import com.alipay.global.api.response.AlipayResponse;
import com.alipay.global.api.tools.SignatureTool;
import com.alipay.global.api.tools.DateTool;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

public abstract class BaseAlipayClient implements AlipayClient{

    /**
     * eg:https://open-na.alipay.com
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

    public BaseAlipayClient(){
    }

    public BaseAlipayClient(String gatewayUrl, String merchantPrivateKey, String alipayPublicKey ){
        this.gatewayUrl = gatewayUrl;
        this.merchantPrivateKey = merchantPrivateKey;
        this.alipayPublicKey = alipayPublicKey;
    }

    public <T extends AlipayResponse> T execute(AlipayRequest<T> alipayRequest) throws AlipayApiException {

        checkRequestParam(alipayRequest);

        String clientId    = alipayRequest.getClientId();
        String httpMethod  = alipayRequest.getHttpMethod();
        String path        = alipayRequest.getPath();
        String reqTime     = DateTool.getCurISO8601Time();
        String reqBody     = JSON.toJSONString(alipayRequest);

        /**
         * 对内容加签(Sign the content)
         */
        String signValue = genSignValue(httpMethod, path, clientId, reqTime, reqBody);
        /**
         *  生成必要header(Generate required headers)
         */
        Map<String, String> header       = buildBaseHeader(reqTime, clientId, signValue);
        Map<String, String> customHeader = buildCustomHeader();
        if(customHeader != null && customHeader.size() > 0){
            header.putAll(customHeader);
        }

        String requestUrl = genRequestUrl(path);
        /**
         * 向网关发起http请求(Make an HTTP request to the gateway)
         */
        HttpRpcResult rsp = sendRequest(requestUrl, httpMethod, header, reqBody);

        if(rsp == null){
            throw new AlipayApiException("HttpRpcResult is null.");
        }

        String   rspBody         = rsp.getRspBody();
        Class<T> responseClass   = alipayRequest.getResponseClass();
        T        alipayResponse  = JSON.parseObject(rspBody, responseClass);
        Result result          = alipayResponse.getResult();
        if(result == null){
            throw new AlipayApiException("Response data error.");
        }

        ResultStatusType rStatus = result.getResultStatus();
        if(ResultStatusType.F.equals(rStatus) || ResultStatusType.U.equals(rStatus)){
            return alipayResponse;
        }

        String rspSignValue = rsp.getRspSign();
        String rspTime      = rsp.getResponseTime();
        /**
         * 对返回结果验签(Verify the result signature)
         */
        boolean isVerifySuccess = checkRspSign(httpMethod, path, clientId, rspTime, rspBody, rspSignValue);
        if(!isVerifySuccess){
            throw new AlipayApiException("Response sign verify fail.");
        }

        return alipayResponse;
    }

    private String genSignValue(String httpMethod, String path, String clientId, String requestTime, String reqBody)throws AlipayApiException{
        String signContent = SignatureTool.genSignConent(httpMethod, path, clientId, requestTime, reqBody);
        String signatureValue;
        try{
            signatureValue = SignatureTool.sign(signContent, merchantPrivateKey);
        } catch(Exception e){
            throw new AlipayApiException(e);
        }
        return signatureValue;
    }

    private boolean checkRspSign(String httpMethod, String path, String clientId, String responseTime, String rspBody, String rspSignValue) throws AlipayApiException{
        String rspCheckSignValue = SignatureTool.genSignConent(httpMethod, path, clientId, responseTime, rspBody);
        try{
            boolean isVerify = SignatureTool.verify(rspCheckSignValue, rspSignValue, alipayPublicKey);
            return isVerify;
        } catch(Exception e){
            throw new AlipayApiException(e);
        }

    }

    private void checkRequestParam(AlipayRequest alipayRequest) throws AlipayApiException{
        String clientId = alipayRequest.getClientId();
        String httpMehod = alipayRequest.getHttpMethod();
        String path = alipayRequest.getPath();

        if(StringUtils.isBlank(gatewayUrl)){
            throw new AlipayApiException("gatewayUrl can't null");
        }

        if(StringUtils.isBlank(clientId)){
            throw new AlipayApiException("clientId can't null");
        }

        if(StringUtils.isBlank(httpMehod)){
            throw new AlipayApiException("httpMehod can't null");
        }

        if(StringUtils.isBlank(path)){
            throw new AlipayApiException("path can't null");
        }

        if(!path.startsWith("/")){
            throw new AlipayApiException("path must start with /");
        }

    }

    private String genRequestUrl(String path){
        if(!gatewayUrl.startsWith("http://") && !gatewayUrl.startsWith("https://")){
            gatewayUrl = "https://" + gatewayUrl;
        }
        if(gatewayUrl.endsWith("/")){
            int len = gatewayUrl.length();
            gatewayUrl = gatewayUrl.substring(0, len - 1);
        }
        String requestUrl = gatewayUrl + path;
        return requestUrl;

    }

    private Map<String,String> buildBaseHeader(String requestTime, String clientId, String signatureValue){
        Map<String, String> header = new HashMap<String, String>();
        header.put("Content-Type", "application/json; charset=UTF-8");
        header.put("Request-Time", requestTime);
        header.put("client-id", clientId);
        String signatureHeader = "algorithm=RSA256,keyVersion=2,signature=" + signatureValue;
        header.put("Signature", signatureHeader);
        return header;
    }

    public abstract Map<String,String> buildCustomHeader();

    public abstract HttpRpcResult sendRequest(String requestUrl, String httpMethod, Map<String, String> header, String reqBody)throws AlipayApiException;


}
