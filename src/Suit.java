/*
 * Description: Suit enum
 *
 * @Author: dong
 * @Date: 2017-09-16
 * @Time: 00:20
 */
public enum Suit {
    CLUB("C"), DIAMOND("D"), HEART("H"), SPADE("S");

    private String value;

    Suit(String s) {
        this.value = s;
    }

    @Override
    public String toString() {
        return value;
    }
}
