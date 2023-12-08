package org.girardsimon.day08;

import org.girardsimon.LineExtractor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class Day08ResolverTest {
    private static Network network;

    @BeforeAll
    static void setUp() throws IOException {
        List<String> lines = LineExtractor.getLines("src/test/resources/day08/inputData.txt");
        network = NetworkParser.parseNetwork(lines);
    }
    @Test
    void resolve_part1_of_day08_problem() {
        // Act
        long actualNumberOfSteps = network.numberOfStepsToReachKey("AAA","ZZZ");

        // Assert
        assertThat(actualNumberOfSteps).isEqualTo(19951L);
    }
    @Test
    void resolve_part2_of_day08_problem() {
        // Act
        long actualNumberOfSteps = network.numberOfStepsToReachKeyForGhosts('A','Z');

        // Assert
        assertThat(actualNumberOfSteps).isEqualTo(16342438708751L);
    }
}
