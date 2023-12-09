package org.girardsimon.day07;

import org.girardsimon.common.SpelledOutIntegers;

import java.util.List;
import java.util.function.BiFunction;

import static org.girardsimon.common.Patterns.POSITIVE_NUMBER_PATTERN;

public final class CamelCardsParser {
    private CamelCardsParser() {
    }

    public static CamelCardsGame parseGame(List<String> lines) {
        List<CamelCardHand> camelCardHandDefaults = lines.stream()
                .map(line -> createCamelCardHand(line, CamelCardHandDefault::new))
                .toList();
        return new CamelCardsGame(camelCardHandDefaults);
    }
    public static CamelCardsGame parseGameJoker(List<String> lines) {
        List<CamelCardHand> camelCardHandDefaults = lines.stream()
                .map(line -> createCamelCardHand(line, CamelCardHandJoker::new))
                .toList();
        return new CamelCardsGame(camelCardHandDefaults);
    }
    private static CamelCardHand createCamelCardHand(String line,
             BiFunction<List<CamelCard>, Integer, CamelCardHand> handFactory) {
        String[] splitLine = line.split(" ");
        List<CamelCard> camelCards = parseCamelCards(splitLine[0]);
        int bid = Integer.parseInt(splitLine[1]);
        return handFactory.apply(camelCards, bid);
    }
    private static List<CamelCard> parseCamelCards(String input) {
        return input.codePoints()
                .mapToObj(c -> String.valueOf((char) c))
                .map(CamelCardsParser::mapCharToCamelCard)
                .toList();
    }
    private static CamelCard mapCharToCamelCard(String singleCharacterString) {
        return POSITIVE_NUMBER_PATTERN.matcher(singleCharacterString).find() ?
                CamelCard.valueOf(
                        SpelledOutIntegers.getSpelledOutIntegerByValue(
                        Integer.parseInt(singleCharacterString)).toString()) :
                CamelCard.valueOf(singleCharacterString);
    }

}
