package com.alipay.global.api.example.model;

import java.io.InputStream;

public interface HttpRequest {

    String getHeader(String name);

    InputStream getInputStream();

}
