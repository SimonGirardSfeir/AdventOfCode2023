package org.girardsimon.day08;

import org.girardsimon.common.Mathematics;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Predicate;
import java.util.stream.StreamSupport;

public record Network(List<NetworkInstruction> instructions, Map<String, NodeElement> nodes) {
    public Network {
        instructions = List.copyOf(instructions);
        nodes = Map.copyOf(nodes);
    }

    public long numberOfStepsToReachKey(String startingKey, String targetKey) {
        return pathLengthThroughNetwork(startingKey, isKeyNotEqualToTarget(targetKey));
    }
    private static Predicate<String> isKeyNotEqualToTarget(String target) {
        return key -> !key.contentEquals(target);
    }
    public long numberOfStepsToReachKeyForGhosts(char endingLetterForStartingKey, char endingLetterForTargetKey) {
        String target = String.valueOf(endingLetterForTargetKey);
        List<Long> keys = nodes.keySet().stream()
                .filter(e -> endingLetterForStartingKey == e.charAt(e.length()-1))
                .map(e -> pathLengthThroughNetwork(e,isLastCharacterNotEqualToTarget(target)))
                .toList();
        return Mathematics.leastCommonMultiple(keys);
    }
    private long pathLengthThroughNetwork(String key, Predicate<String> predicate) {
        return StreamSupport.stream(
                        Spliterators.spliteratorUnknownSize(
                                new KeyIteratorThroughNetwork(key, predicate),
                                Spliterator.IMMUTABLE), false)
                    .count();
    }
    private static Predicate<String> isLastCharacterNotEqualToTarget(String target) {
        return key -> key.charAt(key.length()-1) != target.charAt(0);
    }
    private class KeyIteratorThroughNetwork implements Iterator<String> {
        private final Predicate<String> predicate;
        private int counter;
        private String currentKey;

        public KeyIteratorThroughNetwork(String startingKey, Predicate<String> predicate) {
            this.counter = 0;
            this.currentKey = startingKey;
            this.predicate = predicate;
        }

        @Override
        public boolean hasNext() {
            return predicate.test(currentKey);
        }
        @Override
        public String next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            NodeElement currentNodeElement = nodes.get(currentKey);
            NetworkInstruction currentInstruction = currentInstruction(counter);
            currentKey = currentNodeElement.getKeyFromInstruction(currentInstruction);
            counter++;
            return currentKey;
        }
        private NetworkInstruction currentInstruction(int i) {
            return instructions.get(i % instructions.size());
        }
    }
}
