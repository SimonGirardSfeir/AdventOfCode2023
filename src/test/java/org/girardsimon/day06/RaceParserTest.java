package org.girardsimon.day06;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class RaceParserTest {
    @Test
    void parseRaceDocument_should_parse_race_document_as_expected() {
        // Arrange
        List<String> lines = List.of("Time:      7  15   30", "Distance:  9  40  200");

        // Act
        RaceDocument actualRaceDocument = RaceParser.parseRaceDocument(lines);

        // Assert
        Race race1 = new Race(7L, 9L);
        Race race2 = new Race(15L, 40L);
        Race race3 = new Race(30L, 200L);
        RaceDocument expectedRaceDocument = new RaceDocument(List.of(race1, race2, race3));
        assertThat(actualRaceDocument).isEqualTo(expectedRaceDocument);
    }
    @Test
    void parseRace_should_parse_race_as_expected() {
        // Arrange
        List<String> lines = List.of("Time:      7  15   30", "Distance:  9  40  200");

        // Act
        Race actualRace = RaceParser.parseRace(lines);

        // Assert
        Race expectedRace = new Race(71530L, 940200L);
        assertThat(actualRace).isEqualTo(expectedRace);
    }
}