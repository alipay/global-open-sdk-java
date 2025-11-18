package com.alipay.global.api.response.ams.aba;

import com.alipay.global.api.model.ams.Statement;
import com.alipay.global.api.response.AlipayResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class AlipayInquiryStatementListResponse  extends AlipayResponse {
    private List<Statement> statementList;
}
