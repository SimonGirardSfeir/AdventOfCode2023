package org.girardsimon.day05;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.girardsimon.day05.TestUtils.generateGarden;

class GardenParserTest {

    @Test
    void parseSeedsInputs_should_parse_seeds_as_expected() {
        // Arrange
        String line = "seeds: 79 14 55 13";

        // Act
        List<Long> actualSeeds = GardenParser.parseSeedsInputs(line);

        // Assert
        List<Long> expectedSeeds = List.of(79L, 14L, 55L, 13L);
        assertThat(actualSeeds).isEqualTo(expectedSeeds);
    }
    @Test
    void parseSeedsInputsByPair_should_parse_seeds_as_expected() {
        // Arrange
        String line = "seeds: 79 14 55 13";

        // Act
        List<SeedRange> actualSeedRanges = GardenParser.parseSeedsInputsByPair(line);

        // Assert
        SeedRange seedRange1 = new SeedRange(79L, 92L);
        SeedRange seedRange2 = new SeedRange(55L,67L);
        List<SeedRange> expectedSeedRanges = List.of(seedRange1, seedRange2);
        assertThat(actualSeedRanges).isEqualTo(expectedSeedRanges);
    }

    @Test
    void parseGarden_should_parse_garden_as_expected() {
        // Arrange
        List<String> lines = List.of("seed-to-soil map:",
                "50 98 2",
                "52 50 48",
                "",
                "soil-to-fertilizer map:",
                "0 15 37",
                "37 52 2",
                "39 0 15",
                "",
                "fertilizer-to-water map:",
                "49 53 8",
                "0 11 42",
                "42 0 7",
                "57 7 4",
                "",
                "water-to-light map:",
                "88 18 7",
                "18 25 70",
                "",
                "light-to-temperature map:",
                "45 77 23",
                "81 45 19",
                "68 64 13",
                "",
                "temperature-to-humidity map:",
                "0 69 1",
                "1 0 69",
                "",
                "humidity-to-location map:",
                "60 56 37",
                "56 93 4");

        // Act
        Garden actualGarden = GardenParser.parseGarden(lines);

        // Assert
        Garden expectedGarden = generateGarden();
        assertThat(actualGarden).isEqualTo(expectedGarden);
    }

}