package org.girardsimon.common;

import java.util.HashMap;
import java.util.Map;

public enum SpelledOutIntegers {
    ONE(1), TWO(2), THREE(3), FOUR(4), FIVE(5),
    SIX(6), SEVEN(7), EIGHT(8), NINE(9);

    private static final Map<Integer, SpelledOutIntegers> BY_DIGIT = new HashMap<>();
    private final int value;

    static {
        for (SpelledOutIntegers si : values()) {
            BY_DIGIT.put(si.value(), si);
        }
    }

    SpelledOutIntegers(int value) {
        this.value = value;
    }
    public static SpelledOutIntegers getSpelledOutIntegerByValue(int value) {
        return BY_DIGIT.get(value);
    }

    public int value() {
        return value;
    }
}