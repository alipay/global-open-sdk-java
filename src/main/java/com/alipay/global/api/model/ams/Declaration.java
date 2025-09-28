package com.alipay.global.api.model.ams;


import lombok.*;

/** Declaration */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Declaration {

    private DeclarationBizSceneType declarationBizScene;

    private String declarationBeneficiaryId;
}