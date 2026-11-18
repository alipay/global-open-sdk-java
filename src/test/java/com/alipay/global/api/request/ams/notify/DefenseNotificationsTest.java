package com.alipay.global.api.request.ams.notify;

import com.alipay.global.api.tools.JsonUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import static org.junit.Assert.*;

public class DefenseNotificationsTest {
  private static final String STRATEGY = "{\"disputeId\":\"d1\",\"notifyTime\":\"2026-10-22T01:02:03.123+08:00\",\"winRateLevel\":\"FUTURE\",\"evidenceList\":[{\"evidenceTag\":\"FUTURE_TAG\",\"obtained\":false,\"winRateContribution\":\"FUTURE\"},{\"evidenceTag\":\"ORDER_INFORMATION\",\"obtained\":true}],\"cardholderDisputeReason\":\"Reason\",\"defenseStrategyDescription\":\"Supply evidence\",\"futureField\":{\"ignored\":true}}";
  private static final String DOCUMENT = "{\"disputeId\":\"d1\",\"notifyTime\":\"2026-10-22T01:02:03.123+08:00\",\"defenseFile\":{\"fileName\":\"defense.pdf\",\"fileFormat\":\"FUTURE\",\"fileMd5\":\"5d41402abc4b2a76b9719d911017c592\"},\"winRateLevel\":\"FUTURE\",\"recommendedEvidence\":{\"FUTURE_TAG\":\"提供材料\",\"ORDER_INFORMATION\":\"Order proof\"},\"suggestionsForImprovement\":\"Add proof\"}";

  @Test public void parsesAndRoundTripsBothNotifications() throws Exception {
    AlipayDefenseStrategySuggestionNotify s = JsonUtil.fromJson(STRATEGY, AlipayDefenseStrategySuggestionNotify.class);
    assertEquals(Boolean.FALSE, s.getEvidenceList().get(0).getObtained());
    assertEquals(Boolean.TRUE, s.getEvidenceList().get(1).getObtained());
    assertNull(s.getEvidenceList().get(1).getWinRateContribution());
    ObjectMapper mapper = new ObjectMapper();
    assertEquals(mapper.readTree("{\"disputeId\":\"d1\",\"notifyTime\":\"2026-10-22T01:02:03.123+08:00\",\"winRateLevel\":\"FUTURE\",\"evidenceList\":[{\"evidenceTag\":\"FUTURE_TAG\",\"obtained\":false,\"winRateContribution\":\"FUTURE\"},{\"evidenceTag\":\"ORDER_INFORMATION\",\"obtained\":true}],\"cardholderDisputeReason\":\"Reason\",\"defenseStrategyDescription\":\"Supply evidence\"}"), mapper.readTree(JsonUtil.toJson(s)));
    AlipayDefenseDocumentNotify d = JsonUtil.fromJson(DOCUMENT, AlipayDefenseDocumentNotify.class);
    assertEquals(mapper.readTree(DOCUMENT), mapper.readTree(JsonUtil.toJson(d)));
  }

  @Test public void acceptsMissingAndNullOptionals() throws Exception {
    for (String json : new String[]{"{}", "{\"winRateLevel\":null,\"recommendedEvidence\":null,\"suggestionsForImprovement\":null}"}) {
      AlipayDefenseDocumentNotify d = JsonUtil.fromJson(json, AlipayDefenseDocumentNotify.class);
      assertNull(d.getWinRateLevel()); assertNull(d.getRecommendedEvidence()); assertNull(d.getSuggestionsForImprovement());
    }
    AlipayDefenseStrategySuggestionNotify s = JsonUtil.fromJson("{\"evidenceList\":[{\"evidenceTag\":\"X\"}]}", AlipayDefenseStrategySuggestionNotify.class);
    assertNull(s.getEvidenceList().get(0).getObtained()); assertNull(s.getCardholderDisputeReason());
  }
}
