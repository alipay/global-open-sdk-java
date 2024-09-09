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
import com.alipay.global.api.request.ams.pay.AlipayPayCancelRequest;
import com.alipay.global.api.request.ams.pay.AlipayPayQueryRequest;
import com.alipay.global.api.request.ams.pay.AlipayPayRequest;
import com.alipay.global.api.response.ams.pay.AlipayPayCancelResponse;
import com.alipay.global.api.response.ams.pay.AlipayPayQueryResponse;
import com.alipay.global.api.response.ams.pay.AlipayPayResponse;

import java.util.Arrays;
import java.util.List;

/**
 * The demo code mainly shows how to use the API correctly and the specific implementation needs to be performed by merchants.
 */
public class CashierPayDemoCode {

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

        RetryExecutor.execute(PAY_RETRY_COUNT, new Callback() {

            @Override
            public RetryResult doProcess() {
                final String paymentRequestId = PAYMENT_REQUEST_ID;
                AlipayPayResponse alipayPayResponse = pay(paymentRequestId);
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
                                // TODO check times, retry cancel
                                return RetryResult.ofResult(true);
                            }
                            if (ResultStatusType.S.equals(cancelResult.getResultStatus())) {
                                // TODO Cancel success
                                return RetryResult.ofResult(false);
                            }
                            if (ResultStatusType.F.equals(cancelResult.getResultStatus())) {
                                // TODO Cancel fail,Contact tech support
                                return RetryResult.ofResult(false);
                            }
                            return RetryResult.ofResult(false);
                        }
                    });

                    return RetryResult.ofResult(false);
                }
                Result payResult = alipayPayResponse.getResult();
                /**
                 * If payment failed，terminate this transaction.
                 */
                if (ResultStatusType.F.equals(payResult.getResultStatus())) {
                    // TODO Payment fail
                    return RetryResult.ofResult(false);
                }
                if (ResultStatusType.U.equals(payResult.getResultStatus())) {
                    if (!ResultCode.PAYMENT_IN_PROCESS.name().equals(payResult.getResultCode())) {
                        return RetryResult.ofResult(true);
                    }
                    RedirectActionForm redirectActionForm = alipayPayResponse
                            .getRedirectActionForm();
                    String redirectUrl = redirectActionForm.getRedirectUrl();
                    String method = redirectActionForm.getMethod();
                    String parameters = redirectActionForm.getParameters();
                    // TODO 1、Jump to the checkout page
                    // TODO 2、Wait notify，eg: PayNotifyListener
                    // TODO 3、Start the asynchronous task to query the payment results
                    asyncQueryTask(paymentRequestId);
                }
                return RetryResult.ofResult(false);
            }
        });

    }

    public static void asyncQueryTask(final String paymentRequestId) {
        // 1、TODO Wait 3 minutes for execution

        String orderStatus = TransactionStatusType.PROCESSING.name();
        // 2、TODO Get order status from merchant's DB

        /**
         * Is it in process?
         */
        if (!TransactionStatusType.PROCESSING.name().equals(orderStatus)) {
            return;
        }
        /**
         * Pause interval when the state is PROCESSING
         */
        List<Integer> pauseInterval = Arrays.asList(2, 4, 8, 16, 32, 64, 128, 256, 512);
        boolean isContinue = true;
        Integer QUERY_RETRY_COUNT = 10;
        TransactionStatusType paymentStatus = null;
        AlipayPayQueryResponse alipayPayQueryResponse = null;
        while (isContinue) {
            alipayPayQueryResponse = inquiryPayment(paymentRequestId);
            if (alipayPayQueryResponse == null) {
                return;
            }
            Result payQueryResult = alipayPayQueryResponse.getResult();
            if (ResultStatusType.F.equals(payQueryResult.getResultStatus())) {
                // TODO Contact tech support
                return;
            }
            if (ResultStatusType.U.equals(payQueryResult.getResultStatus())) {
                // TODO retry queryPay
                --QUERY_RETRY_COUNT;
                if (QUERY_RETRY_COUNT > 0) {
                    continue;
                } else {
                    isContinue = false;
                    continue;
                }
            }
            paymentStatus = alipayPayQueryResponse.getPaymentStatus();
            if (paymentStatus != null) {
                if (TransactionStatusType.PROCESSING.equals(paymentStatus)) {
                    if (!pauseInterval.isEmpty()) {
                        long sleepTime = pauseInterval.remove(0);
                        // TODO to wait sleepTime
                        continue;
                    }
                    isContinue = false;
                } else {
                    isContinue = false;
                }
            }
        }

        if (alipayPayQueryResponse == null
                || TransactionStatusType.PROCESSING.equals(paymentStatus)) {
            RetryExecutor.execute(CANCEL_RETRY_COUNT, new Callback() {

                @Override
                public RetryResult doProcess() {
                    AlipayPayCancelResponse alipayPayCancelResponse = cancel(paymentRequestId);
                    if (alipayPayCancelResponse == null) {
                        // TODO Cancel fail
                        return RetryResult.ofResult(false);
                    }
                    Result cancelResult = alipayPayCancelResponse.getResult();
                    if (ResultStatusType.U.equals(cancelResult.getResultStatus())) {
                        // TODO check times, retry cancel
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
            return;
        }
        /**
         * The transaction failed，terminate this transaction.
         */
        if (TransactionStatusType.FAIL.equals(paymentStatus)) {
            // TODO Payment FAIL
            return;
        }
        /**
         * The transaction cancelled，terminate this transaction.
         */
        if (TransactionStatusType.CANCELLED.equals(paymentStatus)) {
            // TODO Payment CANCELLED
            return;
        }
        /**
         * The transaction succeeded，proceed with other logic.
         */
        if (TransactionStatusType.SUCCESS.equals(paymentStatus)) {
            // TODO Payment SUCCESS
            return;
        }

    }

    public static AlipayPayResponse pay(String paymentRequestId) {

        final AlipayPayRequest alipayPayRequest = new AlipayPayRequest();
        alipayPayRequest.setProductCode(ProductCodeType.CASHIER_PAYMENT);
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

        alipayPayRequest.setMerchantRegion("US");
        alipayPayRequest.setAppId("312a74651aa74586be847a0c672243a9");

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

}