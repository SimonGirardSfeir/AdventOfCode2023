package org.girardsimon.day04;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class CardParserTest {
    @Test
    void parse_should_return_expected_cards_pile_of_cards() {
        // Arrange
        String line1 = "Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53";
        String line2 = "Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19";
        List<String> lines = List.of(line1, line2);

        // Act
        CardPile actualCardPile = CardParser.parse(lines);

        // Assert
        Set<Integer> winner1 = Set.of(41, 48, 83, 86, 17);
        Set<Integer> hand1 = Set.of(83, 86, 6, 31, 17, 9, 48, 53);
        Card card1 = new Card(1, winner1, hand1);
        Set<Integer> winner2 = Set.of(13, 32, 20, 16, 61);
        Set<Integer> hand2 = Set.of(61, 30, 68, 82, 17, 32, 24, 19);
        Card card2 = new Card(2, winner2, hand2);
        CardPile expectedCardPile = new CardPile(List.of(card1, card2));
        assertThat(actualCardPile).isEqualTo(expectedCardPile);
    }

}