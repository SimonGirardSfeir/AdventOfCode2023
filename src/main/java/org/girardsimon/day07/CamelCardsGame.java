package org.girardsimon.day07;

import java.util.List;
import java.util.stream.IntStream;

public record CamelCardsGame(List<CamelCardHand> camelCardHandDefaults) {
    public long computeTotalWinning() {
        List<CamelCardHand> sortedList = camelCardHandDefaults.stream()
                .sorted()
                .toList();
        return calculateFinalScore(sortedList);
    }
    private static long calculateFinalScore(List<CamelCardHand> sortedList) {
        return IntStream.range(0, sortedList.size())
                .mapToLong(i -> sortedList.get(i).computeScore(sortedList.get(i), i+1))
                .sum();
    }
}
