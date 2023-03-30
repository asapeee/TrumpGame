package TrumpGame;

/**
 *
 * @author Takuma Asano
 * @version 0.1, 2022-07-05
 *
 */

public class Card {
    private int suit;
    private int number;

    /**
     * 空のカードインスタンスを作る
     */
    public Card() {
    }

    /**
     * 絵柄，数字を指定してカードインスタンスを作る
     *
     * @param suit   絵柄 (0:スペード，1:ダイヤ，2:ハート，3:クラブ, -1:ジョーカー)
     * @param number 数字 (1-13)
     */
    public Card(int suit, int number) {
        this.suit = suit;
        this.number = number;
    }

    /**
     * そのカードの絵柄を取得する
     *
     *
     * @return 絵柄 (0:スペード，1:ダイヤ，2:ハート，3:クラブ, -1:ジョーカー)
     */
    public int getSuit() {
        return suit;
    }

    /**
     * そのカードの絵柄をセットする
     *
     * @param suit 絵柄 (0:スペード，1:ダイヤ，2:ハート，3:クラブ, -1:ジョーカー)
     */
    public void setSuit(int suit) {
        this.suit = suit;
    }

    /**
     * そのカードの数字を取得する
     *
     * @return 数字 (1-13, 0)
     */
    public int getNumber() {
        return number;
    }

    /**
     * そのカードの数字をセットする
     *
     * @param number 数字 (1-13)
     */
    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * そのカードの整数表現を取得する
     *
     * @return 整数表現 (0-51, -1)
     */
    public int toIndex() {
        int index;
        index = suit * 13 + number - 1;
        if (suit == -1) {
            index = -1;
        }

        return index;
    }

    /**
     * そのカードの文字列表現を取得する
     *
     * @return カードの文字列表現 (スペードA, スペード2, ...)
     */
    public String toString() {
        return getString(getSuit(), getNumber());

    }

    /**
     * そのカードの文字列表現を画面に表示する
     */
    public void show() {
        System.out.println(toString());
    }

    /**
     *
     * @param suit   絵柄 (0:スペード，1:ダイヤ，2:ハート，3:クラブ, -1:ジョーカー)
     * @param number 数字 (1-13, 0)
     * @return カードの整数表現 (0-51, -1)
     */
    public static final int getIndex(int suit, int number) {
        int index;
        index = suit * 13 + number - 1;
        if (suit == -1) {
            index = -1;
        }

        return index;
    }

    /**
     * 与えられた絵柄，数字から文字列表現を決定して返す．すべてのカードで共通
     *
     * @param suit   絵柄 (0:スペード，1:ダイヤ，2:ハート，3:クラブ, -1:ジョーカー)
     * @param number 数字 (1-13, 0)
     * @return カードの文字列表現 (スペードA, スペード2, ...)
     */
    public static final String getString(int suit, int number) {
        return getStringSuit(suit) + getStringNumber(number);

    }

    public static final String getStringSuit(int suit) {
        String egara = null;
        switch (suit) {
            case 0:
                egara = "♠";
                break;
            case 1:
                egara = "♢";
                break;
            case 2:
                egara = "♡";
                break;
            case 3:
                egara = "♣";
                break;
            case -1:
                egara = "ジョーカー";
                break;
        }

        return egara ;
    }

    public static final String getStringNumber(int number) {
        String suji = null;

        switch (number) {
            case 1:
                suji = "A";
                break;
            case 11:
                suji = "J";
                break;
            case 12:
                suji = "Q";
                break;
            case 13:
                suji = "K";
                break;
            default:
                suji = String.valueOf(number);
                break;
        }

        return suji;

    }

}
