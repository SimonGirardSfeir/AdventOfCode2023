package org.girardsimon.day12;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class SpringSystemTest {

    @Test
    void sumOfPossibleArrangements_should_return_expected_result() {
        // Arrange
        RowOfSprings rowOfSprings1 = new RowOfSprings("???.###", List.of(1,1,3));
        RowOfSprings rowOfSprings2 = new RowOfSprings(".??..??...?##.", List.of(1,1,3));
        RowOfSprings rowOfSprings3 = new RowOfSprings("?#?#?#?#?#?#?#?", List.of(1,3,1,6));
        RowOfSprings rowOfSprings4 = new RowOfSprings("????.#...#...", List.of(4,1,1));
        RowOfSprings rowOfSprings5 = new RowOfSprings("????.######..#####.", List.of(1,6,5));
        RowOfSprings rowOfSprings6 = new RowOfSprings("?###????????", List.of(3,2,1));
        SpringSystem system = new SpringSystem(List.of(rowOfSprings1, rowOfSprings2, rowOfSprings3, rowOfSprings4,
                rowOfSprings5, rowOfSprings6));

        // Act
        long actualSum = system.sumOfPossibleArrangements();

        // Assert
        assertThat(actualSum).isEqualTo(21L);
    }

}