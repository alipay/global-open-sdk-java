package com.alipay.global.api.response.ams.customs;

import java.util.List;

import com.alipay.global.api.model.ams.DeclarationRecord;
import com.alipay.global.api.response.AlipayResponse;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class AlipayCustomsQueryResponse extends AlipayResponse {

    private List<String>            declarationRequestsNotFound;

    private List<DeclarationRecord> declarationRecords;

}
