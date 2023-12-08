package org.girardsimon.common;

import java.util.regex.Pattern;

public final class Patterns {
    public static final Pattern NUMBER_PATTERN = Pattern.compile("\\d+");
    public static final Pattern ALPHABETIC_PATTERN = Pattern.compile("\\p{IsAlphabetic}+");
    private Patterns() {
    }
}
