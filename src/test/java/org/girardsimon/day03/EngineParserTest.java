package org.girardsimon.day03;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class EngineParserTest {
    @Test
    void parseEngine_should_return_expected_engine() {
        // Arrange
        String line1 = "467..114..";
        String line2 = "...*......";
        String line3 = "..35..633.";
        List<String> lines = List.of(line1, line2, line3);

        // Act
        Engine actualEngine = EngineParser.parseEngine(lines);

        // Assert
        Engine expectedEngine = buildEngine();
        assertThat(actualEngine).isEqualTo(expectedEngine);
    }
    private static Engine buildEngine() {
        EngineElement engineElement1 = new EngineElement("467", 0);
        EngineElement engineElement2 = new EngineElement("114", 5);
        EngineElement engineElement3 = new EngineElement("*", 3);
        EngineElement engineElement4 = new EngineElement("35", 2);
        EngineElement engineElement5 = new EngineElement("633", 6);
        List<EngineElement> row0 = List.of(engineElement1, engineElement2);
        List<EngineElement> row1 = List.of(engineElement3);
        List<EngineElement> row2 = List.of(engineElement4, engineElement5);
        List<List<EngineElement>> rows = List.of(row0, row1, row2);
        return new Engine(rows);
    }

}