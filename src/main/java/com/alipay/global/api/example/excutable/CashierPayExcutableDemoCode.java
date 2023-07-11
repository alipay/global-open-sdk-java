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
import com.alipay.global.api.request.ams.pay.AlipayPayConsultRequest;
import com.alipay.global.api.request.ams.pay.AlipayPayQueryRequest;
import com.alipay.global.api.request.ams.pay.AlipayPayRequest;
import com.alipay.global.api.request.ams.pay.AlipayPaymentSessionRequest;
import com.alipay.global.api.response.ams.pay.AlipayPayConsultResponse;
import com.alipay.global.api.response.ams.pay.AlipayPayQueryResponse;
import com.alipay.global.api.response.ams.pay.AlipayPayResponse;
import com.alipay.global.api.response.ams.pay.AlipayPaymentSessionResponse;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CashierPayExcutableDemoCode {

    private static final String       merchantPrivateKey  = "";

    private static final String       alipayPublicKey     = "";

    private static final String       CLIENT_ID           = "";

    private static final String       GATE_WAY_URL        = "";

    private static final AlipayClient defaultAlipayClient = new DefaultAlipayClient(GATE_WAY_URL,
        merchantPrivateKey, alipayPublicKey);

    public static void main(String[] args) {
        executeConsult();
        // executePayWithCard();
        // executePayWithBlik();
        // executePaymentSessionCreateWithCard();
        // executeInquiry();

    }

    public static void executeConsult() {
        AlipayPayConsultRequest alipayPayConsultRequest = new AlipayPayConsultRequest();
        alipayPayConsultRequest.setClientId(CLIENT_ID);
        alipayPayConsultRequest.setPath("/ams/sandbox/api/v1/payments/consult");
        alipayPayConsultRequest.setProductCode(ProductCodeType.CASHIER_PAYMENT);

        // set amount
        Amount amount = new Amount();
        amount.setCurrency("BRL");
        amount.setValue("4200");
        alipayPayConsultRequest.setPaymentAmount(amount);

        //set env Info
        Env env = new Env();
        env.setTerminalType(TerminalType.WEB);
        alipayPayConsultRequest.setEnv(env);

        AlipayPayConsultResponse alipayPayConsultResponse = null;

        try {
            alipayPayConsultResponse = defaultAlipayClient.execute(alipayPayConsultRequest);
        } catch (AlipayApiException e) {
            String errorMsg = e.getMessage();
            // handle error condition
        }
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


        // set amount
        Amount amount = new Amount();
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


        // set amount
        Amount amount = new Amount();
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

        // set amount
        Amount amount = new Amount();
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
    }
}
