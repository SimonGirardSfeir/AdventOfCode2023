package org.girardsimon.day12;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class RowOfSpringsTest {

    private static Stream<Arguments> rowsAndExpectedPossibleArrangements() {
        return Stream.of(
                Arguments.of("???.###", List.of(1,1,3), 1L),
                Arguments.of(".??..??...?##.", List.of(1,1,3), 4L),
                Arguments.of("?#?#?#?#?#?#?#?", List.of(1,3,1,6), 1L),
                Arguments.of("????.#...#...", List.of(4,1,1), 1L),
                Arguments.of("????.######..#####.", List.of(1,6,5), 4L),
                Arguments.of("?###????????", List.of(3,2,1), 10L),
                Arguments.of(".?????.????.???", List.of(3,1), 28L),
                Arguments.of("??????????#???", List.of(4,4,1), 11L),
                Arguments.of("#....?????", List.of(1), 1L),
                Arguments.of("#??#", List.of(2), 0L)
        );
    }
    @ParameterizedTest
    @MethodSource("rowsAndExpectedPossibleArrangements")
    void numberOfPossibleArrangements_should_return_expected_number_of_possible_arrangements_for_group_of_broken_springs(
            String input, List<Integer> list, long expectedNumber) {
        // Arrange
        RowOfSprings rowOfSprings = new RowOfSprings(input, list);
        // Act
        long actualNumber = rowOfSprings.numberOfPossibleArrangements();
        // Assert
        assertThat(actualNumber).isEqualTo(expectedNumber);
    }
}