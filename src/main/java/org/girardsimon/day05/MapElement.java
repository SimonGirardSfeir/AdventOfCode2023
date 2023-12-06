package org.girardsimon.day05;

import org.girardsimon.common.Range;

public record MapElement(long destinationRangeStart, long sourceRangeStart, long rangeLength) {
    public Long processSeed(Long seed) {
        return seed + (destinationRangeStart - sourceRangeStart);
    }
    public boolean isSeedInRange(Long seed) {
        return seed >= sourceRangeStart
                && seed < sourceRangeStart + rangeLength;
    }
    public boolean isOverlappingWithRange(Range<Long> seedRange) {
        return !(seedRange.end() < sourceRangeStart
                || seedRange.start() >= sourceRangeStart + rangeLength);
    }
    public Range<Long> processRange(Range<Long> seedRange) {
        long min = Math.max(sourceRangeStart, seedRange.start());
        long max = Math.min(sourceRangeStart + rangeLength -1, seedRange.end());
        long distance = destinationRangeStart - sourceRangeStart;
        return new Range<>(min+distance, max+distance);
    }
}
