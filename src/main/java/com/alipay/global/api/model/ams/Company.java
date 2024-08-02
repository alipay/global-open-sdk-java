package com.alipay.global.api.model.ams;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Company {
    private String legalName;
    private CompanyType companyType;
    private Address registeredAddress;
    private Address operatingAddress;
    private String incorporationDate;
    private StockInfo stockInfo;
    private Certificate certificates;
    private List<Attachment> attachments;
    private CompanyUnitType companyUnit;
    private List<Contact> contacts;
    private String vatNo;
}
