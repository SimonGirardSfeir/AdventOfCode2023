package org.girardsimon.day02;

public record SetOfCubes(int redCubes, int greenCubes, int blueCubes) {
    public boolean isSetImpossible(int redCubes, int greenCubes, int blueCubes) {
        return redCubes < this.redCubes || greenCubes < this.greenCubes || blueCubes < this.blueCubes;
    }
}
