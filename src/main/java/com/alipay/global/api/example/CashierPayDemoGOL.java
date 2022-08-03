package com.alipay.global.api.example;

import com.alipay.global.api.AlipayClient;
import com.alipay.global.api.DefaultAlipayClient;
import com.alipay.global.api.example.model.Callback;
import com.alipay.global.api.example.model.ResultCode;
import com.alipay.global.api.example.model.RetryResult;
import com.alipay.global.api.exception.AlipayApiException;
import com.alipay.global.api.model.Result;
import com.alipay.global.api.model.ResultStatusType;
import com.alipay.global.api.model.ams.*;
import com.alipay.global.api.request.ams.pay.AlipayPayCancelRequest;
import com.alipay.global.api.request.ams.pay.AlipayPayQueryRequest;
import com.alipay.global.api.request.ams.pay.AlipayPayRequest;
import com.alipay.global.api.response.ams.pay.AlipayPayCancelResponse;
import com.alipay.global.api.response.ams.pay.AlipayPayQueryResponse;
import com.alipay.global.api.response.ams.pay.AlipayPayResponse;

import java.util.Arrays;
import java.util.List;

public class CashierPayDemoGOL {
    //请求的网关地址从 https://global.alipay.com/docs/devcenter，开发者中心获取
    //或寻求overseas_support@service.alibaba.com的帮助
    private static final String GATEWAY_URL = "replace";

    //密钥用于对请求和返回进行加解密
    //获取方式和详情查看https://global.alipay.com/docs/ac/ams/digital_signature
    private static final String MERCHANT_PRIVATE_KEY = "replace by your private key";
    private static final String ALIPAY_PUBLIC_KEY = "replace by your public key";

    //商户id，从开发者中心获取
    private static final String CLIENT_ID = "replace by your clientId";

    //可以从API接口说明文档中 https://global.alipay.com/docs/ac/ams/payment_cashier?pageVersion=66 API Explorer中获取
    //例如：/ams/sandbox/api/v1/payments/cancel
    private static final String PAY_PATH = "replace";

    //用于发送请求，内涵加解密
    private static final AlipayClient defaultAlipayClient = new DefaultAlipayClient(GATEWAY_URL, MERCHANT_PRIVATE_KEY, ALIPAY_PUBLIC_KEY);

    public static void main(String[] args) {
        //构建支付请求对象
        AlipayPayRequest alipayPayRequest = new AlipayPayRequest();
        alipayPayRequest.setClientId(CLIENT_ID);
        alipayPayRequest.setPath(PAY_PATH);
        alipayPayRequest.setProductCode(ProductCodeType.CASHIER_PAYMENT);
        //由商户提供的唯一ID,用于识别支付请求。
        //该字段是 API 幂等性字段。对于使用相同值 paymentRequestId 发起并达到最终状态（S 或 F）的支付请求，该请求将返回相同的结果
        //最大长度为64位
        alipayPayRequest.setPaymentRequestId("Replace by your paymentId");

        //设置支付币种和金额
        Amount paymentAmount = new Amount();
        paymentAmount.setCurrency("USD");
        paymentAmount.setValue("100");
        alipayPayRequest.setPaymentAmount(paymentAmount);

        //构建订单信息
        Order order = new Order();
        //The unique ID to identify the order on the merchant side,
        //which is assigned by the merchant that provides services or goods directly to the customer
        order.setReferenceOrderId("102775765075669");
        order.setOrderDescription("summary description of the order");

        //构建商户信息
        Merchant merchant = new Merchant();
        //二级商户类别代码，即MCC代码中列出的4位数字 https://global.alipay.com/docs/ac/ref/mcccodes
        merchant.setMerchantMCC("0742");
        //标识直接向客户提供服务或商品的商家的ID。
        merchant.setReferenceMerchantId("MXDTXSETGLKJASELKJG");
        order.setMerchant(merchant);

        //设置收单币种和金额
        Amount orderAmount = new Amount();
        orderAmount.setCurrency("USD");
        orderAmount.setValue("100");
        order.setOrderAmount(orderAmount);
        alipayPayRequest.setOrder(order);

        //设置支付方式
        PaymentMethod paymentMethod = new PaymentMethod();
        paymentMethod.setPaymentMethodType(WalletPaymentMethodType.GCASH.name());
        alipayPayRequest.setPaymentMethod(paymentMethod);

        //设置支付的环境，共有四类： WEB，WAP，APP，MINI_APP
        Env env = new Env();
        env.setTerminalType(TerminalType.APP);
        //设置终端操作系统，分为IOS或ANDROID
        env.setOsType(OsType.IOS);
        order.setEnv(env);

        //设置支付结果通知地址和跳转地址
        alipayPayRequest.setPaymentNotifyUrl("https://www.gcash.com/notify");
        alipayPayRequest.setPaymentRedirectUrl("https://www.gcash.com?param1=sl");

        //设置收单币种
        SettlementStrategy settlementStrategy = new SettlementStrategy();
        settlementStrategy.setSettlementCurrency("USD");
        alipayPayRequest.setSettlementStrategy(settlementStrategy);

        //商户或二级商户经营业务的国家或地区。2个字母的国家/地区代码遵循 https://www.iso.org/obp/ui/#search
        alipayPayRequest.setMerchantRegion("US");
        alipayPayRequest.setAppId("312a74651aa74586be847a0c672243a9");

        //发起支付
        try {
            AlipayPayResponse alipayPayResponse = defaultAlipayClient.execute(alipayPayRequest);
        } catch (AlipayApiException e) {
            //用户应自行处理支付的异常情况
            e.printStackTrace();
        }
    }

}