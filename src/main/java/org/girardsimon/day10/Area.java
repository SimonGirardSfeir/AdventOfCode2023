package org.girardsimon.day10;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
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
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.girardsimon.day10.Pipe.NORTH_EAST;
import static org.girardsimon.day10.Pipe.NORTH_WEST;
import static org.girardsimon.day10.Pipe.SOUTH_EAST;
import static org.girardsimon.day10.Pipe.SOUTH_WEST;
import static org.girardsimon.day10.Pipe.START;
import static org.girardsimon.day10.Pipe.VERTICAL;

public final class Area {
    private final Map<Position, Pipe> positionsAndPipes;
    private final Position startingPosition;
    private final Set<Position> loop;

    public Area(Map<Position, Pipe> positionsAndPipes, Position startingPosition) {
        this.positionsAndPipes = positionsAndPipes;
        this.startingPosition = startingPosition;
        this.loop = buildLoop(positionsAndPipes, startingPosition);
    }

    public int countStepsToReachFarthestPositionInLoop() {
        return (loop.size()) / 2;
    }

    private Set<Position> buildLoop(Map<Position, Pipe> positionsAndPipes, Position startingPosition) {
        List<Position> neighBorPositions = neighborPositions(startingPosition, positionsAndPipes.get(startingPosition));
        // Starting position has always exactly two neighbors
        Set<Position> path = getPath(neighBorPositions.getFirst(), neighBorPositions.getLast());
        path.add(startingPosition);
        return path;
    }

    private Set<Position> getPath(Position startingPosition, Position endingPosition) {
        ResultAndParentsMap resultAndMapTuple = traversePositions(startingPosition, endingPosition);

        return resultAndMapTuple.result()
                .map(position -> getPathFromParentsMap(position, resultAndMapTuple.parentsMap()))
                .orElse(Collections.emptySet());
    }

    private static Set<Position> getPathFromParentsMap(Position result,
                                                       Map<Position, Position> parentsMap) {
        return Stream.iterate(result, Objects::nonNull, parentsMap::get)
                .collect(Collectors.toSet());
    }

    private ResultAndParentsMap traversePositions(Position startingPosition,
                                                  Position endingPosition) {
        boolean isInSearch = true;
        Map<Position, Position> parentsMap = new HashMap<>();
        Set<Position> visitedPositions = new HashSet<>();
        Queue<Position> queue = new LinkedList<>();
        queue.add(startingPosition);
        visitedPositions.add(startingPosition);
        Position targetPosition = null;
        while (!queue.isEmpty() && isInSearch) {
            Position currentPosition = queue.remove();
            if (currentPosition.equals(endingPosition)) {
                targetPosition = currentPosition;
                isInSearch = false;
            }
            Pipe currentPipe = positionsAndPipes.get(currentPosition);
            List<Position> neighBorPositions = neighborPositions(currentPosition, currentPipe);
            neighBorPositions.stream()
                    .filter(neighbor -> !visitedPositions.contains(neighbor))
                    .forEach(neighbor -> {
                        visitedPositions.add(neighbor);
                        queue.add(neighbor);
                        parentsMap.put(neighbor, currentPosition);
                    });
        }
        return new ResultAndParentsMap(Optional.ofNullable(targetPosition), parentsMap);
    }
    private record ResultAndParentsMap(Optional<Position> result, Map<Position, Position> parentsMap) {
    }
    private List<Position> neighborPositions(Position currentPosition, Pipe currentPipe) {
        return currentPipe.neighborPositions(currentPosition, positionsAndPipes);
    }

    public int tilesEnclosedByTheLoop() {
        int xMax = loop.stream()
                .mapToInt(Position::x)
                .max().orElse(0);
        int yMax = loop.stream()
                .mapToInt(Position::y)
                .max().orElse(0);

        return IntStream.rangeClosed(0, yMax + 1)
                .map(i -> countEnclosedTilesInARow(i, xMax)).sum();
    }

    private int countEnclosedTilesInARow(int i, int xMax) {
        // We go through the row west to east.
        BiMap<Pipe, Pipe> openingAndClosingTile = HashBiMap.create();
        openingAndClosingTile.put(NORTH_EAST, NORTH_WEST);
        openingAndClosingTile.put(SOUTH_EAST, SOUTH_WEST);

        int enclosedTiles = 0;
        AtomicInteger numberOfIntersectionsWithLoop = new AtomicInteger();
        AtomicReference<Pipe> previousPipe = new AtomicReference<>();

        for (int j = 0; j <= xMax; j++) {
            Position position = new Position(j, i);
            pipeFromPosition(position)
                    .ifPresent(pipe ->
                            manageIntersections(pipe, numberOfIntersectionsWithLoop,
                                    openingAndClosingTile, previousPipe));
            if (1 == numberOfIntersectionsWithLoop.get() % 2 && !loop.contains(position)) {
                enclosedTiles++;
            }
        }
        return enclosedTiles;
    }
    private static void manageIntersections(Pipe pipe, AtomicInteger numberOfIntersectionsWithLoop,
                                            BiMap<Pipe, Pipe> openingAndClosingTile, AtomicReference<Pipe> previous) {
        if (VERTICAL == pipe) {
            numberOfIntersectionsWithLoop.getAndIncrement();
        }
        if (openingAndClosingTile.containsKey(pipe)) {
            previous.set(pipe);
            numberOfIntersectionsWithLoop.getAndIncrement();
        }
        if (previous.get() == openingAndClosingTile.inverse().get(pipe) &&
                openingAndClosingTile.inverse().containsKey(pipe)) {
            numberOfIntersectionsWithLoop.getAndIncrement();
        }
    }
    private Optional<Pipe> pipeFromPosition(Position position) {
        return START == positionsAndPipes.get(position) ? realPipeBehindStart(position) :
                onlyConsiderPipeInLoop(position);
    }
    private Optional<Pipe> onlyConsiderPipeInLoop(Position position) {
        return loop.contains(position) ? Optional.of(positionsAndPipes.get(position)) : Optional.empty();
    }
    private Optional<Pipe> realPipeBehindStart(Position position) {
        List<Position> neighborPositions =
                neighborPositions(startingPosition, positionsAndPipes.get(startingPosition));

        return Optional.ofNullable(Pipe.getPipeFromItsNeighbor(position, neighborPositions.getFirst(),
                neighborPositions.getLast()));
    }

    public Map<Position, Pipe> positionsAndPipes() {
        return Map.copyOf(positionsAndPipes);
    }
    public Position startingPosition() {
        return startingPosition;
    }
    @SuppressWarnings("MethodWithMultipleReturnPoints")
    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (null == obj || obj.getClass() != this.getClass())
            return false;
        var that = (Area) obj;
        return Objects.equals(this.positionsAndPipes, that.positionsAndPipes()) &&
                Objects.equals(this.startingPosition, that.startingPosition());
    }
    @Override
    public int hashCode() {
        return Objects.hash(positionsAndPipes, startingPosition);
    }
}
