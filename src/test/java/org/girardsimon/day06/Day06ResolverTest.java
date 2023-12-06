package org.girardsimon.day06;

import org.girardsimon.LineExtractor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class Day06ResolverTest {
    private static List<String> lines;

    @BeforeAll
    static void setUp() throws IOException {
        lines = LineExtractor.getLines("src/test/resources/day06/inputData.txt");
    }

    @Test
    void resolve_part1_of_day06_problem() {
        RaceDocument raceDocument = RaceParser.parseRaceDocument(lines);

        // Act
        long actualScore = raceDocument.multiplyNumberOfWaysToBeatRecords();

        // Assert
        assertThat(actualScore).isEqualTo(293046L);
    }
    @Test
    void resolve_part2_of_day06_problem() {
        Race race = RaceParser.parseRace(lines);

        // Act
        long actualNumberOfWaysToBeatRecord = race.numberOfWaysToBeatRecord();

        // Assert
        assertThat(actualNumberOfWaysToBeatRecord).isEqualTo(35150181L);
    }
}
