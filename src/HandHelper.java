import java.util.ArrayList;
import java.util.List;

/**
 * Description: A utility class that helps handle winner decision.
 *
 * @Author: Jize Dong
 * @LoginID: jized
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
        String result = "";

        hands.sort(new HandComparator());
        // the list of winning hands (in case more than one)
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < hands.size() - 1; i++) {
            // If an hand is equal to top ranked hand, it's also a winning hand
            if (hands.size() > 1 && hands.get(i).
                    compareTo(hands.get(hands.size() - 1)) == 0) {
                list.add(hands.get(i).getId());
            }
        }
        // Add the top ranked hand, certainly the winner
        list.add(hands.get(hands.size() - 1).getId());

        // concatenate the result string
        if (list.size() == 1) {
            result = "Player " + list.get(0) + " wins.";
        } else if (list.size() > 1) {
            StringBuilder sb = new StringBuilder();
            sb.append("Players ");
            for (int i = 0; i < list.size() - 2; i++) {
                sb.append(list.get(i)).append(", ");
            }
            sb.append(list.get(list.size() - 2)) // the second last element
                    .append(" and ")
                    .append(list.get(list.size() - 1)) // the last element
                    .append(" draw.");
            result = sb.toString();
        }

        return result;
    }

}
