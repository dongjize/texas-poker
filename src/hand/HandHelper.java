package hand;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: A utility class that helps handle winner decision.
 *
 * @Date: 2017-09-16
 * @Time: 13:25
 */
public class HandHelper {

    /**
     * Decide which hand to win and output appropriate result to the client.
     *
     * @param hands input hand list of players
     * @return result string indicating the winner(s)
     */
    public static String decideWinner(List<Hand> hands) {
        hands.sort(HandComparator.getInstance());

        List<Integer> winnersList = new ArrayList<>();
        for (int i = 0; i < hands.size() - 1; i++) {
            // If a hand is equal to the top-ranked hand, it's also a winning hand
            if (hands.size() > 1 && hands.get(i).
                    compareTo(hands.get(hands.size() - 1)) == 0) {
                winnersList.add(hands.get(i).getId());
            }
        }
        // Add the top-ranked hand, certainly the winner
        winnersList.add(hands.get(hands.size() - 1).getId());

        StringBuilder sb = new StringBuilder();
        if (winnersList.size() == 1) {
            sb.append("Player ").append(winnersList.get(0)).append(" wins.");
        } else if (winnersList.size() > 1) {
            sb.append("Players ");
            for (int i = 0; i < winnersList.size() - 2; i++) {
                sb.append(winnersList.get(i)).append(", ");
            }
            sb.append(winnersList.get(winnersList.size() - 2))
                    .append(" and ")
                    .append(winnersList.get(winnersList.size() - 1))
                    .append(" draw.");
        }
        return sb.toString();
    }

}
