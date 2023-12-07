package org.girardsimon.day07;

import java.util.List;

import static org.girardsimon.day07.CamelCard.A;
import static org.girardsimon.day07.CamelCard.FIVE;
import static org.girardsimon.day07.CamelCard.J;
import static org.girardsimon.day07.CamelCard.K;
import static org.girardsimon.day07.CamelCard.Q;
import static org.girardsimon.day07.CamelCard.SEVEN;
import static org.girardsimon.day07.CamelCard.SIX;
import static org.girardsimon.day07.CamelCard.T;
import static org.girardsimon.day07.CamelCard.THREE;
import static org.girardsimon.day07.CamelCard.TWO;

final class TestUtils {
    private TestUtils() {
    }
    static CamelCardsGame generateGame() {
        CamelCardHandDefault camelCardHandDefault1 = new CamelCardHandDefault(List.of(THREE, TWO, T, THREE, K), 765);
        CamelCardHandDefault camelCardHandDefault2 = new CamelCardHandDefault(List.of(T, FIVE, FIVE, J, FIVE), 684);
        CamelCardHandDefault camelCardHandDefault3 = new CamelCardHandDefault(List.of(K, K, SIX, SEVEN, SEVEN), 28);
        CamelCardHandDefault camelCardHandDefault4 = new CamelCardHandDefault(List.of(K, T, J, J, T), 220);
        CamelCardHandDefault camelCardHandDefault5 = new CamelCardHandDefault(List.of(Q, Q, Q, J, A), 483);
        return new CamelCardsGame(List.of(camelCardHandDefault1, camelCardHandDefault2, camelCardHandDefault3, camelCardHandDefault4, camelCardHandDefault5));
    }
    static CamelCardsGame generateGameJoker() {
        CamelCardHandJoker camelCardHandJoker1 = new CamelCardHandJoker(List.of(THREE, TWO, T, THREE, K), 765);
        CamelCardHandJoker camelCardHandJoker2 = new CamelCardHandJoker(List.of(T, FIVE, FIVE, J, FIVE), 684);
        CamelCardHandJoker camelCardHandJoker3 = new CamelCardHandJoker(List.of(K, K, SIX, SEVEN, SEVEN), 28);
        CamelCardHandJoker camelCardHandJoker4 = new CamelCardHandJoker(List.of(K, T, J, J, T), 220);
        CamelCardHandJoker camelCardHandJoker5 = new CamelCardHandJoker(List.of(Q, Q, Q, J, A), 483);
        return new CamelCardsGame(List.of(camelCardHandJoker1, camelCardHandJoker2, camelCardHandJoker3, camelCardHandJoker4, camelCardHandJoker5));
    }
}
