package org.girardsimon.day11;

import org.girardsimon.common.Position;

import java.util.Set;

final class TestUtils {
    private TestUtils() {
    }
    static Sky generateSky() {
        Position position1 = new Position(0,0);
        Position position2 = new Position(0,9);
        Position position3 = new Position(1,5);
        Position position4 = new Position(4,11);
        Position position5 = new Position(5,0);
        Position position6 = new Position(8,6);
        Position position7 = new Position(9,1);
        Position position8 = new Position(9,10);
        Position position9 = new Position(12,4);
        Set<Position> galaxies = Set.of(position1, position2, position3, position4, position5, position6,
                position7, position8, position9);
        return new Sky(galaxies);
    }
}
