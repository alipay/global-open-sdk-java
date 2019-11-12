
package com.alipay.api;

import com.alibaba.fastjson.JSON;
import com.alipay.api.model.*;
import com.alipay.api.request.auth.AlipayAuthApplyTokenRequest;
import com.alipay.api.request.auth.AlipayAuthConsultRequest;
import com.alipay.api.request.auth.AlipayAuthQueryTokenRequest;
import com.alipay.api.request.auth.AlipayAuthRevokeTokenRequest;
import com.alipay.api.request.pay.*;
import com.alipay.api.request.risk.AlipayRiskScoreInquiryRequest;
import com.alipay.api.response.auth.AlipayAuthApplyTokenResponse;
import com.alipay.api.response.auth.AlipayAuthConsultResponse;
import com.alipay.api.response.auth.AlipayAuthQueryTokenResponse;
import com.alipay.api.response.auth.AlipayAuthRevokeTokenResponse;
import com.alipay.api.response.pay.*;
import com.alipay.api.response.risk.AlipayRiskScoreInquiryResponse;
import com.alipay.api.tools.SignatureTool;

import java.util.ArrayList;
import java.util.List;

public class TestCase {

    public static String merchantPrivateKey = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCi5qPVX5vMqXgZvUup3RwRRwnWz1fs2eo209yo4N/G66xLdD0alqQ3Jzc5rL7GeDt9pNJ7OyYUw4XvYAq/88uTK/FlrBXWFzCSeN9NLB5ZbDGabxMMnaCpIi0qZspC50Bqxwwdpwks59r+DX3K4QBRQuNzlmyJsPvs8nYWY/wD3E+aRE0me7uFr9UE44sJ1nNevKwf0YFOd4IO907HiS2frfi2KaCq/R2lK8i3jUiIMPkxrZ4yvYwDPW9WI2G9gKxa4LtbvWjDiD9Rv0R05wsl3o38FoBGdjYQlJ9Gq6r16fOBuiAN97k2anU0YufoWeQKA5iOf4KDrbOP3fEDeCVzAgMBAAECggEAEvr0k+Pz70C/saAQtNYCczDnwJOgFwS8+W5uA29QI4lKJfCBPhvXPmXfVWT/RXi01crii2E06q5taJq0JfFuAtPX6JTIZo2FJ0vWGNPPJXQpw2i1u36SPaKxyIL5hhoTs11F2B6iPw00wu0nfwAAXXeBsPIR62knsU/+2lUEBB7zOiW+Xmr7ObpVLIvOXxgjIbBZci6qLQfPfcKbaWV5VCxWRUjJTswnIfvNHIoOLkQN7AW5v62EAhbJcIi1tNgUHc4Vvl/3pOvFdGpzAifJHa1qkUiEuRjpgltPAquAvWGPYZAESN902Fa5qiYxIUhVI2ofhnpT7MCbPrQIaTr6IQKBgQDs7giGo9shxFhMDv7AQe0Fw8HFBd4Vf9VsvzMpdlMax1IAy2J0BWp5h+/4msMkVMZMoulRQQO8Z6BuQbopq9RPkhP1O69R24GNTDqHJSrY27PcO2U7QQcKKvXDpp76HNojWXh85F1OSBt7trFl0HkL9rm7p64Tr/YcdqClnljCPwKBgQCwAzufv93Ud81QI5bqVOcS5DeLsXtjXnUkRz9Yiz9ejq5+O4nv6aXAvYVUukC665juzSLIZ6A1RSh8V4YcH7Gxvd0mQrrg97xSzZnyZTtZZfma3Wu82pnczS/Go5YH2ae5hgN8guxuJ/yRqsGf4ab3D7FKcUhvQpn0MDHs8fMnzQKBgHyqsynFRiR6X8uFiF45UjJaUrcM41KEtRRHdvA2JnxMrMVmPyO1RdU0xfwV0uBnCwCAKCxs/T5BeJULWJ1coJZN0tqS0SlNStnCZnl+ARGiaP1U82s+SCn9eNu97KDGVbGL/m4Y60+peWybMLTWARtec1ReYqZRTDql4NnEfAeZAoGAHpAxJkg2yNIUwLmtVvtvNISUcJldB6A7TxVgFov3cP/tpNXJ2fMEaTd/k3rUCqEmaTENvSbhVt6vbQf1raSDQo3pfN9RfAcImIhDFygk6h0e/pk8QArldMXcae21+5771OdEa7aAP5CJ9vFjtdOFHpmG+5o4Lbmu6gwEX1SUGHECgYBrWnvCEF8hcAewcbfmeBhfsBMTDbif/q3Bda0VeA2ufS2LEsuOCk7HN37vXjWuqVRfhPoIGGcLkw8J0GjUxmTkZV/yQK01ZE0ncO2cDeNSPcJzp/FQYlmO/Ujm7o9T1FYR9Lzi1TymEloy6V5MQJA2i4febQJo5HG1N3jwS0ItPQ==";
    public static String merchantPublicKey  = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAouaj1V+bzKl4Gb1Lqd0cEUcJ1s9X7NnqNtPcqODfxuusS3Q9GpakNyc3Oay+xng7faTSezsmFMOF72AKv/PLkyvxZawV1hcwknjfTSweWWwxmm8TDJ2gqSItKmbKQudAascMHacJLOfa/g19yuEAUULjc5ZsibD77PJ2FmP8A9xPmkRNJnu7ha/VBOOLCdZzXrysH9GBTneCDvdOx4ktn634timgqv0dpSvIt41IiDD5Ma2eMr2MAz1vViNhvYCsWuC7W71ow4g/Ub9EdOcLJd6N/BaARnY2EJSfRquq9enzgbogDfe5Nmp1NGLn6FnkCgOYjn+Cg62zj93xA3glcwIDAQAB";
    public static String alipayPublicKey    = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAhDfPw3i9zsGjl/hv25/MELj0q7zA1mXj8cF9tGWMY37JpU1D5qi+0LqYSsxf/oJg53y4k9enF6jPmnICYbZ+gZCGVPd5wl/N5+FgdIntioPu6eVuGL5TJRnuHNDl5ooDtpuTyvfA+zN2wauRPyEAbN0NN5sz3/XmTv0GfW1v4Fa7sHVLP4HT14xU/w3R8FHwxv+vtC/xbs7axLAzmMlwS9pgxWF4vIQJeHWW9IJz32wYZ1EePpUF92D14wEv/dmwwt7adEsQn0NLYlbG9bZLYkvvgjXMpt1oAKvO26UrYop41licCR403hCi+ytYMvORHBvB62STS1m8ivDySqCbywIDAQAB";

    public static void main(String[] args) throws Exception {

//        applyTokenTest();
//        queryTokenTest();
//        authConsultTest();
//        revokeTokenTest();
//        payConsultTest();
//        payQueryTest();
//        refundQueryTest();
//        payTest();
//        refundTest();
//        payCancelTest();
//        riskScoreInquiryTest();
//        payCaptureTest();
//        signTest();
//        verifyTest();
//        verifyNotriyReqTest();

    }

    public static void applyTokenTest()throws Exception{

        AlipayClient defaultAlipayClient = new DefaultAlipayClient("https://open-na.alipay.com", merchantPrivateKey, alipayPublicKey);

        AlipayAuthApplyTokenRequest applyTokenRequest = new AlipayAuthApplyTokenRequest();
        applyTokenRequest.setClientId("T_111222333");
        applyTokenRequest.setPath("/ams/sandbox/api/v1/authorizations/applyToken");
        applyTokenRequest.setGrantType(GrantType.AUTHORIZATION_CODE);
        applyTokenRequest.setCustomerBelongsTo(CustomerBelongsTo.GCASH);
        applyTokenRequest.setAuthCode("669A7FA9D83668EE8AA11FF682989DC988669");

        AlipayAuthApplyTokenResponse alipayResponse = defaultAlipayClient.execute(applyTokenRequest);
        System.out.println(JSON.toJSONString(alipayResponse));

    }

    public static void queryTokenTest()throws Exception{
        AlipayClient defaultAlipayClient = new DefaultAlipayClient("https://open-na.alipay.com", merchantPrivateKey, alipayPublicKey);

        AlipayAuthQueryTokenRequest alipayAuthQueryTokenRequest = new AlipayAuthQueryTokenRequest();
        alipayAuthQueryTokenRequest.setClientId("T_111222333");
        alipayAuthQueryTokenRequest.setPath("/ams/sandbox/api/v1/authorizations/query");
        alipayAuthQueryTokenRequest.setAccessToken("20191105030646157292320611535721879076352459DRsAsCTJIq");

        AlipayAuthQueryTokenResponse alipayResponse = defaultAlipayClient.execute(alipayAuthQueryTokenRequest);
        System.out.println(JSON.toJSONString(alipayResponse));

    }

    public static void authConsultTest()throws Exception{
        AlipayClient defaultAlipayClient = new DefaultAlipayClient("https://open-na.alipay.com", merchantPrivateKey, alipayPublicKey);

        AlipayAuthConsultRequest authConsultRequest = new AlipayAuthConsultRequest();
        // req6.setAuthClientId();
        authConsultRequest.setClientId("T_111222333");
        authConsultRequest.setPath("/ams/sandbox/api/v1/authorizations/consult");
        authConsultRequest.setAuthRedirectUrl("https://www.alipay.com/");
        authConsultRequest.setAuthState("663A8FA9-D836-48EE-8AA1-dd1FF682ff989DC7");
        authConsultRequest.setCustomerBelongsTo(CustomerBelongsTo.GCASH);
        authConsultRequest.setOsType(OsType.ANDROID);
        authConsultRequest.setOsVersion("6.6.6");
        ScopeType[] scopes =  {ScopeType.AGREEMENT_PAY};
        authConsultRequest.setScopes(scopes);
        authConsultRequest.setTerminalType(TerminalType.APP);

        AlipayAuthConsultResponse alipayResponse = defaultAlipayClient.execute(authConsultRequest);
        System.out.println(JSON.toJSONString(alipayResponse));

    }

    public static void revokeTokenTest()throws Exception{
        AlipayClient defaultAlipayClient = new DefaultAlipayClient("https://open-na.alipay.com", merchantPrivateKey, alipayPublicKey);

        AlipayAuthRevokeTokenRequest revokeTokenRequest = new AlipayAuthRevokeTokenRequest();
        revokeTokenRequest.setClientId("T_111222333");
        revokeTokenRequest.setAccessToken("20191028094058157225565805535054331016385592IQFzdVxEtf");

        AlipayAuthRevokeTokenResponse alipayResponse7 = defaultAlipayClient.execute(revokeTokenRequest);
        System.out.println(JSON.toJSONString(alipayResponse7));

    }

    public static void payConsultTest()throws Exception{
        AlipayClient defaultAlipayClient = new DefaultAlipayClient("https://open-na.alipay.com", merchantPrivateKey, alipayPublicKey);

        AlipayPayConsultRequest payConsultRequest = new AlipayPayConsultRequest();

        payConsultRequest.setClientId("T_111222333");
        payConsultRequest.setProductCode(ProductCodeType.CASHIER_PAYMENT);
        payConsultRequest.setCustomerId("aac_123445566");
        Amount paymentAmount = new Amount();
        paymentAmount.setCurrency("PHP");
        paymentAmount.setValue("300");
        payConsultRequest.setPaymentAmount(paymentAmount);
        Env env = new Env();
        env.setTerminalType(TerminalType.APP);
        env.setOsType(OsType.IOS);
        env.setDeviceTokenId("60e62513a925bf5575111cad5976835b");
        env.setClientIp("127.0.0.1");
        payConsultRequest.setEnv(env);

        AlipayPayConsultResponse alipayResponse = defaultAlipayClient.execute(payConsultRequest);
        System.out.println(JSON.toJSONString(alipayResponse));

    }

    public static void payQueryTest()throws Exception{
        AlipayClient defaultAlipayClient = new DefaultAlipayClient("https://open-na.alipay.com", merchantPrivateKey, alipayPublicKey);

        AlipayPayQueryRequest alipayPayQueryRequest = new AlipayPayQueryRequest();
        alipayPayQueryRequest.setClientId("T_111222333");
        alipayPayQueryRequest.setPath("/ams/sandbox/api/v1/payments/inquiryPayment");
        alipayPayQueryRequest.setPaymentId("063601157259016153335505900344216465LtEMKJwHoY201910310000329028");
        AlipayPayQueryResponse alipayPayQueryResponse = defaultAlipayClient.execute(alipayPayQueryRequest);

        System.out.println(JSON.toJSONString(alipayPayQueryResponse));

    }

    public static void refundQueryTest()throws Exception{
        AlipayClient defaultAlipayClient = new DefaultAlipayClient("https://open-na.alipay.com", merchantPrivateKey, alipayPublicKey);

        AlipayRefundQueryRequest alipayRefundQueryRequest = new AlipayRefundQueryRequest();
        alipayRefundQueryRequest.setClientId("T_111222333");
        alipayRefundQueryRequest.setRefundId("042115157232287507341087959745348615twzJWtFZTg201910280000209456");
        alipayRefundQueryRequest.setPath("/ams/sandbox/api/v1/payments/inquiryRefund");

        AlipayRefundQueryResponse alipayRefundQueryResponse = defaultAlipayClient.execute(alipayRefundQueryRequest);
        System.out.println(JSON.toJSONString(alipayRefundQueryResponse));

    }

    public static void payTest()throws Exception{
        AlipayClient defaultAlipayClient = new DefaultAlipayClient("https://open-na.alipay.com", merchantPrivateKey, alipayPublicKey);

        AlipayPayRequest alipayPayRequest = new AlipayPayRequest();
        alipayPayRequest.setClientId("T_111222333");
        alipayPayRequest.setPath("/ams/sandbox/api/v1/payments/pay");
        alipayPayRequest.setProductCode(ProductCodeType.AGREEMENT_PAYMENT);
        alipayPayRequest.setPaymentRequestId("pay_songlin_test_126976066776690");

        Amount paymentAmount = new Amount();
        paymentAmount.setCurrency("PHP");
        paymentAmount.setValue("10.00");
        alipayPayRequest.setPaymentAmount(paymentAmount);

        Order order = new Order();
        order.setReferenceOrderId("102775765075669");
        order.setOrderDescription("Mi Band 3 Wrist Strap Metal Screwless Stainless Steel For Xiaomi Mi Band 3");

        Amount orderAmount = new Amount();
        orderAmount.setCurrency("PHP");
        orderAmount.setValue("10000");
        order.setOrderAmount(orderAmount);
        alipayPayRequest.setOrder(order);

        PaymentMethod paymentMethod = new PaymentMethod();
        paymentMethod.setPaymentMethodType("GCASH");
        paymentMethod.setPaymentMethodId("20191108021214157317913464736094873458516784DsqAKQhDsp");
        alipayPayRequest.setPaymentMethod(paymentMethod);

        alipayPayRequest.setIsAuthorization(true);

        AlipayPayResponse alipayPayResponse = defaultAlipayClient.execute(alipayPayRequest);
        System.out.println(JSON.toJSONString(alipayPayResponse));

    }

    public static void refundTest()throws Exception{

        AlipayClient defaultAlipayClient = new DefaultAlipayClient("https://open-na.alipay.com", merchantPrivateKey, alipayPublicKey);

        AlipayRefundRequest alipayRefundRequest = new AlipayRefundRequest();
        alipayRefundRequest.setClientId("T_111222333");
        alipayRefundRequest.setPaymentId("055510157232851073241093595405019786YzEEQLvoik201910280000308549");
        alipayRefundRequest.setRefundRequestId("refund_20190826081529156680732967870397");

        Amount refundAmount = new Amount();
        refundAmount.setCurrency("PHP");
        refundAmount.setValue("3000");
        alipayRefundRequest.setRefundAmount(refundAmount);
        alipayRefundRequest.setRefundReason("sdk test");
        alipayRefundRequest.setExtendInfo("sdk test");

        AlipayRefundResponse alipayRefundResponse = defaultAlipayClient.execute(alipayRefundRequest);
        System.out.println(JSON.toJSONString(alipayRefundResponse));

    }

    public static void payCancelTest()throws Exception{
        AlipayClient defaultAlipayClient = new DefaultAlipayClient("https://open-na.alipay.com", merchantPrivateKey, alipayPublicKey);

        AlipayPayCancelRequest alipayPayCancelRequest = new AlipayPayCancelRequest();
        alipayPayCancelRequest.setClientId("T_111222333");
        alipayPayCancelRequest.setPaymentId("062140157233010066335128773624385330jjgbawaMjz201910270000306923");
        alipayPayCancelRequest.setPaymentRequestId("pay_1029760066776669_102779765796667639");
        AlipayPayCancelResponse alipayPayCancelResponse = defaultAlipayClient.execute(alipayPayCancelRequest);

        System.out.println(JSON.toJSONString(alipayPayCancelResponse));

    }

    public static void riskScoreInquiryTest()throws Exception{
        AlipayClient defaultAlipayClient = new DefaultAlipayClient("https://open-na.alipay.com", merchantPrivateKey, alipayPublicKey);

        AlipayRiskScoreInquiryRequest alipayRiskScoreInquiryRequest = new AlipayRiskScoreInquiryRequest();
        alipayRiskScoreInquiryRequest.setClientId("T_111222333");
        alipayRiskScoreInquiryRequest.setPath("/ams/sandbox/api/v1/risks/inquiryRiskScore");
        alipayRiskScoreInquiryRequest.setCustomerBelongsTo(CustomerBelongsTo.GCASH);
        alipayRiskScoreInquiryRequest.setCustomerId("123456789");
        alipayRiskScoreInquiryRequest.setCustomerIdType(CustomerIdType.USER_ID);

        List<RiskScoreType> riskScoreTypes = new ArrayList<RiskScoreType>();
        riskScoreTypes.add(RiskScoreType.NSF_SCORE);
        alipayRiskScoreInquiryRequest.setRiskScoreTypes(riskScoreTypes);

        System.out.println(JSON.toJSONString(alipayRiskScoreInquiryRequest));

        AlipayRiskScoreInquiryResponse alipayRiskScoreInquiryResponse = defaultAlipayClient.execute(alipayRiskScoreInquiryRequest);
        System.out.println(JSON.toJSONString(alipayRiskScoreInquiryResponse));

    }

    public static void signTest()throws Exception{
        String reqBody = "{\"key\":\"value\"}";
        String reqContent = "httpMethod" + " " + "path" + "\n" + "clientId" + "." + "requestTime"
                + "." + reqBody;
        String signReqContent = SignatureTool.sign(reqContent, merchantPrivateKey);
        System.out.println(signReqContent);

    }

    public static void verifyTest()throws Exception{
        String reqBody = "{\"key\":\"value\"}";
        String reqContent = "httpMethod" + " " + "path" + "\n" + "clientId" + "." + "requestTime"
                + "." + reqBody;
        String signReqContent = SignatureTool.sign(reqContent, merchantPrivateKey);

        String rspBody = "{\"key\":\"value\"}";
        String rspContent = "httpMethod" + " " + "path" + "\n" + "clientId" + "." + "requestTime"
                + "." + rspBody;
        boolean isPass = SignatureTool.verify(rspContent, signReqContent, merchantPublicKey);
        System.out.println(isPass);

    }

    public static void verifyNotriyReqTest()throws Exception{

        String alipayPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAh/u8qQRxsvTv7epRXx3693VfjIgfZgcTMTdOeTRqdVdNSzNcEkwaA99O4HXLB4xklzhEbgAG7CfJ9fhkKiTgVb4S4hEiSryFnnOcJ/0uiTpV/vrbofxQmeULOZ6OoSVvY0rxQUcPVYdMzp+kLU6Qkcg8t6t9xkzzNf2v4wRLp1FDb8+SD+vF60vMUAVH5a/6nNh3yMQAs1bsVKDXW6gUKq0WvCWCYQhuhVEKzLCjtegzvLO+y6HX+v8EwofwQAUWDHhXzU3MOcKaZ5rp7mx+CY75WhiT9eYi5TsvIJbXKeZozJG6tpNhrr87pwpq4tJby0XXxmpjdInsrEJDGHUDKQIDAQAB";
        String signReqContent  = "Sdx0JwVSxpCO1NeitkTmxHTVeGwvFRFVGJHvO%2B62nnCWnxsZO2mkCopcAYJYqKQpoKj5Jo1HUzqjcV3ZjqTxkAVox7Rw4a5wzIFcykSIOzfd3qRLdp6lsaZHgCVNC6FL%2FpyXyCSaaWn4WiJmOJfkhcsX8GEMnUQoFdgtTu8rmWo0QG209A7G8aVBovYkGetqgVCNbjgTTl57IZjxdatQt7KoY7nL3m2VlXADeBQnpoaeXOs5qsSUXOwUrS2%2BhY4hMjxmarGD4iRwxI5%2BeHi5Sf4geaj99VVahkfdvW80TUIOC0xA%2FwMXOyNbSooImRtD%2FFWWGcoLGk5HZn0KohTA2w%3D%3D";
        String reqBody         = "{\"actualPaymentAmount\":{\"currency\":\"PHP\",\"value\":\"100\"},\"notifyType\":\"PAYMENT_RESULT\",\"notifyUrl\":\"https://local.allpay-test.com/api/back?allPayTn=5a9ddf96cc89dd30134549d68abbbd4a\",\"paymentAmount\":{\"currency\":\"PHP\",\"value\":\"100\"},\"paymentCreateTime\":\"2019-11-12T17:36:04+08:00\",\"paymentId\":\"093604157355136494636350037908059631krbkwaTbZW201911110000388037\",\"paymentRequestId\":\"cc89dd30134549d6\",\"paymentTime\":\"2019-11-12T17:36:04+08:00\",\"pspCustomerInfo\":{\"displayCustomerId\":\"test***@xxx.com\",\"pspCustomerId\":\"20881111111234\",\"pspName\":\"GCASH\"},\"result\":{\"resultCode\":\"SUCCESS\",\"resultMessage\":\"success.\",\"resultStatus\":\"S\"}}";

        String  rspContent = SignatureTool.genSignConent("POST","/api/back","T_ALLPAY_1", "2019-11-12T09:37:00Z", reqBody);
        System.out.println(rspContent);
        boolean isPass     = SignatureTool.verify(rspContent, signReqContent, alipayPublicKey);
        System.out.println();
        System.out.println(isPass);

    }

}