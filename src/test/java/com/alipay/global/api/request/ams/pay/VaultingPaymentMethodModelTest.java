package com.alipay.global.api.request.ams.pay;

import static org.junit.Assert.*;

import com.alipay.global.api.model.ams.*;
import com.alipay.global.api.request.ams.notify.AlipayVaultingNotify;
import com.alipay.global.api.response.ams.pay.AlipayVaultingPaymentMethodResponse;
import com.alipay.global.api.response.ams.pay.AlipayVaultingQueryResponse;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;

public class VaultingPaymentMethodModelTest {
  @Test
  public void serializesRequestMetadataWithoutChangingResponseOrNotificationTypes()
      throws Exception {
    Map<String, Object> metadata = new HashMap<>();
    metadata.put("recurringType", "UNSCHEDULED");
    metadata.put("enabled", false);
    metadata.put("count", 0);
    metadata.put("items", Collections.emptyList());
    VaultingRequestPaymentMethodDetail detail = new VaultingRequestPaymentMethodDetail();
    detail.setPaymentMethodType("CARD");
    detail.setPaymentMethodMetaData(metadata);
    AlipayVaultingPaymentMethodRequest request = new AlipayVaultingPaymentMethodRequest();
    request.setPaymentMethodDetail(detail);
    JsonNode encoded =
        new ObjectMapper()
            .valueToTree(request)
            .get("paymentMethodDetail")
            .get("paymentMethodMetaData");
    assertEquals("UNSCHEDULED", encoded.get("recurringType").asText());
    assertFalse(encoded.get("enabled").asBoolean());
    assertEquals(0, encoded.get("count").asInt());
    assertEquals(0, encoded.get("items").size());
    PaymentMethodDetail responseDetail = new PaymentMethodDetail();
    AlipayVaultingPaymentMethodResponse response = new AlipayVaultingPaymentMethodResponse();
    AlipayVaultingQueryResponse query = new AlipayVaultingQueryResponse();
    response.setPaymentMethodDetail(responseDetail);
    query.setPaymentMethodDetail(responseDetail);
    assertSame(responseDetail, response.getPaymentMethodDetail());
    assertSame(responseDetail, query.getPaymentMethodDetail());
    VaultingCard card = new VaultingCard();
    AlipayVaultingNotify notify = new AlipayVaultingNotify();
    notify.setPaymentMethodDetail(new VaultingPaymentMethodDetail("CARD", card, new Wallet()));
    VaultingCard originalCard = notify.getPaymentMethodDetail().getCard();
    assertSame(card, originalCard);
  }
}
