package org.girardsimon.day03;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.IntStream;

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
    public boolean isAPartNumber(int rowIndex, List<List<EngineElement>> rowsOfEngineElements) {
        return isANumber() && hasSymbolAdjacent(rowIndex, rowsOfEngineElements);
    }
    private boolean hasSymbolAdjacent(int rowIndex, List<List<EngineElement>> rowsOfEngineElements) {
        return IntStream.rangeClosed(Math.max(0, rowIndex - 1), Math.min(rowsOfEngineElements.size() - 1, rowIndex + 1))
                .anyMatch(i -> hasSymbolAdjacentOnLine(rowsOfEngineElements.get(i), i == rowIndex ? whenSameRow() :
                        whenNeighboringRow()));
    }
    private Predicate<EngineElement> whenSameRow() {
        return e -> e.computeXMax() == startingColumn-1 ||
                e.startingColumn() == computeXMax()+1;
    }
    private Predicate<EngineElement> whenNeighboringRow() {
        return e -> e.isElementInRange(startingColumn-1, computeXMax()+1);
    }
    private static boolean hasSymbolAdjacentOnLine(List<EngineElement> engineElements,
                                                  Predicate<EngineElement> condition) {
        return engineElements.stream()
                .filter(EngineElement::isASymbol)
                .anyMatch(condition);
    }
    public int computeGearRatioForElement(int rowIndex, List<List<EngineElement>> rowsOfEngineElements) {
        List<EngineElement> numbersAdjacent = getNumberAdjacent(rowIndex,
                rowsOfEngineElements);
        return 2 == numbersAdjacent.size() ? numbersAdjacent.stream().mapToInt(e -> Integer.parseInt(e.data()))
                .reduce(1, (a,b) -> a*b)
                : 0;
    }
    public List<EngineElement> getNumberAdjacent(int rowIndex,
                                                 List<List<EngineElement>> rowsOfEngineElements) {
        return IntStream.rangeClosed(Math.max(0, rowIndex - 1), Math.min(rowsOfEngineElements.size() - 1, rowIndex + 1))
                .mapToObj(i -> numbersAdjacent(rowsOfEngineElements.get(i), i == rowIndex ?
                        whenSameRow() : whenNeighboringRow()))
                .flatMap(List::stream)
                .toList();
    }
    private static List<EngineElement> numbersAdjacent(List<EngineElement> engineElements,
                                                Predicate<EngineElement> condition) {
        return engineElements.stream()
                .filter(EngineElement::isANumber)
                .filter(condition)
                .toList();
    }
}
