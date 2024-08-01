package com.alipay.global.api.model.ams;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RedirectActionForm {

    private MethodType method;
    private String parameters;
    private String redirectUrl;

    private String actionFormType;

}
