package org.girardsimon.day07;

public enum CamelCard{
    A(14),K(13),Q(12),
    J(11),T(10), NINE(9),
    EIGHT(8),SEVEN(7),SIX(6),
    FIVE(5),FOUR(4),THREE(3),
    TWO(2);
    private final int strength;

    CamelCard(int strength) {
        this.strength = strength;
    }

    public int strength() {
        return strength;
    }

}
