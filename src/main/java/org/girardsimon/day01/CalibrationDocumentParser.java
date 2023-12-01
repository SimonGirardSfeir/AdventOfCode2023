package org.girardsimon.day01;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.girardsimon.common.Constants.DECIMAL_SYSTEM_BASE;
import static org.girardsimon.common.PatternReference.SINGLE_DIGIT_REGEX;

public final class CalibrationDocumentParser {
    private static final Pattern CUSTOM_DIGIT_REGEXP =
            Pattern.compile("\\d|one|two|three|four|five|six|seven|eight|nine");
    
    private CalibrationDocumentParser() {
    }

    private static Integer customIntegerParser(String matcherValue) {
        if(SINGLE_DIGIT_REGEX.matcher(matcherValue).find()) {
            return  Integer.parseInt(matcherValue);
        } else {
            return SpelledOutIntegers.valueOf(matcherValue.toUpperCase(Locale.US)).value();
        }
    }
    private static List<Integer> extractValues(Matcher matcher) {
        List<Integer> values = new ArrayList<>();
        // Use index to manage overlapping
        int i = 0;
        while(matcher.find(i)) {
            values.add(customIntegerParser(matcher.group()));
            i = matcher.start() + 1;
        }
        return values;
    }
    private static int computeCalibrationValue(List<Integer> values) {
        return values.getFirst() * DECIMAL_SYSTEM_BASE + values.getLast();
    }
    private static int parseLine(String line, Pattern pattern) {
        Matcher matcher = pattern.matcher(line);
        List<Integer> values = extractValues(matcher);
        return computeCalibrationValue(values);
    }
    public static CalibrationDocument parseCalibrationDocumentWithoutSpelledOutDigits(List<String> lines) {
        List<Integer> calibrationValues = lines.stream()
                .map((String line) -> parseLine(line, SINGLE_DIGIT_REGEX))
                .toList();
        return new CalibrationDocument(calibrationValues);
    }
    public static CalibrationDocument parseCalibrationDocument(List<String> lines) {
        List<Integer> calibrationValues = lines.stream()
                .map((String line) -> parseLine(line, CUSTOM_DIGIT_REGEXP))
                .toList();
        return new CalibrationDocument(calibrationValues);
    }
}
