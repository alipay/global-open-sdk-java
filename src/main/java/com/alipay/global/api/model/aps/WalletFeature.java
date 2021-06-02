package com.alipay.global.api.model.aps;

public class WalletFeature {

    private Boolean supportCodeScan;
    private Boolean supportCashierRedirection;

    public Boolean getSupportCodeScan() {
        return supportCodeScan;
    }

    public void setSupportCodeScan(Boolean supportCodeScan) {
        this.supportCodeScan = supportCodeScan;
    }

    public Boolean getSupportCashierRedirection() {
        return supportCashierRedirection;
    }

    public void setSupportCashierRedirection(Boolean supportCashierRedirection) {
        this.supportCashierRedirection = supportCashierRedirection;
    }

}
