package org.girardsimon.day01;

import org.girardsimon.common.SpelledOutIntegers;

import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.girardsimon.common.Constants.DECIMAL_SYSTEM_BASE;

public class CalibrationDocumentParserSpelledOutAndDigits implements CalibrationDocumentParser {
    private static final Pattern CUSTOM_NUMBER_PATTERN =
            Pattern.compile("\\d|one|two|three|four|five|six|seven|eight|nine");
    @Override
    public CalibrationDocument parseCalibrationDocument(List<String> lines) {
        List<Integer> calibrationValues = lines.stream()
                .map(CalibrationDocumentParserSpelledOutAndDigits::parseLine)
                .toList();
        return new CalibrationDocument(calibrationValues);
    }
    private static int parseLine(String line) {
        Matcher matcher = CUSTOM_NUMBER_PATTERN.matcher(line);
        int[] values = extractValues(matcher);
        return values[0] * DECIMAL_SYSTEM_BASE + values[1];
    }
    private static int[] extractValues(Matcher matcher) {
        int[] values = new int[2];

        values[0] = matcher.find() ? customIntegerParser(matcher.group()) : 0;
        int i = 0;
        while(matcher.find(i)) {
            values[1] = customIntegerParser(matcher.group());
            i = matcher.start() + 1;
        }
        return values;
    }
    private static int customIntegerParser(String matcherValue) {
        return Character.isDigit(matcherValue.charAt(0)) ? Integer.parseInt(matcherValue) :
                SpelledOutIntegers.valueOf(matcherValue.toUpperCase(Locale.US)).value();
    }
}
