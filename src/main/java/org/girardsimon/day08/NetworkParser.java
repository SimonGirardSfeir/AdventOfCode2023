package org.girardsimon.day08;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.stream.Collectors;

import static org.girardsimon.common.Patterns.ALPHABETIC_PATTERN;

public final class NetworkParser {
    private NetworkParser() {
    }

    public static Network parseNetwork(List<String> lines) {
        List<NetworkInstruction> instructions = parseInstructions(lines.getFirst());
        Map<String, NodeElement> nodes = parseNodes(lines.subList(2, lines.size()));
        return new Network(instructions, nodes);
    }
    private static List<NetworkInstruction> parseInstructions(String line) {
        return line.codePoints()
                .mapToObj(c -> NetworkInstruction.byAbbreviation((char) c))
                .toList();
    }
    private static Map<String, NodeElement> parseNodes(List<String> lines) {
        return lines.stream()
                .map(NetworkParser::parseNode)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
    private static Map.Entry<String, NodeElement> parseNode(String line) {
        Matcher matcher = ALPHABETIC_PATTERN.matcher(line);
        String key = matcher.find() ? matcher.group() :  "";
        String left = matcher.find() ? matcher.group() :  "";
        String right = matcher.find() ? matcher.group() :  "";
        return Map.entry(key, new NodeElement(left,right));
    }
}

