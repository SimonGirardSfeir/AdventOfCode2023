package org.girardsimon.day10;

import org.girardsimon.common.Position;

import java.util.Map;
import java.util.Optional;
import java.util.function.BiFunction;

public interface NeighborPositionCommand extends BiFunction<Position, Map<Position, Pipe>, Optional<Position>> {
}
