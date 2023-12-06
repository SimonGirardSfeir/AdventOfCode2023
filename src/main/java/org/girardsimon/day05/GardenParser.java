package org.girardsimon.day05;

import org.girardsimon.common.Range;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.Collections.emptyList;
import static org.girardsimon.common.Patterns.NUMBER_REGEX;

public final class GardenParser {
    private static final int TO_SOIL_INDEX = 0;
    private static final int TO_FERTILIZER_INDEX = 1;
    private static final int TO_WATER_INDEX = 2;
    private static final int TO_LIGHT_INDEX = 3;
    private static final int TO_TEMPERATURE_INDEX = 4;
    private static final int TO_HUMIDITY_INDEX = 5;
    private static final int TO_LOCATION_INDEX = 6;
    private static final Pattern NUMBER_PAIR_REGEX = Pattern.compile("\\d+ \\d+");
    private GardenParser() {
    }

    public static List<Long> parseSeedsInputs(String line) {
        return NUMBER_REGEX.matcher(line)
                .results()
                .map(result -> Long.parseLong(result.group()))
                .toList();
    }
    public static List<Range<Long>> parseSeedsInputsByPair(String line) {
        return NUMBER_PAIR_REGEX.matcher(line)
                .results()
                .map(result -> parseFromPairToList(result.group()))
                .toList();
    }
    private static Range<Long> parseFromPairToList(String input) {
        Matcher matcher = NUMBER_REGEX.matcher(input);
        long startingRange = matcher.find() ? Long.parseLong(matcher.group()) : 0;
        long rangeLength = matcher.find() ? Long.parseLong(matcher.group()) : 0;
        return new Range<>(startingRange, startingRange + rangeLength - 1);
    }
    public static Garden parseGarden(List<String> lines) {
        List<List<MapElement>> packetsOfElements = new ArrayList<>();

        packetsOfElements =  lines.stream()
                .filter(line -> !line.isBlank())
                .reduce(packetsOfElements, (subtotal, element) -> {
                    if (!element.isBlank() && !Character.isDigit(element.trim().charAt(0))) {
                        subtotal.add(new ArrayList<>());
                    } else {
                        subtotal.get(subtotal.size() - 1).add(parseMapElement(element));
                    }
                    return subtotal;

                }, (list1, list2) -> emptyList());

        return new Garden(packetsOfElements.get(TO_SOIL_INDEX), packetsOfElements.get(TO_FERTILIZER_INDEX),
                packetsOfElements.get(TO_WATER_INDEX), packetsOfElements.get(TO_LIGHT_INDEX),
                packetsOfElements.get(TO_TEMPERATURE_INDEX), packetsOfElements.get(TO_HUMIDITY_INDEX),
                packetsOfElements.get(TO_LOCATION_INDEX));
    }
    private static MapElement parseMapElement(String line) {
        Matcher matcher = NUMBER_REGEX.matcher(line);
        long destinationRangeStart = matcher.find() ? Long.parseLong(matcher.group()) : 0;
        long sourceRangeStart = matcher.find() ? Long.parseLong(matcher.group()) : 0;
        long rangeLength = matcher.find() ? Long.parseLong(matcher.group()) : 0;
        return new MapElement(destinationRangeStart, sourceRangeStart, rangeLength);
    }
}
