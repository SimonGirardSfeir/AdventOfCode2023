package org.girardsimon.day12;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class SpringParserTest {

    @Test
    void parseSpringSystem_should_return_expected_system() {
        // Arrange
        String line1 = "???.### 1,1,3";
        String line2 = ".??..??...?##. 1,1,3";
        String line3 = "?#?#?#?#?#?#?#? 1,3,1,6";
        List<String> lines = List.of(line1, line2, line3);

        // Act
        SpringSystem actualSystem = SpringParser.parseSpringSystem(lines);

        // Assert
        RowOfSprings row1 = new RowOfSprings("???.###", List.of(1,1,3));
        RowOfSprings row2 = new RowOfSprings(".??..??...?##.", List.of(1,1,3));
        RowOfSprings row3 = new RowOfSprings("?#?#?#?#?#?#?#?", List.of(1,3,1,6));
        List<RowOfSprings> rowOfSprings = List.of(row1, row2, row3);
        SpringSystem expectedSystem = new SpringSystem(rowOfSprings);
        assertThat(actualSystem).isEqualTo(expectedSystem);
    }
}