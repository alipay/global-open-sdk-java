package com.alipay.global.api.example.excutable;

import com.alibaba.fastjson.JSONObject;
import com.alipay.global.api.AlipayClient;
import com.alipay.global.api.DefaultAlipayClient;
import com.alipay.global.api.exception.AlipayApiException;
import com.alipay.global.api.model.ams.Amount;
import com.alipay.global.api.model.ams.Buyer;
import com.alipay.global.api.model.ams.Env;
import com.alipay.global.api.model.ams.Order;
import com.alipay.global.api.model.ams.PaymentFactor;
import com.alipay.global.api.model.ams.PaymentMethod;
import com.alipay.global.api.model.ams.ProductCodeType;
import com.alipay.global.api.model.ams.SettlementStrategy;
import com.alipay.global.api.model.ams.TerminalType;
import com.alipay.global.api.request.ams.pay.AlipayPayQueryRequest;
import com.alipay.global.api.request.ams.pay.AlipayPayRequest;
import com.alipay.global.api.request.ams.pay.AlipayPaymentSessionRequest;
import com.alipay.global.api.response.ams.pay.AlipayPayQueryResponse;
import com.alipay.global.api.response.ams.pay.AlipayPayResponse;
import com.alipay.global.api.response.ams.pay.AlipayPaymentSessionResponse;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CashierPayExcutableDemoCode {

    private static final String       merchantPrivateKey  = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQCbn1zMzJMNkPVYVCQRYV6eHyuR5Jm23dOsPyYDJgZyM51Hn6ykqHfm+HbGO9EwdwxVPQOmSWpjeIgi7yBIE4SzQUEQ8fyQXeHITV/S0lqqdEO6wB/eOzQ2l0He3IRCzWpRECeSXKqriX+vbE9wQR1W3FSjL/cNBekfoEbm0IAiOiD2CpJKECBxZH5ohBLUeNpednqdCGGD8zsRyODa4vUUiNVwsAsLhwCF2Jmr7gl0eRqYKg3vZ0ROnqA78zIfA6g8vX9+n8BgDrw/bHmdSbCFy2kK4Sk9mHZPt8di3z9rVj0l/gGJiKTiN5V0svDPKx0OyhwDnWd3F30rqHINDEJ5AgMBAAECggEAPz8uTQGbhjjyrXpMyk+DT8olP/7jRgGzhF6JyV/L4N//PNu1I5pgWJuEUh5/by4C8iWA9TUDKvgSg/gha4mbNesAUa23UskE+Q4RMrJRLNNhr2VjzkLLfqBMohGHI0suuVFSc9Z7eonAPgBL7LhZ/zI+p6/XvRA45aSTDD2SxdWtp+8aSgyMw4/YnQTis1rG58D0S0EjqCPQu2/DcX+3o/fviyHS0k8gaIng970V/CeEp2nGAOhWqzsNa4Cj+Zy0vuOpO+jjyByouHy/RC+X2LPHWg2PxPiLw5cS92nD4RciDe7FGnu6plnPWP5koJOSE2xTmycoazts6NBZbX1HMQKBgQDQk9gSRhPOAu+S90SwTQ0VbA4pUVOSYv0huVOmTpNr0fr/3gv1+PSYdUWcsvon3nmYOnBTnGYtehPLNODznTUVS8wq/qkRX+xAbbCKczJcd2TzcR++O5P/uyu91fazZsjkihXKU4Tst5TYYqUEPhl4vIe25euaCiZdn65l1ltaRQKBgQC/AUwcwc2Qc+3qhV59d6xCaMERoygIFVO0rsrgjcyFixrlZH42uv5CRqBBtRYBHmETmGE9vAtKfHJf2/G72NnYQML2o5Am6ZnvmNIIgqbsLFpeIZNEBNSewTGPSd8ROUe9J0PBIeFWjWECTa0t4tm8UjKkP67J4Ay3W1S1uNoEpQKBgQCt8C0HiDMp4N7jY5t/nrHjhHoG0NBtymkoP4HXtb1Q9ApqaET3OkbYASwtMkBVET27yjg0McAv+pcZJh7WaeqDuPMpg7eASssdK5xGySxdWh/wuAztuoeAgxlGNXGHSJcNp23mjWHTeoEBNrpAYH0jZ62RxAmHKyG1f92TBYCnNQKBgQCwtaqNEF/2PMroJet9eLJN3kyA3Mm0cMa4at4XUhjdlAyWHZsHV4jUGDM7lyV8c3asglW0n7xydR/7v6itebpRb9jwBfLfjU91c5UdeoKkDYStgbKVQ0LSfTo0rstJTA0q2qC8lXO8d6BAWUzXHwECUam4e5JiAn7BRCXi2doaYQKBgQCzqctEZRJgy2lUwAptKxe1ZyPxhcNDa6KDL5Hu5AyK8HsTZGztRXrvZ0123bLdjVrC81f4Z6cwilMcXP/YMOKxYz43t6OVOkfov3RQnhwY4Z7N0nEXmXkf9g8u4qHhtGevOnKw2qDg8FgsDRwy0XeiV80vzwh81bOKNPBNjeSjzw==";

    private static final String       alipayPublicKey     = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAskVLOmRs1/RuzWHHd3bk5dtRfIDIA7dyDkcCFm5FltWzHotJprQag0wawy1e2sDcBB9CU+8ZTz0hfp+e01k9/qXLPI2LPiTlNaZ4KnbZeRG8VnIaUceZeZP58jBmJ23x+V3Hf/JGJoAdLqHy+ZEsv9VhsuvipCocs1hMDJf2o136Aj+jlsYKvlwuUwp9dhXBwVct+1/0Vv2stgRGxax0Hbm/8caV+0GjWQfTcDoht1s6R8s4D4/XXGPHrXYVy+ZEqF/Msqfv0JQsEfHSsf7FmOvryKyI5kgyIUWgiTg28kgQ93Wd/8ISypxFmKsGAG+8Se8CDxakB9+Cf/D7vN3HuwIDAQAB";

    private static final String       CLIENT_ID           = "SANDBOX_5Y684E2YTR2X00990";

    private static final String       GATE_WAY_URL        = "https://open-sea-global.alipay.com";

    private static final AlipayClient defaultAlipayClient = new DefaultAlipayClient(GATE_WAY_URL,
        merchantPrivateKey, alipayPublicKey);

    public static void main(String[] args) {

        // executePayWithCard();
        // executePayWithBlik();
         executePaymentSessionCreateWithCard();
        // executeInquiry();

    }

    /**
     *  show how to finish a payment by Card paymentMethod 
     */
    public static void executePayWithCard() {

        AlipayPayRequest alipayPayRequest = new AlipayPayRequest();
        alipayPayRequest.setClientId(CLIENT_ID);
        alipayPayRequest.setPath("/ams/sandbox/api/v1/payments/pay");
        alipayPayRequest.setProductCode(ProductCodeType.CASHIER_PAYMENT);

        // replace to your paymentRequestId
        String paymentRequestId = UUID.randomUUID().toString();
        alipayPayRequest.setPaymentRequestId(paymentRequestId);
        Amount amount = new Amount();

        // set amount
        amount.setCurrency("BRL");
        amount.setValue("4200");
        alipayPayRequest.setPaymentAmount(amount);

        //set settlement currency
        SettlementStrategy settlementStrategy = new SettlementStrategy();
        settlementStrategy.setSettlementCurrency("USD");
        alipayPayRequest.setSettlementStrategy(settlementStrategy);

        // set paymentMethod
        PaymentMethod paymentMethod = new PaymentMethod();
        paymentMethod.setPaymentMethodType("CARD");
        alipayPayRequest.setPaymentMethod(paymentMethod);

        //if merchant collect card info, set card info in this paymentMethodMetaData
        Map<String, Object> paymentMethodMetaData = new HashMap<String, Object>();
        paymentMethodMetaData.put("cardNo", "0255187751531899");
        paymentMethodMetaData.put("cvv", "712");
        paymentMethodMetaData.put("expiryMonth", "06");
        paymentMethodMetaData.put("expiryYear", "28");
        paymentMethodMetaData.put("tokenize", false);
        paymentMethodMetaData.put("cpf", "671.998.112-31");
        JSONObject cardholderName = new JSONObject();
        cardholderName.put("firstName", "Alan");
        cardholderName.put("lastName", "Wallex");
        paymentMethodMetaData.put("cardholderName", cardholderName);
        paymentMethod.setPaymentMethodMetaData(paymentMethodMetaData);

        // replace to your orderId
        String orderId = UUID.randomUUID().toString();

        // set order Info
        Order order = new Order();
        order.setReferenceOrderId(orderId);
        order.setOrderDescription("antom test order");
        order.setOrderAmount(amount);
        Buyer buyer = new Buyer();
        buyer.setReferenceBuyerId("yourBuyerId");
        order.setBuyer(buyer);
        order.setOrderAmount(amount);
        alipayPayRequest.setOrder(order);

        //set env Info
        Env env = new Env();
        env.setTerminalType(TerminalType.WEB);
        env.setClientIp("114.121.121.01");
        alipayPayRequest.setEnv(env);
        order.setEnv(env);

        // set auth capture payment mode
        PaymentFactor paymentFactor = new PaymentFactor();
        paymentFactor.setAuthorization(true);
        alipayPayRequest.setPaymentFactor(paymentFactor);

        // replace to your notify url
        alipayPayRequest.setPaymentNotifyUrl("http://www.yourNotifyUrl.com");

        // replace to your redirect url
        alipayPayRequest.setPaymentRedirectUrl("http://www.yourRedirectUrl.com");

        //do the Payment
        AlipayPayResponse alipayPayResponse = null;
        System.out.println(JSONObject.toJSON(alipayPayRequest));
        try {
            alipayPayResponse = defaultAlipayClient.execute(alipayPayRequest);
        } catch (AlipayApiException e) {
            String errorMsg = e.getMessage();
            // handle error condition
        }
        //show response
        System.out.println(JSONObject.toJSON(alipayPayResponse));
    }

    /**
     *  show how to finish a payment by Blik (
     */
    public static void executePayWithBlik() {

        AlipayPayRequest alipayPayRequest = new AlipayPayRequest();
        alipayPayRequest.setClientId(CLIENT_ID);
        alipayPayRequest.setPath("/ams/sandbox/api/v1/payments/pay");
        alipayPayRequest.setProductCode(ProductCodeType.CASHIER_PAYMENT);

        // replace to your paymentRequestId
        String paymentRequestId = UUID.randomUUID().toString();
        alipayPayRequest.setPaymentRequestId(paymentRequestId);
        Amount amount = new Amount();

        // set amount
        amount.setCurrency("PLN");
        amount.setValue("4200");
        alipayPayRequest.setPaymentAmount(amount);

        //set settlement currency
        SettlementStrategy settlementStrategy = new SettlementStrategy();
        settlementStrategy.setSettlementCurrency("USD");
        alipayPayRequest.setSettlementStrategy(settlementStrategy);

        // set paymentMethod
        PaymentMethod paymentMethod = new PaymentMethod();
        paymentMethod.setPaymentMethodType("BLIK");
        alipayPayRequest.setPaymentMethod(paymentMethod);

        // replace to your orderId
        String orderId = UUID.randomUUID().toString();

        // set order Info
        Order order = new Order();
        order.setReferenceOrderId(orderId);
        order.setOrderDescription("antom test order");
        order.setOrderAmount(amount);
        Buyer buyer = new Buyer();
        buyer.setReferenceBuyerId("yourBuyerId");
        order.setBuyer(buyer);
        order.setOrderAmount(amount);
        alipayPayRequest.setOrder(order);

        //set env Info
        Env env = new Env();
        env.setTerminalType(TerminalType.WEB);
        env.setClientIp("114.121.121.01");
        alipayPayRequest.setEnv(env);

        // set auth capture payment mode
        PaymentFactor paymentFactor = new PaymentFactor();
        paymentFactor.setAuthorization(true);
        alipayPayRequest.setPaymentFactor(paymentFactor);

        // replace to your notify url
        alipayPayRequest.setPaymentNotifyUrl("http://www.yourNotifyUrl.com");

        // replace to your redirect url
        alipayPayRequest.setPaymentRedirectUrl("http://www.yourRedirectUrl.com");

        //do the Payment
        AlipayPayResponse alipayPayResponse = null;
        System.out.println(JSONObject.toJSON(alipayPayRequest));
        try {
            alipayPayResponse = defaultAlipayClient.execute(alipayPayRequest);
        } catch (AlipayApiException e) {
            String errorMsg = e.getMessage();
            // handle error condition
        }
        //show response
        System.out.println(JSONObject.toJSON(alipayPayResponse));

    }

    /**
     * show how to card payment Session(need to finish payment by Antom SDK)
     */
    public static void executePaymentSessionCreateWithCard(){

        AlipayPaymentSessionRequest alipayPaymentSessionRequest = new AlipayPaymentSessionRequest();
        alipayPaymentSessionRequest.setClientId(CLIENT_ID);
        alipayPaymentSessionRequest.setPath("/ams/sandbox/api/v1/payments/createPaymentSession");
        alipayPaymentSessionRequest.setProductCode(ProductCodeType.CASHIER_PAYMENT);

        // replace to your paymentRequestId
        String paymentRequestId = UUID.randomUUID().toString();
        alipayPaymentSessionRequest.setPaymentRequestId(paymentRequestId);
        Amount amount = new Amount();

        // set amount
        amount.setCurrency("BRL");
        amount.setValue("4200");
        alipayPaymentSessionRequest.setPaymentAmount(amount);

        //set settlement currency
        SettlementStrategy settlementStrategy = new SettlementStrategy();
        settlementStrategy.setSettlementCurrency("USD");
        alipayPaymentSessionRequest.setSettlementStrategy(settlementStrategy);

        // set paymentMethod
        PaymentMethod paymentMethod = new PaymentMethod();
        paymentMethod.setPaymentMethodType("CARD");
        alipayPaymentSessionRequest.setPaymentMethod(paymentMethod);

        // set auth capture payment mode
        PaymentFactor paymentFactor = new PaymentFactor();
        paymentFactor.setAuthorization(true);
        alipayPaymentSessionRequest.setPaymentFactor(paymentFactor);

        // replace to your orderId
        String orderId = UUID.randomUUID().toString();

        // set order Info
        Order order = new Order();
        order.setReferenceOrderId(orderId);
        order.setOrderDescription("antom test order");
        order.setOrderAmount(amount);
        Buyer buyer = new Buyer();
        buyer.setReferenceBuyerId("yourBuyerId");
        order.setBuyer(buyer);
        order.setOrderAmount(amount);
        alipayPaymentSessionRequest.setOrder(order);

        //set env Info
        Env env = new Env();
        env.setTerminalType(TerminalType.WEB);
        env.setClientIp("114.121.121.01");
        alipayPaymentSessionRequest.setEnv(env);

        // replace to your notify url
        alipayPaymentSessionRequest.setPaymentNotifyUrl("http://www.yourNotifyUrl.com");

        // replace to your redirect url
        alipayPaymentSessionRequest.setPaymentRedirectUrl("http://www.yourRedirectUrl.com");

        //do the Payment
        AlipayPaymentSessionResponse alipayPaymentSessionResponse = null;
        System.out.println(JSONObject.toJSON(alipayPaymentSessionRequest));
        try {
            alipayPaymentSessionResponse = defaultAlipayClient.execute(alipayPaymentSessionRequest);
        } catch (AlipayApiException e) {
            String errorMsg = e.getMessage();
            // handle error condition
        }
        //show response
        System.out.println(JSONObject.toJSON(alipayPaymentSessionResponse));

    }

    /**
     *  show how to inquiry PaymentResult (
     */
    public static void executeInquiry() {

        AlipayPayQueryRequest alipayPayQueryRequest = new AlipayPayQueryRequest();
        alipayPayQueryRequest.setClientId(CLIENT_ID);
        alipayPayQueryRequest.setPath("/ams/sandbox/api/v1/payments/inquiryPayment");

        //inquiry payment result
        alipayPayQueryRequest.setPaymentRequestId("yourPaymentRequestId");
        AlipayPayQueryResponse alipayPayQueryResponse = null;
        try {
            alipayPayQueryResponse = defaultAlipayClient.execute(alipayPayQueryRequest);
        } catch (AlipayApiException e) {
            String errorMsg = e.getMessage();
            // handle error condition
        }
        //show response
        System.out.println(JSONObject.toJSON(alipayPayQueryResponse));

    }
}
