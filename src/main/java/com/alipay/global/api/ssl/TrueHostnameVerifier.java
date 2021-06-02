package com.alipay.global.api.ssl;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

public class TrueHostnameVerifier implements HostnameVerifier {
    @Override
    public boolean verify(String s, SSLSession sslSession) {
        // 不允许URL的主机名和服务器的标识主机名不匹配的情况
        return false;
    }
}
