package org.girardsimon.day07;

import org.girardsimon.LineExtractor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class Day07ResolverTest {
    private static CamelCardsGame gameDefault;
    private static CamelCardsGame gameJoker;

    @BeforeAll
    static void setUp() throws IOException {
        List<String> lines = LineExtractor.getLines("src/test/resources/day07/inputData.txt");
        gameDefault = CamelCardsParser.parseGame(lines);
        gameJoker = CamelCardsParser.parseGameJoker(lines);
    }
    @Test
    void resolve_part1_of_day07_problem() {
        // Act
        long actualTotalWinnings = gameDefault.computeTotalWinning();

        // Assert
        assertThat(actualTotalWinnings).isEqualTo(250254244L);
    }
    @Test
    void resolve_part2_of_day07_problem() {
        // Act
        long actualTotalWinnings = gameJoker.computeTotalWinning();

        // Assert
        assertThat(actualTotalWinnings).isEqualTo(250087440L);
    }
}
