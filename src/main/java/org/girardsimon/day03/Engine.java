package org.girardsimon.day03;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.IntStream;

public record Engine(List<List<EngineElement>> rowOfEngineElements) {
    public Engine {
        rowOfEngineElements = List.copyOf(rowOfEngineElements);
    }

    public int sumEngineSchematic() {
        return IntStream.range(0, rowOfEngineElements.size())
                .map(this::sumPartNumbers)
                .sum();
    }
    private int sumPartNumbers(int rowIndex) {
        return rowOfEngineElements.get(rowIndex).stream()
                .filter(engineElement -> isAPartNumber(engineElement, rowIndex))
                .mapToInt(e -> Integer.parseInt(e.data()))
                .sum();
    }
    private boolean isAPartNumber(EngineElement engineElement, int rowIndex) {
        return engineElement.isANumber() &&
                IntStream.rangeClosed(Math.max(0, rowIndex - 1), Math.min(rowOfEngineElements.size() - 1, rowIndex + 1))
                .anyMatch(i -> hasSymbolAdjacent(i, i == rowIndex ? whenSameRow(engineElement) :
                        whenNeighboringRow(engineElement)));
    }
    private static Predicate<EngineElement> whenSameRow(EngineElement element) {
        return e -> e.computeXMax() == element.startingColumn()-1 ||
                e.startingColumn() == element.computeXMax()+1;
    }
    private static Predicate<EngineElement> whenNeighboringRow(EngineElement element) {
        return e -> e.isElementInRange(element.startingColumn()-1, element.computeXMax()+1);
    }
    private boolean hasSymbolAdjacent(int rowIndex, Predicate<EngineElement> condition) {
        return rowOfEngineElements.get(rowIndex).stream()
                .filter(EngineElement::isASymbol)
                .anyMatch(condition);
    }
    public int sumGearRatio() {
        return IntStream.range(0, rowOfEngineElements.size())
                .map(this::computeGearRatioForRow)
                .sum();
    }
    private int computeGearRatioForRow(int rowIndex) {
        return rowOfEngineElements.get(rowIndex)
                .stream()
                .filter(EngineElement::isASymbol)
                .mapToInt(e -> computeGearRatioForElement(e, rowIndex))
                .sum();
    }
    private int computeGearRatioForElement(EngineElement element, int rowIndex) {
        List<EngineElement> numbersAdjacent = getNumberAdjacent(rowIndex,
                element);
        return 2 == numbersAdjacent.size() ? numbersAdjacent.stream().mapToInt(e -> Integer.parseInt(e.data()))
                .reduce(1, (a,b) -> a*b)
                : 0;
    }
    private List<EngineElement> getNumberAdjacent(int rowIndex, EngineElement element) {
        return IntStream.rangeClosed(Math.max(0, rowIndex - 1), Math.min(rowOfEngineElements.size() - 1, rowIndex + 1))
                        .mapToObj(i -> numbersAdjacent(i, i == rowIndex ?
                                whenSameRow(element) : whenNeighboringRow(element)))
                        .flatMap(List::stream)
                        .toList();
    }
    private List<EngineElement> numbersAdjacent(int rowIndex, Predicate<EngineElement> condition) {
        return rowOfEngineElements.get(rowIndex).stream()
                .filter(EngineElement::isANumber)
                .filter(condition)
                .toList();
    }
}
