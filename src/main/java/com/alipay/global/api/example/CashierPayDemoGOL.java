package com.alipay.global.api.example;

import com.alipay.global.api.AlipayClient;
import com.alipay.global.api.DefaultAlipayClient;
import com.alipay.global.api.exception.AlipayApiException;
import com.alipay.global.api.model.ams.*;
import com.alipay.global.api.request.ams.pay.AlipayPayRequest;
import com.alipay.global.api.response.ams.pay.AlipayPayResponse;

public class CashierPayDemoGOL {
    //可以从API接口说明文档中 https://global.alipay.com/docs/ac/ams/payment_cashier?pageVersion=66 API Explorer中获取
    //例如：/ams/sandbox/api/v1/payments/pay
    private static final String PAY_PATH = "replace";

    //商户id，从开发者中心获取
    private static final String CLIENT_ID = "replace by your clientId";

    //请求的网关地址从 https://global.alipay.com/docs/devcenter，开发者中心获取
    //或寻求overseas_support@service.alibaba.com的帮助
    private static final String GATEWAY_URL = "replace";

    //密钥用于对请求和返回进行加签和验签
    //获取方式和详情查看https://global.alipay.com/docs/ac/ams/digital_signature
    private static final String MERCHANT_PRIVATE_KEY = "replace by your private key";
    private static final String ALIPAY_PUBLIC_KEY = "replace by your public key";

    //初始化支付宝客户端
    //对用户屏蔽底层逻辑，用于和支付宝服务端交互。内含加签和验签等通用逻辑，用户关注业务参数即可
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

        // 设置支付币种和金额
        // The payment amount that the merchant requests to receive in the order currency.
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

        //设置订单币种和金额
        Amount orderAmount = new Amount();
        orderAmount.setCurrency("USD");
        orderAmount.setValue("100");
        order.setOrderAmount(orderAmount);
        alipayPayRequest.setOrder(order);

        //设置付款方式
        // The payment method that is used to collect the payment by the merchant or acquirer.
        PaymentMethod paymentMethod = new PaymentMethod();
        // Payment method type, used for specifying the payment method type.
        // By specifying the value of this field, it indicates that Alipay is required to return the cashier URL provided by the specified payment method.
        // 支持的支付方式列表见：https://global.alipay.com/docs/ac/ams/payment_cashier?pageVersion=66
        paymentMethod.setPaymentMethodType(WalletPaymentMethodType.GCASH.name());
        alipayPayRequest.setPaymentMethod(paymentMethod);

        //设置支付的环境，共有四类： WEB，WAP，APP，MINI_APP
        Env env = new Env();
        env.setTerminalType(TerminalType.APP);
        //设置终端操作系统，分为IOS或ANDROID
        env.setOsType(OsType.IOS);
        order.setEnv(env);

        //设置支付通知和跳转地址
        // The URL that is used to receive the payment result notification.
        alipayPayRequest.setPaymentNotifyUrl("https://www.gcash.com/notify");
        // The merchant page URL that the user is redirected to after the payment is completed.
        alipayPayRequest.setPaymentRedirectUrl("https://www.gcash.com?param1=sl");

        // The settlement strategy for the payment request.
        SettlementStrategy settlementStrategy = new SettlementStrategy();
        // The ISO currency code of the currency that the merchant wants to be settled against. The field is required if the merchant signed up for multiple currencies to settle.
        // More information about this field:
        // 	Maximum length: 3 characters
        settlementStrategy.setSettlementCurrency("USD");
        alipayPayRequest.setSettlementStrategy(settlementStrategy);

        // 商户或二级商户经营业务的国家或地区。
        // 2个字母的国家/地区代码遵循 https://www.iso.org/obp/ui/#search
        // The country or region where the merchant or secondary merchant operates the business.
        // The 2-letter country/region code follows ISO 3166 Country Codes standard. Only US, JP, PK, SG are supported now
        // Note: This field is required when you use Global Acquirer Gateway (GAGW) product.
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