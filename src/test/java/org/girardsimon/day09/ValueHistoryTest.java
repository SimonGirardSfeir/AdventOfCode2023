package org.girardsimon.day09;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class ValueHistoryTest {
    private static Stream<Arguments> valuesAndNextExpectedValue() {
        return Stream.of(
                Arguments.of(List.of(0L, 3L, 6L, 9L, 12L, 15L), 18L),
                Arguments.of(List.of(1L, 3L, 6L, 10L, 15L, 21L), 28L),
                Arguments.of(List.of(10L, 13L, 16L, 21L, 30L, 45L), 68L)
        );
    }
    @ParameterizedTest
    @MethodSource("valuesAndNextExpectedValue")
    void nextValueOfHistory_should_return_expected_value(List<Long> input, Long expectedValue) {

        // Arrange
        ValueHistory valueHistory = new ValueHistory(input);

        // Act
        Long actualValue = valueHistory.nextValueOfHistory();

        // Assert
        assertThat(actualValue).isEqualTo(expectedValue);
    }

    @Test
    void previousValueOfHistory_should_return_expected_value() {

        // Arrange
        ValueHistory valueHistory = new ValueHistory(List.of(10L, 13L, 16L, 21L, 30L, 45L));

        // Act
        Long actualValue = valueHistory.previousValueOfHistory();

        // Assert
        assertThat(actualValue).isEqualTo(5L);
    }
}