import java.util.ArrayList;
import java.util.List;

/*
 * Description: Player entity. Records an id and a hand to compare with each other.
 *
 * @Author: dong
 * @Date: 2017-09-17
 * @Time: 15:34
 */
public class Player implements Comparable<Player> {
    private int id;
    private Hand hand; // the hand that the player's holding

    public Player(int id, Hand hand) {
        this.id = id;
        this.hand = hand;
    }

    public int getId() {
        return id;
    }

    public Hand getHand() {
        return hand;
    }


    /**
     * Determine the winner of a list of players.
     * <p>
     * If a single player wins, return "player x wins".
     * If multiple players win, return "player x, y... draws".
     *
     * @param players list of players
     * @return result string
     */
    public static String decideWinner(List<Player> players) {
        String result = "";
        PlayersComparator comparator = new PlayersComparator();

        players.sort(comparator); // Sort the player list by PlayersComparator
        List<Integer> list = new ArrayList<>(); // the list of winners (in case more than one winner)
        for (int i = 0; i < players.size() - 1; i++) {
            // If an player is equal to top ranked player, he/she is also a winner
            if (players.size() > 1 && players.get(i).compareTo(players.get(players.size() - 1)) == 0) {
                list.add(players.get(i).getId());
            }
        }
        list.add(players.get(players.size() - 1).getId()); // the top ranked player, certainly the winner

        // concatenate the result string
        if (list.size() == 1) {
            result = "Player " + list.get(0) + " wins.";
        } else if (list.size() > 1) {
            StringBuilder sb = new StringBuilder();
            sb.append("Players ");
            for (int i = 0; i < list.size() - 2; i++) {
                sb.append(list.get(i));
                sb.append(", ");
            }
            // the second last element
            sb.append(list.get(list.size() - 2));
            // which is followed by "and" instead of a comma
            sb.append(" and ");
            // the last element
            sb.append(list.get(list.size() - 1));
            sb.append(" draw.");
            result = sb.toString();
        }

        return result;
    }

    @Override
    public int compareTo(Player p) {
        return this.hand.compareTo(p.getHand());
    }
}
