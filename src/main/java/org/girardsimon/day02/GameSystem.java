package org.girardsimon.day02;

import java.util.List;

public record GameSystem(List<Game> games) {
    public int sumGamesIdOfPossibleGame(int redCubes, int greenCubes, int blueCubes) {
        return games.stream()
                .filter(game -> game.canPlayWithSuppliedCubes(redCubes, greenCubes, blueCubes))
                .mapToInt(Game::id)
                .sum();
    }
    public int computeGameSystemPower() {
        return games.stream()
                .mapToInt(Game::gamePower)
                .sum();
    }
}
