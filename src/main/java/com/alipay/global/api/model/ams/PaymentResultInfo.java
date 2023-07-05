package com.alipay.global.api.model.ams;

/**
 * @Author yanhong
 * @version $Id: PaymentResultInfo.java, v 0.1 2023年07月05日 6:02 PM yanhong Exp $
 **/
public class PaymentResultInfo {

    /**
     * The raw AVS result. See AVS result codes to check the valid values
     */
    private String avsResultRaw;

    /**
     * The raw Card Verification Value (CVV), Card Security Code (CSC), or Card Verification Code (CVC) result
     */
    private String cvvResultRaw;

    /**
     * The unique ID assigned by the card scheme to identify a transaction. The value of this parameter is used by the same parameter of pay (Cashier Payment) request in subsequent payments
     */
    private String networkTransactionId;

    public String getAvsResultRaw() {
        return avsResultRaw;
    }

    public void setAvsResultRaw(String avsResultRaw) {
        this.avsResultRaw = avsResultRaw;
    }

    public String getCvvResultRaw() {
        return cvvResultRaw;
    }

    public void setCvvResultRaw(String cvvResultRaw) {
        this.cvvResultRaw = cvvResultRaw;
    }

    public String getNetworkTransactionId() {
        return networkTransactionId;
    }

    public void setNetworkTransactionId(String networkTransactionId) {
        this.networkTransactionId = networkTransactionId;
    }
}
