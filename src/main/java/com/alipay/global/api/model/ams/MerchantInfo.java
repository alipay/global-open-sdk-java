package com.alipay.global.api.model.ams;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MerchantInfo {
    private String                   referenceMerchantId;
    private String                   loginId;
    private LegalEntityType          legalEntityType;
    private Company                  company;
    private BusinessInfo             businessInfo;
    private List<EntityAssociations> entityAssociations;

}
