package com.alipay.global.api.tools;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.InputStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class AmountUtilTest {
    private final JsonNode vectors = loadVectors();

    @Test
    public void sharedVectors() {
        for (JsonNode vector : vectors.get("toAmount")) {
            check(vector, new Call() {
                public String run(JsonNode item) {
                    return AmountUtil.toAmount(item.get("amount").asText(), item.get("currency").asText());
                }
            });
        }
        for (JsonNode vector : vectors.get("fromAmount")) {
            check(vector, new Call() {
                public String run(JsonNode item) {
                    return AmountUtil.fromAmount(item.get("value").asText(), item.get("currency").asText());
                }
            });
        }
        for (JsonNode vector : vectors.get("validate")) {
            check(vector, new Call() {
                public String run(JsonNode item) {
                    AmountUtil.validate(item.get("value").asText(), item.get("currency").asText());
                    return null;
                }
            });
        }
    }

    @Test
    public void nullArgumentsUseTypeCategory() {
        expectCategory("INVALID_ARGUMENT_TYPE", new Runnable() {
            public void run() { AmountUtil.toAmount(null, "USD"); }
        });
        expectCategory("INVALID_ARGUMENT_TYPE", new Runnable() {
            public void run() { AmountUtil.validate("1", null); }
        });
    }

    @Test
    public void successfulOutboundVectorsRoundTripExactly() {
        for (JsonNode vector : vectors.get("toAmount")) {
            if (vector.has("result")) {
                String value = vector.get("result").asText();
                String currency = vector.get("currency").asText();
                assertEquals(value, AmountUtil.toAmount(AmountUtil.fromAmount(value, currency), currency));
            }
        }
    }

    private void check(JsonNode vector, Call call) {
        String name = vector.get("name").asText();
        try {
            String actual = call.run(vector);
            if (vector.has("error")) fail(name + " expected " + vector.get("error").asText());
            if (vector.has("result")) assertEquals(name, vector.get("result").asText(), actual);
        } catch (IllegalArgumentException exception) {
            if (!vector.has("error")) throw exception;
            assertTrue(name + ": " + exception.getMessage(),
                    exception.getMessage().startsWith(vector.get("error").asText() + ":"));
        }
    }

    private void expectCategory(String category, Runnable call) {
        try {
            call.run();
            fail("expected " + category);
        } catch (IllegalArgumentException exception) {
            assertTrue(exception.getMessage().startsWith(category + ":"));
        }
    }

    private JsonNode loadVectors() {
        try (InputStream stream = getClass().getResourceAsStream("/amount-conversion-test-cases.json")) {
            if (stream == null) throw new AssertionError("shared vectors are missing");
            return new ObjectMapper().readTree(stream);
        } catch (Exception exception) {
            throw new AssertionError(exception);
        }
    }

    private interface Call {
        String run(JsonNode item);
    }
}
