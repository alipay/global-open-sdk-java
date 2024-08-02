package com.alipay.global.api.model.ams;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MerchantInfo {
    private String referenceMerchantId;
    private String loginId;
    private LegalEntityType legalEntityType;
    private Company company;
    private BusinessInfo businessInfo;
    private List<EntityAssociations> entityAssociations;

}
