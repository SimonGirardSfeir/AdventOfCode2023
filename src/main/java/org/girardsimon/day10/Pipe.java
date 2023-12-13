package org.girardsimon.day10;

import org.girardsimon.common.Position;
import org.girardsimon.common.Tuple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.UnaryOperator;

public enum Pipe {

    START(0,0), VERTICAL (0, 1),
    HORIZONTAL(1, 0), NORTH_WEST(-1, 1),
    NORTH_EAST(1, 1), SOUTH_WEST(-1, -1),
    SOUTH_EAST(1, -1);

    private static final Map<Character, Pipe> BY_CHAR = new HashMap<>();
    private static final Map<Tuple, Pipe> BY_TUPLE = new HashMap<>();

    static {
            BY_CHAR.put('S', START);
            BY_CHAR.put('|', VERTICAL);
            BY_CHAR.put('-', HORIZONTAL);
            BY_CHAR.put('L', NORTH_EAST);
            BY_CHAR.put('J', NORTH_WEST);
            BY_CHAR.put('7', SOUTH_WEST);
            BY_CHAR.put('F', SOUTH_EAST);
            BY_TUPLE.put(new Tuple(2, 0), HORIZONTAL);
            BY_TUPLE.put(new Tuple(0, 2), VERTICAL);
            BY_TUPLE.put(new Tuple(1, 1), NORTH_EAST);
            BY_TUPLE.put(new Tuple(-1, 1), NORTH_WEST);
            BY_TUPLE.put(new Tuple(-1, -1), SOUTH_WEST);
            BY_TUPLE.put(new Tuple(1, -1), SOUTH_EAST);

    }
    private final int dx;
    private final int dy;

    Pipe(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public static Pipe getPipeFromChar(char currentChar) {
        return BY_CHAR.get(currentChar);
    }

    public static Pipe getPipeFromItsNeighbor(Position position, Position firstNeighbor, Position secondNeighbor) {
        int difX1 = firstNeighbor.x() - position.x();
        int difX2 = secondNeighbor.x() - position.x();
        int difY1 = firstNeighbor.y() - position.y();
        int difY2 = secondNeighbor.y() - position.y();

        
        int absoluteDifX = Math.abs(Math.max(difX1, difX2));
        int difX;
        if(1 < absoluteDifX) {
           difX = absoluteDifX;
        } else {
            difX = 0 == difX1 ? difX2 : difX1;
        }
        int absoluteDifY = Math.abs(Math.max(difY1, difY2));
        int difY;
        if(1 < absoluteDifY) {
            difY = absoluteDifY;
        } else {
            difY = 0 == difY1 ? difY2 : difY1;
        }
        return BY_TUPLE.get(new Tuple(difX, difY));
    }

    public List<Position> neighborPositions(Position position, Map<Position, Pipe> positionsAndPipes) {
        List<Position> neighborPositions = new ArrayList<>();
        if(START == this) {
            addPossiblePosition(position, positionsAndPipes, -1, p -> p.fromDx(-1),
                    (p, delta) -> -delta == p.dx() || 0 == p.dy())
                    .ifPresent(neighborPositions::add);
            addPossiblePosition(position, positionsAndPipes, 1, p -> p.fromDx(1),
                    (p, delta) -> -delta == p.dx() || 0 == p.dy())
                    .ifPresent(neighborPositions::add);
            addPossiblePosition(position, positionsAndPipes, 1, p -> p.fromDy(1),
                    (p, delta) -> -delta == p.dy() || 0 == p.dx())
                    .ifPresent(neighborPositions::add);
            addPossiblePosition(position, positionsAndPipes, -1, p -> p.fromDy(-1),
                    (p, delta) -> -delta == p.dy() || 0 == p.dx())
                    .ifPresent(neighborPositions::add);
        } else if (0 == dy()) {
            addPossiblePosition(position, positionsAndPipes, -1, p -> p.fromDx(-1),
                    (p, delta) -> -delta == p.dx() || 0 == p.dy())
                    .ifPresent(neighborPositions::add);
            addPossiblePosition(position, positionsAndPipes, 1, p -> p.fromDx(1),
                    (p, delta) -> -delta == p.dx() || 0 == p.dy())
                    .ifPresent(neighborPositions::add);
        } else if( 0 == dx()) {
            addPossiblePosition(position, positionsAndPipes, 1, p -> p.fromDy(1),
                    (p, delta) -> -delta == p.dy() || 0 == p.dx())
                    .ifPresent(neighborPositions::add);
            addPossiblePosition(position, positionsAndPipes, -1, p -> p.fromDy(-1),
                    (p, delta) -> -delta == p.dy() || 0 == p.dx())
                    .ifPresent(neighborPositions::add);
        } else {
            addPossiblePosition(position, positionsAndPipes, dy(), p -> p.fromDy(dy()),
                    (p, delta) -> -delta == p.dy() || 0 == p.dx())
                    .ifPresent(neighborPositions::add);
            addPossiblePosition(position, positionsAndPipes, dx(), p -> p.fromDx(dx()),
                    (p, delta) -> -delta == p.dx() || 0 == p.dy())
                    .ifPresent(neighborPositions::add);
        }
        return neighborPositions;
    }
    private static Optional<Position> addPossiblePosition(Position position,
                                                          Map<Position, Pipe> positionsAndPipes,
                                                          int delta,
                                                          UnaryOperator<Position> positionTransformer,
                                                          PipeFilter filter) {
        Position positionToBeAdded = positionTransformer.apply(position);
        return (isThereAMatchingPipe(positionToBeAdded, positionsAndPipes, delta, filter)) ?
                Optional.of(positionToBeAdded) : Optional.empty();
    }
    private static boolean isThereAMatchingPipe(Position positionToBeAdded,
                                         Map<Position, Pipe> positionsAndPipes,
                                         int delta,
                                         PipeFilter filter) {
        return positionsAndPipes.containsKey(positionToBeAdded) && Arrays.stream(values())
                .anyMatch(p -> filter.test(p, delta) && p == positionsAndPipes.get(positionToBeAdded) && START != p);

    }

    public int dx() {
        return dx;
    }
    public int dy() {
        return dy;
    }
}
