package org.girardsimon.day05;

public record MapElement(long destinationRangeStart, long sourceRangeStart, long rangeLength) {
    public Long processSeed(Long seed) {
        return seed + (destinationRangeStart - sourceRangeStart);
    }
    public boolean isSeedInRange(Long seed) {
        return seed >= sourceRangeStart
                && seed < sourceRangeStart + rangeLength;
    }
    public boolean isOverlappingWithRange(SeedRange seedRange) {
        return !(seedRange.endOfRange() < sourceRangeStart
                || seedRange.startOfRange() >= sourceRangeStart + rangeLength);
    }
    public SeedRange processRange(SeedRange seedRange) {
        long min = Math.max(sourceRangeStart, seedRange.startOfRange());
        long max = Math.min(sourceRangeStart + rangeLength -1, seedRange.endOfRange());
        long distance = destinationRangeStart - sourceRangeStart;
        return new SeedRange(min+distance, max+distance);
    }
}
