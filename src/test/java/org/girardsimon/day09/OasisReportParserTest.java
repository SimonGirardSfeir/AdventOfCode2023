package org.girardsimon.day09;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class OasisReportParserTest {
    @Test
    void parseOasisReport_should_parse_report_as_expected() {
        // Arrange
        String line1 = "0 3 6 9 12 15";
        String line2 = "1 3 6 10 15 21";
        String line3 = "10 13 16 21 30 45";
        List<String> lines = List.of(line1, line2, line3);
        
        // Act
        OasisReport actualOasisReport = OasisReportParser.parseOasisReport(lines);
        
        // Assert
        ValueHistory valueHistory1 = new ValueHistory(List.of(0L, 3L, 6L, 9L, 12L, 15L));
        ValueHistory valueHistory2 = new ValueHistory(List.of(1L, 3L, 6L, 10L, 15L, 21L));
        ValueHistory valueHistory3 = new ValueHistory(List.of(10L, 13L, 16L, 21L, 30L, 45L));
        OasisReport expectedOasisReport = new OasisReport(List.of(valueHistory1, valueHistory2, valueHistory3));
        assertThat(actualOasisReport).isEqualTo(expectedOasisReport);
    }
}