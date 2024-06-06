package com.alipay.global.api.model.aps;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CodeDetail {

    private CodeValueType codeValueType;
    private String codeValue;
    private DisplayType displayType;

}
