package org.girardsimon.day11;

import org.girardsimon.common.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

public record Sky(Set<Position> galaxies) {
    public Sky {
        galaxies = Set.copyOf(galaxies);
    }

    public long sumOfShortestPath() {
        List<Position> galaxiesAsList = new ArrayList<>(galaxies);
        List<List<Position>> pairs = IntStream.range(0, galaxiesAsList.size())
                .boxed()
                .flatMap(i -> IntStream.range(i + 1, galaxiesAsList.size())
                        .mapToObj(j -> List.of(galaxiesAsList.get(i), galaxiesAsList.get(j))))
                .toList();

        return pairs.stream()
                .mapToLong(l -> shortestPath(l.getFirst(), l.getLast()))
                .sum();
    }
    private static int shortestPath(Position position1, Position position2) {
        return position1.distance(position2);
    }
}
