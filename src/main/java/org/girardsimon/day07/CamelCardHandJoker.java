package org.girardsimon.day07;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.girardsimon.day07.CamelCard.J;

public record CamelCardHandJoker(List<CamelCard> camelCards, int bid) implements CamelCardHand,
        Comparable<CamelCardHandJoker> {
    @Override
    public int compareTo(CamelCardHandJoker o) {
        int compareTypeHand = compareTypeHand(o);
        return 0 != compareTypeHand ? compareTypeHand : compareCards(o);
    }
    @Override
    public int compareCard(CamelCard card, CamelCard cardToCompare) {
        int output;
        if(card != cardToCompare && J == card) {
            output = -1;
        } else if(card != cardToCompare && J == cardToCompare) {
            output = 1;
        } else {
            output = Integer.compare(card.strength(), cardToCompare.strength());
        }
        return output;
    }
    @Override
    public TypeHand getTypeHandFromCards(List<CamelCard> camelCards) {
        List<Long> twoPrincipalOccurrences = camelCards.stream()
                .filter(c -> J != c)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .values()
                .stream()
                .sorted(Comparator.reverseOrder())
                .limit(2L)
                .toList();
        long topOccurrence = twoPrincipalOccurrences.isEmpty() ? 0 : twoPrincipalOccurrences.getFirst();
        long secondTopOccurrence = 1 >= twoPrincipalOccurrences.size() ? 0 : twoPrincipalOccurrences.getLast();

        long numberOfJokers = camelCards.stream()
                .filter(c -> J == c)
                .count();
        topOccurrence += numberOfJokers;
        return TypeHand.byOccurrence(new OccurrencesTuple(topOccurrence,
                secondTopOccurrence));
    }
}
