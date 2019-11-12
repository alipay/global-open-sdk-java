package com.alipay.api.net;

import com.alipay.api.exception.AlipayApiException;
import org.apache.commons.lang3.StringUtils;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URL;
import java.util.Map;
import java.util.Set;

public class DefaultHttpRPC {

    private static int readTimeout    = 15000;
    private static int connectTimeout = 3000;

    public static final String DEFAULT_CHARSET  = "UTF-8";

    public static HttpRpcResult doPost(String url, Map<String, String> header, String reqBody) throws IOException, AlipayApiException {
        String ctype = "application/x-www-form-urlencoded;charset=" + DEFAULT_CHARSET;
        byte[] content = reqBody.getBytes(DEFAULT_CHARSET);

        return doPost(url, ctype, header, content);
    }

    private static HttpRpcResult doPost(String url, String ctype, Map<String, String> reqHeader, byte[] content) throws IOException, AlipayApiException {
        HttpsURLConnection conns = null;
        OutputStream out = null;
        HttpRpcResult rsp = new HttpRpcResult();
        try {
            try {
                conns = getConnection(new URL(url), HttpMethod.POST.name(), ctype);
                conns.setConnectTimeout(connectTimeout);
                conns.setReadTimeout(readTimeout);

                Set<Map.Entry<String, String>> headerEntry = reqHeader.entrySet();
                for(Map.Entry<String, String> entry: headerEntry){
                   String headerName = entry.getKey();
                   String headerValue = entry.getValue();
                    conns.setRequestProperty(headerName, headerValue);
                }
            } catch (IOException e) {
                throw e;
            }

            try {
                out = conns.getOutputStream();
                out.write(content);

                String rspSignValue = getResponseSignature(conns);
                rsp.setRspSign(rspSignValue);
                String responseTime = getResponseTime(conns);
                rsp.setResponseTime(responseTime);
                String rspBody = getResponseAsString(conns);
                rsp.setRspBody(rspBody);

            } catch (IOException e) {
                throw e;
            }

        } finally {
            if (out != null) {
                out.close();
            }
            if (conns != null) {
                conns.disconnect();

            }
        }

        return rsp;
    }


    private static HttpsURLConnection getConnection(URL url, String method, String ctype) throws IOException, AlipayApiException {
        HttpsURLConnection connHttps = null;
        if ("https".equals(url.getProtocol())) {
            connHttps = (HttpsURLConnection) url.openConnection();
        } else {
            throw new AlipayApiException("Only supports HTTPS.");
        }

        connHttps.setRequestMethod(method);
        connHttps.setDoInput(true);
        connHttps.setDoOutput(true);
        connHttps.setRequestProperty("Accept", "text/plain,text/xml,text/javascript,text/html");
        connHttps.setRequestProperty("User-Agent", "sandbox-sdk-java");
        connHttps.setRequestProperty("Content-Type", ctype);
        return connHttps;
    }

    public static String getResponseSignature(HttpsURLConnection conn) {

        String signatureValue = conn.getHeaderField("signature");
        if(StringUtils.isBlank(signatureValue)){
            return null;
        }

        String[] valueItem = signatureValue.split(",");
        if(valueItem.length < 3){
            return null;
        }

        String signatureItem = valueItem[2];
        String[]  itemArr = signatureItem.split("=");
        if(itemArr.length != 2){
            return null;
        }

        return itemArr[1];
    }

    public static String getResponseTime(HttpsURLConnection conn) {

        String responseTime = conn.getHeaderField("response-time");

        return responseTime;
    }


    public static String getResponseAsString(HttpsURLConnection conn) throws IOException {
        String charset = getResponseCharset(conn.getContentType());

        InputStream es = conn.getErrorStream();
        if (es == null) {
            return getStreamAsString(conn.getInputStream(), charset);
        } else {
            String msg = getStreamAsString(es, charset);
            if (StringUtils.isEmpty(msg)) {
                throw new IOException(conn.getResponseCode() + ":" + conn.getResponseMessage());
            } else {
                throw new IOException(msg);
            }
        }
    }

    private static String getResponseCharset(String ctype) {
        String charset = DEFAULT_CHARSET;

        if (!StringUtils.isEmpty(ctype)) {
            String[] params = ctype.split(";");
            for (String param : params) {
                param = param.trim();
                if (param.startsWith("charset")) {
                    String[] pair = param.split("=", 2);
                    if (pair.length == 2) {
                        if (!StringUtils.isEmpty(pair[1])) {
                            charset = pair[1].trim();
                        }
                    }
                    break;
                }
            }
        }

        return charset;
    }

    private static String getStreamAsString(InputStream stream, String charset) throws IOException {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream, charset));
            StringWriter writer = new StringWriter();

            char[] chars = new char[256];
            int count = 0;
            while ((count = reader.read(chars)) > 0) {
                writer.write(chars, 0, count);
            }

            return writer.toString();
        } finally {
            if (stream != null) {
                stream.close();
            }
        }
    }

}
