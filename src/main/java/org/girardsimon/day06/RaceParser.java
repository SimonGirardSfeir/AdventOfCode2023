package org.girardsimon.day06;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

import static org.girardsimon.common.Patterns.NUMBER_REGEX;

public final class RaceParser {
    private RaceParser() {
    }

    public static RaceDocument parseRaceDocument(List<String> lines) {
        Matcher matcherTimes = NUMBER_REGEX.matcher(lines.getFirst());
        Matcher matcherDistances = NUMBER_REGEX.matcher(lines.getLast());
        List<Race> races = new ArrayList<>();
        while(matcherTimes.find() && matcherDistances.find()) {
            races.add(new Race(Long.parseLong(matcherTimes.group()),
                    Long.parseLong(matcherDistances.group())));
        }
        return new RaceDocument(races);
    }

    public static Race parseRace(List<String> lines) {
        Matcher matcherTimes = NUMBER_REGEX.matcher(lines.getFirst());
        Matcher matcherDistances = NUMBER_REGEX.matcher(lines.getLast());
        StringBuilder timeFeeder = new StringBuilder();
        StringBuilder  distanceFeeder = new StringBuilder();

        while(matcherTimes.find()) {
            timeFeeder.append(matcherTimes.group());
        }
        while(matcherDistances.find()) {
            distanceFeeder.append(matcherDistances.group());
        }
        return new Race(Long.parseLong(timeFeeder.toString()),
                Long.parseLong(distanceFeeder.toString()));
    }
}
