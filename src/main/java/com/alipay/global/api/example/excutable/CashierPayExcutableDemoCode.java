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
import com.alipay.global.api.response.ams.pay.AlipayPayQueryResponse;
import com.alipay.global.api.response.ams.pay.AlipayPayResponse;

import java.util.UUID;

public class CashierPayExcutableDemoCode {

    private static final String       merchantPrivateKey  = "";

    private static final String       alipayPublicKey     = "";

    private static final String       CLIENT_ID           = "";

    private static final String       GATE_WAY_URL        = "";

    private static final AlipayClient defaultAlipayClient = new DefaultAlipayClient(GATE_WAY_URL,
        merchantPrivateKey, alipayPublicKey);

    public static void main(String[] args) {

        //executePayWithCard();
        //executePayWithBlik();
        //executeInquiry();

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
        JSONObject paymentMethodMetaDataObject = new JSONObject();
        paymentMethodMetaDataObject.put("cardNo", "0255187751531899");
        paymentMethodMetaDataObject.put("cvv", "712");
        paymentMethodMetaDataObject.put("cpf", "671.998.112-31");
        paymentMethodMetaDataObject.put("expiryYear", "28");
        paymentMethodMetaDataObject.put("expiryMonth", "28");
        JSONObject cardHolderName = new JSONObject();
        cardHolderName.put("firstName", "Alan");
        cardHolderName.put("lastName", "Wallex");
        paymentMethodMetaDataObject.put("cardHolderName", cardHolderName);
        paymentMethod
            .setPaymentMethodMetaData(JSONObject.toJSONString(paymentMethodMetaDataObject));

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
        Env env = new Env();
        env.setTerminalType(TerminalType.WEB);
        env.setClientIp("114.121.121.01");
        order.setEnv(env);
        alipayPayRequest.setOrder(order);

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
        Env env = new Env();
        env.setTerminalType(TerminalType.WEB);
        env.setClientIp("114.121.121.01");
        order.setEnv(env);
        alipayPayRequest.setOrder(order);

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
