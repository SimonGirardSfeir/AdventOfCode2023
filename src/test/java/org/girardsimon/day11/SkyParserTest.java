package org.girardsimon.day11;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.girardsimon.day11.TestUtils.generateSky;

class SkyParserTest {
    @Test
    void parseSky_should_return_expected_sky() {
        // Arrange
        String line1 = "...#......";
        String line2 = ".......#..";
        String line3 = "#.........";
        String line4 = "..........";
        String line5 = "......#...";
        String line6 = ".#........";
        String line7 = ".........#";
        String line8 = "..........";
        String line9 = ".......#..";
        String line10 = "#...#.....";
        List<String> lines = List.of(line1, line2, line3, line4, line5, line6, line7, line8, line9, line10);

        // Act
        Sky actualSky = SkyParser.parseSky(lines);

        // Assert
        Sky expectedSky = generateSky();
        assertThat(actualSky).isEqualTo(expectedSky);
    }
}