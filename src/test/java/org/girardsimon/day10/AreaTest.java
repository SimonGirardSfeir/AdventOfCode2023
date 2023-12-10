package org.girardsimon.day10;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.girardsimon.day10.TestUtils.buildArea;
import static org.junit.jupiter.api.Assertions.*;

class AreaTest {
    @Test
    void countStepsToReachFarthestPositionInLoop_should_give_expected_number_of_steps() {
        // Arrange
        Area area = buildArea();

        // Act
        int actualSteps = area.countStepsToReachFarthestPositionInLoop();

        // Assert
        assertThat(actualSteps).isEqualTo(8);
    }

}