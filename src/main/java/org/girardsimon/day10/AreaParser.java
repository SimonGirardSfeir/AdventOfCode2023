package org.girardsimon.day10;
import org.girardsimon.common.Position;
import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.Optional;

public final class AreaParser {

    private AreaParser() {
    }

    public static Area parseArea(List<String> lines) {
        int linesSize = lines.size();
        List<AbstractMap.SimpleEntry<Position, Pipe>> positionsAndPipesList = IntStream.range(0, linesSize)
                .boxed()
                .flatMap(i -> IntStream
                        .range(0, lines.get(i).length())
                        .filter(j -> '.' != lines.get(i).charAt(j))
                        .mapToObj(j -> new AbstractMap.SimpleEntry<>(
                                new Position(j, linesSize - 1 - i),
                                Pipe.getPipeFromChar(lines.get(i).charAt(j))
                        ))
                ).toList();

        Optional<AbstractMap.SimpleEntry<Position, Pipe>> startPosEntryOpt = positionsAndPipesList.stream()
                .filter(entry -> Pipe.START == entry.getValue())
                .findFirst();

        Map<Position, Pipe> positionsAndPipes = positionsAndPipesList.stream()
                .collect(Collectors.toMap(AbstractMap.SimpleEntry::getKey, AbstractMap.SimpleEntry::getValue));

        return startPosEntryOpt
                .map(startPosEntry -> new Area(positionsAndPipes, startPosEntry.getKey()))
                .orElseThrow(() -> new IllegalArgumentException("Starting position not found."));
    }
}