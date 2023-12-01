package org.girardsimon.day01;

import java.util.List;

public interface CalibrationDocumentParser {
    CalibrationDocument parseCalibrationDocument(List<String> lines);
}
