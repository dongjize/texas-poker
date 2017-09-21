import java.util.ArrayList;
import java.util.List;

/*
 * Description: A utility class that helps handle winner decision.
 *
 * @Author: dong
 * @Date: 2017-09-20
 * @Time: 13:25
 */
public class HandHelper {

    /**
     * Decide which hand to win and output appropriate result string to the client.
     *
     * @param hands input hand list of winners
     * @return result string indicating the winner(s)
     */
    public static String decideWinner(List<Hand> hands) {
        String result = "";
        HandComparator comparator = new HandComparator();

        hands.sort(comparator); // Sort the hand list by HandComparator
        List<Integer> list = new ArrayList<>(); // the list of winning hands (in case more than one)
        for (int i = 0; i < hands.size() - 1; i++) {
            // If an hand is equal to top ranked hand, it's also a winning hand
            if (hands.size() > 1 && hands.get(i).compareTo(hands.get(hands.size() - 1)) == 0) {
                list.add(hands.get(i).getId());
            }
        }
        list.add(hands.get(hands.size() - 1).getId()); // the top ranked hand, certainly the winner

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


}
