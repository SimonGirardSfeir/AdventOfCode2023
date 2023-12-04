package org.girardsimon.day04;

import java.util.Set;

public record Card(int id, Set<Integer> winningNumbers, Set<Integer> handNumbers) implements Comparable<Card> {
    public Card {
        winningNumbers = Set.copyOf(winningNumbers);
        handNumbers = Set.copyOf(handNumbers);
    }

    public int points() {
        return (int) Math.pow(2, (numberOfWinningNumber() - 1));
    }
    public int numberOfWinningNumber() {
        return (int) handNumbers.stream()
                .filter(winningNumbers::contains)
                .count();
    }
    @Override
    public int compareTo(Card o) {
        return id - o.id();
    }
}
