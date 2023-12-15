package org.girardsimon.day12;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

import static org.girardsimon.common.Patterns.POSITIVE_NUMBER_PATTERN;

public final class SpringParser {
    private SpringParser() {
    }

    public static SpringSystem parseSpringSystem(List<String> lines) {
        List<RowOfSprings> rowOfSprings = lines.stream()
                .map(SpringParser::parseRowOfSprings)
                .toList();
        return new SpringSystem(rowOfSprings);
    }

    private static RowOfSprings parseRowOfSprings(String line) {
        String[] splitLine = line.split(" ");
        String content = splitLine[0];

        Matcher matcher = POSITIVE_NUMBER_PATTERN.matcher(splitLine[1]);

        List<Integer> groupsOfDamaged = new ArrayList<>();

        while(matcher.find()) {
            groupsOfDamaged.add(Integer.parseInt(matcher.group()));
        }

        return new RowOfSprings(content, groupsOfDamaged);
    }
}
