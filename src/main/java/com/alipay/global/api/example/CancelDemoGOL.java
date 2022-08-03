package com.alipay.global.api.example;

import com.alipay.global.api.AlipayClient;
import com.alipay.global.api.DefaultAlipayClient;
import com.alipay.global.api.exception.AlipayApiException;
import com.alipay.global.api.request.ams.pay.AlipayPayCancelRequest;
import com.alipay.global.api.response.ams.pay.AlipayPayCancelResponse;

public class CancelDemoGOL {
    //请求的网关地址从 https://global.alipay.com/docs/devcenter，开发者中心获取
    //或寻求overseas_support@service.alibaba.com的帮助
    private static final String GATEWAY_URL = "replace";

    //密钥用于对请求和返回进行加解密
    //获取方式和详情查看https://global.alipay.com/docs/ac/ams/digital_signature
    private static final String MERCHANT_PRIVATE_KEY = "replace by your private key";
    private static final String ALIPAY_PUBLIC_KEY = "replace by your public key";

    //商户id，从开发者中心获取
    private static final String CLIENT_ID = "replace by your clientId";

    //可以从API接口说明文档中 https://global.alipay.com/docs/ac/ams/paymentc_online?pageVersion=27 API Explorer中获取
    //例如：/ams/sandbox/api/v1/payments/cancel
    private static final String CANCEL_PATH = "replace";

    //用于发送请求，内涵加解密
    private static final AlipayClient defaultAlipayClient = new DefaultAlipayClient(GATEWAY_URL, MERCHANT_PRIVATE_KEY, ALIPAY_PUBLIC_KEY);

    public static void main(String[] args) {
        //创建取消支付请求对象
        final AlipayPayCancelRequest alipayPayCancelRequest = new AlipayPayCancelRequest();
        alipayPayCancelRequest.setClientId(CLIENT_ID);
        alipayPayCancelRequest.setPath(CANCEL_PATH);
        //支付时，提供的支付id
        alipayPayCancelRequest.setPaymentRequestId("Replace by your paymentId");

        try {
            AlipayPayCancelResponse alipayPayCancelResponse = defaultAlipayClient.execute(alipayPayCancelRequest);
        } catch (AlipayApiException e) {
            //用户应自行处理支付的异常情况
            e.printStackTrace();
        }
    }

}


