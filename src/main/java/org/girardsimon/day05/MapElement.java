package org.girardsimon.day05;

import org.girardsimon.common.Range;

public record MapElement(long destinationRangeStart, Range sourceRange) implements Comparable<MapElement> {
    public Long processSeed(Long seed) {
        return seed + (destinationRangeStart - sourceRange.start());
    }
    public boolean isSeedProcessable(Long seed) {
        return sourceRange.isValueInRange(seed);
    }
    public boolean isOverlappingWithMapElement(Range seedRange) {
        return sourceRange.isOverlappingWithRange(seedRange);
    }
    public Range applyOnRange(Range seedRange) {
        return sourceRange.sendRangeToDestination(seedRange, destinationRangeStart);
    }
    @Override
    public int compareTo(MapElement o) {
        return sourceRange.compareTo(o.sourceRange());
    }
}
