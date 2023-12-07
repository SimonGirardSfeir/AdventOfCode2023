package org.girardsimon.day05;

import org.girardsimon.common.Range;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/*
    This warning advocates the implementation of a builder pattern,
    which is inappropriate in this context. All fields in this scenario are required,
    indicating that there's no variability in object construction that would
    necessitate a builder pattern.
 */
@SuppressWarnings("ConstructorWithTooManyParameters")
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
        return  mapElementsOverlappingWithSeedRange.isEmpty() ? List.of(seedRange) :
        processSeedRangesWhenThereAreRangeToProcess(seedRange, mapElementsOverlappingWithSeedRange);
    }
    private static List<Range<Long>> processSeedRangesWhenThereAreRangeToProcess(
            Range<Long> seedRange, List<MapElement> mapElementsOverlappingWithSeedRange) {
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
        List<Range<Long>> rangesFromMapElements = mapElements.stream()
                .map(MapElement::sourceRange)
                .toList();
        long startOfSeedRange = seedRange.start();
        long startOfMapElementRange = rangesFromMapElements.getFirst().start();
        for (Range<Long>  rangesFromMapElement : rangesFromMapElements) {
            if (startOfSeedRange < startOfMapElementRange) {
                seedRangesExcludedForProcess.add(new Range<>(startOfSeedRange, startOfMapElementRange - 1));
            }
            startOfSeedRange = rangesFromMapElement.end() + 1;
            startOfMapElementRange = rangesFromMapElements
                    .indexOf(rangesFromMapElement) < rangesFromMapElements.size() - 1 ?
                    rangesFromMapElements.get(rangesFromMapElements.indexOf(rangesFromMapElement) + 1).start()
                    : seedRange.end();
        }
        return seedRangesExcludedForProcess;
    }
}
