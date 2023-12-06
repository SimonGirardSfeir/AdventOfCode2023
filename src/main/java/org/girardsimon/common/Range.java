package org.girardsimon.common;

public record Range<T extends Number & Comparable<T>>(T start, T end)  implements Comparable<Range<T>> {
    @Override
    public int compareTo(Range<T> o) {
        return start.compareTo(o.start());
    }
    public boolean isOverlappingWithRange(Range<T> o) {
        return !(o.end().compareTo(start) < 0
                || o.start().compareTo(end) > 0);
    }
    public boolean isValueInRange(T t) {
        return t.compareTo(start) >= 0
                && 0 >= t.compareTo(end);
    }
}
