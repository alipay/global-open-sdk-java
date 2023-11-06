/*
 * Ant Group
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package com.alipay.global.api.request.ams.risk;

import com.alipay.global.api.model.ams.Amount;
import com.alipay.global.api.model.ams.Buyer;
import com.alipay.global.api.model.ams.Env;
import com.alipay.global.api.model.risk.AuthorizationPhase;
import com.alipay.global.api.model.risk.Order;
import com.alipay.global.api.model.risk.PaymentDetail;
import com.alipay.global.api.request.AlipayRequest;
import com.alipay.global.api.response.ams.risk.RiskDecideResponse;

import java.util.List;

/**
 * The request of Ant Group's risk decide API.
 * 调用蚂蚁集团风控实时决策接口的请求参数。
 */
public class RiskDecideRequest extends AlipayRequest<RiskDecideResponse> {
    /**
     * A unique ID assigned to a merchant who provides a service or product directly to a customer and is used to identify the transaction.
     * 直接向买家提供服务或商品的商户分配的唯一ID，用于识别交易。
     */
    private String              referenceTransactionId;
    /**
     * The stage at which the API is called. Valid values are as follows:
     * PRE_AUTHORIZATION: Indicates that you initiated this call before the card payment was authorized.
     * POST_AUTHORIZATION : Indicates that you initiated this call after the card payment was authorized
     * 调用 API 的阶段。有效值为：
     * PRE_AUTHORIZATION：表示您在卡支付授权之前发起此请求。
     * POST_AUTHORIZATION ：表示您在卡支付授权后发起此请求。
     */
    private AuthorizationPhase  authorizationPhase;
    /**
     * Order information, including merchant, product, amount, and shipping information.
     * 订单信息，包括商户、商品、金额和运输信息等。
     */
    private List<Order>         orders;
    /**
     * Buyer information, including the buyer's ID, name, phone number, email, etc.
     * 买家信息，包括买家的 ID、姓名、电话号码和电子邮件等。
     */
    private Buyer               buyer;
    /**
     * The amount that the buyer actually needs to pay after deducting the discount.
     * 扣除折扣后买家实际需要支付的金额。
     */
    private Amount              actualPaymentAmount;
    /**
     * Payment method details, including payment method type, card information, etc.
     * 支付方式详细信息，包括支付方式类型、卡信息等。
     */
    private List<PaymentDetail> paymentDetails;
    /**
     * The amount of the discount for this transaction.
     * 本次交易的折扣金额。
     */
    private Amount              discountAmount;
    /**
     * Information about the environment in which the order was placed, such as device information.
     * 下单环境信息，例如设备信息。
     */
    private Env                 env;

    public String getReferenceTransactionId() {
        return referenceTransactionId;
    }

    public void setReferenceTransactionId(String referenceTransactionId) {
        this.referenceTransactionId = referenceTransactionId;
    }

    public AuthorizationPhase getAuthorizationPhase() {
        return authorizationPhase;
    }

    public void setAuthorizationPhase(AuthorizationPhase authorizationPhase) {
        this.authorizationPhase = authorizationPhase;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    public Amount getActualPaymentAmount() {
        return actualPaymentAmount;
    }

    public void setActualPaymentAmount(Amount actualPaymentAmount) {
        this.actualPaymentAmount = actualPaymentAmount;
    }

    public List<PaymentDetail> getPaymentDetails() {
        return paymentDetails;
    }

    public void setPaymentDetails(List<PaymentDetail> paymentDetails) {
        this.paymentDetails = paymentDetails;
    }

    public Amount getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(Amount discountAmount) {
        this.discountAmount = discountAmount;
    }

    public Env getEnv() {
        return env;
    }

    public void setEnv(Env env) {
        this.env = env;
    }

    @Override
    public Class<RiskDecideResponse> getResponseClass() {
        return RiskDecideResponse.class;
    }
}