package org.girardsimon.day05;

import org.girardsimon.common.Range;

import java.util.List;

final class TestUtils {
    private TestUtils() {
    }

    static Garden generateGarden() {
        MapElement toSoilElement1 = new MapElement(50, new Range<>(98L, 99L));
        MapElement toSoilElement2 = new MapElement(52, new Range<>(50L, 97L));
        List<MapElement> toSoilElements = List.of(toSoilElement1, toSoilElement2);
        MapElement toFertilizerElement1 = new MapElement(0, new Range<>(15L, 51L));
        MapElement toFertilizerElement2 = new MapElement(37, new Range<>(52L, 53L));
        MapElement toFertilizerElement3 = new MapElement(39, new Range<>(0L, 14L));
        List<MapElement> toFertilizerElements = List.of(toFertilizerElement1, toFertilizerElement2, toFertilizerElement3);
        MapElement toWaterElement1 = new MapElement(49, new Range<>(53L, 60L));
        MapElement toWaterElement2 = new MapElement(0, new Range<>(11L, 52L));
        MapElement toWaterElement3 = new MapElement(42, new Range<>(0L, 6L));
        MapElement toWaterElement4 = new MapElement(57, new Range<>(7L, 10L));
        List<MapElement> toWaterElements = List.of(toWaterElement1, toWaterElement2, toWaterElement3, toWaterElement4);
        MapElement toLightElement1 = new MapElement(88, new Range<>(18L, 24L));
        MapElement toLightElement2 = new MapElement(18, new Range<>(25L, 94L));
        List<MapElement> toLightElements = List.of(toLightElement1, toLightElement2);
        MapElement toTemperatureElement1 = new MapElement(45, new Range<>(77L, 99L));
        MapElement toTemperatureElement2 = new MapElement(81, new Range<>(45L, 63L));
        MapElement toTemperatureElement3 = new MapElement(68, new Range<>(64L, 76L));
        List<MapElement> toTemperatureElements = List.of(toTemperatureElement1, toTemperatureElement2, toTemperatureElement3);
        MapElement toHumidityElement1 = new MapElement(0, new Range<>(69L, 69L));
        MapElement toHumidityElement2 = new MapElement(1, new Range<>(0L, 68L));
        List<MapElement> toHumidityElements = List.of(toHumidityElement1, toHumidityElement2);
        MapElement toLocationElement1 = new MapElement(60, new Range<>(56L, 92L));
        MapElement toLocationElement2 = new MapElement(56, new Range<>(93L, 96L));
        List<MapElement> toLocationElements = List.of(toLocationElement1, toLocationElement2);
        return new Garden(toSoilElements, toFertilizerElements, toWaterElements, toLightElements, toTemperatureElements,
                toHumidityElements, toLocationElements);
    }
}
