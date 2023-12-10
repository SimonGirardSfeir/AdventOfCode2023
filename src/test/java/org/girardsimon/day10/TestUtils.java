package org.girardsimon.day10;

import org.girardsimon.common.Position;

import java.util.Map;

import static org.girardsimon.day10.Pipe.HORIZONTAL;
import static org.girardsimon.day10.Pipe.NORTH_EAST;
import static org.girardsimon.day10.Pipe.NORTH_WEST;
import static org.girardsimon.day10.Pipe.SOUTH_EAST;
import static org.girardsimon.day10.Pipe.SOUTH_WEST;
import static org.girardsimon.day10.Pipe.START;
import static org.girardsimon.day10.Pipe.VERTICAL;

final class TestUtils {
    private TestUtils() {
    }

    static Area buildArea() {
        Map.Entry<Position, Pipe> entry1 = Map.entry(new Position(0,0), NORTH_EAST);
        Map.Entry<Position, Pipe> entry2 = Map.entry(new Position(1,0), NORTH_WEST);
        Map.Entry<Position, Pipe> entry3 = Map.entry(new Position(3,0), NORTH_EAST);
        Map.Entry<Position, Pipe> entry4 = Map.entry(new Position(4,0), NORTH_WEST);
        Map.Entry<Position, Pipe> entry5 = Map.entry(new Position(0,1), VERTICAL);
        Map.Entry<Position, Pipe> entry6 = Map.entry(new Position(1,1), SOUTH_EAST);
        Map.Entry<Position, Pipe> entry22 = Map.entry(new Position(2,1), HORIZONTAL);
        Map.Entry<Position, Pipe> entry23 = Map.entry(new Position(3,1), HORIZONTAL);
        Map.Entry<Position, Pipe> entry7 = Map.entry(new Position(4,1), NORTH_WEST);
        Map.Entry<Position, Pipe> entry8 = Map.entry(new Position(0,2), START);
        Map.Entry<Position, Pipe> entry9 = Map.entry(new Position(1,2), NORTH_WEST);
        Map.Entry<Position, Pipe> entry10 = Map.entry(new Position(2,2), NORTH_EAST);
        Map.Entry<Position, Pipe> entry11 = Map.entry(new Position(3,2), NORTH_EAST);
        Map.Entry<Position, Pipe> entry12 = Map.entry(new Position(4,2), SOUTH_WEST);
        Map.Entry<Position, Pipe> entry13 = Map.entry(new Position(1,3), SOUTH_EAST);
        Map.Entry<Position, Pipe> entry14 = Map.entry(new Position(2,3), NORTH_WEST);
        Map.Entry<Position, Pipe> entry15 = Map.entry(new Position(3,3), VERTICAL);
        Map.Entry<Position, Pipe> entry16 = Map.entry(new Position(4,3), SOUTH_WEST);
        Map.Entry<Position, Pipe> entry17 = Map.entry(new Position(0,4), SOUTH_WEST);
        Map.Entry<Position, Pipe> entry18 = Map.entry(new Position(1,4), HORIZONTAL);
        Map.Entry<Position, Pipe> entry19 = Map.entry(new Position(2,4), SOUTH_EAST);
        Map.Entry<Position, Pipe> entry20 = Map.entry(new Position(3,4), SOUTH_WEST);
        Map.Entry<Position, Pipe> entry21 = Map.entry(new Position(4,4), HORIZONTAL);
        Position startingPosition = new Position(0,2);
        Map<Position, Pipe> positionsAndPipes = Map.ofEntries(entry1, entry2, entry3, entry4, entry5, entry6,entry22, entry23, entry7, entry8,
                entry9, entry10, entry11, entry12, entry13, entry14, entry15, entry16, entry17, entry18, entry19, entry20, entry21);
        return new Area(positionsAndPipes, startingPosition);
    }
}
