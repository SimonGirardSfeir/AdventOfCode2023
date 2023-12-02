package org.girardsimon.day02;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

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
        SetOfCubes expectedSetOfCubes1 = new SetOfCubes(4,0,3);
        SetOfCubes expectedSetOfCubes2 = new SetOfCubes(1,2,6);
        SetOfCubes expectedSetOfCubes3 = new SetOfCubes(0,2,0);
        Game expectedGame1 = new Game(1, List.of(expectedSetOfCubes1, expectedSetOfCubes2, expectedSetOfCubes3));
        SetOfCubes expectedSetOfCubes4 = new SetOfCubes(0,2,1);
        SetOfCubes expectedSetOfCubes5 = new SetOfCubes(1,3,4);
        SetOfCubes expectedSetOfCubes6 = new SetOfCubes(0,1,1);
        Game expectedGame2 = new Game(2, List.of(expectedSetOfCubes4, expectedSetOfCubes5, expectedSetOfCubes6));
        SetOfCubes expectedSetOfCubes7 = new SetOfCubes(20,8,6);
        SetOfCubes expectedSetOfCubes8 = new SetOfCubes(4,13,5);
        SetOfCubes expectedSetOfCubes9 = new SetOfCubes(1,5,0);
        Game expectedGame3 = new Game(3, List.of(expectedSetOfCubes7, expectedSetOfCubes8, expectedSetOfCubes9));
        SetOfCubes expectedSetOfCubes10 = new SetOfCubes(3,1,6);
        SetOfCubes expectedSetOfCubes11 = new SetOfCubes(6,3,0);
        SetOfCubes expectedSetOfCubes12 = new SetOfCubes(14,3,15);
        Game expectedGame4 = new Game(4, List.of(expectedSetOfCubes10, expectedSetOfCubes11, expectedSetOfCubes12));
        SetOfCubes expectedSetOfCubes13 = new SetOfCubes(6,3,1);
        SetOfCubes expectedSetOfCubes14 = new SetOfCubes(1,2,2);
        Game expectedGame5 = new Game(5, List.of(expectedSetOfCubes13, expectedSetOfCubes14));
        GameSystem expectedGameSystem = new GameSystem(List.of(expectedGame1, expectedGame2, expectedGame3, expectedGame4, expectedGame5));
        assertThat(actualGameSystem).isEqualTo(expectedGameSystem);
    }
}