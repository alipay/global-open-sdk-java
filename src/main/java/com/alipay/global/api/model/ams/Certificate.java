package com.alipay.global.api.model.ams;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Certificate {

    private CertificateType certificateType;
    private String certificateNo;
    private UserName holderName;
    private List<String> fileKeys;
    private String certificateAuthority;

}
