package org.girardsimon.day10;

import org.girardsimon.common.Position;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public enum Pipe {

    START {
        @Override
        public List<Position> neighborPositions(Position currentPosition, Map<Position, Pipe> positionsAndPipes) {
            List<Position> neighborPositions = new ArrayList<>();
            northPosition(currentPosition, positionsAndPipes)
                    .ifPresent(neighborPositions::add);
            southPosition(currentPosition, positionsAndPipes)
                    .ifPresent(neighborPositions::add);
            eastPosition(currentPosition, positionsAndPipes)
                    .ifPresent(neighborPositions::add);
            westPosition(currentPosition, positionsAndPipes)
                    .ifPresent(neighborPositions::add);
            return neighborPositions;
        }
    }, VERTICAL {
        @Override
        public List<Position> neighborPositions(Position currentPosition, Map<Position, Pipe> positionsAndPipes) {
            List<Position> neighborPositions = new ArrayList<>();
            northPosition(currentPosition, positionsAndPipes)
                    .ifPresent(neighborPositions::add);
            southPosition(currentPosition, positionsAndPipes)
                    .ifPresent(neighborPositions::add);
            return neighborPositions;
        }
    }, HORIZONTAL {
        @Override
        public List<Position> neighborPositions(Position currentPosition, Map<Position, Pipe> positionsAndPipes) {
            List<Position> neighborPositions = new ArrayList<>();
            eastPosition(currentPosition, positionsAndPipes)
                    .ifPresent(neighborPositions::add);
            westPosition(currentPosition, positionsAndPipes)
                    .ifPresent(neighborPositions::add);
            return neighborPositions;
        }
    }, NORTH_WEST {
        @Override
        public List<Position> neighborPositions(Position currentPosition, Map<Position, Pipe> positionsAndPipes) {
            List<Position> neighborPositions = new ArrayList<>();
            northPosition(currentPosition, positionsAndPipes)
                    .ifPresent(neighborPositions::add);
            westPosition(currentPosition, positionsAndPipes)
                    .ifPresent(neighborPositions::add);
            return neighborPositions;
        }
    }, NORTH_EAST {
        @Override
        public List<Position> neighborPositions(Position currentPosition, Map<Position, Pipe> positionsAndPipes) {
            List<Position> neighborPositions = new ArrayList<>();
            northPosition(currentPosition, positionsAndPipes)
                    .ifPresent(neighborPositions::add);
            eastPosition(currentPosition, positionsAndPipes)
                    .ifPresent(neighborPositions::add);
            return neighborPositions;
        }
    }, SOUTH_WEST {
        @Override
        public List<Position> neighborPositions(Position currentPosition, Map<Position, Pipe> positionsAndPipes) {
            List<Position> neighborPositions = new ArrayList<>();
            southPosition(currentPosition, positionsAndPipes)
                    .ifPresent(neighborPositions::add);
            westPosition(currentPosition, positionsAndPipes)
                    .ifPresent(neighborPositions::add);
            return neighborPositions;
        }
    }, SOUTH_EAST {
        @Override
        public List<Position> neighborPositions(Position currentPosition, Map<Position, Pipe> positionsAndPipes) {
            List<Position> neighborPositions = new ArrayList<>();
            southPosition(currentPosition, positionsAndPipes)
                    .ifPresent(neighborPositions::add);
            eastPosition(currentPosition, positionsAndPipes)
                    .ifPresent(neighborPositions::add);
            return neighborPositions;
        }
    };

    private static final Map<Character, Pipe> BY_CHAR = new HashMap<>();

    static {
            BY_CHAR.put('S', START);
            BY_CHAR.put('|', VERTICAL);
            BY_CHAR.put('-', HORIZONTAL);
            BY_CHAR.put('L', NORTH_EAST);
            BY_CHAR.put('J', NORTH_WEST);
            BY_CHAR.put('7', SOUTH_WEST);
            BY_CHAR.put('F', SOUTH_EAST);
    }

    public static Pipe getPipeFromChar(char currentChar) {
        return BY_CHAR.get(currentChar);
    }

    public abstract List<Position> neighborPositions(Position currentPosition, Map<Position, Pipe> positionsAndPipes);
    private static Optional<Position> northPosition(Position position, Map<Position, Pipe> positionsAndPipes) {
        Position toConsider = new Position(position.x(), position.y()+1);
        if(positionsAndPipes.containsKey(toConsider)) {
            Pipe pipe = positionsAndPipes.get(toConsider);
            if (VERTICAL == pipe || SOUTH_EAST == pipe || SOUTH_WEST == pipe) {
                return Optional.of(toConsider);
            }
        }
        return Optional.empty();
    }
    private static Optional<Position> southPosition(Position position, Map<Position, Pipe> positionsAndPipes) {
        Position toConsider = new Position(position.x(), position.y()-1);
        if(positionsAndPipes.containsKey(toConsider)) {
            Pipe pipe = positionsAndPipes.get(toConsider);
            if (VERTICAL == pipe || NORTH_EAST == pipe || NORTH_WEST == pipe) {
                return Optional.of(toConsider);
            }
        }
        return Optional.empty();
    }
    private static Optional<Position> westPosition(Position position, Map<Position, Pipe> positionsAndPipes) {
        Position toConsider = new Position(position.x()-1, position.y());
        if(positionsAndPipes.containsKey(toConsider)) {
            Pipe pipe = positionsAndPipes.get(toConsider);
            if (HORIZONTAL == pipe || NORTH_EAST == pipe || SOUTH_EAST == pipe) {
                return Optional.of(toConsider);
            }
        }
        return Optional.empty();
    }
    private static Optional<Position> eastPosition(Position position, Map<Position, Pipe> positionsAndPipes) {
        Position toConsider = new Position(position.x()+1, position.y());
        if(positionsAndPipes.containsKey(toConsider)) {
            Pipe pipe = positionsAndPipes.get(toConsider);
            if (HORIZONTAL == pipe || NORTH_WEST == pipe || SOUTH_WEST == pipe) {
                return Optional.of(toConsider);
            }
        }
        return Optional.empty();
    }
}
