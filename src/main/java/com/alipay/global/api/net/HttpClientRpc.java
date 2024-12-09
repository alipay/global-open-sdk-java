package com.alipay.global.api.net;

import com.alipay.global.api.tools.Constants;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

public class HttpClientRpc {


    public static HttpRpcResult doPost(CloseableHttpClient httpClient, String url, Map<String, String> header, String jsonPayload) {
        HttpRpcResult httpRpcResult = new HttpRpcResult();

        // 创建HttpClient实例
        try {
            // 创建POST请求
            HttpPost postRequest = new HttpPost(url);
            postRequest.addHeader("Connection", "keep-alive");
            postRequest.setHeader("Content-Type", "application/json");

            // 设置请求体
            StringEntity entity = new StringEntity(jsonPayload);
            postRequest.setEntity(entity);

            // 设置请求头
            if (header != null) {
                for (Map.Entry<String, String> entry : header.entrySet()) {
                    postRequest.setHeader(entry.getKey(), entry.getValue());
                }
            }

            // 执行请求
            try (CloseableHttpResponse response = httpClient.execute(postRequest)) {
                // 获取HTTP响应代码
                int httpRespCode = response.getStatusLine().getStatusCode();
                httpRpcResult.setRspCode(httpRespCode);

                Header connectionHeader = response.getFirstHeader("Connection");
                if (connectionHeader != null) {
                    System.out.println("Connection Header: " + connectionHeader.getValue());
                }

                // 获取响应内容
                String responseBody = EntityUtils.toString(response.getEntity());
                httpRpcResult.setRspBody(responseBody);

                // 提取响应签名和响应时间
                httpRpcResult.setRspSign(getResponseSignature(response));

                // 此处假设您有某种方法来获取响应时间
                String responseTime = Optional.ofNullable(response.getFirstHeader(Constants.RSP_TIME_HEADER))
                                    .map(Header::getValue).orElse(null);
                httpRpcResult.setResponseTime(responseTime);
            }
        } catch (IOException e) {
            e.printStackTrace();
            httpRpcResult.setRspCode(500); // 可以设置为合适的错误代码
            httpRpcResult.setRspBody("Error: " + e.getMessage());
        }

        return httpRpcResult;
    }


    public static String getResponseSignature(CloseableHttpResponse response) {
        String signatureValue = Optional.ofNullable(response.getFirstHeader("Signature"))
                                    .map(Header::getValue).orElse(null);
        if (StringUtils.isBlank(signatureValue)) {
            return null;
        }

        String[] valueItem = signatureValue.split(",");
        if (valueItem.length < 3) {
            return null;
        }

        String signatureItem = valueItem[2];
        String[] itemArr = signatureItem.split("=");
        if (itemArr.length != 2) {
            return null;
        }

        return itemArr[1];
    }
}
