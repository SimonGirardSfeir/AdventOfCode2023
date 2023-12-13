package org.girardsimon.day10;

import org.girardsimon.common.Position;
import org.girardsimon.common.Tuple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

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
            neighborPositions.addAll(addPositionsHorizontally(position, positionsAndPipes, -1));
            neighborPositions.addAll(addPositionsHorizontally(position, positionsAndPipes, 1));
            neighborPositions.addAll(addPositionsVertically(position, positionsAndPipes, -1));
            neighborPositions.addAll(addPositionsVertically(position, positionsAndPipes, 1));
        } else if (0 == dy()) {
            neighborPositions.addAll(addPositionsHorizontally(position, positionsAndPipes, -1));
            neighborPositions.addAll(addPositionsHorizontally(position, positionsAndPipes, 1));
        } else if( 0 == dx()) {
            neighborPositions.addAll(addPositionsVertically(position, positionsAndPipes, -1));
            neighborPositions.addAll(addPositionsVertically(position, positionsAndPipes, 1));
        } else {
            neighborPositions.addAll(addPositionsVertically(position, positionsAndPipes, dy()));
            neighborPositions.addAll(addPositionsHorizontally(position, positionsAndPipes, dx()));
        }
        return neighborPositions;
    }

    private static List<Position> addPositionsHorizontally(Position position,
                               Map<Position, Pipe> positionsAndPipes, int dx) {
        List<Position> neighborPositions = new ArrayList<>();
        if (positionsAndPipes.containsKey(position.fromDx(dx))) {
            Pipe pipe = positionsAndPipes.get(position.fromDx(dx));
            Set<Pipe> pipes = Arrays.stream(values())
                    .filter(p -> (-dx == p.dx() || 0 == p.dy()) && START != p)
                    .collect(Collectors.toSet());
            if (pipes.contains(pipe)) {
                neighborPositions.add(position.fromDx(dx));
            }
        }
        return neighborPositions;
    }
    private static List<Position> addPositionsVertically(Position position,
                                                         Map<Position, Pipe> positionsAndPipes, int dy) {
        List<Position> neighborPositions = new ArrayList<>();
        if (positionsAndPipes.containsKey(position.fromDy(dy))) {
            Pipe pipe = positionsAndPipes.get(position.fromDy(dy));
            Set<Pipe> pipes = Arrays.stream(values())
                    .filter(p -> (-dy == p.dy() || 0 == p.dx()) && START != p)
                    .collect(Collectors.toSet());
            if (pipes.contains(pipe)) {
                neighborPositions.add(position.fromDy(dy));
            }
        }
        return neighborPositions;
    }

    public int dx() {
        return dx;
    }
    public int dy() {
        return dy;
    }
}
