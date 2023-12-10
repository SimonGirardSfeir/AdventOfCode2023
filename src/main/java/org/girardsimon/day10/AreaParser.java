package org.girardsimon.day10;

import org.girardsimon.common.Position;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class AreaParser {
    private AreaParser() {
    }

    public static Area parseArea(List<String> lines) {
        Map<Position, Pipe> positionsAndPipes = new HashMap<>();
        Position startingPosition = null;
        for(int i = 0; i < lines.getFirst().length(); i++) {
            String currentLine = lines.get(i);
            for(int j = 0; j < currentLine.length(); j++) {
                char currentChar = currentLine.charAt(j);
                if('.' != currentChar) {
                    Position currentPosition = new Position(j, currentLine.length()-1-i);
                    Pipe currentPipe = Pipe.getPipeFromChar(currentChar);
                    if(Pipe.START == currentPipe) {
                        startingPosition = currentPosition;
                    }
                    positionsAndPipes.put(currentPosition, currentPipe);
                }
            }
        }

        return new Area(positionsAndPipes, startingPosition);
    }
}
