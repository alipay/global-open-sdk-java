/*
 * Ant Group
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package com.alipay.global.api.example;

import com.alipay.global.api.AlipayClient;
import com.alipay.global.api.DefaultAlipayClient;
import com.alipay.global.api.exception.AlipayApiException;
import com.alipay.global.api.model.ams.*;
import com.alipay.global.api.model.constants.EndPointConstants;
import com.alipay.global.api.model.risk.*;
import com.alipay.global.api.model.risk.Merchant;
import com.alipay.global.api.model.risk.Order;
import com.alipay.global.api.model.risk.PaymentMethod;
import com.alipay.global.api.request.ams.risk.RiskDecideRequest;
import com.alipay.global.api.request.ams.risk.RiskReportRequest;
import com.alipay.global.api.request.ams.risk.SendPaymentResultRequest;
import com.alipay.global.api.request.ams.risk.SendRefundResultRequest;
import com.alipay.global.api.response.ams.risk.RiskDecideResponse;
import com.alipay.global.api.response.ams.risk.RiskReportResponse;
import com.alipay.global.api.response.ams.risk.SendPaymentResultResponse;
import com.alipay.global.api.response.ams.risk.SendRefundResultResponse;
import java.util.Collections;
import java.util.Date;

public class RiskDecideDemoCode {
  /**
   * replace with your client id. find your client id here: <a
   * href="https://dashboard.alipay.com/global-payments/developers/quickStart">quickStart</a>
   */
  public static final String CLIENT_ID = "";

  /**
   * replace with your antom public key (used to verify signature). find your antom public key here:
   * <a href="https://dashboard.alipay.com/global-payments/developers/quickStart">quickStart</a>
   */
  public static final String ANTOM_PUBLIC_KEY = "";

  /**
   * replace with your private key (used to sign). please ensure the secure storage of your private
   * key to prevent leakage
   */
  public static final String MERCHANT_PRIVATE_KEY = "";

  /**
   * please replace with your endpoint. find your endpoint here: <a
   * href="https://dashboard.alipay.com/global-payments/developers/quickStart">quickStart</a>
   */
  private static final AlipayClient CLIENT =
      new DefaultAlipayClient(
          EndPointConstants.SG, MERCHANT_PRIVATE_KEY, ANTOM_PUBLIC_KEY, CLIENT_ID);

  public static void main(String[] args) {
    preAuthDecide();
  }

  public static RiskDecideResponse preAuthDecide() {
    RiskDecideRequest request = new RiskDecideRequest();
    request.setReferenceTransactionId("test_20231012091493242");
    request.setAuthorizationPhase(AuthorizationPhase.PRE_AUTHORIZATION);
    buildRiskDecideRequest(request);

    RiskDecideResponse response = null;
    try {
      response = CLIENT.execute(request);
    } catch (AlipayApiException e) {
      // TODO Handle AlipayApiException and log
    }
    return response;
  }

  public static RiskDecideResponse postAuthDecide() {
    RiskDecideRequest request = new RiskDecideRequest();
    request.setReferenceTransactionId("test_20231012091493242");
    request.setAuthorizationPhase(AuthorizationPhase.POST_AUTHORIZATION);
    buildRiskDecideRequest(request);
    PaymentDetail paymentDetail = request.getPaymentDetails().get(0);
    PaymentMethodMetaData paymentMethodMetaData =
        paymentDetail.getPaymentMethod().getPaymentMethodMetaData();
    CardVerificationResult cardVerificationResult = new CardVerificationResult();
    cardVerificationResult.setAuthenticationType("3D");
    cardVerificationResult.setAuthorizationCode("10000");
    RiskThreeDSResult riskThreeDSResult = new RiskThreeDSResult();
    riskThreeDSResult.setEci("00");
    riskThreeDSResult.setThreeDSVersion("2.0");
    riskThreeDSResult.setCavv("0");
    riskThreeDSResult.setThreeDSInteractionMode("CHALLENGE");
    cardVerificationResult.setThreeDSResult(riskThreeDSResult);
    paymentMethodMetaData.setCardVerificationResult(cardVerificationResult);

    RiskDecideResponse response = null;
    try {
      response = CLIENT.execute(request);
    } catch (AlipayApiException e) {
      // TODO Handle AlipayApiException and log
    }
    return response;
  }

  public static SendPaymentResultResponse sendPaymentResult() {
    SendPaymentResultRequest request = new SendPaymentResultRequest();
    request.setReferenceTransactionId("test_20231012091493242");

    request.setPaymentStatus("SUCCESS");
    request.setReferenceTransactionId("test_20231012091493242");
    CardVerificationResult cardVerificationResult = new CardVerificationResult();
    cardVerificationResult.setAuthenticationType("3D");
    cardVerificationResult.setAuthorizationCode("10000");
    RiskThreeDSResult threeDSResult = new RiskThreeDSResult();
    threeDSResult.setEci("05");
    threeDSResult.setThreeDSVersion("2.0");
    threeDSResult.setCavv("0");
    threeDSResult.setThreeDSInteractionMode("CHALLENGE");
    cardVerificationResult.setThreeDSResult(threeDSResult);
    request.setCardVerificationResult(cardVerificationResult);
    SendPaymentResultResponse response = null;
    try {
      response = CLIENT.execute(request);
    } catch (AlipayApiException e) {
      // TODO Handle AlipayApiException and log
    }
    return response;
  }

  public static SendRefundResultResponse sendPaymentRefund() {
    SendRefundResultRequest request = new SendRefundResultRequest();
    request.setReferenceTransactionId("test_20231012091493242");

    SendRefundResultResponse response = null;
    try {
      response = CLIENT.execute(request);
    } catch (AlipayApiException e) {
      // TODO Handle AlipayApiException and log
    }
    return response;
  }

  public static RiskReportResponse reportRisk() {
    RiskReportRequest request = new RiskReportRequest();
    request.setReferenceTransactionId("test_20231012091493242");
    request.setReportReason("test");
    request.setRiskType("FRAUD");
    request.setRiskOccurrenceTime(new Date());

    RiskReportResponse response = null;
    try {
      response = CLIENT.execute(request);
    } catch (AlipayApiException e) {
      // TODO Handle AlipayApiException and log
    }
    return response;
  }

  public static void buildRiskDecideRequest(RiskDecideRequest request) {
    Order order = new Order();
    order.setReferenceOrderId("test_202310120914932421");
    Amount orderAmount = new Amount();
    orderAmount.setCurrency("BRL");
    orderAmount.setValue("30000");
    order.setOrderAmount(orderAmount);
    order.setOrderDescription("Cappuccino #grande (Mika's coffee shop)");
    Merchant merchant = new Merchant();
    merchant.setReferenceMerchantId("SM_001");
    order.setMerchant(merchant);
    Goods goods = new Goods();
    goods.setReferenceGoodsId("383382011_SGAMZ-904520356");
    goods.setGoodsName(
        "[3 Boxes] Starbucks Cappuccino Milk Coffee Pods / Coffee Capsules by Nescafe Dolce Gusto");
    goods.setGoodsCategory("Digital  Goods/Digital  Vouchers/Food  and Beverages");
    goods.setDeliveryMethodType("DIGITAL");
    goods.setGoodsQuantity("1");
    Amount goodsAmount = new Amount();
    goodsAmount.setValue("30000");
    goodsAmount.setCurrency("BRL");
    goods.setGoodsUnitAmount(goodsAmount);
    order.setGoods(Collections.singletonList(goods));
    Shipping shipping = new Shipping();
    shipping.setShippingCarrier("FedEx");
    shipping.setShippingPhoneNo("12345678912");
    shipping.setShipToEmail("fenghoureject@163.com");
    UserName shippingName = new UserName();
    shippingName.setFirstName("Dehua");
    shippingName.setLastName("Liu");
    shippingName.setMiddleName("Skr");
    shippingName.setFullName("Dehua Skr Liu");
    shipping.setShippingName(shippingName);
    Address shippingAddress = new Address();
    shippingAddress.setRegion("CN");
    shippingAddress.setState("Zhejiang");
    shippingAddress.setCity("Hangzhou");
    shippingAddress.setAddress1("Wuchang road");
    shippingAddress.setAddress2("Xihu");
    shippingAddress.setZipCode("310000");
    shipping.setShippingAddress(shippingAddress);
    order.setShipping(shipping);
    request.setOrders(Collections.singletonList(order));

    Buyer buyer = new Buyer();
    buyer.setReferenceBuyerId("test12345678");
    buyer.setBuyerPhoneNo("12345678912");
    buyer.setBuyerEmail("alipay@alipay.com");
    buyer.setIsAccountVerified(true);
    buyer.setSuccessfulOrderCount(100);
    UserName buyerName = new UserName();
    buyerName.setFirstName("Dehua");
    buyerName.setLastName("Liu");
    buyerName.setMiddleName("Skr");
    buyerName.setFullName("Dehua Skr Liu");
    buyer.setBuyerName(buyerName);
    request.setBuyer(buyer);

    Amount actualPaymentAmount = new Amount();
    actualPaymentAmount.setCurrency("BRL");
    actualPaymentAmount.setValue("300000");
    request.setActualPaymentAmount(actualPaymentAmount);

    PaymentDetail paymentDetail = new PaymentDetail();
    Amount paymentAmount = new Amount();
    paymentAmount.setCurrency("BRL");
    paymentAmount.setValue("300000");
    paymentDetail.setAmount(paymentAmount);
    PaymentMethod paymentMethod = new PaymentMethod();
    paymentMethod.setPaymentMethodId("0656XXXXXXX0001");
    paymentMethod.setPaymentMethodType("CARD");
    PaymentMethodMetaData paymentMethodMetaData = new PaymentMethodMetaData();
    paymentMethodMetaData.setCpf("02987654320");
    paymentMethodMetaData.setExpiryMonth("12");
    paymentMethodMetaData.setExpiryYear("29");
    paymentMethodMetaData.setCardNo("4117347806156383");
    UserName cardHolderName = new UserName();
    cardHolderName.setFirstName("Tom");
    cardHolderName.setLastName("Jay");
    paymentMethodMetaData.setCardHolderName(cardHolderName);
    Address billingAddress = new Address();
    billingAddress.setRegion("CN");
    billingAddress.setState("Zhejiang");
    billingAddress.setCity("Hangzhou");
    billingAddress.setAddress1("test_address1");
    billingAddress.setAddress2("test_address2");
    billingAddress.setZipCode("310000");
    paymentMethodMetaData.setBillingAddress(billingAddress);
    paymentMethod.setPaymentMethodMetaData(paymentMethodMetaData);
    paymentDetail.setPaymentMethod(paymentMethod);
    request.setPaymentDetails(Collections.singletonList(paymentDetail));

    Env env = new Env();
    env.setTerminalType(TerminalType.APP);
    env.setClientIp("00.00.00.00");
    env.setOsType(OsType.IOS);
    env.setDeviceLanguage("zh_CN");
    env.setDeviceId("test_deviceId");
    request.setEnv(env);

    Amount discountAmount = new Amount();
    discountAmount.setCurrency("BRL");
    discountAmount.setValue("0");
    request.setDiscountAmount(discountAmount);
  }
}
