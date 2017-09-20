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

        // Judge the legality of card count
        judgeCardCountLegal(args);

        // The card count is legal, then judge the legality of rank and suit
        judgeRankSuitLegal(args);

        // Initialize player list and determine the winner
        process(args);

    }

    /**
     * Judge the legality of card count
     *
     * @param args user input arguments
     */
    private static void judgeCardCountLegal(String[] args) {
        if (args.length <= 0 || args.length % 5 != 0) {
            System.out.println("Error: wrong number of arguments; must be a multiple of 5");
            System.exit(0);
        }
    }

    /**
     * Judge rank and suit legality
     *
     * @param args user input arguments
     */
    private static void judgeRankSuitLegal(String[] args) {
        String rankStr = "23456789TtJjQqKkAa";
        String suitStr = "CcDdHhSs";

        for (int i = 0; i < args.length; i++) {
            String s = args[i];
            // Judge the legality of rank and suit
            if (s.length() != 2 || !rankStr.contains(s.substring(0, 1)) || !suitStr.contains(s.substring(1, 2))) {
                System.out.println("Error: invalid card name '" + s + "'");
                System.exit(0);
            }
        }
    }

    /**
     * Initialize playerList and determine the winner
     *
     * @param args user input arguments
     */
    private static void process(String[] args) {
        List<Player> playerList = new ArrayList<>();

        // Segment arguments array by 5
        for (int i = 0; i < args.length / 5; i++) {
            // Instantiate an array containing five elements
            Card[] cards = new Card[5];
            // For each card in a hand, instantiate according to the input string
            for (int j = 0; j < 5; j++) {
                Card card = new Card(args[i * 5 + j].substring(0, 1), args[i * 5 + j].substring(1, 2));
                cards[j] = card;
            }
            Hand hand = new Hand(cards);
            Player player = new Player(i + 1, hand); // for i == 0, player's id == 1
            playerList.add(player);
            System.out.println("Player " + player.getId() + ": " + hand.getDescription());
        }

        // If more than 1 player, determine the winner.
        if (playerList.size() > 1) {
            String s = Player.decideWinner(playerList);
            System.out.println(s);
        }
    }

}