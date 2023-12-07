package org.girardsimon.day01;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.girardsimon.common.Constants.DECIMAL_SYSTEM_BASE;

public class CalibrationDocumentParserSpelledOutAndDigits implements CalibrationDocumentParser {
    private static final Pattern CUSTOM_DIGIT_REGEXP =
            Pattern.compile("\\d|one|two|three|four|five|six|seven|eight|nine");
    @Override
    public CalibrationDocument parseCalibrationDocument(List<String> lines) {
        List<Integer> calibrationValues = lines.stream()
                .map(CalibrationDocumentParserSpelledOutAndDigits::parseLine)
                .toList();
        return new CalibrationDocument(calibrationValues);
    }
    private static int parseLine(String line) {
        Matcher matcher = CUSTOM_DIGIT_REGEXP.matcher(line);
        List<Integer> values = extractValues(matcher);
        return values.getFirst() * DECIMAL_SYSTEM_BASE + values.getLast();
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
    private static int customIntegerParser(String matcherValue) {
        return Character.isDigit(matcherValue.charAt(0)) ? Integer.parseInt(matcherValue) :
                SpelledOutIntegers.valueOf(matcherValue.toUpperCase(Locale.US)).value();
    }
}
