package org.girardsimon.day03;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class EngineParser {
    private static final Pattern ENGINE_SYMBOL_PATTERN =
            Pattern.compile("(\\d+)|([^\\d.])");
    private EngineParser() {
    }

    public static Engine parseEngine(List<String> lines) {
        List<List<EngineElement>> rows = lines.stream()
                .map(EngineParser::parseLine)
                .toList();
        return new Engine(rows);
    }
    private static List<EngineElement> parseLine(String line) {
        List<EngineElement> engineElements = new ArrayList<>();
        Matcher matcher = ENGINE_SYMBOL_PATTERN.matcher(line);

        while(matcher.find()) {
            EngineElement engineElement = new EngineElement(matcher.group(), matcher.start());
            engineElements.add(engineElement);
        }
        return  engineElements;
    }
}
