package org.girardsimon.day09;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class OasisReportTest {
    @Test
    void sumNextValues_should_return_expected_value() {
        // Arrange
        ValueHistory valueHistory1 = new ValueHistory(List.of(0L, 3L, 6L, 9L, 12L, 15L));
        ValueHistory valueHistory2 = new ValueHistory(List.of(1L, 3L, 6L, 10L, 15L, 21L));
        ValueHistory valueHistory3 = new ValueHistory(List.of(10L, 13L, 16L, 21L, 30L, 45L));
        OasisReport oasisReport = new OasisReport(List.of(valueHistory1, valueHistory2, valueHistory3));

        // Act
        long actualSumOfNextValues = oasisReport.sumNextValues();

        // Assert
        assertThat(actualSumOfNextValues).isEqualTo(114);
    }

}