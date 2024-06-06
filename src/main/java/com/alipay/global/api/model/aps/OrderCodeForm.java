package com.alipay.global.api.model.aps;

import com.alipay.global.api.model.ams.CodeDetail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
