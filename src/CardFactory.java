import java.util.HashMap;
import java.util.Map;

/**
 * Description: In charge of each operation related to Card.
 * Statically possesses two maps that map the input string to Rank and Suit,
 * preventing Card from handling mapping tasks each time it's instantiated.
 *
 * @Author: Jize Dong
 * @LoginID: jized
 * @Date: 2017-09-27
 * @Time: 23:52
 */
public class CardFactory {

    private static Map<String, Rank> rankMap = new HashMap<>();
    private static Map<String, Suit> suitMap = new HashMap<>();

    static {
        // Maps input string to Rank enum
        rankMap.put("2", Rank.TWO);
        rankMap.put("3", Rank.THREE);
        rankMap.put("4", Rank.FOUR);
        rankMap.put("5", Rank.FIVE);
        rankMap.put("6", Rank.SIX);
        rankMap.put("7", Rank.SEVEN);
        rankMap.put("8", Rank.EIGHT);
        rankMap.put("9", Rank.NINE);
        rankMap.put("T", Rank.TEN);
        rankMap.put("J", Rank.JACK);
        rankMap.put("Q", Rank.QUEEN);
        rankMap.put("K", Rank.KING);
        rankMap.put("A", Rank.ACE);

        // Maps input string to Suit enum
        suitMap.put("C", Suit.CLUB);
        suitMap.put("D", Suit.DIAMOND);
        suitMap.put("H", Suit.HEART);
        suitMap.put("S", Suit.SPADE);

    }

    public static Rank rank(String key) {
        if (!rankMap.containsKey(key)) {
            return null;
        }
        return rankMap.get(key);
    }

    public static Suit suit(String key) {
        if (!suitMap.containsKey(key)) {
            return null;
        }
        return suitMap.get(key);
    }

    public static Card createCard(String s) {
        return new Card(s);
    }

}
