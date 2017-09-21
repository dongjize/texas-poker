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
    // essentially the id number of a player, also can represent a hand
    private int id;
    // the hand type enum
    private HandType handType;
    // output string of hand description
    private String description;
    // the cards constituting the hand
    private Card[] cardArray;

    /**
     * Constructor method. Sorts the input card array and determines the category of hand.
     *
     * @param id    the id number of player, also the hand
     * @param cards an array of five cards
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
     * @param arr input card array
     */
    private void decideCategory(Card[] arr) {
        // Straight flush
        if (!"".equals(judgeStraight(arr)) && !"".equals(judgeFlush(arr))) {
            setHandType(HandType.STRAIGHT_FLUSH);
            setDescription(judgeStraight(arr) + "-high straight flush");
            return;
        }

        // Four of a kind
        if (nOfAKind(arr, 4).size() > 0) {
            setHandType(HandType.FOUR_OF_A_KIND);
            setDescription("Four " + nOfAKind(arr, 4).get(0) + "s");
            return;
        }

        // Full house
        if (nOfAKind(arr, 3).size() > 0 && nOfAKind(arr, 2).size() > 0) {
            setHandType(HandType.FULL_HOUSE);
            setDescription(nOfAKind(arr, 3).get(0) + "s full of " + nOfAKind(arr, 2).get(0) + "s");
            return;
        }

        // Flush
        if (!"".equals(judgeFlush(arr))) {
            setHandType(HandType.FLUSH);
            setDescription(judgeFlush(arr) + "-high flush");
            return;
        }

        // Straight
        if (!"".equals(judgeStraight(arr))) {
            setHandType(HandType.STRAIGHT);
            setDescription(judgeStraight(arr) + "-high straight");
            return;
        }

        // Three of a kind
        if ((nOfAKind(arr, 3).size() > 0)) {
            setHandType(HandType.THREE_OF_A_HAND);
            setDescription("Three " + nOfAKind(arr, 3).get(0) + "s");
            return;
        }

        // Two pair
        if (nOfAKind(arr, 2).size() == 2) {
            List<String> list = nOfAKind(arr, 2);
            setHandType(HandType.TWO_PAIR);
            setDescription(list.get(1) + "s over " + list.get(0) + "s");
            return;
        }

        // One pair
        if (nOfAKind(arr, 2).size() == 1) {
            List<String> list = nOfAKind(arr, 2);
            setHandType(HandType.ONE_PAIR);
            setDescription("Pair of " + list.get(0) + "s");
            return;
        }

        // Otherwise, type should be High card
        setHandType(HandType.HIGH_CARD);
        setDescription(cardArray[cardArray.length - 1].getRank().toString() + "-high");
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

    public HandType getHandType() {
        return handType;
    }

    private void setHandType(HandType handType) {
        this.handType = handType;
    }

    public String getDescription() {
        return description;
    }

    private void setDescription(String description) {
        this.description = description;
    }

    /**
     * For two different kinds of hands, compare their hand type straightforward.
     * If hand types are the same, compare the card rank in descending order.
     *
     * @param h the hand to compare to
     * @return 1 if greater than h; -1 if smaller than h; 0 if equal to h.
     */
    @Override
    public int compareTo(Hand h) {
        if (handType == null || h.getHandType() == null) {
            throw new RuntimeException(Hand.class.getSimpleName() + ": Hand is null!");
        }

        // judge whether the two hands have the same type
        if (handType.compareTo(h.getHandType()) < 0) {
            return 1;
        }
        if (handType.compareTo(h.getHandType()) > 0) {
            return -1;
        }

        // If both have the same type, compare the rank of cards in descending order
        Card[] arr = h.getCardArray();
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
