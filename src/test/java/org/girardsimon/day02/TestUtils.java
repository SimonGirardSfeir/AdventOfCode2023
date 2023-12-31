package org.girardsimon.day02;

import java.util.List;

final class TestUtils {
    private TestUtils() {
    }
    static GameSystem buildGameSystem() {
        SetOfCubes setOfCubes1 = new SetOfCubes(4,0,3);
        SetOfCubes setOfCubes2 = new SetOfCubes(1,2,6);
        SetOfCubes setOfCubes3 = new SetOfCubes(0,2,0);
        Game game1 = new Game(1, List.of(setOfCubes1, setOfCubes2, setOfCubes3));
        SetOfCubes setOfCubes4 = new SetOfCubes(0,2,1);
        SetOfCubes setOfCubes5 = new SetOfCubes(1,3,4);
        SetOfCubes setOfCubes6 = new SetOfCubes(0,1,1);
        Game game2 = new Game(2, List.of(setOfCubes4, setOfCubes5, setOfCubes6));
        SetOfCubes setOfCubes7 = new SetOfCubes(20,8,6);
        SetOfCubes setOfCubes8 = new SetOfCubes(4,13,5);
        SetOfCubes setOfCubes9 = new SetOfCubes(1,5,0);
        Game game3 = new Game(3, List.of(setOfCubes7, setOfCubes8, setOfCubes9));
        SetOfCubes setOfCubes10 = new SetOfCubes(3,1,6);
        SetOfCubes setOfCubes11 = new SetOfCubes(6,3,0);
        SetOfCubes setOfCubes12 = new SetOfCubes(14,3,15);
        Game game4 = new Game(4, List.of(setOfCubes10, setOfCubes11, setOfCubes12));
        SetOfCubes setOfCubes13 = new SetOfCubes(6,3,1);
        SetOfCubes setOfCubes14 = new SetOfCubes(1,2,2);
        Game game5 = new Game(5, List.of(setOfCubes13, setOfCubes14));
        return new GameSystem(List.of(game1, game2, game3, game4, game5));
    }
}
