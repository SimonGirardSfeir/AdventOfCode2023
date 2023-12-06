package org.girardsimon.day04;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;

import static org.girardsimon.common.Patterns.NUMBER_REGEX;

public final class CardParser {
    private CardParser() {
    }

    public static CardPile parse(List<String> lines) {
        List<Card> cards = lines.stream()
                .map(CardParser::parseLine)
                .toList();
        return new CardPile(cards);
    }
    private static Card parseLine(String line) {
        String[] splitLine = line.split(":");
        int idCard = parseIdCard(splitLine[0]);
        Set<Integer> winningNumber = parseNumbers(splitLine[1].split("\\|")[0]);
        Set<Integer> handNumbers = parseNumbers(splitLine[1].split("\\|")[1]);
        return new Card(idCard, winningNumber, handNumbers);
    }
    private static Set<Integer> parseNumbers(String input) {
        Matcher matcher = NUMBER_REGEX.matcher(input);
        Set<Integer> winningNumbers = new HashSet<>();
        while(matcher.find()) {
            winningNumbers.add(Integer.parseInt(matcher.group()));
        }
        return winningNumbers;
    }
    private static int parseIdCard(String input) {
        Matcher matcher = NUMBER_REGEX.matcher(input);
        return matcher.find() ? Integer.parseInt(matcher.group()) : 0;
    }
}
