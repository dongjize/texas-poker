import java.util.Comparator;

/**
 * Description: Comparator implemented to sort hand list
 *
 * @Date: 2017-09-26
 * @Time: 13:08
 */
public class HandComparator implements Comparator<Hand> {

    private static HandComparator INSTANCE = null;

    private HandComparator() {

    }

    public static HandComparator getInstance() {
        if (INSTANCE == null) {
            synchronized (HandComparator.class) {
                if (INSTANCE == null) {
                    INSTANCE = new HandComparator();
                }
            }
        }
        return INSTANCE;
    }

    /**
     * Simply implements default comparing method
     *
     * @param h1 hand1
     * @param h2 hand2
     * @return the result of hand1 and hand2 comparison
     */
    @Override
    public int compare(Hand h1, Hand h2) {
        return h1.compareTo(h2);
    }
}
