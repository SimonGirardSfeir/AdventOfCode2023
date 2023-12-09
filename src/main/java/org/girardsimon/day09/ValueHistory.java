package org.girardsimon.day09;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public record ValueHistory(List<Long> historyOfValues) {
    public ValueHistory {
        // The constructor only takes the historyOfValues to be extended as its input
        historyOfValues = List.copyOf(extendHistoryWithNeighborValues(historyOfValues));
    }

    public long nextValueOfHistory() {
        return historyOfValues.getLast();
    }
    public long previousValueOfHistory() {
        return historyOfValues.getFirst();
    }
    private static List<Long> extendHistoryWithNeighborValues(List<Long> historyOfValues) {
        List<Long> currentSequenceOfDifferences = new LinkedList<>(historyOfValues);
        List<List<Long>> allSequencesOfDifferences = new ArrayList<>();
        allSequencesOfDifferences.add(currentSequenceOfDifferences);
        while (isThereAnyValueDifferentThanZero(currentSequenceOfDifferences)) {
            currentSequenceOfDifferences = sequenceOfDifferences(currentSequenceOfDifferences);
            allSequencesOfDifferences.add(currentSequenceOfDifferences);
        }
        return updateHistoryWithSequences(currentSequenceOfDifferences, allSequencesOfDifferences);
    }
    private static List<Long> sequenceOfDifferences(List<Long> currentSequenceOfDifferences) {
        return IntStream.range(0, currentSequenceOfDifferences.size() - 1)
                .mapToObj(i -> currentSequenceOfDifferences.get(i + 1) - currentSequenceOfDifferences.get(i))
                .collect(Collectors.toCollection(LinkedList::new));
    }
    private static List<Long> updateHistoryWithSequences(List<Long> currentValues,
                                                         List<List<Long>> allSequencesOfDifferences) {
        currentValues.addLast(0L);
        currentValues.addFirst(0L);
        IntStream.range(1, allSequencesOfDifferences.size())
                .map(i -> allSequencesOfDifferences.size() - i)
                .forEach( i -> updateEachSequenceOfDifferences(allSequencesOfDifferences, i));
        return allSequencesOfDifferences.getFirst();
    }
    private static void updateEachSequenceOfDifferences(List<List<Long>> allSequencesOfDifferences, int i) {
        List<Long> sequenceToBeUpdated = allSequencesOfDifferences.get(i -1);
        List<Long> previousSequenceOfDifferences = allSequencesOfDifferences.get(i);
        sequenceToBeUpdated
                .addLast(previousSequenceOfDifferences.getLast() + sequenceToBeUpdated.getLast());
        sequenceToBeUpdated
                .addFirst(sequenceToBeUpdated.getFirst() - previousSequenceOfDifferences.getFirst());
    }
    private static boolean isThereAnyValueDifferentThanZero(List<Long> values) {
        return values.stream().anyMatch(value -> 0L != value);
    }
}
