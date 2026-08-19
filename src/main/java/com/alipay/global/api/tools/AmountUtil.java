package com.alipay.global.api.tools;

public final class AmountUtil {
    private static final int MAX_VALUE_LENGTH = 16;

    public static String toAmount(String amount, String currency) {
        int minorUnit = minorUnit(currency);
        if (amount == null) {
            throw invalid("INVALID_ARGUMENT_TYPE", "amount must be a string");
        }
        if (!amount.matches("[0-9]+(?:\\.[0-9]+)?")) {
            throw invalid("INVALID_AMOUNT_FORMAT", "amount must be an unsigned ASCII decimal string");
        }

        int point = amount.indexOf('.');
        String whole = point < 0 ? amount : amount.substring(0, point);
        String fraction = point < 0 ? "" : amount.substring(point + 1);
        if (fraction.length() > minorUnit) {
            String excess = fraction.substring(minorUnit);
            if (!allZeros(excess)) {
                throw invalid("EXCESS_PRECISION", "amount exceeds the currency minor unit");
            }
            fraction = fraction.substring(0, minorUnit);
        }
        StringBuilder padded = new StringBuilder(fraction);
        while (padded.length() < minorUnit) {
            padded.append('0');
        }
        String value = canonicalDigits(whole + padded.toString());
        validateCanonical(value, currency);
        return value;
    }

    public static String fromAmount(String value, String currency) {
        int minorUnit = minorUnit(currency);
        validateValueFormat(value, true);
        String canonical = canonicalDigits(value);
        if (minorUnit == 0) {
            return canonical;
        }
        StringBuilder padded = new StringBuilder(canonical);
        while (padded.length() <= minorUnit) {
            padded.insert(0, '0');
        }
        int point = padded.length() - minorUnit;
        return padded.substring(0, point) + "." + padded.substring(point);
    }

    public static void validate(String value, String currency) {
        minorUnit(currency);
        validateValueFormat(value, false);
        validateCanonical(value, currency);
    }

    private static int minorUnit(String currency) {
        if (currency == null) {
            throw invalid("INVALID_ARGUMENT_TYPE", "currency must be a string");
        }
        if (!currency.matches("[A-Z]{3}")) {
            throw invalid("INVALID_CURRENCY", "currency must be three uppercase ASCII letters");
        }
        AmountRuleLoader.RuleSet rules = AmountRuleLoader.rules();
        if (!rules.currencies.containsKey(currency)) {
            throw invalid("UNKNOWN_CURRENCY", "currency is not present in the ISO snapshot");
        }
        Integer minorUnit = rules.currencies.get(currency);
        if (minorUnit == null) {
            throw invalid("UNSUPPORTED_MINOR_UNIT", "currency has no numeric minor unit");
        }
        return minorUnit;
    }

    private static void validateValueFormat(String value, boolean allowZero) {
        if (value == null) {
            throw invalid("INVALID_ARGUMENT_TYPE", "value must be a string");
        }
        if (!value.matches("[0-9]+")) {
            throw invalid("INVALID_VALUE_FORMAT", "value must contain ASCII digits only");
        }
        if (value.length() > MAX_VALUE_LENGTH) {
            throw invalid("VALUE_TOO_LONG", "value exceeds 16 digits");
        }
        if (!allowZero && allZeros(value)) {
            throw invalid("AMOUNT_NOT_POSITIVE", "value must be greater than zero");
        }
    }

    private static void validateCanonical(String value, String currency) {
        if (allZeros(value)) {
            throw invalid("AMOUNT_NOT_POSITIVE", "value must be greater than zero");
        }
        if (value.length() > MAX_VALUE_LENGTH) {
            throw invalid("VALUE_TOO_LONG", "value exceeds 16 digits");
        }
        String multiple = AmountRuleLoader.rules().multiples.get(currency);
        if (multiple != null && !value.endsWith(multiple.substring(1))) {
            throw invalid("RULE_VIOLATION", "value does not satisfy the Antom currency constraint");
        }
    }

    private static String canonicalDigits(String value) {
        int index = 0;
        while (index < value.length() - 1 && value.charAt(index) == '0') {
            index++;
        }
        return value.substring(index);
    }

    private static boolean allZeros(String value) {
        for (int i = 0; i < value.length(); i++) {
            if (value.charAt(i) != '0') {
                return false;
            }
        }
        return true;
    }

    private static IllegalArgumentException invalid(String category, String detail) {
        return new IllegalArgumentException(category + ": " + detail);
    }

    private AmountUtil() {
    }
}
