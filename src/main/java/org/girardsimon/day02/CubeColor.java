package org.girardsimon.day02;

import java.util.regex.Pattern;

import static org.girardsimon.day02.CubePatterns.BLUE_CUBES_REGEXP;
import static org.girardsimon.day02.CubePatterns.GREEN_CUBES_REGEXP;
import static org.girardsimon.day02.CubePatterns.RED_CUBES_REGEXP;

public enum CubeColor {
    RED {
        @Override
        public Pattern regexpPattern() {
            return RED_CUBES_REGEXP;
        }
    }, GREEN {
        @Override
        public Pattern regexpPattern() {
            return GREEN_CUBES_REGEXP;
        }
    }, BLUE {
        @Override
        public Pattern regexpPattern() {
            return BLUE_CUBES_REGEXP;
        }
    };

    public abstract Pattern regexpPattern();
}
