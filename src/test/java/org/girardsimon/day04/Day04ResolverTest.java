package org.girardsimon.day04;


import org.girardsimon.LineExtractor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class Day04ResolverTest {
    private List<String> lines;

    @BeforeEach
    void setUp() throws IOException {
        lines = LineExtractor.getLines("src/test/resources/day04/inputData.txt");
    }

    @Test
    void resolve_part1_of_day04_problem() {
        // Arrange
        CardPile cardPile = CardParser.parse(lines);

        // Act
        int actualPoints = cardPile.computePoints();

        // Assert
        assertThat(actualPoints).isEqualTo(27845);
    }
    @Test
    void resolve_part2_of_day04_problem() {
        // Arrange
        CardPile cardPile = CardParser.parse(lines);

        // Act
        int actualPoints = cardPile.numberOfCards();

        // Assert
        assertThat(actualPoints).isEqualTo(9496801);
    }
}
