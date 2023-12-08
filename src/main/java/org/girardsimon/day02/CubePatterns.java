package org.girardsimon.day02;

import java.util.regex.Pattern;

public final class CubePatterns {
    public static final Pattern RED_CUBES_PATTERN = Pattern.compile("\\d+ red");
    public static final Pattern GREEN_CUBES_PATTERN = Pattern.compile("\\d+ green");
    public static final Pattern BLUE_CUBES_PATTERN = Pattern.compile("\\d+ blue");

    private CubePatterns() {
    }
}
