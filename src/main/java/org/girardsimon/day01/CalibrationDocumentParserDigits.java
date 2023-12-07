package org.girardsimon.day01;

import java.util.List;

import static org.girardsimon.common.Constants.DECIMAL_SYSTEM_BASE;

public class CalibrationDocumentParserDigits implements CalibrationDocumentParser {
    @Override
    public CalibrationDocument parseCalibrationDocument(List<String> lines) {
        List<Integer> calibrationValues = lines.stream()
                .map(CalibrationDocumentParserDigits::parseLine)
                .toList();
        return new CalibrationDocument(calibrationValues);
    }
    private static int parseLine(String line) {
        int firstDigit = -1;
        int lastDigit = -1;

        for (int i = 0; i < line.length(); i++) {
            if (Character.isDigit(line.charAt(i))) {
                int digit = Character.getNumericValue(line.charAt(i));
                if (-1 == firstDigit) {
                    firstDigit = digit;
                }
                lastDigit = digit;
            }
        }
        return firstDigit * DECIMAL_SYSTEM_BASE + lastDigit;
    }
}
