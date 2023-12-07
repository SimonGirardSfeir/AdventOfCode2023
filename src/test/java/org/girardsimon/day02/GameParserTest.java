package org.girardsimon.day02;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.girardsimon.day02.TestUtils.buildGameSystem;

class GameParserTest {
    @Test
    void parseGameSystem_should_return_expected_game_system() {
        // Arrange
        String line1 = "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green";
        String line2 = "Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue";
        String line3 = "Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red";
        String line4 = "Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red";
        String line5 = "Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green";

        // Act
        GameSystem actualGameSystem = GameParser.parseGameSystem(List.of(line1, line2, line3, line4, line5));

        // Assert
        GameSystem expectedGameSystem = buildGameSystem();
        assertThat(actualGameSystem).isEqualTo(expectedGameSystem);
    }
}