package org.girardsimon.common;

public record Position(int x, int y) implements Comparable<Position> {
    public Position fromDx(int dx) {
        return new Position(x + dx, y);
    }
    public Position fromDy(int dy) {
        return new Position(x, y + dy);
    }
    public int distance(Position o) {
        return Math.abs(x-o.x()) + Math.abs(y- o.y());
    }
    @Override
    public int compareTo(Position o) {
        return Math.abs(x - o.x()) + Math.abs(y - o.y());
    }
}
