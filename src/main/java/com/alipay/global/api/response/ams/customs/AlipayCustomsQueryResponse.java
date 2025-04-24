package com.alipay.global.api.response.ams.customs;

import com.alipay.global.api.model.ams.DeclarationRecord;
import com.alipay.global.api.response.AlipayResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class AlipayCustomsQueryResponse extends AlipayResponse {

    private List<String> declarationRequestsNotFound;

    private List<DeclarationRecord> declarationRecords;

}
