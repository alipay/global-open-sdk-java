package com.alipay.global.api.example.legacy;

import com.alipay.global.api.AlipayClient;
import com.alipay.global.api.DefaultAlipayClient;
import com.alipay.global.api.example.model.Callback;
import com.alipay.global.api.example.model.ResultCode;
import com.alipay.global.api.example.model.RetryResult;
import com.alipay.global.api.exception.AlipayApiException;
import com.alipay.global.api.model.Result;
import com.alipay.global.api.model.ResultStatusType;
import com.alipay.global.api.model.ams.*;
import com.alipay.global.api.model.constants.EndPointConstants;
import com.alipay.global.api.request.ams.auth.AlipayAuthApplyTokenRequest;
import com.alipay.global.api.request.ams.auth.AlipayAuthConsultRequest;
import com.alipay.global.api.request.ams.pay.AlipayPayCancelRequest;
import com.alipay.global.api.request.ams.pay.AlipayPayQueryRequest;
import com.alipay.global.api.request.ams.pay.AlipayPayRequest;
import com.alipay.global.api.response.ams.auth.AlipayAuthApplyTokenResponse;
import com.alipay.global.api.response.ams.auth.AlipayAuthConsultResponse;
import com.alipay.global.api.response.ams.pay.AlipayPayCancelResponse;
import com.alipay.global.api.response.ams.pay.AlipayPayQueryResponse;
import com.alipay.global.api.response.ams.pay.AlipayPayResponse;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * The demo Code mainly shows the correct use of API,
 * and the specific implementation needs to be implemented by merchants
 */
public class AgreementPayDemoCode {

    private static final Integer TIMEOUT_RETRY_COUNT = 3;
    private static final Integer CANCEL_RETRY_COUNT = 3;
    private static final Integer PAY_RETRY_COUNT = 3;
    private static final String GATE_WAY_URL = "";
    private static final String MERCHANT_PRIVATE_KEY = "";
    private static final String ANTOM_PUBLIC_KEY = "";
    private static final String CLIENT_ID = "";
    private static final String PAYMENT_REQUEST_ID = "";
    private final static AlipayClient CLIENT = new DefaultAlipayClient(
            EndPointConstants.SG, MERCHANT_PRIVATE_KEY, ANTOM_PUBLIC_KEY, CLIENT_ID);

    public static void main(String[] args) {

        // 1、/v1/authorizations/consult
        String authUrl = authConsult();
        // TODO 2、redirect to authUrl
        // TODO 3、user authorization
        // TODO 4、redirect to merchantUrl
        // TODO 5、receive authCode
        String authCode = "";
        // 6、/v1/authorizations/applyToken
        String accessToken = applyToken(authCode);
        // 7、/v1/payments/pay
        pay(accessToken);

    }

    public static void pay(final String accessToken) {

        RetryExecutor.execute(PAY_RETRY_COUNT, new Callback() {
            @Override
            public RetryResult doProcess() {
                final String paymentRequestId = PAYMENT_REQUEST_ID;
                AlipayPayResponse alipayPayResponse = pay(accessToken, paymentRequestId);
                if (alipayPayResponse == null) {
                    RetryExecutor.execute(CANCEL_RETRY_COUNT, new Callback() {

                        @Override
                        public RetryResult doProcess() {
                            AlipayPayCancelResponse alipayPayCancelResponse = cancel(
                                    paymentRequestId);
                            if (alipayPayCancelResponse == null) {
                                // TODO Cancel fail
                                return RetryResult.ofResult(false);
                            }
                            Result cancelResult = alipayPayCancelResponse.getResult();
                            if (ResultStatusType.U.equals(cancelResult.getResultStatus())) {
                                // TODO Retry cancel
                                return RetryResult.ofResult(true);
                            }
                            if (ResultStatusType.S.equals(cancelResult.getResultStatus())) {
                                // TODO Cancel success
                                return RetryResult.ofResult(false);
                            }
                            if (ResultStatusType.F.equals(cancelResult.getResultStatus())) {
                                // TODO Cancel fail,contact tech support
                                return RetryResult.ofResult(false);
                            }
                            return RetryResult.ofResult(false);
                        }
                    });

                    return RetryResult.ofResult(false);
                }
                Result payResult = alipayPayResponse.getResult();
                /**
                 * If payment is successful，proceed with other logic.
                 */
                if (ResultStatusType.S.equals(payResult.getResultStatus())) {
                    return RetryResult.ofResult(false);
                }
                /**
                 * If payment is failed，terminate this transaction.
                 */
                if (ResultStatusType.F.equals(payResult.getResultStatus())) {
                    return RetryResult.ofResult(false);
                }
                if (ResultStatusType.U.equals(payResult.getResultStatus())) {
                    if (!ResultCode.PAYMENT_IN_PROCESS.name().equals(payResult.getResultCode())) {
                        return RetryResult.ofResult(true);
                    }
                    TransactionStatusType paymentStatus = null;
                    /**
                     * Pause interval when the state is PROCESSING
                     */
                    ArrayList<Integer> pauseInterval = (ArrayList<Integer>) Arrays.asList(2, 4, 8,
                            16, 32);
                    /**
                     * Number of retries when the state is U
                     */
                    Integer QUERY_RETRY_COUNT = 10;

                    boolean isContinue = true;
                    AlipayPayQueryResponse alipayPayQueryResponse = null;
                    while (isContinue) {
                        alipayPayQueryResponse = inquiryPayment(paymentRequestId);
                        if (alipayPayQueryResponse == null) {
                            break;
                        }
                        Result payQueryResult = alipayPayQueryResponse.getResult();
                        if (ResultStatusType.F.equals(payQueryResult.getResultStatus())) {
                            // TODO Contact tech support
                            return RetryResult.ofResult(false);
                        }
                        if (ResultStatusType.U.equals(payQueryResult.getResultStatus())) {
                            --QUERY_RETRY_COUNT;
                            if (QUERY_RETRY_COUNT > 0) {
                                continue;
                            } else {
                                isContinue = false;
                                continue;
                            }
                        }
                        paymentStatus = alipayPayQueryResponse.getPaymentStatus();
                        if (TransactionStatusType.PROCESSING.equals(paymentStatus)) {
                            if (!pauseInterval.isEmpty()) {
                                long sleepTime = pauseInterval.remove(0);
                                // TODO to wait sleepTime
                            }
                            isContinue = false;
                            continue;
                        } else {
                            isContinue = false;
                        }
                    }
                    /**
                     * If the result is still unavailable after retry, cancel the payment.
                     */
                    if (alipayPayQueryResponse == null
                            || TransactionStatusType.PROCESSING.equals(paymentStatus)) {

                        RetryExecutor.execute(CANCEL_RETRY_COUNT, new Callback() {

                            @Override
                            public RetryResult doProcess() {
                                AlipayPayCancelResponse alipayPayCancelResponse = cancel(
                                        paymentRequestId);
                                if (alipayPayCancelResponse == null) {
                                    // TODO Cancel fail
                                    return RetryResult.ofResult(false);
                                }
                                Result cancelResult = alipayPayCancelResponse.getResult();
                                if (ResultStatusType.U.equals(cancelResult.getResultStatus())) {
                                    // TODO Retry cancel
                                    return RetryResult.ofResult(true);
                                }
                                if (ResultStatusType.S.equals(cancelResult.getResultStatus())) {
                                    // TODO Cancel success
                                    return RetryResult.ofResult(false);
                                }
                                if (ResultStatusType.F.equals(cancelResult.getResultStatus())) {
                                    // TODO Cancel fail,contact tech support
                                    return RetryResult.ofResult(false);
                                }
                                return RetryResult.ofResult(false);
                            }
                        });

                        return RetryResult.ofResult(false);
                    }
                    /**
                     * The transaction failed，terminate this transaction.
                     */
                    if (TransactionStatusType.FAIL.equals(paymentStatus)) {
                        // TODO Payment fail
                        return RetryResult.ofResult(false);
                    }
                    /**
                     * The transaction cancelled，terminate this transaction.
                     */
                    if (TransactionStatusType.CANCELLED.equals(paymentStatus)) {
                        // TODO Payment cancel
                        return RetryResult.ofResult(false);
                    }
                    /**
                     * The transaction succeeded，proceed with the other logic.
                     */
                    if (TransactionStatusType.SUCCESS.equals(paymentStatus)) {
                        // TODO Payment success
                        return RetryResult.ofResult(false);
                    }
                }

                return RetryResult.ofResult(false);
            }
        });

    }

    public static AlipayPayResponse pay(String accessToken, String paymentRequestId) {

        final AlipayPayRequest alipayPayRequest = new AlipayPayRequest();
        alipayPayRequest.setProductCode(ProductCodeType.AGREEMENT_PAYMENT);
        alipayPayRequest.setPaymentRequestId(paymentRequestId);

        Amount paymentAmount = new Amount();
        paymentAmount.setCurrency("PHP");
        paymentAmount.setValue("30000");
        alipayPayRequest.setPaymentAmount(paymentAmount);

        Order order = new Order();
        order.setReferenceOrderId("102775765075669");
        order.setOrderDescription(
                "Mi Band 3 Wrist Strap Metal Screwless Stainless Steel For Xiaomi Mi Band 3");

        Merchant merchant = new Merchant();
        merchant.setMerchantMCC("Test");
        merchant.setReferenceMerchantId("MXDTXSETGLKJASELKJG");
        order.setMerchant(merchant);

        Amount orderAmount = new Amount();
        orderAmount.setCurrency("PHP");
        orderAmount.setValue("30000");
        order.setOrderAmount(orderAmount);
        alipayPayRequest.setOrder(order);

        PaymentMethod paymentMethod = new PaymentMethod();
        paymentMethod.setPaymentMethodType(WalletPaymentMethodType.GCASH.name());
        paymentMethod.setPaymentMethodId(accessToken);
        alipayPayRequest.setPaymentMethod(paymentMethod);

        Env env = new Env();
        env.setTerminalType(TerminalType.APP);
        env.setOsType(OsType.IOS);

        order.setEnv(env);

        alipayPayRequest.setPaymentNotifyUrl("https://www.gcash.com/notify");
        alipayPayRequest.setPaymentRedirectUrl("https://www.gcash.com?param1=sl");

        SettlementStrategy settlementStrategy = new SettlementStrategy();
        settlementStrategy.setSettlementCurrency("USD");
        alipayPayRequest.setSettlementStrategy(settlementStrategy);

        Object obj = RetryExecutor.execute(TIMEOUT_RETRY_COUNT, new Callback() {
            @Override
            public RetryResult doProcess() {
                AlipayPayResponse alipayPayResponse = null;
                try {
                    alipayPayResponse = CLIENT.execute(alipayPayRequest);
                } catch (AlipayApiException e) {
                    String errorMsg = e.getMessage();
                    if (errorMsg.indexOf("SocketTimeoutException") > 0) {
                        // TODO timeout retry and log
                        return RetryResult.ofResult(true);
                    } else {
                        // TODO Handle AlipayApiException and log
                        return RetryResult.ofResult(false);
                    }
                }
                return RetryResult.ofResult(false, alipayPayResponse);
            }
        });

        return obj == null ? null : (AlipayPayResponse) obj;
    }

    public static AlipayPayQueryResponse inquiryPayment(String paymentRequestId) {
        final AlipayPayQueryRequest alipayPayQueryRequest = new AlipayPayQueryRequest();
        alipayPayQueryRequest.setPaymentRequestId(paymentRequestId);

        Object obj = RetryExecutor.execute(TIMEOUT_RETRY_COUNT, new Callback() {
            @Override
            public RetryResult doProcess() {
                AlipayPayQueryResponse alipayPayQueryResponse = null;
                try {
                    alipayPayQueryResponse = CLIENT.execute(alipayPayQueryRequest);
                } catch (AlipayApiException e) {
                    String errorMsg = e.getMessage();
                    if (errorMsg.indexOf("SocketTimeoutException") > 0) {
                        // TODO timeout retry and log
                        return RetryResult.ofResult(true);
                    } else {
                        // TODO Handle AlipayApiException and log
                        return RetryResult.ofResult(false);
                    }
                }
                return RetryResult.ofResult(false, alipayPayQueryResponse);
            }
        });

        return obj == null ? null : (AlipayPayQueryResponse) obj;
    }

    public static AlipayPayCancelResponse cancel(String paymentRequestId) {
        final AlipayPayCancelRequest alipayPayCancelRequest = new AlipayPayCancelRequest();
        alipayPayCancelRequest.setPaymentRequestId(paymentRequestId);

        Object obj = RetryExecutor.execute(TIMEOUT_RETRY_COUNT, new Callback() {
            @Override
            public RetryResult doProcess() {
                AlipayPayCancelResponse alipayPayCancelResponse = null;
                try {
                    alipayPayCancelResponse = CLIENT.execute(alipayPayCancelRequest);
                } catch (AlipayApiException e) {
                    String errorMsg = e.getMessage();
                    if (errorMsg.indexOf("SocketTimeoutException") > 0) {
                        // TODO timeout retry and log
                        return RetryResult.ofResult(true);
                    } else {
                        // TODO Handle AlipayApiException and log
                        return RetryResult.ofResult(false);
                    }
                }
                return RetryResult.ofResult(false, alipayPayCancelResponse);
            }
        });

        return obj == null ? null : (AlipayPayCancelResponse) obj;
    }

    public static String applyToken(String authCode) {

        final AlipayAuthApplyTokenRequest applyTokenRequest = new AlipayAuthApplyTokenRequest();
        applyTokenRequest.setGrantType(GrantType.AUTHORIZATION_CODE);
        applyTokenRequest.setCustomerBelongsTo(CustomerBelongsTo.BKASH);
        applyTokenRequest.setAuthCode(authCode);
        applyTokenRequest.setMerchantRegion("US");

        Object obj = RetryExecutor.execute(TIMEOUT_RETRY_COUNT, new Callback() {
            @Override
            public RetryResult doProcess() {
                AlipayAuthApplyTokenResponse alipayAuthApplyTokenResponse = null;
                try {
                    alipayAuthApplyTokenResponse = CLIENT.execute(applyTokenRequest);
                } catch (AlipayApiException e) {
                    String errorMsg = e.getMessage();
                    if (errorMsg.indexOf("SocketTimeoutException") > 0) {
                        // TODO timeout retry and log
                        return RetryResult.ofResult(true);
                    } else {
                        // TODO Handle AlipayApiException and log
                        return RetryResult.ofResult(false);
                    }
                }
                return RetryResult.ofResult(false, alipayAuthApplyTokenResponse);
            }
        });

        // TODO alipayAuthApplyTokenResponse insert DB
        return obj == null ? null : ((AlipayAuthApplyTokenResponse) obj).getAccessToken();
    }

    public static String authConsult() {
        final AlipayAuthConsultRequest authConsultRequest = new AlipayAuthConsultRequest();
        authConsultRequest.setAuthRedirectUrl("https://www.taobao.com/?param1=567&param2=123");
        authConsultRequest.setAuthState("663A8FA9D83656EE8AA1dd6F6F682ff989DC7");
        authConsultRequest.setCustomerBelongsTo(CustomerBelongsTo.GCASH);
        authConsultRequest.setOsType(OsType.ANDROID);
        authConsultRequest.setOsVersion("6.6.6");
        ScopeType[] scopes = {ScopeType.USER_LOGIN_ID};
        authConsultRequest.setScopes(scopes);
        authConsultRequest.setTerminalType(TerminalType.APP);
        authConsultRequest.setMerchantRegion("US");

        Object obj = RetryExecutor.execute(TIMEOUT_RETRY_COUNT, new Callback() {
            @Override
            public RetryResult doProcess() {
                AlipayAuthConsultResponse alipayAuthConsultResponse = null;
                try {
                    alipayAuthConsultResponse = CLIENT.execute(authConsultRequest);
                } catch (AlipayApiException e) {
                    String errorMsg = e.getMessage();
                    if (errorMsg.indexOf("SocketTimeoutException") > 0) {
                        // TODO timeout retry and log
                        return RetryResult.ofResult(true);
                    } else {
                        // TODO Handle AlipayApiException and log
                        return RetryResult.ofResult(false);
                    }
                }
                return RetryResult.ofResult(false, alipayAuthConsultResponse);
            }
        });

        if (obj != null) {
            AlipayAuthConsultResponse alipayAuthConsultResponse = (AlipayAuthConsultResponse) obj;
            String authUrl = alipayAuthConsultResponse.getAuthUrl();

            return authUrl;
        }

        return null;
    }

}
