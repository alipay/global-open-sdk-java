package com.alipay.global.api.request.ams.notify;

import com.alipay.global.api.tools.JsonUtil;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.InputStream;
import java.util.Iterator;
import org.junit.Test;
import static org.junit.Assert.*;

public class AlipayCardTransactionEventNotifyTest {
  private final ObjectMapper mapper = new ObjectMapper();

  private JsonNode withoutNulls(JsonNode value) {
    if (value.isObject()) {
      ObjectNode result = mapper.createObjectNode();
      Iterator<String> keys = value.fieldNames();
      while (keys.hasNext()) {
        String key = keys.next();
        if (!value.get(key).isNull()) result.set(key, withoutNulls(value.get(key)));
      }
      return result;
    }
    return value;
  }

  @Test public void parsesEveryDocumentedFieldWithoutAnEnvelope() throws Exception {
    assertEquals(22, AlipayCardTransactionEventNotify.class.getDeclaredFields().length);
    try (InputStream stream = getClass().getResourceAsStream("/card-transaction-event-notify.json")) {
      assertNotNull(stream);
      for (JsonNode fixture : mapper.readTree(stream)) {
        ObjectNode body = fixture.get("body").deepCopy();
        body.putObject("futureField").put("ignored", true);
        AlipayCardTransactionEventNotify notify =
            JsonUtil.fromJson(body.toString(), AlipayCardTransactionEventNotify.class);
        assertEquals(fixture.get("name").asText(), withoutNulls(fixture.get("body")),
            mapper.readTree(JsonUtil.toJson(notify)));
        assertEquals(fixture.get("body").get("eventId").asText(), notify.getEventId());
      }
    }
  }

  @Test public void preservesCaptureZeroAndAuthorizationCorrelationTime() throws Exception {
    try (InputStream stream = getClass().getResourceAsStream("/card-transaction-event-notify.json")) {
      JsonNode fixtures = mapper.readTree(stream);
      AlipayCardTransactionEventNotify auth =
          JsonUtil.fromJson(fixtures.get(0).get("body").toString(), AlipayCardTransactionEventNotify.class);
      assertNull(auth.getAuthTime());
      AlipayCardTransactionEventNotify capture =
          JsonUtil.fromJson(fixtures.get(1).get("body").toString(), AlipayCardTransactionEventNotify.class);
      assertEquals("0", capture.getOutAmount().getValue());
      assertEquals("USD", capture.getOutAmount().getCurrency());
      assertEquals("30000", capture.getTransactionAmount().getValue());
      assertEquals("2026-07-09T10:35:10+00:00", capture.getAuthTime());
      assertNull(capture.getInAmount());
      assertEquals("ANTOM_BIZ_ACCOUNT", capture.getBalanceType());
    }
    AlipayCardTransactionEventNotify empty = new AlipayCardTransactionEventNotify();
    empty.setEventId("setter");
    assertEquals("setter", empty.getEventId());
    assertNull(empty.getMetadata());
  }
}
