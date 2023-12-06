package org.girardsimon.day05;

import org.girardsimon.common.Range;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public record Garden(List<MapElement> toSoilElements, List<MapElement> toFertilizerElements,
                     List<MapElement> toWaterElements, List<MapElement> toLightElements,
                     List<MapElement> toTemperatureElements, List<MapElement> toHumidityElements,
                     List<MapElement> toLocationElements) {
    public Garden {
        toSoilElements = List.copyOf(toSoilElements);
        toFertilizerElements = List.copyOf(toFertilizerElements);
        toWaterElements = List.copyOf(toWaterElements);
        toLightElements = List.copyOf(toLightElements);
        toTemperatureElements = List.copyOf(toTemperatureElements);
        toHumidityElements = List.copyOf(toHumidityElements);
        toLocationElements = List.copyOf(toLocationElements);
    }

    public long lowestLocation(List<Long> seeds) {
        return seeds.stream()
                .map(s -> processSeed(s, toSoilElements))
                .map(s -> processSeed(s, toFertilizerElements))
                .map(s -> processSeed(s, toWaterElements))
                .map(s -> processSeed(s, toLightElements))
                .map(s -> processSeed(s, toTemperatureElements))
                .map(s -> processSeed(s, toHumidityElements))
                .map(s -> processSeed(s, toLocationElements))
                .mapToLong(Long::longValue)
                .min()
                .orElse(0L);
    }
    private static Long processSeed(Long seed, List<MapElement> elements) {
        Optional<MapElement> mapElement = elements.stream()
                .filter(m -> m.isSeedProcessable(seed)).findFirst();
        return mapElement.map(element -> element.processSeed(seed)).orElse(seed);
    }
    public long lowestLocationRangeInput(List<Range<Long>> seedsRanges) {
        return seedsRanges.stream()
                .map(s -> processSeedsRanges(s, toSoilElements))
                .flatMap(List::stream)
                .map(s -> processSeedsRanges(s, toFertilizerElements))
                .flatMap(List::stream)
                .map(s -> processSeedsRanges(s, toWaterElements))
                .flatMap(List::stream)
                .map(s -> processSeedsRanges(s, toLightElements))
                .flatMap(List::stream)
                .map(s -> processSeedsRanges(s, toTemperatureElements))
                .flatMap(List::stream)
                .map(s -> processSeedsRanges(s, toHumidityElements))
                .flatMap(List::stream)
                .map(s -> processSeedsRanges(s, toLocationElements))
                .flatMap(List::stream)
                .mapToLong(Range::start)
                .min()
                .orElse(0L);
    }

    private static List<Range<Long>> processSeedsRanges(Range<Long> seedRange, List<MapElement> elements) {
        List<MapElement> mapElementsOverlappingWithSeedRange = elements.stream()
                .filter(e -> e.isOverlappingWithMapElement(seedRange))
                .sorted(MapElement::compareTo)
                .toList();
        if(mapElementsOverlappingWithSeedRange.isEmpty()) {
            return List.of(seedRange);
        }

        List<Range<Long>> seedRanges = new ArrayList<>();
        seedRanges.addAll(getSeedRangesExcludedForProcess(seedRange, mapElementsOverlappingWithSeedRange));
        seedRanges.addAll(mapElementsOverlappingWithSeedRange.stream()
                .map(element -> element.processRange(seedRange))
                .toList());
        return seedRanges;
    }
    private static List<Range<Long>> getSeedRangesExcludedForProcess(Range<Long> seedRange,
                                                                   List<MapElement> mapElements) {
        List<Range<Long>> seedRangesExcludedForProcess = new ArrayList<>();
        long startOfSeedRange = seedRange.start();
        long startOfMapElementRange = mapElements.getFirst().sourceRange().start();
        for (MapElement mapElement : mapElements) {
            if (startOfSeedRange < startOfMapElementRange) {
                seedRangesExcludedForProcess.add(new Range<>(startOfSeedRange, startOfMapElementRange - 1));
            }
            startOfSeedRange = mapElement.sourceRange().end() + 1;
            startOfMapElementRange = mapElements.indexOf(mapElement) < mapElements.size() - 1 ?
                    mapElements.get(mapElements.indexOf(mapElement) + 1).sourceRange().start()
                    : seedRange.end();
        }
        return seedRangesExcludedForProcess;
    }
}
