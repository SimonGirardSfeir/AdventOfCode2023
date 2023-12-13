package org.girardsimon.common;

public record Tuple(int first, int second) implements Comparable<Tuple> {
    @Override
    public int compareTo(Tuple o) {
        return first == o.first() ?
                Integer.compare(second, o.second()) : Integer.compare(first, o.first());
    }
}
