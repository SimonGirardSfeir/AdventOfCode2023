package org.girardsimon.day10;

@FunctionalInterface
public interface PipeFilter {
    boolean test(Pipe pipe, int delta);
}
