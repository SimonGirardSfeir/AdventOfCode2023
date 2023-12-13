package org.girardsimon.day11;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.girardsimon.day11.TestUtils.generateSky;

class SkyTest {
    @Test
    void sumOfShortestPath_should_return_expected_result() {
        // Arrange
        Sky sky = generateSky();

        // Act
        long actualSumOfShortestPath = sky.sumOfShortestPath();

        // Assert
        assertThat(actualSumOfShortestPath).isEqualTo(374L);
    }
}