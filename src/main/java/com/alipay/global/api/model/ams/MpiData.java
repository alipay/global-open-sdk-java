package com.alipay.global.api.model.ams;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MpiData {
    private String threeDSVersion;
    private String eci;
    private String cavv;
    private String dsTransactionId;
}
