package com.alipay.global.api.model.ams;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EntityAssociations {
    private String      associationType;
    private String      legalEntityType;
    private Company     company;
    private Individual  individual;
    private String      shareholdingRatio;
}
