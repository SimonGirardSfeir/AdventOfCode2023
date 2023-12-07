package org.girardsimon.day03;

public record EngineElement(String data, int startingColumn) {
    public int computeXMax() {
        return startingColumn + data.length() -1;
    }
    public boolean isElementInRange(int xMin, int xMax) {
        return !(startingColumn > xMax || computeXMax() < xMin);
    }
    public boolean isANumber() {
        return Character.isDigit(data.charAt(0));
    }
    public boolean isASymbol() {
        return 1 == data.length();
    }
}
