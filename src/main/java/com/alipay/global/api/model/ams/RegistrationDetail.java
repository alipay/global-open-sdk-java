package com.alipay.global.api.model.ams;




import java.util.Date;
import java.util.List;


public class RegistrationDetail {


    private String            legalName;
    private List<Attachment>  attachments;
    private List<ContactInfo> contactInfo;
    private String            registrationType;
    private String            registrationNo;
    private Address           registrationAddress;
    private String            businessType;
    private Date              registrationEffectiveDate;
    private Date              registrationExpireDate;

    public String getLegalName() {
        return legalName;
    }

    public void setLegalName(String legalName) {
        this.legalName = legalName;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }

    public List<ContactInfo> getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(List<ContactInfo> contactInfo) {
        this.contactInfo = contactInfo;
    }

    public String getRegistrationType() {
        return registrationType;
    }

    public void setRegistrationType(String registrationType) {
        this.registrationType = registrationType;
    }

    public String getRegistrationNo() {
        return registrationNo;
    }

    public void setRegistrationNo(String registrationNo) {
        this.registrationNo = registrationNo;
    }

    public Address getRegistrationAddress() {
        return registrationAddress;
    }

    public void setRegistrationAddress(Address registrationAddress) {
        this.registrationAddress = registrationAddress;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public Date getRegistrationEffectiveDate() {
        return registrationEffectiveDate;
    }

    public void setRegistrationEffectiveDate(Date registrationEffectiveDate) {
        this.registrationEffectiveDate = registrationEffectiveDate;
    }

    public Date getRegistrationExpireDate() {
        return registrationExpireDate;
    }

    public void setRegistrationExpireDate(Date registrationExpireDate) {
        this.registrationExpireDate = registrationExpireDate;
    }
}
