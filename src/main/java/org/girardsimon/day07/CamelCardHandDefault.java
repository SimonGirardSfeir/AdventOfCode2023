package org.girardsimon.day07;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class CamelCardHandDefault extends CamelCardHand {
    public CamelCardHandDefault(List<CamelCard> camelCards, int bid) {
        super(camelCards, bid);
    }

    @Override
    public int compareCard(CamelCard card, CamelCard cardToCompare) {
        return Integer.compare(card.strength(), cardToCompare.strength());
    }
    @Override
    public TypeHand getTypeHandFromCards(List<CamelCard> camelCards) {
        List<Long> twoPrincipalOccurrences = camelCards.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .values()
                .stream()
                .sorted(Comparator.reverseOrder())
                .limit(2L)
                .toList();
        long topOccurrence = twoPrincipalOccurrences.getFirst();
        long secondTopOccurrence = 1 == twoPrincipalOccurrences.size() ? 0 : twoPrincipalOccurrences.getLast();
        return TypeHand.byOccurrence(new OccurrencesTuple(topOccurrence,
                secondTopOccurrence));
    }
}
