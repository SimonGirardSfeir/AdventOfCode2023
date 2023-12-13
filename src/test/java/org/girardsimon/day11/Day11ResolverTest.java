package org.girardsimon.day11;

import org.girardsimon.LineExtractor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class Day11ResolverTest {
    private static List<String> lines;

    @BeforeAll
    static void setUp() throws IOException {
        lines = LineExtractor.getLines("src/test/resources/day11/inputData.txt");
    }
    @Test
    void resolve_part1_of_day11_problem() {
        // Arrange
        Sky sky = SkyParser.parseSky(lines, 1);

        // Act
        long actualSumOfShortestPath = sky.sumOfShortestPath();

        // Assert
        assertThat(actualSumOfShortestPath).isEqualTo(10173804L);
    }

    @Test
    void resolve_part2_of_day11_problem() {
        // Arrange
        Sky sky = SkyParser.parseSky(lines, 1000000-1);

        // Act
        long actualSumOfShortestPath = sky.sumOfShortestPath();

        // Assert
        assertThat(actualSumOfShortestPath).isEqualTo(634324905172L);
    }
}
