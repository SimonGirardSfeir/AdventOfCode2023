package org.girardsimon.day08;

import java.util.List;
import java.util.Map;

import static org.girardsimon.day08.NetworkInstruction.LEFT;
import static org.girardsimon.day08.NetworkInstruction.RIGHT;

final class TestUtils {
    private TestUtils() {
    }
    static Network generateNetwork() {
        List<NetworkInstruction> instructions = List.of(RIGHT, LEFT);
        Map.Entry<String, NodeElement> node1 = Map.entry("AAA", new NodeElement("BBB", "CCC"));
        Map.Entry<String, NodeElement> node2 = Map.entry("BBB", new NodeElement("DDD", "EEE"));
        Map.Entry<String, NodeElement> node3 = Map.entry("CCC", new NodeElement("ZZZ", "GGG"));
        Map.Entry<String, NodeElement> node4 = Map.entry("DDD", new NodeElement("DDD", "DDD"));
        Map.Entry<String, NodeElement> node5 = Map.entry("EEE", new NodeElement("EEE", "EEE"));
        Map.Entry<String, NodeElement> node6 = Map.entry("GGG", new NodeElement("GGG", "GGG"));
        Map.Entry<String, NodeElement> node7 = Map.entry("ZZZ", new NodeElement("ZZZ", "ZZZ"));
        Map<String, NodeElement> nodes = Map.ofEntries(node1, node2, node3, node4, node5, node6, node7);
        return new Network(instructions, nodes);
    }
    static Network generateNetworkWithLessInstructionThatExpectedStepsToReachTarget() {
        List<NetworkInstruction> instructions = List.of(LEFT, LEFT, RIGHT);
        Map.Entry<String, NodeElement> node1 = Map.entry("AAA", new NodeElement("BBB", "BBB"));
        Map.Entry<String, NodeElement> node2 = Map.entry("BBB", new NodeElement("AAA", "ZZZ"));
        Map.Entry<String, NodeElement> node3 = Map.entry("ZZZ", new NodeElement("ZZZ", "ZZZ"));
        Map<String, NodeElement> nodes = Map.ofEntries(node1, node2, node3);
        return new Network(instructions, nodes);
    }
    static Network generateNetworkForGhost() {
        List<NetworkInstruction> instructions = List.of(LEFT, RIGHT);
        Map.Entry<String, NodeElement> node1 = Map.entry("11A", new NodeElement("11B", "XXX"));
        Map.Entry<String, NodeElement> node2 = Map.entry("11B", new NodeElement("XXX", "11Z"));
        Map.Entry<String, NodeElement> node3 = Map.entry("11Z", new NodeElement("11B", "XXX"));
        Map.Entry<String, NodeElement> node4 = Map.entry("22A", new NodeElement("22B", "XXX"));
        Map.Entry<String, NodeElement> node5 = Map.entry("22B", new NodeElement("22C", "22C"));
        Map.Entry<String, NodeElement> node6 = Map.entry("22C", new NodeElement("22Z", "22Z"));
        Map.Entry<String, NodeElement> node7 = Map.entry("22Z", new NodeElement("22B", "22B"));
        Map.Entry<String, NodeElement> node8 = Map.entry("XXX", new NodeElement("XXX", "XXX"));
        Map<String, NodeElement> nodes = Map.ofEntries(node1, node2, node3, node4, node5, node6, node7, node8);
        return new Network(instructions, nodes);
    }
}
