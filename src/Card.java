import java.util.HashMap;
import java.util.Map;

/*
 * Description: Card entity.
 * Each card has a rank and a suit, and only rank is to be compared.
 * There's also a priorRank field, indicating whether it has a special privilege for ranking.
 *
 * @Author: dong
 * @Date: 2017-09-15
 * @Time: 00:36
 */
public class Card implements Comparable<Card> {
    private static Map<String, Rank> rankMap;
    private static Map<String, Suit> suitMap;
    private Rank rank;
    private Suit suit;
    // If card is "n of a kind", assign n to priorRank for comparison
    private int priorRank = -1;

    static {
        mapRank();
        mapSuit();
    }

    // ============ Static methods =============

    /**
     * Map the input string to a Rank enum
     */
    private static void mapRank() {
        rankMap = new HashMap<>();
        rankMap.put("2", Rank.TWO);
        rankMap.put("3", Rank.THREE);
        rankMap.put("4", Rank.FOUR);
        rankMap.put("5", Rank.FIVE);
        rankMap.put("6", Rank.SIX);
        rankMap.put("7", Rank.SEVEN);
        rankMap.put("8", Rank.EIGHT);
        rankMap.put("9", Rank.NINE);
        rankMap.put("T", Rank.TEN);
        rankMap.put("t", Rank.TEN);
        rankMap.put("J", Rank.JACK);
        rankMap.put("j", Rank.JACK);
        rankMap.put("Q", Rank.QUEEN);
        rankMap.put("q", Rank.QUEEN);
        rankMap.put("K", Rank.KING);
        rankMap.put("k", Rank.KING);
        rankMap.put("A", Rank.ACE);
        rankMap.put("a", Rank.ACE);
    }

    /**
     * Map the input string to a Suit enum
     */
    private static void mapSuit() {
        suitMap = new HashMap<>();
        suitMap.put("C", Suit.CLUB);
        suitMap.put("c", Suit.CLUB);
        suitMap.put("D", Suit.DIAMOND);
        suitMap.put("d", Suit.DIAMOND);
        suitMap.put("H", Suit.HEART);
        suitMap.put("h", Suit.HEART);
        suitMap.put("S", Suit.SPADE);
        suitMap.put("s", Suit.SPADE);
    }

    // ============= Constructor Methods ==============

    /**
     * Constructor method
     *
     * @param s1 one-character string representing rank
     * @param s2 one-character string representing suit
     */
    public Card(String s1, String s2) {
        if ("".equals(s1) || "".equals(s2)) {
            return;
        }
        rank = rankMap.get(s1);
        suit = suitMap.get(s2);

    }

    /**
     * Constructor method
     *
     * @param s two-character string representing rank and suit
     */
    public Card(String s) {
        if ("".equals(s) || s.length() < 2) {
            return;
        }
        rank = rankMap.get(s.substring(0, 1));
        suit = suitMap.get(s.substring(1, 2));
    }

    /**
     * Constructor method (directly pass rank and suit to constructor)
     *
     * @param rank rank enum
     * @param suit suit enum
     */
    public Card(Rank rank, Suit suit) {
        if (rank == null && suit == null) {
            return;
        }
        this.rank = rank;
        this.suit = suit;
    }

    // =====================================

    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    public Suit getSuit() {
        return suit;
    }

    public void setSuit(Suit suit) {
        this.suit = suit;
    }

    public int getPriorRank() {
        return priorRank;
    }

    public void setPriorRank(int priorRank) {
        this.priorRank = priorRank;
    }


    /**
     * First compare prior ranks. If equal, compare ranks then.
     *
     * @param c card to compare to
     * @return Result number. larger: > 0; smaller: < 0; equal: == 0
     */
    @Override
    public int compareTo(Card c) {
        //First compare prior ranks
        if (priorRank > c.getPriorRank()) {
            return 1;
        } else if (priorRank < c.getPriorRank()) {
            return -1;
        }

        //Prior ranks are equal
        if (rank.compareTo(c.getRank()) < 0) {
            return -1;
        } else if (rank.compareTo(c.getRank()) > 0) {
            return 1;
        }
        return 0;
    }
}