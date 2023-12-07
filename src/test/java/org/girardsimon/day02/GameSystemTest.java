package org.girardsimon.day02;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.girardsimon.day02.TestUtils.buildGameSystem;

class GameSystemTest {
    @Test
    void sumGamesIdOfPossibleGame_should_return_the_sum_of_game_id_that_can_happen_given_overall_number_of_red_green_and_blue_cubes() {
        // Arrange
        GameSystem gameSystem = buildGameSystem();

        // Act
        int actualSumOfGameId = gameSystem.sumGamesIdOfPossibleGame(12, 13, 14);

        // Assert
        assertThat(actualSumOfGameId).isEqualTo(8);
    }
    /*
        Each game -> minimum number of red, green and blue cubes
        The power associated for each game is = minimum number of red cubes * minimum number of green cubes * minimum number of blue cubes
        The GameSystem power is the sum of each of these power
     */
    @Test
    void computeGameSystemPower_should_return_expected_value() {
        // Arrange
        GameSystem gameSystem = buildGameSystem();

        // Act
        int actualGameSystemPower = gameSystem.computeGameSystemPower();

        // Assert
        assertThat(actualGameSystemPower).isEqualTo(2286);
    }
}