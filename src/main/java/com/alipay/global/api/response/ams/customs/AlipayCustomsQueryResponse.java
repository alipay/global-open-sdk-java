package com.alipay.global.api.response.ams.customs;

import com.alipay.global.api.model.ams.DeclarationRecord;
import com.alipay.global.api.response.AlipayResponse;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AlipayCustomsQueryResponse extends AlipayResponse {

  private List<String> declarationRequestsNotFound;

  private List<DeclarationRecord> declarationRecords;
}
