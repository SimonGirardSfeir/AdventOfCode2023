package org.girardsimon.day06;

/*
    Use of magic numbers in case of resolving quadratic equations is not an issue to my opinion.
 */
@SuppressWarnings("java:S109")
public record Race(long timeInMilliseconds, long recordDistanceInMillimeters) {
    /*
        Basically, we just need to solve the following quadratic equation.
        x here is the time needed to hold the button for beating the recordDistanceInMillimeters
        x² - timeInMilliseconds*x + recordDistanceInMillimeters = 0
        and return the gap between the two solutions
     */
    public long numberOfWaysToBeatRecord() {
        double solution1 = (timeInMilliseconds - discriminant()) / 2.0;
        double solution2 = (timeInMilliseconds + discriminant()) / 2.0;

        long minimumTimeHoldingButtonNeededForBeatingRecord = (long)Math.floor(solution1);
        long maximumTimeHoldingButtonNeededForBeatingRecord = (long)solution2;

        if (isAnInteger(solution1)) {
            minimumTimeHoldingButtonNeededForBeatingRecord++;
        }

        return maximumTimeHoldingButtonNeededForBeatingRecord - minimumTimeHoldingButtonNeededForBeatingRecord;
    }
    private static boolean isAnInteger(double leftBoundary) {
        return Double.isFinite(leftBoundary) &&
                0 == Double.compare(leftBoundary, StrictMath.rint(leftBoundary));
    }
    private double discriminant() {
        return Math.sqrt(Math.pow(timeInMilliseconds, 2) - 4 * recordDistanceInMillimeters);
    }
}
