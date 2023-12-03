package org.girardsimon.day03;

public record EngineElement(String data , int startingColumn) {
    public int computeValue() {
        return Character.isDigit(data.charAt(0)) ? Integer.parseInt(data) : 0;
    }
    public int computeXMax() {
        return startingColumn + data.length() -1;
    }
    public boolean isInColumnsRange(int xMin, int xMax) {
        return !(startingColumn > xMax || computeXMax() < xMin);
    }
}
