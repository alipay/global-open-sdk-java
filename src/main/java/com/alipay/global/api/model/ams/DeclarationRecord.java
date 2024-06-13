package com.alipay.global.api.model.ams;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeclarationRecord {

    private String declarationRequestId;
    private String customsPaymentId;
    private String customsOrderId;
    private CustomsInfo customs;
    private MerchantCustomsInfo merchantCustomsInfo;
    private Amount declarationAmount;
    private Boolean splitOrder;
    private String declarationRequestStatus;
    private String lastModifiedTime;
    private String customsDeclarationResultCode;
    private String customsDeclarationResultDesc;
    private String customsDeclarationReturnTime;

}
