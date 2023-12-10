package org.girardsimon.day10;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.girardsimon.day10.TestUtils.buildArea;

class AreaParserTest {
    @Test
    void parseArea_should_return_expected_area() {
        // Arrange
        String line1 = "7-F7-";
        String line2 = ".FJ|7";
        String line3 = "SJLL7";
        String line4 = "|F--J";
        String line5 = "LJ.LJ";
        List<String> lines = List.of(line1, line2, line3, line4, line5);

        // Act
        Area actualArea = AreaParser.parseArea(lines);

        // Assert
        Area expectedArea = buildArea();
        assertThat(actualArea).isEqualTo(expectedArea);
    }
}