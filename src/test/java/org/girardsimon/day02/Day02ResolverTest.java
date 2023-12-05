package org.girardsimon.day02;

import org.girardsimon.LineExtractor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class Day02ResolverTest {
    private static GameSystem gameSystem;

    @BeforeAll
    static void setUp() throws IOException {
        List<String> lines = LineExtractor.getLines("src/test/resources/day02/inputData.txt");
        gameSystem = GameParser.parseGameSystem(lines);
    }

    @Test
    void resolve_part1_of_day02_problem() {
        // Act
        int actualSumOfGamesIdOfPossibleGame = gameSystem.sumGamesIdOfPossibleGame(12, 13, 14);

        // Assert
        assertThat(actualSumOfGamesIdOfPossibleGame).isEqualTo(2683);
    }
    @Test
    void resolve_part2_of_day02_problem() {
        // Act
        int actualGameSystemPower = gameSystem.computeGameSystemPower();

        // Assert
        assertThat(actualGameSystemPower).isEqualTo(49710);
    }
}
