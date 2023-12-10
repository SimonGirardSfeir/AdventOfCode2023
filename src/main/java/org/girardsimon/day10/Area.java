package org.girardsimon.day10;

import org.girardsimon.common.Position;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.girardsimon.day10.Pipe.HORIZONTAL;

public record Area(Map<Position, Pipe> positionsAndPipes, Position startingPosition) {
    public int countStepsToReachFarthestPositionInLoop() {
        List<Position> neighBorPositions = neighborPositions(startingPosition, positionsAndPipes.get(startingPosition));
        // Starting position has always exactly two neighbors
        Set<Position> loopPathExcludingStartingPosition =
                getPath(neighBorPositions.getFirst(), neighBorPositions.getLast());
        return (loopPathExcludingStartingPosition.size()+1)/2;
    }

    private Set<Position> getPath(Position startingPosition, Position endingPosition) {
        ResultAndParentsMap resultAndMapTuple = traversePositions(startingPosition, endingPosition);

        return resultAndMapTuple.result()
                .map(position -> getPathFromParentsMap(position, resultAndMapTuple.parentsMap()))
                .orElse(Collections.emptySet());
    }

    public int tilesEnclosedByTheLoop() {
        List<Position> neighBorPositions = neighborPositions(startingPosition, positionsAndPipes.get(startingPosition));

        Set<Position> loop = getPath(neighBorPositions.getFirst(), neighBorPositions.getLast());
        loop.add(startingPosition);

        var a = loop.stream()
                .filter(p -> (HORIZONTAL == positionsAndPipes.get(p))
                        && !loop.contains(new Position(p.x(), p.y()+1))
                        && !loop.contains(new Position(p.x(), p.y()-1)))
                .findAny();

        return 0;
    }

    private static Set<Position> getPathFromParentsMap(Position result,
                                                       Map<Position, Position> parentsMap) {
        return Stream.iterate(result, Objects::nonNull, parentsMap::get)
                .collect(Collectors.toSet());
    }

    private ResultAndParentsMap traversePositions(Position startingPosition,
                                                 Position endingPosition) {
        Map<Position, Position> parentsMap = new HashMap<>();
        Set<Position> visitedPositions = new HashSet<>();
        Queue<Position> queue = new LinkedList<>();
        queue.add(startingPosition);
        visitedPositions.add(startingPosition);
        Position targetPosition = null;
        while (!queue.isEmpty()) {
            Position currentPosition = queue.remove();
            if (currentPosition.equals(endingPosition)) {
                targetPosition = currentPosition;
                break;
            }
            Pipe currentPipe = positionsAndPipes.get(currentPosition);
            List<Position> neighBorPositions = neighborPositions(currentPosition, currentPipe);
            for (Position neighbor : neighBorPositions) {
                if (!visitedPositions.contains(neighbor)) {
                    visitedPositions.add(neighbor);
                    queue.add(neighbor);
                    parentsMap.put(neighbor, currentPosition);
                }
            }
        }
        return new ResultAndParentsMap(Optional.ofNullable(targetPosition), parentsMap);
    }
    private record ResultAndParentsMap(Optional<Position> result, Map<Position, Position> parentsMap) {
    }
    private List<Position> neighborPositions(Position currentPosition, Pipe currentPipe) {
        return currentPipe.neighborPositions(currentPosition, positionsAndPipes);
    }
}
