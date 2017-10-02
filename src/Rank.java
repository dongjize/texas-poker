/**
 * Description: Rank enum
 *
 * @Date: 2017-09-16
 * @Time: 00:11
 */
public enum Rank {
    TWO("2"), THREE("3"), FOUR("4"), FIVE("5"), SIX("6"), SEVEN("7"),
    EIGHT("8"), NINE("9"), TEN("10"), JACK("Jack"), QUEEN("Queen"),
    KING("King"), ACE("Ace");

    private String value;

    Rank(String s) {
        this.value = s;
    }

    @Override
    public String toString() {
        return value;
    }
}
