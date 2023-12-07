package org.girardsimon.day07;

public record OccurrencesTuple(long topOccurrence, long secondTopOccurrence) implements Comparable<OccurrencesTuple> {
    @Override
    public int compareTo(OccurrencesTuple o) {
        return topOccurrence == o.topOccurrence() ? Long.compare(secondTopOccurrence, o.secondTopOccurrence()) :
                Long.compare(topOccurrence, o.topOccurrence());
    }
}
