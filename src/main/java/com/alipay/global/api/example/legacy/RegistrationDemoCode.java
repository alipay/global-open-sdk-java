package com.alipay.global.api.example.legacy;

import com.alipay.global.api.AlipayClient;
import com.alipay.global.api.DefaultAlipayClient;
import com.alipay.global.api.example.model.Callback;
import com.alipay.global.api.example.model.RetryResult;
import com.alipay.global.api.exception.AlipayApiException;
import com.alipay.global.api.model.Result;
import com.alipay.global.api.model.ResultStatusType;
import com.alipay.global.api.model.ams.*;
import com.alipay.global.api.model.aps.Logo;
import com.alipay.global.api.model.constants.EndPointConstants;
import com.alipay.global.api.request.ams.merchant.AlipayMerchantRegistrationInfoQueryRequest;
import com.alipay.global.api.request.ams.merchant.AlipayMerchantRegistrationRequest;
import com.alipay.global.api.request.ams.merchant.AlipayMerchantRegistrationStatusQueryRequest;
import com.alipay.global.api.response.ams.merchant.AlipayMerchantRegistrationInfoQueryResponse;
import com.alipay.global.api.response.ams.merchant.AlipayMerchantRegistrationResponse;
import com.alipay.global.api.response.ams.merchant.AlipayMerchantRegistrationStatusQueryResponse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RegistrationDemoCode {

    private static final Integer TIMEOUT_RETRY_COUNT = 3;
    private static final Integer REGISTER_RETRY_COUNT = 3;
    private static final String GATE_WAY_URL = "";
    private static final String MERCHANT_PRIVATE_KEY = "";
    private static final String ANTOM_PUBLIC_KEY = "";
    private static final String CLIENT_ID = "";
    private static final String PAYMENT_REQUEST_ID = "";
    private static final String registrationRequestId = "";
    private static final String referenceMerchantId = "";
    private final static AlipayClient CLIENT = new DefaultAlipayClient(
            EndPointConstants.SG, MERCHANT_PRIVATE_KEY, ANTOM_PUBLIC_KEY, CLIENT_ID);

    public static void main(String[] args) {
        //step1 register merchant info
        AlipayMerchantRegistrationResponse response = registerMerchant(buildRegisterRequest());
        if (response != null) {
            Result result = response.getResult();
            if (ResultStatusType.F.equals(result.getResultStatus())) {
                //TODO register fail
                return;
            }
            //step2. query registration status.
            if (ResultStatusType.S.equals(result.getResultStatus())) {
                AlipayMerchantRegistrationStatusQueryResponse statusResponse = queryRegistrationStatus(
                        buildAlipayMerchantRegistrationStatusQueryRequest());
                if (statusResponse != null) {
                    Result statusResult = statusResponse.getResult();
                    if (ResultStatusType.F.equals(statusResult.getResultStatus())) {
                        //TODO query register status fail
                        return;
                    }
                }
                //step3. query registration info.
                AlipayMerchantRegistrationInfoQueryResponse infoResponse = queryRegistrationInfo(
                        buildAlipayMerchantRegistrationInfoQueryRequest());
                if (infoResponse != null) {
                    Result infoResult = infoResponse.getResult();
                    if (ResultStatusType.F.equals(infoResult.getResultStatus())) {
                        //TODO query register info fail
                        return;
                    }
                }
            }

        }

    }

    public static AlipayMerchantRegistrationRequest buildRegisterRequest() {
        final AlipayMerchantRegistrationRequest request = new AlipayMerchantRegistrationRequest();

        //TODO build your merchant info request
        request.setRegistrationRequestId(registrationRequestId);

        request.setProductCodes(Collections.singletonList(ProductCodeType.CASHIER_PAYMENT));
        request.setPassThroughInfo("{\"extraInfo\":\"extra\"}");
        request.setRegistrationNotifyURL("https://merchant/example");

        MerchantRegistrationInfo merchant = new MerchantRegistrationInfo();
        Logo logo = new Logo();
        logo.setLogoName("expLogo");
        logo.setLogoUrl("http://www.logo.com");
        merchant.setLogo(logo);

        Address address = new Address();
        address.setAddress1("38 Leighton Road, ****");
        address.setAddress2("40 Leighton Road, ****");
        address.setCity("hong kong");
        address.setRegion("HK");
        address.setState("HK");
        address.setZipCode("zipCode");
        address.setLabel("label");
        merchant.setMerchantAddress(address);

        merchant.setMerchantDisplayName("Example Merchant Name");
        merchant.setMerchantMCC("5812");

        RegistrationDetail detail = new RegistrationDetail();
        detail.setLegalName("Example Legal Name");
        detail.setRegistrationNo("registration*****");
        detail.setRegistrationType("ENTERPRISE_REGISTRATION_NO");
        List<Attachment> attachments = new ArrayList<Attachment>();
        Attachment attachment = new Attachment();
        attachment.setAttachmentName("attachmentTestName");
        attachment.setAttachmentType("ARTICLES_OF_ASSOCIATION");
        attachment.setFile("testFile");
        attachments.add(attachment);
        detail.setAttachments(attachments);

        List<ContactInfo> contactInfos = new ArrayList<ContactInfo>();
        ContactInfo contactInfo = new ContactInfo();
        contactInfo.setContactNo("contactNo123");
        contactInfo.setContactType("MOBILE_PHONE");
        contactInfos.add(contactInfo);
        detail.setContactInfo(contactInfos);

        Address registrationAddress = new Address();
        registrationAddress.setRegion("HK");
        detail.setRegistrationAddress(registrationAddress);
        detail.setBusinessType("ENTERPRISE");
        merchant.setRegistrationDetail(detail);

        List<WebSite> webSites = new ArrayList<WebSite>();
        WebSite webSite = new WebSite();
        webSite.setUrl("http://www.webSite.com");
        webSite.setDesc("this is webSite desc");
        webSite.setName("webName");
        webSites.add(webSite);
        merchant.setWebsites(webSites);

        merchant.setReferenceMerchantId(referenceMerchantId);
        request.setMerchantInfo(merchant);

        return request;
    }

    public static AlipayMerchantRegistrationInfoQueryRequest buildAlipayMerchantRegistrationInfoQueryRequest() {
        final AlipayMerchantRegistrationInfoQueryRequest request = new AlipayMerchantRegistrationInfoQueryRequest();
        request.setReferenceMerchantId(referenceMerchantId);
        return request;
    }

    public static AlipayMerchantRegistrationInfoQueryResponse queryRegistrationInfo(final AlipayMerchantRegistrationInfoQueryRequest request) {
        Object obj = RetryExecutor.execute(REGISTER_RETRY_COUNT, new Callback() {
            @Override
            public RetryResult doProcess() {
                AlipayMerchantRegistrationInfoQueryResponse response = null;
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
        return obj != null ? (AlipayMerchantRegistrationInfoQueryResponse) obj : null;
    }

    public static AlipayMerchantRegistrationStatusQueryRequest buildAlipayMerchantRegistrationStatusQueryRequest() {
        final AlipayMerchantRegistrationStatusQueryRequest request = new AlipayMerchantRegistrationStatusQueryRequest();
        request.setReferenceMerchantId(referenceMerchantId);
        //request.setRegistrationRequestId(registrationRequestId);
        return request;
    }

    public static AlipayMerchantRegistrationStatusQueryResponse queryRegistrationStatus(final AlipayMerchantRegistrationStatusQueryRequest request) {
        Object obj = RetryExecutor.execute(REGISTER_RETRY_COUNT, new Callback() {
            @Override
            public RetryResult doProcess() {
                AlipayMerchantRegistrationStatusQueryResponse response = null;
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
        return obj != null ? (AlipayMerchantRegistrationStatusQueryResponse) obj : null;
    }

    public static AlipayMerchantRegistrationResponse registerMerchant(final AlipayMerchantRegistrationRequest request) {

        Object obj = RetryExecutor.execute(REGISTER_RETRY_COUNT, new Callback() {
            @Override
            public RetryResult doProcess() {
                AlipayMerchantRegistrationResponse response = null;
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
        return obj != null ? (AlipayMerchantRegistrationResponse) obj : null;
    }
}
