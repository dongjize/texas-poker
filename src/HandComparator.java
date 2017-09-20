import java.util.Comparator;

/*
 * Description: Comparator implemented to sort hand list
 *
 * @Author: dong
 * @Date: 2017-09-20
 * @Time: 13:08
 */
public class HandComparator implements Comparator<Hand> {
    @Override
    public int compare(Hand h1, Hand h2) {
        return h1.compareTo(h2);
    }
}
