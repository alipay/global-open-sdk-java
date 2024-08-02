package com.alipay.global.api.example.legacy;

import com.alipay.global.api.AlipayClient;
import com.alipay.global.api.DefaultAlipayClient;
import com.alipay.global.api.example.model.Callback;
import com.alipay.global.api.example.model.RetryResult;
import com.alipay.global.api.exception.AlipayApiException;
import com.alipay.global.api.model.Result;
import com.alipay.global.api.model.ResultStatusType;
import com.alipay.global.api.model.ams.*;
import com.alipay.global.api.model.constants.EndPointConstants;
import com.alipay.global.api.request.ams.customs.AlipayCustomsDeclareRequest;
import com.alipay.global.api.request.ams.customs.AlipayCustomsQueryRequest;
import com.alipay.global.api.response.ams.customs.AlipayCustomsDeclareResponse;
import com.alipay.global.api.response.ams.customs.AlipayCustomsQueryResponse;

import java.util.ArrayList;
import java.util.List;

public class CustomsDemoCode {

    private static final Integer TIMEOUT_RETRY_COUNT = 3;
    private static final Integer QUERY_DECLARE_RETRY_COUNT = 3;
    private static final Integer DECLARE_RETRY_COUNT = 3;
    //TODO build your clientId
    private static final String GATE_WAY_URL = "";
    private static final String MERCHANT_PRIVATE_KEY = "";
    private static final String ANTOM_PUBLIC_KEY = "";
    private static final String CLIENT_ID = "";
    private final static AlipayClient CLIENT = new DefaultAlipayClient(
            EndPointConstants.SG, MERCHANT_PRIVATE_KEY, ANTOM_PUBLIC_KEY, CLIENT_ID);

    public static void main(String[] args) {
        //step.1 finish a payment. Because transmit information to customs need a paymentId;
        //TODO build your paymentId
        final String paymentId = "";

        //step.2 declare
        AlipayCustomsDeclareRequest declareRequest = buildAlipayCustomsDeclareRequest(paymentId);
        AlipayCustomsDeclareResponse response = declare(declareRequest);
        if (response != null) {
            Result result = response.getResult();
            if (ResultStatusType.F.equals(result.getResultStatus())) {
                //TODO declare fail
                return;
            }
            //step.3 query customs declaration status
            if (ResultStatusType.S.equals(result.getResultStatus())) {
                List<String> requestIds = new ArrayList<String>();
                requestIds.add(declareRequest.getDeclarationRequestId());
                AlipayCustomsQueryResponse customsQueryResponse = queryCustomsDeclare(
                        buildAlipayCustomsQueryRequest(requestIds));
                if (customsQueryResponse != null) {
                    Result statusResult = customsQueryResponse.getResult();
                    if (ResultStatusType.F.equals(statusResult.getResultStatus())) {
                        //TODO query declare info fail
                        return;
                    }
                }
            }
        }

    }

    public static AlipayCustomsDeclareRequest buildAlipayCustomsDeclareRequest(String paymentId) {
        final AlipayCustomsDeclareRequest request = new AlipayCustomsDeclareRequest();

        Certificate buyerCertificate = new Certificate();
        buyerCertificate.setCertificateNo("3412228959867522116");
        buyerCertificate.setCertificateType(CertificateType.ID_CARD);

        UserName userName = new UserName();
        userName.setFullName("李小二");
        userName.setFirstName("李");
        userName.setMiddleName("小");
        userName.setLastName("二");
        buyerCertificate.setHolderName(userName);

        request.setBuyerCertificate(buyerCertificate);
        CustomsInfo customsInfo = new CustomsInfo();
        customsInfo.setRegion("CN");
        customsInfo.setCustomsCode("zongshu");
        request.setCustoms(customsInfo);

        //TODO build your amount depend on your finished payment
        Amount declarationAmount = new Amount();
        declarationAmount.setCurrency("CNY");
        declarationAmount.setValue("1");
        request.setDeclarationAmount(declarationAmount);

        request.setPaymentId(paymentId);
        MerchantCustomsInfo merchantCustomsInfo = new MerchantCustomsInfo();
        merchantCustomsInfo.setMerchantCustomsName("guangzhou_zongshu");
        merchantCustomsInfo.setMerchantCustomsCode("jwyhanguo_card11");
        request.setMerchantCustomsInfo(merchantCustomsInfo);

        request.setSplitOrder(false);
        request.setSubOrderId("11111111");
        //TODO build your requestId
        request.setDeclarationRequestId("1234567890");
        return request;
    }

    public static AlipayCustomsDeclareResponse declare(final AlipayCustomsDeclareRequest request) {
        Object obj = RetryExecutor.execute(DECLARE_RETRY_COUNT, new Callback() {
            @Override
            public RetryResult doProcess() {
                AlipayCustomsDeclareResponse response = null;
                try {
                    response = CLIENT.execute(request);
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
                return RetryResult.ofResult(false, response);
            }
        });
        return obj != null ? (AlipayCustomsDeclareResponse) obj : null;
    }

    public static AlipayCustomsQueryRequest buildAlipayCustomsQueryRequest(List<String> requestIds) {
        final AlipayCustomsQueryRequest request = new AlipayCustomsQueryRequest();
        request.setDeclarationRequestIds(requestIds);
        return request;
    }

    public static AlipayCustomsQueryResponse queryCustomsDeclare(final AlipayCustomsQueryRequest request) {
        Object obj = RetryExecutor.execute(QUERY_DECLARE_RETRY_COUNT, new Callback() {
            @Override
            public RetryResult doProcess() {
                AlipayCustomsQueryResponse response = null;
                try {
                    response = CLIENT.execute(request);
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
                return RetryResult.ofResult(false, response);
            }
        });
        return obj != null ? (AlipayCustomsQueryResponse) obj : null;
    }

}
