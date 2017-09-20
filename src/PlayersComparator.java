import java.util.Comparator;

/*
 * Description: Used to compare players
 *
 * @Author: dong
 * @Date: 2017-09-16
 * @Time: 15:39
 */
public class PlayersComparator implements Comparator<Player> {
    @Override
    public int compare(Player p1, Player p2) {
        return p1.compareTo(p2);
    }
}
