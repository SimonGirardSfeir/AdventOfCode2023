package org.girardsimon.day01;

import org.girardsimon.LineExtractor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class Day01ResolverTest {
    private List<String> lines;

    @BeforeEach
    void setUp() throws IOException {
        lines = LineExtractor.getLines("src/test/resources/day01/inputData.txt");
    }

    @Test
    void resolve_part1_of_day01_problem() {
        // Arrange
        CalibrationDocument calibrationDocument = CalibrationDocumentParser.parseCalibrationDocumentWithoutSpelledOutDigits(lines);

        // Act
        long actualSumOfCalibrationValues = calibrationDocument.sumCalibrationValues();

        // Assert
        assertThat(actualSumOfCalibrationValues).isEqualTo(54968L);
    }
    @Test
    void resolve_part2_of_day01_problem() {
        // Arrange
        CalibrationDocument calibrationDocument = CalibrationDocumentParser.parseCalibrationDocument(lines);

        // Act
        long actualSumOfCalibrationValues = calibrationDocument.sumCalibrationValues();

        // Assert
        assertThat(actualSumOfCalibrationValues).isEqualTo(54094L);
    }
}
