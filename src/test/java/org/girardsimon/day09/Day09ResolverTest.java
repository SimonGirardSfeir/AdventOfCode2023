package org.girardsimon.day09;

import org.girardsimon.LineExtractor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class Day09ResolverTest {
    private static OasisReport oasisReport;

    @BeforeAll
    static void setUp() throws IOException {
        List<String> lines = LineExtractor.getLines("src/test/resources/day09/inputData.txt");
        oasisReport = OasisReportParser.parseOasisReport(lines);
    }
    @Test
    void resolve_part1_of_day09_problem() {
        // Act
        long actualSumOfNextValues = oasisReport.sumNextValues();

        // Assert
        assertThat(actualSumOfNextValues).isEqualTo(1819125966L);
    }
    @Test
    void resolve_part2_of_day09_problem() {
        // Act
        long actualSumOfNextValues = oasisReport.sumPreviousValues();

        // Assert
        assertThat(actualSumOfNextValues).isEqualTo(1140L);
    }
}
