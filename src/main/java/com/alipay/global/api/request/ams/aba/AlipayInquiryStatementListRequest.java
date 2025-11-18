package com.alipay.global.api.request.ams.aba;

import com.alipay.global.api.model.constants.AntomPathConstants;
import com.alipay.global.api.request.AlipayRequest;
import com.alipay.global.api.response.ams.aba.AlipayInquiryStatementListResponse;
import com.alipay.global.api.response.ams.auth.AlipayAuthConsultResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class AlipayInquiryStatementListRequest extends AlipayRequest<AlipayInquiryStatementListResponse> {

    private String customerId;
    private String accessToken;
    private String startTime;
    private String endTime;
    private List<String> transactionTypeList;
    private List<String> currencyList;
    private String pageSize;
    private String pageNumber;

    public AlipayInquiryStatementListRequest() {
        this.setPath(AntomPathConstants.ABA_INQUERY_STATEMENT_LIST_PATH);
    }

    @Override
    public Class<AlipayInquiryStatementListResponse> getResponseClass() {
        return AlipayInquiryStatementListResponse.class;
    }
}
