package org.girardsimon.day07;

import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

public abstract class CamelCardHand  implements Comparable<CamelCardHand>{
    private final List<CamelCard> camelCards;
    private final int bid;
    private final TypeHand typeHand;
    /*
        typeHand is a computed property and the computed process
        vary between implementations of this class
     */
    @SuppressWarnings("java:S1699")
    protected CamelCardHand(List<CamelCard> camelCards, int bid) {
        this.camelCards = List.copyOf(camelCards);
        this.bid = bid;
        this.typeHand = getTypeHandFromCards(camelCards);
    }

    public int compareTypeHand(CamelCardHand o) {
        return typeHand.compareTo(o.typeHand());
    }
    public abstract TypeHand getTypeHandFromCards(List<CamelCard> camelCards);
    public abstract int compareCard(CamelCard card, CamelCard cardToCompare);
    public int compareCards(CamelCardHand o) {
        return IntStream.range(0, camelCards.size())
                .map(i -> compareCard(camelCards.get(i), o.camelCards().get(i)))
                .filter(result -> 0 != result)
                .findFirst()
                .orElse(0);
    }
    @Override
    public int compareTo(CamelCardHand o) {
        int compareTypeHand = compareTypeHand(o);
        return 0 != compareTypeHand ? compareTypeHand : compareCards(o);
    }
    public List<CamelCard> camelCards() {
        return camelCards;
    }
    public int bid() {
        return bid;
    }
    public TypeHand typeHand() {
        return typeHand;
    }
    @SuppressWarnings("MethodWithMultipleReturnPoints")
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (null == o || getClass() != o.getClass())
            return false;
        CamelCardHand that = (CamelCardHand) o;
        return bid == that.bid() && Objects.equals(camelCards, that.camelCards()) && typeHand == that.typeHand();
    }
    @Override
    public int hashCode() {
        return Objects.hash(camelCards, bid, typeHand);
    }
}
