package com.alipay.global.api.response.ams.customs;


import com.alipay.global.api.model.ams.DeclarationRecord;
import com.alipay.global.api.response.AlipayResponse;

import java.util.List;

public class AlipayCustomsQueryResponse extends AlipayResponse {

    private List<String> declarationRequestsNotFound;

    private List<DeclarationRecord> declarationRecords;

    public List<String> getDeclarationRequestsNotFound() {
        return declarationRequestsNotFound;
    }

    public void setDeclarationRequestsNotFound(List<String> declarationRequestsNotFound) {
        this.declarationRequestsNotFound = declarationRequestsNotFound;
    }

    public List<DeclarationRecord> getDeclarationRecords() {
        return declarationRecords;
    }

    public void setDeclarationRecords(List<DeclarationRecord> declarationRecords) {
        this.declarationRecords = declarationRecords;
    }
}
