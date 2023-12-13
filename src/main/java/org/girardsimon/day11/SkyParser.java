package org.girardsimon.day11;

import org.girardsimon.common.Position;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class SkyParser {
    private SkyParser() {
    }

    public static Sky parseSky(List<String> lines, int space) {
        Set<Position> positions = generateGalaxies(lines);

        List<Integer> indexOfRowsWithoutGalaxy = indexesOfRowsWithoutGalaxy(positions);
        List<Integer> indexOfColumnsWithoutGalaxy = indexesOfColumnsWithoutGalaxy(positions);

        Set<Position> expandedHorizontally =
                expandPositions(positions, indexOfColumnsWithoutGalaxy, Position::x, Position::fromDx, space);
        Set<Position> expandedVertically =
                expandPositions(expandedHorizontally, indexOfRowsWithoutGalaxy, Position::y, Position::fromDy, space);

        return new Sky(expandedVertically);
    }
    private static List<Integer> indexesOfRowsWithoutGalaxy(Set<Position> positions) {
        int max = positions.stream()
                .mapToInt(Position::y)
                .max()
                .orElse(0);
        return IntStream.range(0, max)
                .filter(i -> positions.stream().noneMatch(p -> p.y() == i))
                .boxed()
                .toList();
    }
    private static List<Integer> indexesOfColumnsWithoutGalaxy(Set<Position> positions) {
        int max = positions.stream()
                .mapToInt(Position::x)
                .max()
                .orElse(0);
        return IntStream.range(0, max)
                .filter(i -> positions.stream().noneMatch(p -> p.x() == i))
                .boxed()
                .toList();
    }
    private static Set<Position> generateGalaxies(List<String> lines) {
        return IntStream.range(0, lines.size())
                .mapToObj(i -> generateGalaxiesInRow(lines, i))
                .flatMap(Set::stream)
                .collect(Collectors.toSet());
    }
    private static Set<Position> generateGalaxiesInRow(List<String> lines, int i) {
        return IntStream.range(0,  lines.getFirst().length())
                .filter( j-> '#' == lines.get(i).charAt(j))
                .mapToObj(j -> new Position(j, lines.size() - i -1))
                .collect(Collectors.toSet());
    }
    private static Set<Position> expandPositions(Set<Position> positions,
                                                 List<Integer> indexesWithoutGalaxy,
                                                 ToIntFunction<Position> positionAxisToCheck,
                                                 BiFunction<Position, Integer, Position> createPosition,
                                                 int space) {
        Set<Position> mutablePositions = new HashSet<>(positions);
        int numberOfExpansion = 0;
        for (int index : indexesWithoutGalaxy) {
            int copyCnt = numberOfExpansion;
            mutablePositions = mutablePositions.stream()
                    .map(position -> {
                        if (positionAxisToCheck.applyAsInt(position) > index + copyCnt) {
                            return createPosition.apply(position, space);
                        } else {
                            return position;
                        }
                    })
                    .collect(Collectors.toSet());
            numberOfExpansion += space;
        }
        return mutablePositions;
    }
}
