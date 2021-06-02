package com.alipay.global.api.model.aps;

public class Wallet {

    private String        walletName;
    private String        walletBrandName;
    private Logo          walletLogo;
    private String        walletRegion;
    private WalletFeature walletFeature;

    public String getWalletName() {
        return walletName;
    }

    public void setWalletName(String walletName) {
        this.walletName = walletName;
    }

    public String getWalletBrandName() {
        return walletBrandName;
    }

    public void setWalletBrandName(String walletBrandName) {
        this.walletBrandName = walletBrandName;
    }

    public Logo getWalletLogo() {
        return walletLogo;
    }

    public void setWalletLogo(Logo walletLogo) {
        this.walletLogo = walletLogo;
    }

    public String getWalletRegion() {
        return walletRegion;
    }

    public void setWalletRegion(String walletRegion) {
        this.walletRegion = walletRegion;
    }

    public WalletFeature getWalletFeature() {
        return walletFeature;
    }

    public void setWalletFeature(WalletFeature walletFeature) {
        this.walletFeature = walletFeature;
    }

}
