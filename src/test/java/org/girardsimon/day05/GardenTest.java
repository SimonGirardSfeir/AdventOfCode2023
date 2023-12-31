package org.girardsimon.day05;

import org.girardsimon.common.Range;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.girardsimon.day05.TestUtils.generateGarden;

class GardenTest {
    @Test
    void computeLowestLocation_case_input_as_list_should_return_lowest_location_between_seeds() {
        // Arrange
        Garden garden = generateGarden();

        // Act
        long actualLowestLocation = garden.lowestLocation(List.of(79L, 14L, 55L, 13L));

        // Assert
        assertThat(actualLowestLocation).isEqualTo(35);
    }
    @Test
    void lowestLocationRangeInput_as_range_should_return_lowest_location_between_seeds() {
        // Arrange
        Garden garden = generateGarden();
        Range seedRange1 = new Range(79L, 92L);
        Range seedRange2 = new Range(55L, 67L);
        List<Range> seedRanges = List.of(seedRange1, seedRange2);
        // Act
        long actualLowestLocation = garden.lowestLocationRangeInput(seedRanges);

        // Assert
        assertThat(actualLowestLocation).isEqualTo(46);
    }
}