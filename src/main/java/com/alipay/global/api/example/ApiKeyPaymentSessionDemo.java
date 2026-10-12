package com.alipay.global.api.example;

import com.alipay.global.api.ApiKeyAlipayClient;
import com.alipay.global.api.model.ResultStatusType;
import com.alipay.global.api.model.ams.Amount;
import com.alipay.global.api.model.ams.Env;
import com.alipay.global.api.model.ams.Order;
import com.alipay.global.api.model.ams.PaymentFactor;
import com.alipay.global.api.model.ams.PaymentMethod;
import com.alipay.global.api.model.ams.ProductCodeType;
import com.alipay.global.api.model.ams.SettlementStrategy;
import com.alipay.global.api.model.ams.TerminalType;
import com.alipay.global.api.request.ams.pay.AlipayPaymentSessionRequest;
import com.alipay.global.api.response.ams.pay.AlipayPaymentSessionResponse;
import com.alipay.global.api.tools.JsonUtil;
import java.util.UUID;

/** Creates one sandbox payment session using an authorized Restricted TEST key. */
public class ApiKeyPaymentSessionDemo {
  public static void main(String[] args) throws Exception {
    String key = requiredEnv("ANTOM_API_KEY");
    if (!key.startsWith("irak_TEST_")) {
      throw new IllegalArgumentException("This example requires a Restricted TEST key");
    }
    ApiKeyAlipayClient client = new ApiKeyAlipayClient(requiredEnv("ANTOM_GATEWAY_URL"), key);
    String notifyUrl = requiredEnv("ANTOM_NOTIFY_URL");
    Amount amount = Amount.builder().currency("USD").value("100").build();
    AlipayPaymentSessionRequest request = new AlipayPaymentSessionRequest();
    request.setProductCode(ProductCodeType.CASHIER_PAYMENT);
    request.setProductScene("CHECKOUT_PAYMENT");
    request.setPaymentRequestId("example-session-" + UUID.randomUUID());
    request.setOrder(
        Order.builder()
            .referenceOrderId("example-order-" + UUID.randomUUID())
            .orderDescription("API Key sandbox example")
            .orderAmount(amount)
            .build());
    request.setPaymentAmount(amount);
    request.setPaymentMethod(PaymentMethod.builder().paymentMethodType("CARD").build());
    request.setPaymentFactor(PaymentFactor.builder().isAuthorization(false).build());
    request.setSettlementStrategy(SettlementStrategy.builder().settlementCurrency("USD").build());
    request.setEnv(Env.builder().terminalType(TerminalType.WEB).clientIp("127.0.0.1").build());
    // Use your redirect page separately from your notification endpoint in a real integration.
    request.setPaymentRedirectUrl(notifyUrl);
    request.setPaymentNotifyUrl(notifyUrl);

    AlipayPaymentSessionResponse response = client.execute(request);
    // Local debugging only: the response contains payment-session credentials.
    System.out.println(JsonUtil.toJson(response));
    if (response.getResult() == null
        || response.getResult().getResultStatus() != ResultStatusType.S
        || !"SUCCESS".equals(response.getResult().getResultCode())) {
      throw new IllegalStateException("createPaymentSession failed; see the response result");
    }
  }

  private static String requiredEnv(String name) {
    String value = System.getenv(name);
    if (value == null || value.trim().isEmpty()) {
      throw new IllegalArgumentException("Missing environment variable: " + name);
    }
    return value;
  }
}
