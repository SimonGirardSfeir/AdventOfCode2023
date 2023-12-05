package org.girardsimon.day03;

import org.girardsimon.LineExtractor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class Day03ResolverTest {
    private static Engine engine;

    @BeforeAll
    static void setUp() throws IOException {
        List<String> lines = LineExtractor.getLines("src/test/resources/day03/inputData.txt");
        engine = EngineParser.parseEngine(lines);
    }

    @Test
    void resolve_part1_of_day03_problem() {
         // Act
        int actualSumEngineSchematic = engine.sumEngineSchematic();

        // Assert
        assertThat(actualSumEngineSchematic).isEqualTo(539713);
    }
    @Test
    void resolve_part2_of_day03_problem() {
        // Act
        int actualSumEngineSchematic = engine.sumGearRatio();

        // Assert
        assertThat(actualSumEngineSchematic).isEqualTo(84159075);
    }
}
