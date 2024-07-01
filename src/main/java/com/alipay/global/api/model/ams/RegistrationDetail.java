package com.alipay.global.api.model.ams;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationDetail {

    private String legalName;
    private List<Attachment> attachments;
    private List<ContactInfo> contactInfo;
    private String registrationType;
    private String registrationNo;
    private Address registrationAddress;
    private String businessType;
    private Date registrationEffectiveDate;
    private Date registrationExpireDate;

}
