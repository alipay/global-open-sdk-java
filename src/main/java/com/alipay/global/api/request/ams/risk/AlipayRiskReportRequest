package com.alipay.global.api.request.ams.risk;

import com.alipay.global.api.request.AlipayRequest;
import com.alipay.global.api.response.AlipayResponse;

import java.util.Date;

public class AlipayRiskReportRequest extends AlipayRequest<AlipayResponse> {
    /**
     * Universally unique transaction identifier.
     */
    private String referenceTransactionId;

    /**
     * This field can be used to capture the reason why this report is given.
     */
    private String reportReason;

    /**
     * The type of report:
     * <p> SUSPICIOUS</p>
     * <p> CHARGEBACK </p>
     * <p> FRAUD </p>
     */
    private String riskType;

    /**
     * Time when the risk event occurs.
     */
    private Date riskOccurrenceTime;

    public String getReferenceTransactionId() {
        return referenceTransactionId;
    }

    public void setReferenceTransactionId(String referenceTransactionId) {
        this.referenceTransactionId = referenceTransactionId;
    }

    public String getReportReason() {
        return reportReason;
    }

    public void setReportReason(String reportReason) {
        this.reportReason = reportReason;
    }

    public String getRiskType() {
        return riskType;
    }

    public void setRiskType(String riskType) {
        this.riskType = riskType;
    }

    public Date getRiskOccurrenceTime() {
        return riskOccurrenceTime;
    }

    public void setRiskOccurrenceTime(Date riskOccurrenceTime) {
        this.riskOccurrenceTime = riskOccurrenceTime;
    }

    @Override
    public Class<AlipayResponse> getResponseClass() {
        return AlipayResponse.class;
    }
}
