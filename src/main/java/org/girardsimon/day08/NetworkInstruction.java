package org.girardsimon.day08;

import java.util.HashMap;
import java.util.Map;

public enum NetworkInstruction {
    LEFT, RIGHT;

    private static final Map<Character, NetworkInstruction> BY_ABBREVIATION = new HashMap<>();

    static {
        for (NetworkInstruction networkInstruction : values()) {
            BY_ABBREVIATION.put(networkInstruction.toString().charAt(0), networkInstruction);
        }
    }
    public static NetworkInstruction byAbbreviation(char abbreviation) {
        return BY_ABBREVIATION.get(abbreviation);
    }
}
