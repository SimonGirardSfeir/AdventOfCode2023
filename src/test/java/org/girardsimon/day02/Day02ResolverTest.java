package org.girardsimon.day02;

import org.girardsimon.LineExtractor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class Day02ResolverTest {
    private List<String> lines;

    @BeforeEach
    void setUp() throws IOException {
        lines = LineExtractor.getLines("src/test/resources/day02/inputData.txt");
    }

    @Test
    void resolve_part1_of_day02_problem() {
        // Arrange
        GameSystem gameSystem = GameParser.parseGameSystem(lines);

        // Act
        int actualSumOfGamesIdOfPossibleGame = gameSystem.sumGamesIdOfPossibleGame(12, 13, 14);

        // Assert
        assertThat(actualSumOfGamesIdOfPossibleGame).isEqualTo(2683);
    }
    @Test
    void resolve_part2_of_day02_problem() {
        // Arrange
        GameSystem gameSystem = GameParser.parseGameSystem(lines);

        // Act
        int actualGameSystemPower = gameSystem.computeGameSystemPower();

        // Assert
        assertThat(actualGameSystemPower).isEqualTo(49710);
    }
}
