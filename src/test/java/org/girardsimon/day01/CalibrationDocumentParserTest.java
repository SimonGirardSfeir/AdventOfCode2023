package org.girardsimon.day01;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CalibrationDocumentParserTest {
    @Test
    void CalibrationDocumentParserDigits_parseCalibrationDocument_should_return_expected_calibration_document() {
        // Arrange
        List<String> lines = List.of("1abc2", "pqr3stu8vwx", "a1b2c3d4e5f", "treb7uchet");
        CalibrationDocumentParser parser = new CalibrationDocumentParserDigits();

        // Act
        CalibrationDocument actualCalibrationDocument = parser.parseCalibrationDocument(lines);

        // Assert
        List<Integer> expectedCalibrationValues = List.of(12, 38, 15, 77);
        CalibrationDocument expectedCalibrationDocument = new CalibrationDocument(expectedCalibrationValues);
        assertThat(actualCalibrationDocument).isEqualTo(expectedCalibrationDocument);
    }
    @Test
    void CalibrationDocumentParserSpelledOutAndDigits_parseCalibrationDocument_should_return_expected_calibration_document() {
        // Arrange
        List<String> lines = List.of("two1nine", "eightwothree", "abcone2threexyz", "xtwone3four", "4nineeightseven2", "zoneight234", "7pqrstsixteen",
        "8threesevenfourgbgteight5twonenjr");
        CalibrationDocumentParser parser = new CalibrationDocumentParserSpelledOutAndDigits();

        // Act
        CalibrationDocument actualCalibrationDocument = parser.parseCalibrationDocument(lines);

        // Assert
        List<Integer> expectedCalibrationValues = List.of(29, 83, 13, 24, 42, 14, 76, 81);
        CalibrationDocument expectedCalibrationDocument = new CalibrationDocument(expectedCalibrationValues);
        assertThat(actualCalibrationDocument).isEqualTo(expectedCalibrationDocument);
    }

}