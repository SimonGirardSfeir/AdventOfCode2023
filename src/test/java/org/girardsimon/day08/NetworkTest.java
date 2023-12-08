package org.girardsimon.day08;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.girardsimon.day08.TestUtils.generateNetwork;
import static org.girardsimon.day08.TestUtils.generateNetworkForGhost;
import static org.girardsimon.day08.TestUtils.generateNetworkWithLessInstructionThatExpectedStepsToReachTarget;

class NetworkTest {
    static Stream<Arguments> provideNetworkAndExpectedOutput() {
        return Stream.of(
                Arguments.of(generateNetwork(), 2L),
                Arguments.of(generateNetworkWithLessInstructionThatExpectedStepsToReachTarget(), 6L)
        );
    }
    @ParameterizedTest
    @MethodSource("provideNetworkAndExpectedOutput")
    void numberOfStepsToReachKey_should_return_the_number_of_steps_to_reach_target_key_from_starting_key_in_network(
            Network network, long expectedSteps) {
        // Act
        long actualNumberOfSteps = network.numberOfStepsToReachKey("AAA","ZZZ");

        // Assert
        assertThat(actualNumberOfSteps).isEqualTo(expectedSteps);
    }

    @Test
    void numberOfStepsToReachKeyForGhosts_should_return_the_number_of_steps_to_reach_target_key_from_starting_key_in_network() {
        //Arrange
        Network network = generateNetworkForGhost();

        // Act
        long actualNumberOfSteps = network.numberOfStepsToReachKeyForGhosts('A','Z');

        // Assert
        assertThat(actualNumberOfSteps).isEqualTo(6L);
    }
}