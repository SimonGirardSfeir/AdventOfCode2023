package org.girardsimon.day07;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.girardsimon.day07.CamelCard.J;

public record CamelCardHandJoker(List<CamelCard> camelCards, int bid) implements CamelCardHand,
        Comparable<CamelCardHandJoker> {
    public CamelCardHandJoker {
        camelCards = List.copyOf(camelCards);
    }

    @Override
    public int compareTo(CamelCardHandJoker o) {
        int compareTypeHand = compareTypeHand(o);
        return 0 != compareTypeHand ? compareTypeHand : compareCards(o);
    }
    @Override
    public int compareCard(CamelCard card, CamelCard cardToCompare) {
        return isExactlyOneOfTheCardAJoker(card, cardToCompare) ?
                jokerIsTheWeakest(card) :
                Integer.compare(card.strength(), cardToCompare.strength());
    }
    private static int jokerIsTheWeakest(CamelCard card) {
        return J == card ? -1 : 1;
    }
    private static boolean isExactlyOneOfTheCardAJoker(CamelCard card, CamelCard comparisonCard) {
        return ((J == card) || (J == comparisonCard)) && card != comparisonCard;
    }
    @Override
    public TypeHand getTypeHandFromCards(List<CamelCard> camelCards) {
        List<Long> topTwoOccurrencesExcludingJoker = camelCards.stream()
                .filter(c -> J != c)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .values()
                .stream()
                .sorted(Comparator.reverseOrder())
                .limit(2L)
                .toList();
        long topOccurrence = topTwoOccurrencesExcludingJoker.isEmpty() ?
                0 : topTwoOccurrencesExcludingJoker.getFirst();
        long secondTopOccurrence = 1 >= topTwoOccurrencesExcludingJoker.size() ?
                0 : topTwoOccurrencesExcludingJoker.getLast();

        long numberOfJokers = camelCards.stream()
                .filter(c -> J == c)
                .count();
        topOccurrence += numberOfJokers;
        return TypeHand.byOccurrence(new OccurrencesTuple(topOccurrence,
                secondTopOccurrence));
    }
}
