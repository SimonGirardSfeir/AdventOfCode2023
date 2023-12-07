package org.girardsimon.day07;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.girardsimon.day07.TestUtils.generateGame;
import static org.girardsimon.day07.TestUtils.generateGameJoker;

class CamelCardsParserTest {
    @Test
    void parseGame_should_parse_camelCardsGame_as_expected() {
        // Arrange
        String line1 = "32T3K 765";
        String line2 = "T55J5 684";
        String line3 = "KK677 28";
        String line4 = "KTJJT 220";
        String line5 = "QQQJA 483";
        List<String> lines = List.of(line1, line2, line3, line4, line5);

        // Act
        CamelCardsGame actualGame = CamelCardsParser.parseGame(lines);

        // Assert
        CamelCardsGame expectedGame = generateGame();
        assertThat(actualGame).isEqualTo(expectedGame);
    }
    @Test
    void parseGameJoker_should_parse_camelCardsGame_as_expected() {
        // Arrange
        String line1 = "32T3K 765";
        String line2 = "T55J5 684";
        String line3 = "KK677 28";
        String line4 = "KTJJT 220";
        String line5 = "QQQJA 483";
        List<String> lines = List.of(line1, line2, line3, line4, line5);

        // Act
        CamelCardsGame actualGame = CamelCardsParser.parseGameJoker(lines);

        // Assert
        CamelCardsGame expectedGame = generateGameJoker();
        assertThat(actualGame).isEqualTo(expectedGame);
    }
}