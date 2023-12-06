package org.girardsimon.day05;

import org.girardsimon.common.Range;

public record MapElement(long destinationRangeStart, Range<Long> sourceRange) implements Comparable<MapElement> {
    public Long processSeed(Long seed) {
        return seed + (destinationRangeStart - sourceRange.start());
    }
    public boolean isSeedProcessable(Long seed) {
        return sourceRange.isValueInRange(seed);
    }
    public boolean isOverlappingWithMapElement(Range<Long> seedRange) {
        return sourceRange.isOverlappingWithRange(seedRange);
    }
    public Range<Long> processRange(Range<Long> seedRange) {
        long min = Math.max(sourceRange.start(), seedRange.start());
        long max = Math.min(sourceRange.end(), seedRange.end());
        long distance = destinationRangeStart - sourceRange.start();
        return new Range<>(min+distance, max+distance);
    }
    @Override
    public int compareTo(MapElement o) {
        return sourceRange.compareTo(o.sourceRange);
    }
}
