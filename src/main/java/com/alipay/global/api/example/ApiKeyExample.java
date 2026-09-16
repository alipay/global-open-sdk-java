package com.alipay.global.api.example;

// Read ANTOM_GATEWAY_URL, ANTOM_API_KEY, ANTOM_REDIRECT_URL and ANTOM_NOTIFY_URL from the environment.
// Use a TEST key with createPaymentSession permission for sandbox testing.
// This example creates a CARD session for USD 1.00; it does not complete a payment.
// Replace 127.0.0.1 with the buyer's IP. Notifications require a reachable endpoint.

import java.util.UUID;
import com.alipay.global.api.DefaultAlipayClient;
import com.alipay.global.api.model.ams.*;
import com.alipay.global.api.request.ams.pay.AlipayPaymentSessionRequest;
import com.alipay.global.api.response.ams.pay.AlipayPaymentSessionResponse;

public class ApiKeyExample {
    public static void main(String[] args) throws Exception {
        DefaultAlipayClient client = new DefaultAlipayClient(
            System.getenv("ANTOM_GATEWAY_URL"), System.getenv("ANTOM_API_KEY"));
        Amount amount = Amount.builder().currency("USD").value("100").build();
        AlipayPaymentSessionRequest request = new AlipayPaymentSessionRequest();
        request.setProductCode(ProductCodeType.CASHIER_PAYMENT);
        request.setProductScene("CHECKOUT_PAYMENT");
        request.setPaymentRequestId(UUID.randomUUID().toString());
        request.setOrder(Order.builder()
            .referenceOrderId(UUID.randomUUID().toString())
            .orderDescription("API Key example").orderAmount(amount).build());
        request.setPaymentAmount(amount);
        request.setPaymentMethod(PaymentMethod.builder().paymentMethodType("CARD").build());
        request.setPaymentFactor(PaymentFactor.builder().isAuthorization(false).build());
        request.setSettlementStrategy(SettlementStrategy.builder().settlementCurrency("USD").build());
        request.setEnv(Env.builder().terminalType(TerminalType.WEB).clientIp("127.0.0.1").build());
        request.setPaymentRedirectUrl(System.getenv("ANTOM_REDIRECT_URL"));
        request.setPaymentNotifyUrl(System.getenv("ANTOM_NOTIFY_URL"));

        // Transport errors propagate as exceptions; also check the business result.
        AlipayPaymentSessionResponse response = client.execute(request);
        if (response.getResult() == null
                || !"S".equals(response.getResult().getResultStatus())
                || !"SUCCESS".equals(response.getResult().getResultCode())) {
            throw new IllegalStateException("Session creation was not successful: "
                + (response.getResult() == null ? "missing result" : response.getResult().getResultCode()));
        }
        if (response.getPaymentSessionId() == null || response.getPaymentSessionId().isEmpty()) {
            throw new IllegalStateException("Missing paymentSessionId");
        }
        // Use response.getPaymentSessionData() or the returned URL with your checkout.
        System.out.println("Payment session created");
    }
}
