package com.alipay.global.api.model.ams;


public class Certificate {

    private CertificateType     certificateType;
    private String              certificateNo;
    private UserName            holderName;

    public CertificateType getCertificateType() {
        return certificateType;
    }

    public void setCertificateType(CertificateType certificateType) {
        this.certificateType = certificateType;
    }

    public String getCertificateNo() {
        return certificateNo;
    }

    public void setCertificateNo(String certificateNo) {
        this.certificateNo = certificateNo;
    }

    public UserName getHolderName() {
        return holderName;
    }

    public void setHolderName(UserName holderName) {
        this.holderName = holderName;
    }
}
