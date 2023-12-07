package org.girardsimon.day07;

import java.util.List;
import java.util.stream.IntStream;

public interface CamelCardHand {
    default int compareTypeHand(CamelCardHand o) {
        TypeHand typeHand = getTypeHandFromCards(camelCards());
        TypeHand typHandToCompare = getTypeHandFromCards(o.camelCards());
        return typeHand.compareTo(typHandToCompare);
    }
    TypeHand getTypeHandFromCards(List<CamelCard> camelCards);
    int compareCard(CamelCard card, CamelCard cardToCompare);
    default int compareCards(CamelCardHand o) {
        return IntStream.range(0, camelCards().size())
                .map(i -> compareCard(camelCards().get(i), o.camelCards().get(i)))
                .filter(result -> 0 != result)
                .findFirst()
                .orElse(0);
    }
    /*
        Workaround because abstract class is incompatible with record
     */
    List<CamelCard> camelCards();
    int bid();
}
