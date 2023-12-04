package org.girardsimon.day04;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public record CardPile(List<Card> cards) {
    public CardPile {
        cards = List.copyOf(cards);
    }

    public int computePoints() {
        return cards.stream()
                .mapToInt(Card::points)
                .sum();
    }
    public int numberOfCards() {
        Map<Card, Integer> cardsAndNumberOfCopy = initCardMap();
        fillCardMapWithCopy(cardsAndNumberOfCopy);
        return cardsAndNumberOfCopy.values()
                .stream()
                .mapToInt(i -> i)
                .sum();
    }
    private void fillCardMapWithCopy(Map<Card, Integer> cardsAndNumberOfCopy) {
        IntStream.range(0, cards.size()).forEach(i -> {
            int numbersOfWinningNumbersInCard = cards.get(i).numberOfWinningNumber();
            int currentNumberOfCardCopy = cardsAndNumberOfCopy.get(cards.get(i));
            IntStream.range(i+1, i + numbersOfWinningNumbersInCard + 1).forEach(j -> {
                int currentValue = cardsAndNumberOfCopy.get(cards.get(j));
                cardsAndNumberOfCopy.put(cards.get(j), currentValue + currentNumberOfCardCopy);
            });
        });
    }
    private Map<Card, Integer> initCardMap() {
        return cards.stream()
                .collect(Collectors.toMap(Function.identity(), card -> 1));
    }
}
