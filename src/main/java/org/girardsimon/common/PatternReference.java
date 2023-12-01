package org.girardsimon.common;

import java.util.regex.Pattern;

public final class PatternReference {
    public static final Pattern SINGLE_DIGIT_REGEX = Pattern.compile("\\d");

    private PatternReference() {
    }
}
