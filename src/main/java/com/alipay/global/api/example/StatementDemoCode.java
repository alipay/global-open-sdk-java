package com.alipay.global.api.example;

import com.alipay.global.api.AlipayClient;
import com.alipay.global.api.DefaultAlipayClient;
import com.alipay.global.api.exception.AlipayApiException;
import com.alipay.global.api.model.constants.EndPointConstants;
import com.alipay.global.api.request.ams.aba.AlipayInquiryStatementDetailRequest;
import com.alipay.global.api.request.ams.aba.AlipayInquiryStatementListRequest;
import com.alipay.global.api.request.ams.aba.AlipayInquiryStatementRequest;
import com.alipay.global.api.response.ams.aba.AlipayInquiryStatementDetailResponse;
import com.alipay.global.api.response.ams.aba.AlipayInquiryStatementListResponse;
import com.alipay.global.api.response.ams.aba.AlipayInquiryStatementResponse;
import java.util.Arrays;

public class StatementDemoCode {

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
    // General Query
    inquiryStatementGeneral();

    // General Query with Currency Filter
    inquiryStatementWithCurrencyFilter();

    // General Query with Transaction Type Filter
    inquiryStatementWithTransactionTypeFilter();

    // Query with an ID (fuzzyName)
    inquiryStatementWithFuzzyName();

    // Inquiry Statement Detail
    inquiryStatementDetail();
  }

  /**
   * General Query
   */
  public static void inquiryStatementGeneral() {
    AlipayInquiryStatementRequest request = new AlipayInquiryStatementRequest();
    request.setStartTime("2025-10-01T00:00:00");
    request.setEndTime("2025-12-30T00:00:00");
    request.setPageSize(30);
    request.setPageNumber(1);

    AlipayInquiryStatementResponse response = null;
    try {
      response = CLIENT.execute(request);
    } catch (AlipayApiException e) {
      String errorMsg = e.getMessage();
      // handle error condition
    }

    System.out.println("Inquiry Statement General Response: " + response);
  }

  /**
   * General Query with Currency Filter
   */
  public static void inquiryStatementWithCurrencyFilter() {
    AlipayInquiryStatementRequest request = new AlipayInquiryStatementRequest();
    request.setStartTime("2025-10-01T00:00:00");
    request.setEndTime("2025-12-30T00:00:00");
    request.setPageSize(30);
    request.setPageNumber(1);
    request.setCurrencyList(Arrays.asList("CNY", "USD"));

    AlipayInquiryStatementResponse response = null;
    try {
      response = CLIENT.execute(request);
    } catch (AlipayApiException e) {
      String errorMsg = e.getMessage();
      // handle error condition
    }

    System.out.println("Inquiry Statement With Currency Filter Response: " + response);
  }

  /**
   * General Query with Transaction Type Filter
   */
  public static void inquiryStatementWithTransactionTypeFilter() {
    AlipayInquiryStatementRequest request = new AlipayInquiryStatementRequest();
    request.setStartTime("2025-10-01T00:00:00");
    request.setEndTime("2025-12-30T00:00:00");
    request.setPageSize(30);
    request.setPageNumber(1);
    request.setTransactionTypeList(Arrays.asList(
        AlipayInquiryStatementRequest.TransactionTypeListEnum.TRANSFER,
        AlipayInquiryStatementRequest.TransactionTypeListEnum.CARD_PAYMENT
    ));

    AlipayInquiryStatementResponse response = null;
    try {
      response = CLIENT.execute(request);
    } catch (AlipayApiException e) {
      String errorMsg = e.getMessage();
      // handle error condition
    }

    System.out.println("Inquiry Statement With Transaction Type Filter Response: " + response);
  }

  /**
   * Query with an ID (fuzzyName)
   */
  public static void inquiryStatementWithFuzzyName() {
    AlipayInquiryStatementListRequest request = new AlipayInquiryStatementListRequest();
    request.setPageSize("30");
    request.setPageNumber("1");
    request.setFuzzyName("id123");

    AlipayInquiryStatementListResponse response = null;
    try {
      response = CLIENT.execute(request);
    } catch (AlipayApiException e) {
      String errorMsg = e.getMessage();
      // handle error condition
    }

    System.out.println("Inquiry Statement With FuzzyName Response: " + response);
  }

  /**
   * Inquiry Statement Detail
   */
  public static void inquiryStatementDetail() {
    AlipayInquiryStatementDetailRequest request = new AlipayInquiryStatementDetailRequest();
    request.setStatementId("id123");

    AlipayInquiryStatementDetailResponse response = null;
    try {
      response = CLIENT.execute(request);
    } catch (AlipayApiException e) {
      String errorMsg = e.getMessage();
      // handle error condition
    }

    System.out.println("Inquiry Statement Detail Response: " + response);
  }
}