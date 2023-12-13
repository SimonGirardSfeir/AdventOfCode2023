package org.girardsimon.day09;

import java.util.List;

public record OasisReport(List<ValueHistory> valueHistories) {
    public OasisReport {
        valueHistories = List.copyOf(valueHistories);
    }

    public long sumNextValues() {
        return valueHistories.stream()
                .mapToLong(ValueHistory::nextValueOfHistory)
                .sum();
    }
    public long sumPreviousValues() {
        return valueHistories.stream()
                .mapToLong(ValueHistory::previousValueOfHistory)
                .sum();
    }
}
