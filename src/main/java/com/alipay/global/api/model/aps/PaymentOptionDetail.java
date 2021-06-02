package com.alipay.global.api.model.aps;

public class PaymentOptionDetail {

    private PaymentOptionDetailType paymentOptionDetailType;
    private WalletPaymentOptionDetail connectWallet;

    public PaymentOptionDetailType getPaymentOptionDetailType() {
        return paymentOptionDetailType;
    }

    public void setPaymentOptionDetailType(PaymentOptionDetailType paymentOptionDetailType) {
        this.paymentOptionDetailType = paymentOptionDetailType;
    }

    public WalletPaymentOptionDetail getConnectWallet() {
        return connectWallet;
    }

    public void setConnectWallet(WalletPaymentOptionDetail connectWallet) {
        this.connectWallet = connectWallet;
    }

}
