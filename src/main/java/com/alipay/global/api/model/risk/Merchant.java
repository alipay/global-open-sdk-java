/*
 * Ant Group
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package com.alipay.global.api.model.risk;

import com.alipay.global.api.model.ams.Address;

import java.util.Date;

/**
 * Secondary merchant information.
 * 二级商户信息
 */
public class Merchant {
    /**
     * The ID of the merchant that identifies the service or product that is provided directly to the customer.
     * 用于标识直接向买家提供服务或商品的商户的 ID。
     */
    private String  referenceMerchantId;
    /**
     * Secondary merchant category code
     * 二级商户类别码
     */
    private String  merchantMCC;
    /**
     * Merchant's name.
     * 商户名称
     */
    private String  merchantName;
    /**
     * Displays the name of the merchant used.
     * 显示使用的商户名称
     */
    private String  merchantDisplayName;
    /**
     * Merchant's address.
     * 商户地址
     */
    private Address merchantAddress;
    /**
     * Merchant registration date
     * 商户注册日期
     */
    private Date    merchantRegistrationTime;
    /**
     * Merchant's email.
     * 商户邮箱
     */
    private String  merchantEmail;

    public String getReferenceMerchantId() {
        return referenceMerchantId;
    }

    public void setReferenceMerchantId(String referenceMerchantId) {
        this.referenceMerchantId = referenceMerchantId;
    }

    public String getMerchantMCC() {
        return merchantMCC;
    }

    public void setMerchantMCC(String merchantMCC) {
        this.merchantMCC = merchantMCC;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getMerchantDisplayName() {
        return merchantDisplayName;
    }

    public void setMerchantDisplayName(String merchantDisplayName) {
        this.merchantDisplayName = merchantDisplayName;
    }

    public Address getMerchantAddress() {
        return merchantAddress;
    }

    public void setMerchantAddress(Address merchantAddress) {
        this.merchantAddress = merchantAddress;
    }

    public Date getMerchantRegistrationTime() {
        return merchantRegistrationTime;
    }

    public void setMerchantRegistrationTime(Date merchantRegistrationTime) {
        this.merchantRegistrationTime = merchantRegistrationTime;
    }

    public String getMerchantEmail() {
        return merchantEmail;
    }

    public void setMerchantEmail(String merchantEmail) {
        this.merchantEmail = merchantEmail;
    }
}