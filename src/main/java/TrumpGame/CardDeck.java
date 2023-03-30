package TrumpGame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Takuma Asano
 * @version 0.1, 2022-07-05
 *
 */
public class CardDeck {
    private ArrayList<Card> cards = new ArrayList<Card>();

    /**
     * 空のカードデッキインスタンスを作る
     */
    public CardDeck() {

    }



    public ArrayList<Card> getCards() {
        return cards;
    }



    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }



    /**
     * 自らをフルデッキにする
     */
    public void createFulDeck() {
        //System.out.println("== 1. フルデッキを作ります．==");
        for (int i = 0; i < 4; i++) {
            for (int j = 1; j < 14; j++) {
                Card c = new Card();
                c.setSuit(i);
                c.setNumber(j);
                cards.add(c);
            }
        }
    }

    /**
     * 自らを空デッキにする
     */
    public void clear() {
        //System.out.println("デッキを空にします．");
        cards.clear();
    }

    /**
     * デッキをシャッフルしてカードの順番をまぜる
     */
    public void shuffle() {
        //System.out.println("== 2. シャッフルします．==");
        Collections.shuffle(cards);
    }

    /**
     * デッキの一番最後に，任意のカードを追加する
     * @param card カード
     */
    public void addCard(Card card) {
        //System.out.println(card.toString() + "も追加します．");
        cards.add(card);
    }

    /**
     * デッキの i 番目に，任意のカードを追加する
     * @param card カード
     */
    public void addCard(int i, Card card) {
        //System.out.println(card.toString() + "を" + i + "番目に追加します．");
        cards.add(i - 1, card);
    }

    /**
     * デッキの一番上の（１番目の）カードを取る
     * @return カード
     */
    public Card takeCard() {
        //System.out.println("上から1枚取ってみます．");
        Card c = cards.get(0);
        //System.out.println(c.toString());
        cards.remove(0);
        return c;
    }

    /**
     * デッキの i 番目から，カードを抜き取る
     * @param i デッキの上からの枚数
     * @return カード
     */
    public Card takeCard(int i) {
        //System.out.println("上から" + i + "番目のカード取ってみます．");
        Card c = cards.get(i - 1);
        //System.out.println(c.toString());
        cards.remove(i - 1);
        return c;
    }

    /**
     * デッキのi番目にあるカードを見る
     * @param i デッキの上からの枚数
     * @return カード
     */
    public Card seeCard(int i) {
        Card c = cards.get(i - 1);
        //System.out.println("上から" + i + "番目のカードは，" + c.toString() + "です．");
        return c;
    }

    /**
     * 絵柄suitと番号numberを与えて，そのカードがデッキの何番目にあるかを調べる
     * @param suit 絵柄
     * @param number 数字
     * @return そのカーがデッキの何番目にあるか
     */
    public int searchCard(int suit, int number) {
        for (int i = 0; i < cards.size(); i++) {
            if (cards.get(i).getSuit() == suit && cards.get(i).getNumber() == number) {
                //System.out.println(cards.get(i).toString() + "はデッキの" + (i + 1) + "番目にあります．");
                return i + 1;
            }
        }

        return 0;
    }

    /**
     * 現在のデッキが空かどうか，判定する
     * @return 空ならtrue，空でないならfalse
     */
    public boolean isEmpty() {
        if (cards.isEmpty()) {
            //System.out.println("現在のデッキは空です．");
        } else {
            //System.out.println("現在のデッキは空ではありません．");
        }
        return cards.isEmpty();
    }

    /**
     * 現在デッキにあるカード枚数を返す
     * @return デッキにあるカード枚数
     */
    public int size() {
        System.out.println("現在のデッキの枚数は，" + cards.size() + "です．");
        return cards.size();
    }

    /**
     * 現在のすべてのカードを画面に表示する
     */
    public void showAllCards() {
        System.out.println("------------現在の山を表示します．-----------");
        for (int i = 0; i < cards.size(); i++) {
            System.out.print((i + 1) + "番目のカード：");
            cards.get(i).show();
        }
        System.out.println("------------ここまで-----------");
    }

    /**
     * 現在デッキにある全てのカードを返す
     * @return 現在デッキにある全てのカード
     */
    public List<Card> getAllCards() {
        return cards;

    }

}
