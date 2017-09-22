import java.util.ArrayList;
import java.util.List;

/*
 * Description: Client. Deals with the input arguments.
 *
 * @Author: dong
 * @Date: 2017-09-13
 * @Time: 00:28
 */
public class Poker {
    public static void main(String[] args) {
        judgeCardCountLegal(args);
        judgeRankSuitLegal(args);
        process(args);
    }

    /**
     * Judge the legality of card count
     *
     * @param args user input arguments
     */
    private static void judgeCardCountLegal(String[] args) {
        if (args.length <= 0 || args.length % 5 != 0) {
            System.out.println("Error: wrong number of arguments; " +
                    "must be a multiple of 5");
            System.exit(0);
        }
    }

    /**
     * Judge rank and suit legality, by checking the argument's length whether
     * argument strings exist in possible rank and suit strings.
     *
     * @param args user input arguments
     */
    private static void judgeRankSuitLegal(String[] args) {
        String rankStr = "23456789TJQKA";
        String suitStr = "CDHS";

        for (int i = 0; i < args.length; i++) {
            String s = args[i].toUpperCase();
            // Judge the legality of rank and suit
            if (s.length() != 2 || !rankStr.contains(s.substring(0, 1)) ||
                    !suitStr.contains(s.substring(1, 2))) {
                System.out.println("Error: invalid card name '" + s + "'");
                System.exit(0);
            }
        }
    }

    /**
     * Initialize the list of hands and determine the winner.
     * <p>
     * In case there're more than one hand, cope with a hand list containing
     * all winning hands.
     *
     * @param args user input arguments
     */
    private static void process(String[] args) {
        List<Hand> handList = new ArrayList<>();

        // Segment arguments array by 5
        for (int i = 0; i < args.length / 5; i++) {
            // Instantiate an array containing five elements
            Card[] cards = new Card[5];
            // Instantiate each card in a hand according to the input string
            for (int j = 0; j < 5; j++) {
                Card card = new Card(args[i * 5 + j].toUpperCase());
                cards[j] = card;
            }
            Hand hand = new Hand(i + 1, cards);
            handList.add(hand);
            System.out.println("Player " + hand.getId() + ": " +
                    hand.getDescription());
        }

        // If more than 1 player, determine the winner.
        if (handList.size() > 1) {
            String s = HandHelper.decideWinner(handList);
            System.out.println(s);
        }
    }


}
