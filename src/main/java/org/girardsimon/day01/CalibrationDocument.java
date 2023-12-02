package org.girardsimon.day01;

import java.util.List;

public record CalibrationDocument(List<Integer> calibrationValues) {
    public CalibrationDocument {
        calibrationValues = List.copyOf(calibrationValues);
    }

    public int sumCalibrationValues() {
        return calibrationValues.stream().mapToInt(Integer::intValue).sum();
    }
}
