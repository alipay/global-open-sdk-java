package com.alipay.global.api.model.aps;

import java.util.List;

public class WalletPaymentOptionDetail {

    private List<Wallet> supportWallets;

    public List<Wallet> getSupportWallets() {
        return supportWallets;
    }

    public void setSupportWallets(List<Wallet> supportWallets) {
        this.supportWallets = supportWallets;
    }

}
