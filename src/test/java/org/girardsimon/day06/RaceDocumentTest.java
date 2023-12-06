package org.girardsimon.day06;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class RaceDocumentTest {
    @Test
    void multiplyNumberOfWaysToBeatRecords_should_return_expected_result() {
        // Arrange
        Race race1 = new Race(7L, 9L);
        Race race2 = new Race(15L, 40L);
        Race race3 = new Race(30L, 200L);
        RaceDocument raceDocument = new RaceDocument(List.of(race1, race2, race3));

        // Act
        long actualScore = raceDocument.multiplyNumberOfWaysToBeatRecords();

        // Assert
        assertThat(actualScore).isEqualTo(288L);
    }

}