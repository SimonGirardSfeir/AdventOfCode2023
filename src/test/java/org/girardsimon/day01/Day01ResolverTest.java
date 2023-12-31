package org.girardsimon.day01;

import org.girardsimon.LineExtractor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class Day01ResolverTest {
    private static List<String> lines;

    @BeforeAll
    static void setUp() throws IOException {
        lines = LineExtractor.getLines("src/test/resources/day01/inputData.txt");
    }

    @Test
    void resolve_part1_of_day01_problem() {
        // Arrange
        CalibrationDocumentParser parser = new CalibrationDocumentParserDigits();
        CalibrationDocument calibrationDocument = parser.parseCalibrationDocument(lines);

        // Act
        int actualSumOfCalibrationValues = calibrationDocument.sumCalibrationValues();

        // Assert
        assertThat(actualSumOfCalibrationValues).isEqualTo(54968);
    }
    @Test
    void resolve_part2_of_day01_problem() {
        // Arrange
        CalibrationDocumentParser parser = new CalibrationDocumentParserSpelledOutAndDigits();
        CalibrationDocument calibrationDocument = parser.parseCalibrationDocument(lines);

        // Act
        int actualSumOfCalibrationValues = calibrationDocument.sumCalibrationValues();

        // Assert
        assertThat(actualSumOfCalibrationValues).isEqualTo(54094);
    }
}
