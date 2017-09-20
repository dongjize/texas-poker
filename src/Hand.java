import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * Description: Hand Entity. Each hand contains an array of five cards.
 *
 * @Author: dong
 * @Date: 2017-09-15
 * @Time: 22:58
 */
public class Hand implements Comparable<Hand> {
    private int id;
    private String category;
    private String description;
    private HandType handType;
    private Card[] cardArray;

    /**
     * Constructor method
     *
     * @param cards An array of 5 cards
     */
    public Hand(int id, Card[] cards) {
        // Process only if cards[] contains 5 elements
        if (cards != null && cards.length == 5) {
            this.id = id;
            this.cardArray = cards;
            Arrays.sort(cardArray); // sort the array into ascending order
            decideCategory(cardArray);
        }
    }

    /**
     * Determine the category and type of the hand and set description
     *
     * @param arr card array
     */
    private void decideCategory(Card[] arr) {
        // Straight flush
        if (!"".equals(judgeStraight(arr)) && !"".equals(judgeFlush(arr))) {
            setCategory("Straight flush");
            setDescription(judgeStraight(arr) + "-high straight flush");
            setHandType(HandType.STRAIGHT_FLUSH);
            return;
        }

        // Four of a kind
        if (nOfAKind(arr, 4).size() > 0) {
            setCategory("Four of a kind");
            setDescription("Four " + nOfAKind(arr, 4).get(0) + "s");
            setHandType(HandType.FOUR_OF_A_KIND);
            return;
        }

        // Full house
        if (nOfAKind(arr, 3).size() > 0 && nOfAKind(arr, 2).size() > 0) {
            setCategory("Full house");
            setDescription(nOfAKind(arr, 3).get(0) + "s full of " + nOfAKind(arr, 2).get(0) + "s");
            setHandType(HandType.FULL_HOUSE);
            return;
        }

        // Flush
        if (!"".equals(judgeFlush(arr))) {
            setCategory("Flush");
            setDescription(judgeFlush(arr) + "-high flush");
            setHandType(HandType.FLUSH);
            return;
        }

        // Straight
        if (!"".equals(judgeStraight(arr))) {
            setCategory("Straight");
            setDescription(judgeStraight(arr) + "-high straight");
            setHandType(HandType.STRAIGHT);
            return;
        }

        // Three of a kind
        if ((nOfAKind(arr, 3).size() > 0)) {
            setCategory("Three of a kind");
            setDescription("Three " + nOfAKind(arr, 3).get(0) + "s");
            setHandType(HandType.THREE_OF_A_HAND);
            return;
        }

        // Two pair
        if (nOfAKind(arr, 2).size() == 2) {
            List<String> list = nOfAKind(arr, 2);
            setCategory("Two pair");
            setDescription(list.get(1) + "s over " + list.get(0) + "s");
            setHandType(HandType.TWO_PAIR);
            return;
        }

        // One pair
        if (nOfAKind(arr, 2).size() == 1) {
            List<String> list = nOfAKind(arr, 2);
            setCategory("One pair");
            setDescription("Pair of " + list.get(0) + "s");
            setHandType(HandType.ONE_PAIR);
            return;
        }

        // Otherwise, type should be High card
        setCategory("High card");
        setDescription(cardArray[cardArray.length - 1].getRank().toString() + "-high");
        setHandType(HandType.HIGH_CARD);
    }


    /**
     * Judge whether a array of card is straight.
     * If each element is 1 larger than previous, the hand is straight
     *
     * @param cards the array of cards
     * @return If straight, return the highest rank string.
     */
    private String judgeStraight(Card[] cards) {
        for (int i = 1; i < cards.length; i++) {
            if (cards[i].getRank().ordinal() - cards[i - 1].getRank().ordinal() != 1) {
                return "";
            }
        }
        return cards[cards.length - 1].getRank().toString();
    }


    /**
     * Judge whether an array of card is flush.
     * If all cards have the same suit, the hand is flush.
     *
     * @param cards the array of cards
     * @return If flush, return the highest rank string.
     */
    private String judgeFlush(Card[] cards) {
        Suit suit = cards[cards.length - 1].getSuit();
        for (Card card : cards) {
            if (card.getSuit() != suit) {
                return "";
            }
        }
        return cards[cards.length - 1].getRank() != null ?
                cards[cards.length - 1].getRank().toString() : "";
    }


    /**
     * Judge whether a list of card contains n same cards.
     *
     * @param cards the list of cards
     * @param n     number of a same kind
     * @return If does contain, return a list of their ranks
     */
    private List<String> nOfAKind(Card[] cards, int n) {
        ArrayList<String> sList = new ArrayList<>(); // a container of "n" element ranks
        if (cards == null || n < 2 || cards.length < n) {
            return sList; // empty list now
        }

        // Start loop from 0 to (cards.length - n)
        int i = 0;
        while (i <= cards.length - n) {
            int gap = 1; // the pace to increase i
            // If ranks keep equal, add gap by 1
            while (i + gap < cards.length && (cards[i].getRank() == cards[i + gap].getRank())) {
                gap++;
            }
            // If gap is exactly equal to n
            if (gap == n) {
                // Means cards[] is an "n of a kind", thus set the prior rank of cards
                for (int j = i; j < i + n; j++) {
                    cards[j].setPriorRank(n);
                }
                // Add the rank to result list
                sList.add(cards[i + gap - 1].getRank().toString());
            }
            i += gap;
        }

        // Re-sort the card array, after updating prior ranks.
        Arrays.sort(cards);

        return sList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Card[] getCardArray() {
        return cardArray;
    }

    public String getCategory() {
        return category;
    }

    private void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    private void setDescription(String description) {
        this.description = description;
    }

    public HandType getHandType() {
        return handType;
    }

    private void setHandType(HandType handType) {
        this.handType = handType;
    }


    @Override
    public int compareTo(Hand h) {
        if (handType == null || h.getHandType() == null) {
            throw new RuntimeException("Hand is null! ");
        }

        if (handType.compareTo(h.getHandType()) < 0) {
            return 1;
        }
        if (handType.compareTo(h.getHandType()) > 0) {
            return -1;
        }

        // If either hand has the same type
        Card[] arr = h.getCardArray();
        // compare the rank of cards
        for (int i = cardArray.length - 1; i >= 0; i--) {
            if (cardArray[i].getRank().compareTo(arr[i].getRank()) < 0) {
                return -1;
            } else if (cardArray[i].getRank().compareTo(arr[i].getRank()) > 0) {
                return 1;
            }
        }
        return 0;
    }

}
