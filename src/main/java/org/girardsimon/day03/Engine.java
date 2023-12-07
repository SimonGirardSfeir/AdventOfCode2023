package org.girardsimon.day03;

import java.util.List;
import java.util.stream.IntStream;

public record Engine(List<List<EngineElement>> rowOfEngineElements) {
    public Engine {
        rowOfEngineElements = List.copyOf(rowOfEngineElements);
    }

    public int sumEngineSchematic() {
        return IntStream.range(0, rowOfEngineElements.size())
                .map(this::sumPartNumbersForRow)
                .sum();
    }
    private int sumPartNumbersForRow(int rowIndex) {
        return rowOfEngineElements.get(rowIndex).stream()
                .filter(engineElement -> engineElement.isAPartNumber(rowIndex, rowOfEngineElements))
                .mapToInt(e -> Integer.parseInt(e.data()))
                .sum();
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
                .mapToInt(e -> e.computeGearRatioForElement(rowIndex, rowOfEngineElements))
                .sum();
    }
}
