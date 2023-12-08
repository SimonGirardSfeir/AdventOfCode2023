package org.girardsimon.day08;

import static org.girardsimon.day08.NetworkInstruction.LEFT;

public record NodeElement(String left, String right) {
    public String getKeyFromInstruction(NetworkInstruction instruction) {
        return LEFT == instruction ? left : right;
    }
}
