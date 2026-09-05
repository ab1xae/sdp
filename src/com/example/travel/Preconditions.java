package com.example.travel;

final class Preconditions {
    private Preconditions() {
        throw new AssertionError("Utility class must not be instantiated");
    }

    static String requireText(String value, String parameterName) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(parameterName + " must not be empty");
        }
        return value.trim();
    }

    static int requireInRange(int value, int min, int max, String parameterName) {
        if (value < min || value > max) {
            throw new IllegalArgumentException(
                    parameterName + " must be between " + min + " and " + max
                            + ", but was " + value);
        }
        return value;
    }

    static <T> T requireNotNull(T value, String parameterName) {
        if (value == null) {
            throw new IllegalArgumentException(parameterName + " must not be null");
        }
        return value;
    }
}
