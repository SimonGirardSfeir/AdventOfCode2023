package org.girardsimon.day07;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.girardsimon.day07.TestUtils.generateGame;
import static org.girardsimon.day07.TestUtils.generateGameJoker;

class CamelCardsGameTest {

    @Test
    void computeTotalWinnings_should_return_expected_results() {
        // Arrange
        CamelCardsGame game = generateGame();

        // Act
        long actualTotalWinnings = game.computeTotalWinning();

        // Assert
        assertThat(actualTotalWinnings).isEqualTo(6440L);
    }
    @Test
    void computeTotalWinningsWithJokerSpecificity_should_return_expected_results() {
        // Arrange
        CamelCardsGame game = generateGameJoker();

        // Act
        long actualTotalWinnings = game.computeTotalWinning();

        // Assert
        assertThat(actualTotalWinnings).isEqualTo(5905L);
    }
}