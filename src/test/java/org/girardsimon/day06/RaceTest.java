package org.girardsimon.day06;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RaceTest {
    @Test
    void numberOfWaysToBeatRecord_should_return_expected_value() {
        // Arrange
        Race race = new Race(71530L, 940200L);

        // Act
        long actualNumberOfWaysToBeatRecord = race.numberOfWaysToBeatRecord();

        // Assert
        assertThat(actualNumberOfWaysToBeatRecord).isEqualTo(71503L);
    }

}