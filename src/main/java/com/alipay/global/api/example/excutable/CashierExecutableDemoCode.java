package com.alipay.global.api.example.excutable;

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
import com.alipay.global.api.model.constants.EndPointConstants;
import com.alipay.global.api.model.constants.PathConstants;
import com.alipay.global.api.request.ams.pay.AlipayPayQueryRequest;
import com.alipay.global.api.request.ams.pay.AlipayPayRequest;
import com.alipay.global.api.response.ams.pay.AlipayPayQueryResponse;
import com.alipay.global.api.response.ams.pay.AlipayPayResponse;

import java.util.UUID;


public class CashierExecutableDemoCode {


    private static final String       merchantPrivateKey  = "";

    private static final String       alipayPublicKey     = "";

    private static final String       CLIENT_ID           = "";




    public static void main(String[] args){

    }



    private static void cashierPay(){

        // Select your gateway address according to the recommendations of the developer center.
        AlipayClient defaultAlipayClient = new DefaultAlipayClient(EndPointConstants.SG,
                merchantPrivateKey, alipayPublicKey);

        AlipayPayRequest alipayPayRequest = new AlipayPayRequest();
        alipayPayRequest.setClientId(CLIENT_ID);
        alipayPayRequest.setPath(PathConstants.PAY_PROD_PATH);
        alipayPayRequest.setProductCode(ProductCodeType.CASHIER_PAYMENT);

        // replace to your paymentRequestId
        String paymentRequestId = UUID.randomUUID().toString();
        alipayPayRequest.setPaymentRequestId(paymentRequestId);


        // set amount
        Amount amount = new Amount();
        amount.setCurrency("SGD");
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
        paymentFactor.setIsAuthorization(true);
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
        System.out.println(alipayPayResponse);
    }



    private static void cashierInquiryPayment(){

        AlipayClient defaultAlipayClient = new DefaultAlipayClient(EndPointConstants.SG,
                merchantPrivateKey, alipayPublicKey);

        AlipayPayQueryRequest alipayPayQueryRequest = new AlipayPayQueryRequest();
        alipayPayQueryRequest.setClientId(CLIENT_ID);
        alipayPayQueryRequest.setPath("/ams/api/v1/payments/pay");

        AlipayPayQueryResponse alipayPayQueryResponse;
        try {
            alipayPayQueryResponse = defaultAlipayClient.execute(alipayPayQueryRequest);
        } catch (AlipayApiException e) {
            String errorMsg = e.getMessage();
            // handle error condition
        }
    }






}
