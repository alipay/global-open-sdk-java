package com.alipay.global.api.tools;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

final class AmountRuleLoader {
    private static final String RESOURCE = "/com/alipay/global/api/tools/amount-currency-rules.json";

    static RuleSet rules() {
        return Holder.RULES;
    }

    private static RuleSet load() {
        try (InputStream stream = AmountRuleLoader.class.getResourceAsStream(RESOURCE)) {
            if (stream == null) {
                throw new IllegalStateException("RULE_DATA_ERROR: amount currency rules resource is missing");
            }
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(JsonParser.Feature.STRICT_DUPLICATE_DETECTION);
            JsonNode root = mapper.readTree(stream);
            if (root == null || !root.isObject() || root.path("schemaVersion").asInt(-1) != 1) {
                throw new IllegalStateException("RULE_DATA_ERROR: unsupported amount currency rules schema");
            }
            JsonNode currenciesNode = root.get("currencies");
            JsonNode constraintsNode = root.get("antomConstraints");
            if (currenciesNode == null || !currenciesNode.isObject()
                    || constraintsNode == null || !constraintsNode.isObject()) {
                throw new IllegalStateException("RULE_DATA_ERROR: invalid amount currency rules structure");
            }

            Map<String, Integer> currencies = new HashMap<String, Integer>();
            java.util.Iterator<Map.Entry<String, JsonNode>> currencyFields = currenciesNode.fields();
            while (currencyFields.hasNext()) {
                Map.Entry<String, JsonNode> field = currencyFields.next();
                JsonNode minorUnit = field.getValue().get("minorUnit");
                if (minorUnit == null || minorUnit.isNull()) {
                    currencies.put(field.getKey(), null);
                } else if (minorUnit.isIntegralNumber() && minorUnit.asInt() >= 0 && minorUnit.asInt() <= 4) {
                    currencies.put(field.getKey(), minorUnit.asInt());
                } else {
                    throw new IllegalStateException("RULE_DATA_ERROR: invalid minor unit");
                }
            }

            Map<String, String> multiples = new HashMap<String, String>();
            java.util.Iterator<Map.Entry<String, JsonNode>> constraintFields = constraintsNode.fields();
            while (constraintFields.hasNext()) {
                Map.Entry<String, JsonNode> field = constraintFields.next();
                JsonNode multiple = field.getValue().get("minorValueMultiple");
                if (multiple == null || !multiple.isTextual() || !multiple.asText().matches("10*")) {
                    throw new IllegalStateException("RULE_DATA_ERROR: invalid amount constraint");
                }
                multiples.put(field.getKey(), multiple.asText());
            }
            return new RuleSet(currencies, multiples);
        } catch (IllegalStateException exception) {
            throw exception;
        } catch (Exception exception) {
            throw new IllegalStateException("RULE_DATA_ERROR: unable to load amount currency rules", exception);
        }
    }

    static final class RuleSet {
        final Map<String, Integer> currencies;
        final Map<String, String> multiples;

        RuleSet(Map<String, Integer> currencies, Map<String, String> multiples) {
            this.currencies = Collections.unmodifiableMap(new HashMap<String, Integer>(currencies));
            this.multiples = Collections.unmodifiableMap(new HashMap<String, String>(multiples));
        }
    }

    private static final class Holder {
        private static final RuleSet RULES = load();
    }

    private AmountRuleLoader() {
    }
}
