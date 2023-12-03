package org.girardsimon.day03;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.IntStream;

public record Engine(List<List<EngineElement>> rowOfEngineElements) {
    public int sumEngineSchematic() {
        return IntStream.range(0, rowOfEngineElements.size())
                .map(this::sumPartNumbers)
                .sum();
    }
    private int sumPartNumbers(int rowIndex) {
        return rowOfEngineElements.get(rowIndex).stream()
                .filter(engineElement -> isAPartNumber(engineElement, rowIndex))
                .mapToInt(EngineElement::computeValue)
                .sum();
    }
    private boolean isAPartNumber(EngineElement engineElement, int rowIndex) {
        Predicate<EngineElement> isElementAdjacentToAnotherNextRow = isElementAdjacentToAnotherNextRow(engineElement);
        Predicate<EngineElement> isElementAdjacentToAnotherSameRow = isElementAdjacentToAnotherSameRow(engineElement);
        return engineElement.computeValue() > 0 &&
                IntStream.rangeClosed(Math.max(0, rowIndex - 1), Math.min(rowOfEngineElements.size() - 1, rowIndex + 1))
                .anyMatch(i -> hasAdjacentInRow(i, i == rowIndex ? isElementAdjacentToAnotherSameRow :
                        isElementAdjacentToAnotherNextRow));
    }
    private static Predicate<EngineElement> isElementAdjacentToAnotherSameRow(EngineElement engineElement) {
        return e -> e.computeXMax() == engineElement.startingColumn()-1 ||
                e.startingColumn() == engineElement.computeXMax()+1;
    }
    private static Predicate<EngineElement> isElementAdjacentToAnotherNextRow(EngineElement engineElement) {
        return e -> e.isInColumnsRange(engineElement.startingColumn()-1, engineElement.computeXMax()+1);
    }
    private boolean hasAdjacentInRow(int rowIndex, Predicate<EngineElement> condition) {
        return rowOfEngineElements.get(rowIndex).stream().anyMatch(condition);
    }
    public int sumGearRatio() {
        return IntStream.range(0, rowOfEngineElements.size())
                .map(this::computeGearRatioForRow)
                .sum();
    }
    private int computeGearRatioForRow(int rowIndex) {
        return rowOfEngineElements.get(rowIndex)
                .stream()
                .filter(e -> e.computeValue() == 0)
                .mapToInt(e -> computeGearRatioForElement(e, rowIndex))
                .sum();
    }
    private int computeGearRatioForElement(EngineElement element, int rowIndex) {
        Predicate<EngineElement> isElementAdjacentToAnotherNextRow = isElementAdjacentToAnotherNextRow(element);
        Predicate<EngineElement> isElementAdjacentToAnotherSameRow = isElementAdjacentToAnotherSameRow(element);
        List<EngineElement> numbersAdjacent = getNumberAdjacent(rowIndex,
                isElementAdjacentToAnotherNextRow, isElementAdjacentToAnotherSameRow);

        return numbersAdjacent.size() == 2 ? (numbersAdjacent.getFirst().computeValue() *
                numbersAdjacent.getLast().computeValue())
                : 0;
    }
    private List<EngineElement> getNumberAdjacent(int rowIndex,
                                                  Predicate<EngineElement> isElementAdjacentToAnotherNextRow,
                                                  Predicate<EngineElement> isElementAdjacentToAnotherSameRow) {
        return IntStream.rangeClosed(Math.max(0, rowIndex - 1), Math.min(rowOfEngineElements.size() - 1, rowIndex + 1))
                        .mapToObj(i -> numbersInRow(i, i == rowIndex ?
                                isElementAdjacentToAnotherSameRow : isElementAdjacentToAnotherNextRow))
                        .flatMap(List::stream)
                        .toList();
    }
    private List<EngineElement> numbersInRow(int rowIndex, Predicate<EngineElement> condition) {
        return rowOfEngineElements.get(rowIndex).stream()
                .filter(condition)
                .filter(engineElement -> engineElement.computeValue() > 0)
                .toList();
    }
}
