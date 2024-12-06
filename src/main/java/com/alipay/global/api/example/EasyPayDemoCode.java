package com.alipay.global.api.example;

import com.alipay.global.api.AlipayClient;
import com.alipay.global.api.DefaultAlipayClient;
import com.alipay.global.api.exception.AlipayApiException;
import com.alipay.global.api.model.ams.*;
import com.alipay.global.api.model.constants.EndPointConstants;
import com.alipay.global.api.model.constants.ProductSceneConstants;
import com.alipay.global.api.request.ams.pay.AlipayPayQueryRequest;
import com.alipay.global.api.request.ams.pay.AlipayPayRequest;
import com.alipay.global.api.request.ams.pay.AlipayPaymentSessionRequest;
import com.alipay.global.api.response.ams.pay.AlipayPayQueryResponse;
import com.alipay.global.api.response.ams.pay.AlipayPayResponse;
import com.alipay.global.api.response.ams.pay.AlipayPaymentSessionResponse;

import java.util.Objects;
import java.util.UUID;

public class EasyPayDemoCode {

    /**
     * replace with your client id.
     * find your client id here: <a href="https://dashboard.alipay.com/global-payments/developers/quickStart">quickStart</a>
     */
    public static final String CLIENT_ID = "SANDBOX_5YBZ1G2ZHUPS06086";

    /**
     * replace with your antom public key (used to verify signature).
     * find your antom public key here: <a href="https://dashboard.alipay.com/global-payments/developers/quickStart">quickStart</a>
     */
    public static final String ANTOM_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAkJIL3C7NSzSQxP1DNK0Grktr5G5bMEj4ndEIBnSyFv8+e6ytS+G1+ch7EdZ4Lt7KYUGoFW1wJKyTS8V5UBMJTWxAkdc2uX3GrQiWbPvReMl3sNa3SC9Kmi8ofVKQdpAt8aZZaTLxmti0YyCh8zUTddE9AOeMZi8xvzC8chcGbfx4FA5meFGkPEBeYfxZgQzCjXnMJ/A2JFeh5g2443pfAq/caoIamcnTcA9qhJCMaqDyXb2pxXmg/VOClhqhaOjj4dnxzYKln1YNJw02SaVT9zjkNJkbY2QzCjEV0NdG/QLCQ/xBkFlDvlJ+nyCiTySqVOuJXGCos1cljMoYJGZLXQIDAQAB";

    /**
     * replace with your private key (used to sign).
     * please ensure the secure storage of your private key to prevent leakage
     */
    public static final String MERCHANT_PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC/PqnrI3zdRMIAqIFzOlNkop2o/jMVva22j05W7/Sv78PU7SMmwQGObmw9COcm2nX0N1ix0dlqH2lmXRmoE69hElXcWnDEzcSHpNe1HtM0eWgOLozrwpa0PXue0O0cxvEhZxuWqKUMrwf3cV9dziTzbmmK70wP5PcIstjqy8SK/LyMZ/W5q1xRrBlRmXQvAOnx79A9ok0m8H99ziwOye9aDr8Tb0LEVBxeUVB+XwniiHYDbxRjY1awsYOaK66W+gZcx4ORXx8xDjDPX6q45RNIuJRgSXdqsQQxEk5tKSHpSj8LSqz67Dr/Jyq8MVJn30VXL+uX+yHyeCNKe5tf+2IHAgMBAAECggEATw+DxU5tbzfej9EZet5Q3ViQnu0/hyxb5Q3HUA9w807GgX7rOjkuAIjLvEuy65AClUxQIWrkW4fS1duFIMPKi/G9hxPobKO4LG9MMXclzxqllr9NyKUwEiEcuuIaM/xWcP2kHRto6B5vx66ZwzjWc8BgZ2xX4HZCXdk57Y8BmIb9IejKgIYtyUWrYuGkvQsh+SHWX5CZVEgGlCy3uYOMDj0BkSJOrLc3yW6octGuVQLEgFx3EEB0ZJMThUB+gxInj/pl06iwCuWbZXxkRkXHrllz1gOvfGwhtccwPHrKGJDHDY+OIIgsd4eEDJjySdMjx2hydmCbxbRBxEIK5iW/kQKBgQD10ReS5F6T+QDgc2VdscZo/64PLldXtH71sTJoIat8XK0oPNSbkzJzPpuWwj6hpSKO1/8Hsq52gM2IjJBSx2q7nD9azVrBevl/aLhAmM4OX7ahSDaQZx3yHwL40onXAZIVDECyi1zRLtBDgKaGgLJdIZ2lzgo7Jhcw8J3S/Np52QKBgQDHKtTAl43dKuTV5QZ7PwItrgcjOvm6LzhuI+xbM5YnAOLcp7g9UP6LLI5kB00UkB9fDnoePVbwEgl0ShIKHCWBU++F4mjPoayoTQCKBzNuvK5wKklVPoVDsnwhGXIS8fkBmU5VtjArB0kCAoIpR5HEzIaCiaZZGWMiZ0kfBdBu3wKBgQC+ZRJ2Qw4CXMZSEu87b/u2zfq6ZXFfTD1d/b6GKzYQ4BN6bAtc6NkVrDOExLUQLMCklSZChyJcRQ1tKzqJ8013POFRamdWHvLqvWihF/nZ5kalizJADK6EH4MEyMXc06mbRd9Cq3Db0P+cmSPiYAJG4keh6gHAqJMj4+rKRfDOmQKBgCbdp9jRemCffzpyT/p7CDzLyh7I4nS/xD5SCkyd235PAPZYUG6+wH1+O2cvuY36tfSBybje9Xkxu+CSl8SbS4JaU9KHpTZncV8Cb8l/sDy62zuONPNKmQzl5q063vTtfU8fkJbPT8UFzexzetz9V2fVFaahn/GhL6RGDZHdO5h3AoGALV6PDVsB6VjJtE8RA8Bpsmwl6ytLDiEljFImkzXQzq4/gTz2NJXjKGnUC70pae1Z1OWMHoZWqFgg1YAxVHeFZBqLQ88HwmCwwRjLkUyFxPRlJ7y4V8olBsDY57E1j03MNENaal88bRpvKUoZRTh8HCbk6BV8e6o+2vMf3HUe2Ss=";

    /**
     * please replace with your endpoint.
     * find your endpoint here: <a href="https://dashboard.alipay.com/global-payments/developers/quickStart">quickStart</a>
     */
    private final static AlipayClient CLIENT = new DefaultAlipayClient(
            EndPointConstants.SG, MERCHANT_PRIVATE_KEY, ANTOM_PUBLIC_KEY, CLIENT_ID);

    public static void main(String[] args) {
        easyPaySession();
    }

    public static void easyPaySession() {
        AlipayPaymentSessionRequest alipayPaymentSessionRequest = new AlipayPaymentSessionRequest();
        alipayPaymentSessionRequest.setProductScene(ProductSceneConstants.EASY_PAY);
        alipayPaymentSessionRequest.setProductCode(ProductCodeType.CASHIER_PAYMENT);

        // replace with your paymentRequestId
        String paymentRequestId = UUID.randomUUID().toString();
        alipayPaymentSessionRequest.setPaymentRequestId(paymentRequestId);

        // set amount
        Amount amount = Amount.builder().value("4200").currency("HKD").build();
        alipayPaymentSessionRequest.setPaymentAmount(amount);

        // set paymentMethod
        PaymentMethod paymentMethod = PaymentMethod.builder().paymentMethodType("CARD").build();
        alipayPaymentSessionRequest.setPaymentMethod(paymentMethod);

        // replace with your orderId
        String orderId = UUID.randomUUID().toString();

        // set order info
        Order order = Order.builder().referenceOrderId(orderId)
                .orderDescription("antom testing order").orderAmount(amount).build();
        alipayPaymentSessionRequest.setOrder(order);

        AlipayPaymentSessionResponse alipayPaymentSessionResponse = null;

        try {
            alipayPaymentSessionResponse = CLIENT.execute(alipayPaymentSessionRequest);
        } catch (AlipayApiException e) {
            String errorMsg = e.getMessage();
            // handle error condition
        }

        System.out.println(alipayPaymentSessionResponse);

    }







}
