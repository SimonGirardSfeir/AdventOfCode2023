package org.girardsimon.day01;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CalibrationDocumentTest {
    @Test
    void sumCalibrationValues_should_return_sum_of_all_calibration_values() {
        // Arrange
        CalibrationDocument calibrationDocument = new CalibrationDocument(List.of(12, 38, 15, 77));

        // Act
        long sumCalibrationValues = calibrationDocument.sumCalibrationValues();

        // Assert
        assertThat(sumCalibrationValues).isEqualTo(142L);
    }
}