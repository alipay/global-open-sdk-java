package com.alipay.global.api.request.ams.notify;

import com.alipay.global.api.tools.JsonUtil;
import org.junit.Test;
import static org.junit.Assert.*;

public class AlipayDisputeNotifyTest {
  @Test
  public void parsesEventTimesWithoutChangingOffsets() throws Exception {
    String[][] cases = {
      {"DISPUTE_CANCELLED", "disputeCancelledTime", "2026-08-03T03:56:00+00:00"},
      {"DEFENSE_SUPPLIED", "defenseSuppliedTime", "2022-09-22T01:10:05-07:00"}
    };
    for (String[] item : cases) {
      String body = "{\"disputeId\":\"d1\",\"disputeNotificationType\":\"" + item[0]
          + "\",\"" + item[1] + "\":\"" + item[2] + "\"}";
      AlipayDisputeNotify notify = JsonUtil.fromJson(body, AlipayDisputeNotify.class);
      assertEquals("d1", notify.getDisputeId());
      assertEquals(item[2], item[1].equals("disputeCancelledTime")
          ? notify.getDisputeCancelledTime() : notify.getDefenseSuppliedTime());
      assertNull(item[1].equals("disputeCancelledTime")
          ? notify.getDefenseSuppliedTime() : notify.getDisputeCancelledTime());
      assertTrue(JsonUtil.toJson(notify).contains("\"" + item[1] + "\":\"" + item[2] + "\""));
    }
  }

  @Test
  public void acceptsLegacyAndNullTimes() throws Exception {
    for (String body : new String[] {
        "{\"disputeId\":\"legacy\",\"disputeTime\":\"2022-09-20T23:41:32-07:00\"}",
        "{\"disputeCancelledTime\":null,\"defenseSuppliedTime\":null}"}) {
      AlipayDisputeNotify notify = JsonUtil.fromJson(body, AlipayDisputeNotify.class);
      assertNull(notify.getDisputeCancelledTime());
      assertNull(notify.getDefenseSuppliedTime());
    }
  }
}
