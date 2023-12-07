package org.girardsimon.day07;

import java.util.HashMap;
import java.util.Map;
public enum TypeHand {
    HIGH_CARD(0, 1, 1),
    ONE_PAIR(1, 2, 1),
    TWO_PAIR(2, 2, 2),
    THREE_OF_A_KIND(3, 3, 1),
    FULL_HOUSE(4, 3, 2),
    FOUR_OF_A_KIND(5, 4, 1),
    FIVE_OF_A_KIND(6, 5, 0);

    private static final Map<OccurrencesTuple, TypeHand> BY_OCCURRENCES = new HashMap<>();

    private final int value;
    private final int topOccurrence;
    private final int secondTopOccurrence;

    static {
        for (TypeHand typeHand : values()) {
            BY_OCCURRENCES.put(new OccurrencesTuple(typeHand.topOccurrence(), typeHand.secondTopOccurrence()),
                    typeHand);
        }
    }

    TypeHand(int value, int topOccurrence, int secondTopOccurrence) {
        this.value = value;
        this.topOccurrence = topOccurrence;
        this.secondTopOccurrence = secondTopOccurrence;
    }

    public static TypeHand byOccurrence(OccurrencesTuple occurrencesTuple) {
        return BY_OCCURRENCES.get(occurrencesTuple);
    }
    public int value() {
        return value;
    }
    public int topOccurrence() {
        return topOccurrence;
    }
    public int secondTopOccurrence() {
        return secondTopOccurrence;
    }
}
