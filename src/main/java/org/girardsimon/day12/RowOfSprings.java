package org.girardsimon.day12;

import org.girardsimon.common.Range;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public record RowOfSprings(String content, List<Integer> groupsOfDamaged) {

    public long numberOfPossibleArrangements() {
        Set<Long> noDamagedIndexes = IntStream.range(0, content.length())
                .filter(i -> '.' == content.charAt(i))
                .mapToLong(Long::valueOf)
                .boxed()
                .collect(Collectors.toSet());
        Set<Long> damagedIndexes = IntStream.range(0, content.length())
                .filter(i -> '#' == content.charAt(i))
                .mapToLong(Long::valueOf)
                .boxed()
                .collect(Collectors.toSet());

        Map<Integer, List<Range>> allPossibleRangeForEachIndex = getAllPossibleRangeForEachIndexInIsolation(
                noDamagedIndexes);

        return findAllCombinations(allPossibleRangeForEachIndex).stream()
                .filter(ranges -> checkIfAllDamagedAreCoveredByRange(ranges, damagedIndexes) && isCorrectOrder(ranges))
                .toList()
                .size();
    }
    private Map<Integer, List<Range>> getAllPossibleRangeForEachIndexInIsolation(Set<Long> noDamagedIndexes) {
         return IntStream.range(0, groupsOfDamaged.size())
                 .boxed()
                .collect(Collectors.toMap(i -> i, i -> rangesForIndex(noDamagedIndexes, i)));
    }
    private List<Range> rangesForIndex(Set<Long> noDamagedIndexes, Integer i) {
        int minRange = IntStream.range(0, i)
                .map(groupsOfDamaged::get)
                .sum() + i;
        int maxRange = calculateMaxRange(i);
        return IntStream.rangeClosed(minRange, maxRange - groupsOfDamaged.get(i))
                .mapToObj(r -> new Range(r, (r + groupsOfDamaged.get(i) - 1)))
                .filter(r -> !r.areAnyValuesInRange(noDamagedIndexes))
                .toList();
    }
    private int calculateMaxRange(int index) {
        int toSubtract = IntStream.range(index+1, groupsOfDamaged.size())
                .map(groupsOfDamaged::get)
                .sum()+ groupsOfDamaged.size()-index-1;
        return content.length()- toSubtract;
    }
    private boolean isCorrectOrder(List<Range> ranges) {
        return IntStream.range(0, groupsOfDamaged.size())
                .noneMatch(i -> groupsOfDamaged.get(i) != ranges.get(i).length());
    }

    private static boolean checkIfAllDamagedAreCoveredByRange(List<Range> ranges, Set<Long> damagedIndexes) {
        return damagedIndexes.stream()
                .allMatch(damagedIndex -> ranges.stream().anyMatch(range -> range.isValueInRange(damagedIndex)));
    }

    private static Set<List<Range>> findAllCombinations(Map<Integer, List<Range>> allPossibleRangeForEachIndex) {
        return findCombinationsRec(new TreeMap<>(allPossibleRangeForEachIndex), new HashSet<>(), new HashSet<>());
    }

    private static Set<List<Range>> findCombinationsRec(TreeMap<Integer, List<Range>> possibleRangeMap,
                                          Set<Range> currentComb, Set<List<Range>> result) {
        if (possibleRangeMap.isEmpty()) {
            List<Range> list = currentComb.stream()
                            .sorted().toList();
            result.add(list);
            return result;
        }

        Map.Entry<Integer, List<Range>> entry = possibleRangeMap.pollFirstEntry();

        entry.getValue()
                .stream()
                .filter(range -> areAllRangesDistantToEachOther(currentComb, range))
                .forEach(range -> {
                    currentComb.add(range);
                    findCombinationsRec(new TreeMap<>(possibleRangeMap), currentComb, result);
                    currentComb.remove(range);
                });

        possibleRangeMap.put(entry.getKey(), entry.getValue());

        return result;
    }
    private static boolean areAllRangesDistantToEachOther(Set<Range> currentComb, Range toCheck) {
        return currentComb.stream()
                .allMatch(range -> range.isDistantFromRange(toCheck));
    }
}
