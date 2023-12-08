package org.girardsimon.day08;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.girardsimon.day08.TestUtils.generateNetwork;

class NetworkParserTest {
    @Test
    void parseNetwork_should_parse_expected_network() {
        // Arrange
        String line1 = "RL";
        String line2 = "";
        String line3 = "AAA = (BBB, CCC)";
        String line4 = "BBB = (DDD, EEE)";
        String line5 = "CCC = (ZZZ, GGG)";
        String line6 = "DDD = (DDD, DDD)";
        String line7 = "EEE = (EEE, EEE))";
        String line8 = "GGG = (GGG, GGG)";
        String line9 = "ZZZ = (ZZZ, ZZZ)";
        List<String> lines = List.of(line1, line2, line3, line4, line5, line6, line7, line8, line9);

        // Act
        Network actualNetwork = NetworkParser.parseNetwork(lines);

        // Assert
        Network expectedNetwork = generateNetwork();
        assertThat(actualNetwork).isEqualTo(expectedNetwork);
    }
}