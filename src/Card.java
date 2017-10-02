/**
 * Description: Card class. Deals with comparison.
 * Each card has a rank and a suit, only rank to be compared.
 * There's also a priorRank field, indicating whether it has a special
 * priority for ranking.
 *
 * @Date: 2017-09-15
 * @Time: 00:36
 */
public class Card implements Comparable<Card> {
    private Rank rank;
    private Suit suit;
    // If card is "n of a kind", assign n to priorRank for comparison
    private int priorRank = -1;

    /**
     * Constructor method. Assign rank and suit to the card.
     *
     * @param s a two-character string representing rank and suit
     */
    public Card(String s) {
        if ("".equals(s) || s.length() < 2) {
            return;
        }
        setRank(CardFactory.rank(s.substring(0, 1)));
        setSuit(CardFactory.suit(s.substring(1, 2)));
    }

    public Rank getRank() {
        return rank;
    }

    private void setRank(Rank rank) {
        this.rank = rank;
    }

    public Suit getSuit() {
        return suit;
    }

    private void setSuit(Suit suit) {
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

        //Prior ranks are equal, thus compare ranks
        if (rank.compareTo(c.getRank()) < 0) {
            return -1;
        } else if (rank.compareTo(c.getRank()) > 0) {
            return 1;
        }

        //If all the same, equal
        return 0;
    }
}
