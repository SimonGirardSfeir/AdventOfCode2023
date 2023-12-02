package org.girardsimon.day02;

import java.util.List;
import java.util.OptionalInt;

public record Game(int id, List<SetOfCubes> setOfCubesList) {
    public Game {
        setOfCubesList = List.copyOf(setOfCubesList);
    }

    public boolean canPlayWithSuppliedCubes(int redCubes, int greenCubes, int blueCubes) {
        return setOfCubesList().stream()
                .noneMatch(setOfCube -> setOfCube.isSetImpossible(redCubes, greenCubes, blueCubes));
    }
    public int gamePower() {
        OptionalInt minRed = setOfCubesList().stream().mapToInt(SetOfCubes::redCubes).max();
        OptionalInt minGreen = setOfCubesList().stream().mapToInt(SetOfCubes::greenCubes).max();
        OptionalInt minBlue = setOfCubesList().stream().mapToInt(SetOfCubes::blueCubes).max();
        return minRed.orElse(0) * minGreen.orElse(0) * minBlue.orElse(0);
    }
}
