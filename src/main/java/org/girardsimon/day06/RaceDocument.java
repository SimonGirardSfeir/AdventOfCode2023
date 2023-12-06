package org.girardsimon.day06;

import java.util.List;

public record RaceDocument(List<Race> races) {
    public RaceDocument {
        races = List.copyOf(races);
    }

    public long multiplyNumberOfWaysToBeatRecords() {
        return races().stream()
                .mapToLong(Race::numberOfWaysToBeatRecord)
                .reduce(1L, (a, b) -> a*b);
    }
}
