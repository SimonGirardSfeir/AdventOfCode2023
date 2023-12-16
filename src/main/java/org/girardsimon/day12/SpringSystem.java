package org.girardsimon.day12;

import java.util.List;

public record SpringSystem(List<RowOfSprings> rowOfSprings) {
    public SpringSystem {
        rowOfSprings = List.copyOf(rowOfSprings);
    }
    public long sumOfPossibleArrangements() {
        return rowOfSprings.stream()
                .mapToLong(RowOfSprings::numberOfPossibleArrangements)
                .sum();
    }
    public long sumOfPossibleArrangementsUnfolded() {
        return rowOfSprings.stream()
                .mapToLong(RowOfSprings::numberOfPossibleArrangementsUnfolded)
                .sum();
    }
}
