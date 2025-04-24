/*
 * Ant Group
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package com.alipay.global.api.request.ams.risk;

import com.alipay.global.api.model.ams.Amount;
import com.alipay.global.api.model.constants.AntomPathConstants;
import com.alipay.global.api.model.risk.RefundRecord;
import com.alipay.global.api.request.AlipayRequest;
import com.alipay.global.api.response.ams.risk.SendRefundResultResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * The request of Ant Group's send refund result API.
 * 调用蚂蚁集团退款结果通知接口的请求参数。
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SendRefundResultRequest extends AlipayRequest<SendRefundResultResponse> {
    /**
     * A unique ID assigned to a merchant who provides a service or product directly to a customer and is used to identify the transaction.
     * 直接向买家提供服务或商品的商户分配的唯一ID，用于识别交易。
     */
    private String referenceTransactionId;
    /**
     * A unique ID assigned by the merchant who provides the service or product directly to the buyer to identify the refund.
     * 由直接向买家提供服务或商品的商户分配的识别退款的唯一ID 。
     */
    private String referenceRefundId;
    /**
     * The total amount of the actual refund.
     * 实际退款总额。
     */
    private Amount actualRefundAmount;
    /**
     * Refund history for this transaction.
     * 本次交易的退款记录。
     */
    private List<RefundRecord> refundRecords;

    public SendRefundResultRequest() {
        this.setPath(AntomPathConstants.RISK_SEND_REFUND_RESULT_PATH);
    }

    @Override
    public boolean usingSandboxUrl() {
        return false;
    }

    @Override
    public Class<SendRefundResultResponse> getResponseClass() {
        return SendRefundResultResponse.class;
    }
}