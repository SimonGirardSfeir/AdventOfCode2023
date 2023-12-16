package org.girardsimon.day12;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public record RowOfSprings(String content, List<Integer> groupsOfDamaged) {

    public long numberOfPossibleArrangements() {
        return countPatterns(0,0,0, new HashMap<>());
    }
    private long countPatterns(int indexStartingCurrentGroupOfDamaged,
                                      int indexCurrentSpring,
                                      int currentGroupOfDamagedIndex,
                              Map<State3Tuple, Long> cache) {

        return indexCurrentSpring == content.length() ?
                countPatternsWhenThereIsNoSpringLeft(indexStartingCurrentGroupOfDamaged,
                indexCurrentSpring, currentGroupOfDamagedIndex) :
                countPatternFromSpring(indexStartingCurrentGroupOfDamaged,
                        indexCurrentSpring, currentGroupOfDamagedIndex, cache);
    }
    private int countPatternsWhenThereIsNoSpringLeft(int indexStartingCurrentGroupOfDamaged,
                                                     int indexCurrentSpring, int currentGroupOfDamagedIndex) {
        return isPatternValid(indexStartingCurrentGroupOfDamaged,
                indexCurrentSpring,
                currentGroupOfDamagedIndex) ? 1 : 0;
    }
    private long countPatternFromSpring(int indexStartingCurrentGroupOfDamaged,
                                        int indexCurrentSpring,
                                        int currentGroupOfDamagedIndex,
                                        Map<State3Tuple, Long> cache) {

        State3Tuple currentState = new State3Tuple(indexStartingCurrentGroupOfDamaged, indexCurrentSpring,
                currentGroupOfDamagedIndex);
        return cache.containsKey(currentState) ? cache.get(currentState) :
                countPatternFromSpringFirstTime(cache, currentState);

    }

    private long countPatternFromSpringFirstTime(Map<State3Tuple, Long> cache, State3Tuple currentState) {
        int indexCurrentSpring = currentState.indexCurrentSpring();
        int indexStartingCurrentGroupOfDamaged = currentState.indexStartingCurrentGroupOfDamaged();
        int currentGroupOfDamagedIndex = currentState.currentGroupOfDamagedIndex();
        char currentSpring = content.charAt(indexCurrentSpring);
        if ('#' == currentSpring) {
            long result = countPatterns(indexStartingCurrentGroupOfDamaged,
                    indexCurrentSpring + 1, currentGroupOfDamagedIndex, cache);
            cache.put(currentState, result);
            return result;
        } else if ('.' == currentSpring) {
            if (indexStartingCurrentGroupOfDamaged != indexCurrentSpring &&
                    isStateInconsistentOrCountingOver(currentState)) {
                return 0;
            }
            int groupOfDamagedIndex = indexStartingCurrentGroupOfDamaged == indexCurrentSpring ?
                    currentGroupOfDamagedIndex :
                    (currentGroupOfDamagedIndex + 1);
            long result = countPatterns(indexCurrentSpring + 1,
                    indexCurrentSpring + 1, groupOfDamagedIndex, cache);
            cache.put(currentState, result);
            return result;
        } else {
            long result = countPatterns(indexStartingCurrentGroupOfDamaged,
                    indexCurrentSpring + 1, currentGroupOfDamagedIndex, cache);
            if (indexStartingCurrentGroupOfDamaged != indexCurrentSpring &&
                    isStateInconsistentOrCountingOver(currentState)) {
                    cache.put(currentState, result);
                return result;
            }
            int groupOfDamagedIndex = indexStartingCurrentGroupOfDamaged == indexCurrentSpring ?
                    currentGroupOfDamagedIndex :
                    (currentGroupOfDamagedIndex + 1);
            result += countPatterns(indexCurrentSpring + 1,
                    indexCurrentSpring + 1, groupOfDamagedIndex, cache);
            cache.put(currentState, result);
            return result;
        }
    }

    private boolean isStateInconsistentOrCountingOver(State3Tuple state) {
        return state.currentGroupOfDamagedIndex() == groupsOfDamaged.size() ||
                groupsOfDamaged.get(state.currentGroupOfDamagedIndex()) !=
                        state.indexCurrentSpring() - state.indexStartingCurrentGroupOfDamaged();
    }
    private boolean isPatternValid(int indexStartingCurrentGroupOfDamaged,
                                   int indexCurrentSpring, int currentGroupOfDamagedIndex) {
        return isThereNoGroupOfDamagedSpring(indexStartingCurrentGroupOfDamaged, indexCurrentSpring,
                currentGroupOfDamagedIndex) ||
                isLastGroupOfDamagedSpringJustPassed(indexStartingCurrentGroupOfDamaged,
                        indexCurrentSpring, currentGroupOfDamagedIndex);
    }
    private boolean isLastGroupOfDamagedSpringJustPassed(int indexStartingCurrentGroupOfDamaged,
                                                         int indexCurrentSpring,
                                                         int currentGroupOfDamagedIndex) {
        return groupsOfDamaged.size() - 1 == currentGroupOfDamagedIndex
                && indexCurrentSpring - indexStartingCurrentGroupOfDamaged ==
                groupsOfDamaged.get(currentGroupOfDamagedIndex);
    }
    private  boolean isThereNoGroupOfDamagedSpring(int indexStartingCurrentGroupOfDamaged, int indexCurrentSpring,
                                                   int currentGroupOfDamagedIndex) {
        return indexStartingCurrentGroupOfDamaged == indexCurrentSpring &&
                groupsOfDamaged.size() == currentGroupOfDamagedIndex;
    }
    private record State3Tuple(int indexStartingCurrentGroupOfDamaged, int indexCurrentSpring,
                               int currentGroupOfDamagedIndex) implements Comparable<State3Tuple> {
        @Override
        public int compareTo(State3Tuple o) {
            int result = 0 == compareByStartIndex(o) ? compareByCurrentIndex(o) :
                    compareByStartIndex(o);
            return 0 == result ? compareByGroupIndex(o) : result;
        }
        private int compareByStartIndex(State3Tuple o) {
            return Integer.compare(indexStartingCurrentGroupOfDamaged, o.indexStartingCurrentGroupOfDamaged());
        }
        private int compareByCurrentIndex(State3Tuple o) {
            return Integer.compare(indexCurrentSpring, o.indexCurrentSpring());
        }
        private int compareByGroupIndex(State3Tuple o) {
            return Integer.compare(currentGroupOfDamagedIndex, o.currentGroupOfDamagedIndex());
        }
    }

    public long numberOfPossibleArrangementsUnfolded() {
        String unfoldedContent = content + '?' +
                content + '?' +
                content + '?' +
                content + '?' +
                content;
        List<Integer> unfoldedGroupsOfDamaged = new ArrayList<>(groupsOfDamaged);
        unfoldedGroupsOfDamaged.addAll(groupsOfDamaged);
        unfoldedGroupsOfDamaged.addAll(groupsOfDamaged);
        unfoldedGroupsOfDamaged.addAll(groupsOfDamaged);
        unfoldedGroupsOfDamaged.addAll(groupsOfDamaged);
        RowOfSprings rowOfSprings = new RowOfSprings(unfoldedContent, unfoldedGroupsOfDamaged);
        return  rowOfSprings.numberOfPossibleArrangements();
    }
}
