package org.girardsimon.day12;

import org.girardsimon.LineExtractor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class Day12ResolverTest {
    private static List<String> lines;

    @BeforeAll
    static void setUp() throws IOException {
        lines = LineExtractor.getLines("src/test/resources/day12/inputData.txt");
    }
    @Test
    void resolve_part1_of_day12_problem() {
        // Arrange
        SpringSystem system = SpringParser.parseSpringSystem(lines);

        // Act
        long actualNumber = system.sumOfPossibleArrangements();

        // Assert
        assertThat(actualNumber).isEqualTo(7221L);
    }
}
