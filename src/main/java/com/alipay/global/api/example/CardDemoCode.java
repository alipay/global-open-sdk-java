package com.alipay.global.api.example;

import com.alipay.global.api.AlipayClient;
import com.alipay.global.api.DefaultAlipayClient;
import com.alipay.global.api.exception.AlipayApiException;
import com.alipay.global.api.model.ams.AuthorizationControl;
import com.alipay.global.api.model.ams.CardLimitInfo;
import com.alipay.global.api.model.constants.EndPointConstants;
import com.alipay.global.api.request.ams.aba.AlipayApplyCardRequest;
import com.alipay.global.api.request.ams.aba.AlipayInquireCardDetailRequest;
import com.alipay.global.api.request.ams.aba.AlipayInquireCardRequest;
import com.alipay.global.api.request.ams.aba.AlipayInquireCardSensitiveInfoRequest;
import com.alipay.global.api.request.ams.aba.AlipayUpdateCardRequest;
import com.alipay.global.api.request.ams.aba.AlipayUpdateCardStatusRequest;
import com.alipay.global.api.response.ams.aba.AlipayApplyCardResponse;
import com.alipay.global.api.response.ams.aba.AlipayInquireCardDetailResponse;
import com.alipay.global.api.response.ams.aba.AlipayInquireCardResponse;
import com.alipay.global.api.response.ams.aba.AlipayInquireCardSensitiveInfoResponse;
import com.alipay.global.api.response.ams.aba.AlipayUpdateCardResponse;
import com.alipay.global.api.response.ams.aba.AlipayUpdateCardStatusResponse;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CardDemoCode {

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
    // Case 1: Apply card without cardBinRule
    applyCardWithoutCardBinRule();

    // Case 2: Apply card with cardBinRule
    applyCardWithCardBinRule();

    // Inquire card list
    inquireCard();

    // Inquire card detail
    inquireCardDetail();

    // Inquire card sensitive info
    inquireCardSensitiveInfo();

    // Update card
    updateCard();

    // Update card status
    updateCardStatus();
  }

  /** Case 1: Apply card without cardBinRule */
  public static void applyCardWithoutCardBinRule() {
    AlipayApplyCardRequest request = new AlipayApplyCardRequest();
    request.setRequestId("ff477093-8102-47cd-b214-93f5766d92ad");
    request.setCardNickName("Travel card");
    request.setNote("Travel card");

    // Set authorization control
    AuthorizationControl authorizationControl = new AuthorizationControl();
    authorizationControl.setCardActiveTime("2025-12-01T00:00:00-00:00");
    authorizationControl.setCardCancelTime("2025-12-31T00:00:00-00:00");
    authorizationControl.setAllowedAuthTimes(10);
    authorizationControl.setAllowedCurrencies(Arrays.asList("USD", "EUR"));

    // Set card limit info
    CardLimitInfo cardLimitInfo = new CardLimitInfo();
    cardLimitInfo.setCurrency("USD");
    cardLimitInfo.setDailyLimitMax("1000000");
    cardLimitInfo.setMonthlyLimitMax("10000000");
    cardLimitInfo.setPerTransactionLimitMax("100000");
    cardLimitInfo.setPerCardLimitMax("10000000");
    authorizationControl.setCardLimitInfo(cardLimitInfo);

    request.setAuthorizationControl(authorizationControl);

    Map<String, String> metadata = new HashMap<>();
    metadata.put("FIGHT_NO", "SQ1111");
    request.setMetadata(metadata);

    AlipayApplyCardResponse response = null;
    try {
      response = CLIENT.execute(request);
    } catch (AlipayApiException e) {
      String errorMsg = e.getMessage();
      // handle error condition
    }

    System.out.println("Apply Card Without CardBinRule Response: " + response);
  }

  /** Case 2: Apply card with cardBinRule */
  public static void applyCardWithCardBinRule() {
    AlipayApplyCardRequest request = new AlipayApplyCardRequest();
    request.setRequestId("f051ed48-b545-4c5e-af5f-a95634312ec3");
    request.setCardBinRule("WorldCardABA001");
    request.setCardNickName("Travel card");
    request.setNote("Travel card");

    // Set authorization control
    AuthorizationControl authorizationControl = new AuthorizationControl();
    authorizationControl.setCardActiveTime("2025-12-01T00:00:00-00:00");
    authorizationControl.setCardCancelTime("2025-12-31T00:00:00-00:00");
    authorizationControl.setAllowedAuthTimes(10);
    authorizationControl.setAllowedCurrencies(Arrays.asList("USD", "EUR"));

    // Set card limit info
    CardLimitInfo cardLimitInfo = new CardLimitInfo();
    cardLimitInfo.setCurrency("USD");
    cardLimitInfo.setDailyLimitMax("1000000");
    cardLimitInfo.setMonthlyLimitMax("10000000");
    cardLimitInfo.setPerTransactionLimitMax("100000");
    cardLimitInfo.setPerCardLimitMax("10000000");
    authorizationControl.setCardLimitInfo(cardLimitInfo);

    request.setAuthorizationControl(authorizationControl);

    Map<String, String> metadata = new HashMap<>();
    metadata.put("FIGHT_NO", "SQ1111");
    request.setMetadata(metadata);

    AlipayApplyCardResponse response = null;
    try {
      response = CLIENT.execute(request);
    } catch (AlipayApiException e) {
      String errorMsg = e.getMessage();
      // handle error condition
    }

    System.out.println("Apply Card With CardBinRule Response: " + response);
  }

  /** Inquire card listÏ */
  public static void inquireCard() {
    AlipayInquireCardRequest request = new AlipayInquireCardRequest();
    request.setPageNumber(1);
    request.setPageSize(5);
    request.setCardStatus(AlipayInquireCardRequest.CardStatusEnum.ACTIVE);

    AlipayInquireCardResponse response = null;
    try {
      response = CLIENT.execute(request);
    } catch (AlipayApiException e) {
      String errorMsg = e.getMessage();
      // handle error condition
    }

    System.out.println("Inquire Card Response: " + response);
  }

  /** Inquire card detail */
  public static void inquireCardDetail() {
    AlipayInquireCardDetailRequest request = new AlipayInquireCardDetailRequest();
    request.setAssetId("2025120429027200120386700007321");

    AlipayInquireCardDetailResponse response = null;
    try {
      response = CLIENT.execute(request);
    } catch (AlipayApiException e) {
      String errorMsg = e.getMessage();
      // handle error condition
    }

    System.out.println("Inquire Card Detail Response: " + response);
  }

  /** Inquire card sensitive info */
  public static void inquireCardSensitiveInfo() {
    AlipayInquireCardSensitiveInfoRequest request = new AlipayInquireCardSensitiveInfoRequest();
    request.setAssetId("2025120429027200120386700007321");

    AlipayInquireCardSensitiveInfoResponse response = null;
    try {
      response = CLIENT.execute(request);
    } catch (AlipayApiException e) {
      String errorMsg = e.getMessage();
      // handle error condition
    }

    System.out.println("Inquire Card Sensitive Info Response: " + response);
  }

  /** Update card */
  public static void updateCard() {
    AlipayUpdateCardRequest request = new AlipayUpdateCardRequest();
    request.setRequestId("388d5b6a-b193-4989-9825-b8527eb0e9ed");
    request.setAssetId("2025120429027200120386700007321");

    Map<String, String> metadata = new HashMap<>();
    metadata.put("hotelId", "1526086");
    metadata.put("hotelName", "Pullman Paris Montparnasse");
    request.setMetadata(metadata);

    AlipayUpdateCardResponse response = null;
    try {
      response = CLIENT.execute(request);
    } catch (AlipayApiException e) {
      String errorMsg = e.getMessage();
      // handle error condition
    }

    System.out.println("Update Card Response: " + response);
  }

  /** Update card status - UNFREEZE */
  public static void updateCardStatus() {
    // Example 1: Unfreeze card
    updateCardStatusUnfreeze();

    // Example 2: Freeze card
    updateCardStatusFreeze();

    // Example 3: Cancel card
    updateCardStatusCancel();
  }

  /** Update card status - UNFREEZE */
  public static void updateCardStatusUnfreeze() {
    AlipayUpdateCardStatusRequest request = new AlipayUpdateCardStatusRequest();
    request.setRequestId("d665b22c-eeeb-4379-abea-4e0d13085969");
    request.setAssetId("2025120429027200120386700007321");
    request.setOperateType(AlipayUpdateCardStatusRequest.OperateTypeEnum.UNFREEZE);
    request.setNotifyUrl("https://webhook-test.com");

    AlipayUpdateCardStatusResponse response = null;
    try {
      response = CLIENT.execute(request);
    } catch (AlipayApiException e) {
      String errorMsg = e.getMessage();
      // handle error condition
    }

    System.out.println("Update Card Status (UNFREEZE) Response: " + response);
  }

  /** Update card status - FREEZE */
  public static void updateCardStatusFreeze() {
    AlipayUpdateCardStatusRequest request = new AlipayUpdateCardStatusRequest();
    request.setRequestId("d665b22c-eeeb-4379-abea-4e0d13085970");
    request.setAssetId("2025120429027200120386700007321");
    request.setOperateType(AlipayUpdateCardStatusRequest.OperateTypeEnum.FREEZE);
    request.setNotifyUrl("https://webhook-test.com");

    AlipayUpdateCardStatusResponse response = null;
    try {
      response = CLIENT.execute(request);
    } catch (AlipayApiException e) {
      String errorMsg = e.getMessage();
      // handle error condition
    }

    System.out.println("Update Card Status (FREEZE) Response: " + response);
  }

  /** Update card status - CANCEL */
  public static void updateCardStatusCancel() {
    AlipayUpdateCardStatusRequest request = new AlipayUpdateCardStatusRequest();
    request.setRequestId("d665b22c-eeeb-4379-abea-4e0d13085971");
    request.setAssetId("2025120429027200120386700007321");
    request.setOperateType(AlipayUpdateCardStatusRequest.OperateTypeEnum.CANCEL);
    request.setNotifyUrl("https://webhook-test.com");

    AlipayUpdateCardStatusResponse response = null;
    try {
      response = CLIENT.execute(request);
    } catch (AlipayApiException e) {
      String errorMsg = e.getMessage();
      // handle error condition
    }

    System.out.println("Update Card Status (CANCEL) Response: " + response);
  }
}
