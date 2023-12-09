package org.girardsimon.day09;

import java.util.Arrays;
import java.util.List;

public final class OasisReportParser {
    private OasisReportParser() {
    }

    public static OasisReport parseOasisReport(List<String> lines) {
        return new OasisReport(lines.stream()
                .map(OasisReportParser::parseValueHistory)
                .toList());
    }
    private static ValueHistory parseValueHistory(String line) {
        List<Long> values = Arrays.stream(line.split(" "))
                .map(Long::parseLong)
                .toList();
        return new ValueHistory(values);
    }
}