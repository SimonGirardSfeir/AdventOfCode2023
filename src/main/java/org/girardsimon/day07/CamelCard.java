package org.girardsimon.day07;

public enum CamelCard {
    A(14, 14),K(13, 13),Q(12, 12),
    J(11, 0),T(10, 10), NINE(9, 9),
    EIGHT(8, 8),SEVEN(7, 7),SIX(6, 6),
    FIVE(5, 5),FOUR(4, 4),THREE(3, 3),
    TWO(2, 2);
    private final int strength;
    private final int strengthJoker;

    CamelCard(int strength, int strengthJoker) {
        this.strength = strength;
        this.strengthJoker = strengthJoker;
    }

    public int strength() {
        return strength;
    }

    public int strengthJoker() {
        return strengthJoker;
    }
}
