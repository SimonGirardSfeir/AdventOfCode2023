package org.girardsimon.day04;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class CardPileTest {
    @Test
    void computePoints_should_return_expected_number_of_points() {
        // Arrange
        Set<Integer> winner1 = Set.of(41, 48, 83, 86, 17);
        Set<Integer> hand1 = Set.of(83, 86, 6, 31, 17, 9, 48, 53);
        Card card1 = new Card(1, winner1, hand1);
        Set<Integer> winner2 = Set.of(13, 32, 20, 16, 61);
        Set<Integer> hand2 = Set.of(61, 30, 68, 82, 17, 32, 24, 19);
        Card card2 = new Card(2, winner2, hand2);
        CardPile cardPile = new CardPile(List.of(card1, card2));

        // Act
        int actualPoints = cardPile.computePoints();

        // Assert
        assertThat(actualPoints).isEqualTo(10);
    }


    @Test
    void numberOfCards_should_return_expectednumber_of_cards() {
        // Arrange
        Set<Integer> winner1 = Set.of(41, 48, 83, 86, 17);
        Set<Integer> hand1 = Set.of(83, 86, 6, 31, 17, 9, 48, 53);
        Card card1 = new Card(1, winner1, hand1);
        Set<Integer> winner2 = Set.of(13, 32, 20, 16, 61);
        Set<Integer> hand2 = Set.of(61, 30, 68, 82, 17, 32, 24, 19);
        Card card2 = new Card(2, winner2, hand2);
        Set<Integer> winner3 = Set.of(1, 21, 53, 59, 44);
        Set<Integer> hand3 = Set.of(69, 82, 63, 72, 16, 21, 14, 1);
        Card card3 = new Card(3, winner3, hand3);
        Set<Integer> winner4 = Set.of(41, 92, 73, 84, 69);
        Set<Integer> hand4 = Set.of(59, 84, 76, 51, 58, 5, 54, 83);
        Card card4 = new Card(4, winner4, hand4);
        Set<Integer> winner5 = Set.of(87, 83, 26, 28, 32);
        Set<Integer> hand5 = Set.of(88, 30, 70, 12, 93, 22, 82, 36);
        Card card5 = new Card(5, winner5, hand5);
        Set<Integer> winner6 = Set.of(31, 18, 13, 56, 72);
        Set<Integer> hand6 = Set.of(74, 77, 10, 23, 35, 67, 36, 11);
        Card card6 = new Card(6, winner6, hand6);
        CardPile cardPile = new CardPile(List.of(card1, card2, card3, card4, card5, card6));

        // Act
        int actualNumberOfCards = cardPile.numberOfCards();

        // Assert
        assertThat(actualNumberOfCards).isEqualTo(30);
    }

}