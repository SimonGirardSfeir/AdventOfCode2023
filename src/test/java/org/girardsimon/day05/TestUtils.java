package org.girardsimon.day05;

import java.util.List;

class TestUtils {
    static Garden generateGarden() {
        MapElement toSoilElement1 = new MapElement(50, 98, 2);
        MapElement toSoilElement2 = new MapElement(52, 50, 48);
        List<MapElement> toSoilElements = List.of(toSoilElement1, toSoilElement2);
        MapElement toFertilizerElement1 = new MapElement(0, 15, 37);
        MapElement toFertilizerElement2 = new MapElement(37, 52, 2);
        MapElement toFertilizerElement3 = new MapElement(39, 0, 15);
        List<MapElement> toFertilizerElements = List.of(toFertilizerElement1, toFertilizerElement2, toFertilizerElement3);
        MapElement toWaterElement1 = new MapElement(49, 53, 8);
        MapElement toWaterElement2 = new MapElement(0, 11, 42);
        MapElement toWaterElement3 = new MapElement(42, 0, 7);
        MapElement toWaterElement4 = new MapElement(57, 7, 4);
        List<MapElement> toWaterElements = List.of(toWaterElement1, toWaterElement2, toWaterElement3, toWaterElement4);
        MapElement toLightElement1 = new MapElement(88, 18, 7);
        MapElement toLightElement2 = new MapElement(18, 25, 70);
        List<MapElement> toLightElements = List.of(toLightElement1, toLightElement2);
        MapElement toTemperatureElement1 = new MapElement(45, 77, 23);
        MapElement toTemperatureElement2 = new MapElement(81, 45, 19);
        MapElement toTemperatureElement3 = new MapElement(68, 64, 13);
        List<MapElement> toTemperatureElements = List.of(toTemperatureElement1, toTemperatureElement2, toTemperatureElement3);
        MapElement toHumidityElement1 = new MapElement(0, 69, 1);
        MapElement toHumidityElement2 = new MapElement(1, 0, 69);
        List<MapElement> toHumidityElements = List.of(toHumidityElement1, toHumidityElement2);
        MapElement toLocationElement1 = new MapElement(60, 56, 37);
        MapElement toLocationElement2 = new MapElement(56, 93, 4);
        List<MapElement> toLocationElements = List.of(toLocationElement1, toLocationElement2);
        return new Garden(toSoilElements, toFertilizerElements, toWaterElements, toLightElements, toTemperatureElements,
                toHumidityElements, toLocationElements);
    }
}
