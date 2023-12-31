package org.girardsimon.day02;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;

import static org.girardsimon.common.Patterns.POSITIVE_NUMBER_PATTERN;

public final class GameParser {
    private GameParser() {
    }
    public static GameSystem parseGameSystem(List<String> lines) {
        List<Game> games = lines.stream().map(GameParser::parseGame).toList();
        return new GameSystem(games);
    }
    private static Game parseGame(String line) {
        String[] splitLine = line.split(":");
        int idGame = parseIdGame(splitLine[0]);
        List<SetOfCubes> setOfCubesList = parseSetOfCubesList(splitLine[1]);
        return new Game(idGame, setOfCubesList);
    }
    private static int parseIdGame(String input) {
        Matcher matcher = POSITIVE_NUMBER_PATTERN.matcher(input);
        return matcher.find() ? Integer.parseInt(matcher.group()) : 0;
    }
    private static List<SetOfCubes> parseSetOfCubesList(String setOfCubesListAsString) {
        List<SetOfCubes> setOfCubesList = new ArrayList<>();
        String[] feedSetOfCubes = setOfCubesListAsString.split(";");

        for(String setOfCubesAsString : feedSetOfCubes) {
            SetOfCubes setOfCubes = parseSetOfCubes(setOfCubesAsString);
            setOfCubesList.add(setOfCubes);
        }
        return setOfCubesList;
    }
    private static SetOfCubes parseSetOfCubes(String setOfCubesAsString) {
        int redCubes = getNumberOfCubesForGivenColor(CubeColor.RED, setOfCubesAsString);
        int greenCubes = getNumberOfCubesForGivenColor(CubeColor.GREEN, setOfCubesAsString);
        int blueCubes = getNumberOfCubesForGivenColor(CubeColor.BLUE, setOfCubesAsString);
        return new SetOfCubes(redCubes,greenCubes, blueCubes);
    }
    private static int getNumberOfCubesForGivenColor(CubeColor cubeColor, String setOfCubesAsString) {
        Matcher matcher = cubeColor.regexpPattern().matcher(setOfCubesAsString);
        return Optional.of(matcher)
                // find the pattern associated with asked color
                .filter(Matcher::find)
                .map(m -> POSITIVE_NUMBER_PATTERN.matcher(m.group()))
                // find the number of cubes associated with the color
                .filter(Matcher::find)
                .map(Matcher::group)
                .map(Integer::parseInt)
                .orElse(0);
    }
}
