package org.girardsimon.day10;


import org.girardsimon.LineExtractor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class Day10ResolverTest {
    private static Area area;

    @BeforeAll
    static void setUp() throws IOException {
        List<String> lines = LineExtractor.getLines("src/test/resources/day10/inputData.txt");
        area = AreaParser.parseArea(lines);
    }
    @Test
    void resolve_part1_of_day10_problem() {
        // Act
        int actualSteps = area.countStepsToReachFarthestPositionInLoop();

        // Assert
        assertThat(actualSteps).isEqualTo(7097);
    }
    @Test
    void resolve_part2_of_day10_problem() {
        // Act
        int actualSteps = area.tilesEnclosedByTheLoop();

        // Assert
        assertThat(actualSteps).isEqualTo(7097);
    }
}
