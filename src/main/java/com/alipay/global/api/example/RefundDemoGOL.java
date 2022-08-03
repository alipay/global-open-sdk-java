package com.alipay.global.api.example;

import com.alipay.global.api.AlipayClient;
import com.alipay.global.api.DefaultAlipayClient;
import com.alipay.global.api.exception.AlipayApiException;
import com.alipay.global.api.model.ams.Amount;
import com.alipay.global.api.request.ams.pay.AlipayRefundRequest;
import com.alipay.global.api.response.ams.pay.AlipayRefundResponse;

public class RefundDemoGOL {
    //可以从API接口说明文档中 https://global.alipay.com/docs/ac/ams/refund_online API Explorer中获取
    //例如：/ams/sandbox/api/v1/payments/refund
    private static final String REFUND_PATH = "replace";

    //商户id，从开发者中心获取
    private static final String CLIENT_ID = "replace by your clientId";

    //请求的网关地址从 https://global.alipay.com/docs/devcenter，开发者中心获取
    //或寻求overseas_support@service.alibaba.com的帮助
    private static final String GATEWAY_URL = "replace";

    //密钥用于对请求和返回进行加解密
    //获取方式和详情查看https://global.alipay.com/docs/ac/ams/digital_signature
    private static final String MERCHANT_PRIVATE_KEY = "replace by your private key";
    private static final String ALIPAY_PUBLIC_KEY = "replace by your public key";

    //初始化支付宝客户端
    //对用户屏蔽底层逻辑，用于和支付宝服务端交互。内含加签和验签等通用逻辑，用户关注业务参数即可
    private static final AlipayClient defaultAlipayClient = new DefaultAlipayClient(GATEWAY_URL, MERCHANT_PRIVATE_KEY, ALIPAY_PUBLIC_KEY);

    public static void main(String[] args) {
        // 构建退款请求对象
        AlipayRefundRequest alipayRefundRequest = new AlipayRefundRequest();
        alipayRefundRequest.setClientId(CLIENT_ID);
        alipayRefundRequest.setPath(REFUND_PATH);

        // The refund amount that is initiated by the merchant.
        Amount amount = new Amount();
        amount.setCurrency("USD");
        amount.setValue("100");
        alipayRefundRequest.setRefundAmount(amount);

        // The unique ID assigned by the merchant to identify a refund request.
        // More information about this field:
        // This field is an API idempotency field. The merchant uses the refundRequestId field for idempotency control. For payment requests that are initiated with the same value of refundRequestId and reach a final status (S or F), the same result is to be returned for the request.
        // Maximum length: 64 characters
        alipayRefundRequest.setRefundRequestId("replace by your refundId");

        // The unique ID assigned by Alipay for the original payment to be refunded.
        alipayRefundRequest.setPaymentId("replace by your paymentId");

        try {
            AlipayRefundResponse alipayPayQueryResponse = defaultAlipayClient.execute(alipayRefundRequest);
        } catch (AlipayApiException e) {
            //用户应自行处理退款异常情况
            e.printStackTrace();
        }
    }

}