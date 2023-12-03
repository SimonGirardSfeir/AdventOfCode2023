package org.girardsimon.day03;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class EngineTest {
    @Test
    void sumEngineSchematic_should_return_expected_result() {
        // Arrange
        String line1 = "467..114..";
        String line2 = "...*......";
        String line3 = "..35..633.";
        String line4 = "......#...";
        String line5 = "617*......";
        String line6 = ".....+.58.";
        String line7 = "..592.....";
        String line8 = "......755.";
        String line9 = "...$.*....";
        String line10 = ".664.598..";
        Engine engine = EngineParser.parseEngine(List.of(line1, line2, line3, line4, line5,
                line6, line7, line8, line9, line10));

        // Act
        int actualSumEngineSchematic = engine.sumEngineSchematic();

        // Assert
        assertThat(actualSumEngineSchematic).isEqualTo(4361);
    }
    @Test
    void sumGearRatio_should_return_expected_result() {
        // Arrange
        String line1 = "467..114..";
        String line2 = "...*......";
        String line3 = "..35..633.";
        String line4 = "......#...";
        String line5 = "617*......";
        String line6 = ".....+.58.";
        String line7 = "..592.....";
        String line8 = "......755.";
        String line9 = "...$.*....";
        String line10 = ".664.598..";
        Engine engine = EngineParser.parseEngine(List.of(line1, line2, line3, line4, line5,
                line6, line7, line8, line9, line10));

        // Act
        int actualSumEngineSchematic = engine.sumGearRatio();

        // Assert
        assertThat(actualSumEngineSchematic).isEqualTo(467835);
    }

}