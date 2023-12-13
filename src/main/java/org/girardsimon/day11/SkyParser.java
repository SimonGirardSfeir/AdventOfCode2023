package org.girardsimon.day11;

import org.girardsimon.common.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class SkyParser {
    private SkyParser() {
    }

    public static Sky parseSky(List<String> lines) {
        List<String> expandedVertically = lines.stream()
                .map(SkyParser::expandVertically)
                .flatMap(List::stream)
                .toList();

        List<Integer> indexOfColumnsWithoutGalaxy = indexesOfColumnsWithoutGalaxy(expandedVertically);

        List<String> expandedHorizontally = expandLinesHorizontally(expandedVertically, indexOfColumnsWithoutGalaxy);

        Set<Position> positions = generateGalaxies(expandedHorizontally);
        return new Sky(positions);
    }
    private static Set<Position> generateGalaxies(List<String> expandedHorizontally) {
        return IntStream.range(0, expandedHorizontally.size())
                .mapToObj(i -> generateGalaxiesInRow(expandedHorizontally, i))
                .flatMap(Set::stream)
                .collect(Collectors.toSet());
    }
    private static Set<Position> generateGalaxiesInRow(List<String> expandedHorizontally, int i) {
        return IntStream.range(0,  expandedHorizontally.getFirst().length())
                .filter( j-> '#' == expandedHorizontally.get(i).charAt(j))
                .mapToObj(j -> new Position(j, expandedHorizontally.size() - i -1))
                .collect(Collectors.toSet());
    }
    private static List<String> expandLinesHorizontally(List<String> expandedVertically,
                                                        List<Integer> indexOfColumnsWithoutGalaxy) {
        List<String> expandedHorizontally = new ArrayList<>(expandedVertically);
        int numberOfHorizontalExpansion = 0;
        for(int a : indexOfColumnsWithoutGalaxy) {
            int copyCnt = numberOfHorizontalExpansion;
            expandedHorizontally = expandedHorizontally.stream()
                    .map(s -> {
                        StringBuilder sb = new StringBuilder(s);
                        sb.insert(a+copyCnt, '.');
                        return sb.toString();
                    })
                    .toList();
            numberOfHorizontalExpansion++;
        }
        return expandedHorizontally;
    }
    private static List<Integer> indexesOfColumnsWithoutGalaxy(List<String> lines) {
        return IntStream.range(0, lines.getFirst().length())
                .filter(i -> lines.stream().allMatch(s -> '.' == s.charAt(i)))
                .boxed()
                .toList();
    }
    private static List<String> expandVertically(String line) {
        return line.chars().anyMatch(c -> '.' != (char) c) ? List.of(line)
                : List.of(line, line);
    }
}
