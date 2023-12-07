package org.girardsimon.common;

import java.util.ArrayList;
import java.util.List;

public record Range(long start, long end)  implements Comparable<Range> {
    @Override
    public int compareTo(Range o) {
        return Long.compare(start, o.start());
    }
    public boolean isOverlappingWithRange(Range o) {
        return !(o.end() < start
                || end < o.start());
    }
    public boolean isValueInRange(Long t) {
        return 0 <= t.compareTo(start)
                && 0 >= t.compareTo(end);
    }
    public Range sendRangeToDestination(Range seedRange, long destinationRangeStart) {
        long min = Math.max(start, seedRange.start());
        long max = Math.min(end, seedRange.end());
        long distance = destinationRangeStart - start;
        return new Range(min+distance, max+distance);
    }
    public List<Range> partsOfRangeThatDoNotIntersectWithAnyOfTheTargetRange(List<Range> targetRanges) {
        List<Range> partsOfRangeThatDoNotIntersectWithAnyOfTheTargetRange = new ArrayList<>();
        long startOfSeedRange = start;
        long startOfMapElementRange = targetRanges.getFirst().start();
        for (Range  currentTargetRange : targetRanges) {
            if (startOfSeedRange < startOfMapElementRange) {
                partsOfRangeThatDoNotIntersectWithAnyOfTheTargetRange.add(
                        new Range(startOfSeedRange, startOfMapElementRange - 1));
            }
            startOfSeedRange = currentTargetRange.end() + 1;
            startOfMapElementRange = targetRanges
                    .indexOf(currentTargetRange) < targetRanges.size() - 1 ?
                    targetRanges.get(targetRanges.indexOf(currentTargetRange) + 1).start()
                    : end;
        }
        return partsOfRangeThatDoNotIntersectWithAnyOfTheTargetRange;
    }
}
