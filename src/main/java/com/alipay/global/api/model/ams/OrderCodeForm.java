package com.alipay.global.api.model.ams;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderCodeForm {

    private String paymentMethodType;
    private String expireTime;
    private List<CodeDetail> codeDetails;
    private String extendInfo;

}
