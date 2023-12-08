package org.girardsimon.common;

import java.util.List;

public final class Mathematics {
    private Mathematics() {
    }

    private static long greatestCommonDivisor(long number1, long number2) {
        return 0 == number2 ? number1 : greatestCommonDivisor(number2, number1 % number2);
    }
    private static long leastCommonMultiple(long number1, long number2) {
        return (number1 * number2) / greatestCommonDivisor(number1, number2);
    }
    public static long leastCommonMultiple(List<Long> numbers) {
        return numbers.stream()
                .reduce(1L, Mathematics::leastCommonMultiple);
    }
}
