package org.girardsimon.common;

public record Range<T extends Number & Comparable<T>>(T start, T end)  implements Comparable<Range<T>> {
    @Override
    public int compareTo(Range<T> o) {
        return start.compareTo(o.start());
    }
    public boolean isOverlappingWithRange(Range<T> o) {
        return !(0 > o.end().compareTo(start)
                || 0 < o.start().compareTo(end));
    }
    public boolean isValueInRange(T t) {
        return 0 <= t.compareTo(start)
                && 0 >= t.compareTo(end);
    }
}
