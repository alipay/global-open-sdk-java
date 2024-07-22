package com.alipay.global.api.model.ams;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MerchantInfo {
    private String referenceMerchantId;
    private String loginId;
    private String legalEntityType;
    private Company company;
    private BusinessInfo businessInfo;
    private EntityAssociations entityAssociations;


}
