package org.girardsimon.day05;

import org.girardsimon.LineExtractor;
import org.girardsimon.common.Range;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class Day05ResolverTest {
    private static List<String> lines;

    @BeforeAll
    static void setUp() throws IOException {
        lines = LineExtractor.getLines("src/test/resources/day05/inputData.txt");
    }

    @Test
    void resolve_part1_of_day05_problem() {
        List<Long> soilInputs = GardenParser.parseSeedsInputs(lines.getFirst());
        Garden garden = GardenParser.parseGarden(lines.subList(2, lines.size()));

        // Act
        long actualLowestLocation = garden.lowestLocation(soilInputs);

        // Assert
        assertThat(actualLowestLocation).isEqualTo(510109797L);
    }
    @Test
    void resolve_part2_of_day05_problem() {
        List<Range> soilInputs = GardenParser.parseSeedsInputsByPair(lines.getFirst());
        Garden garden = GardenParser.parseGarden(lines.subList(2, lines.size()));

        // Act
        long actualLowestLocation = garden.lowestLocationRangeInput(soilInputs);

        // Assert
        assertThat(actualLowestLocation).isEqualTo(9622622L);
    }
}
