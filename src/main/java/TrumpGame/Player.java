package TrumpGame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * プレイヤを表す抽象クラス．名前，手札，手を持つ．ユーザ，CPUはこのクラスを継承すること．
 * @author 浅野卓磨
 *
 */
public abstract class Player {
    /** 名前 */
    protected String name;
    /** 手札 */
    protected ArrayList<Card> tefuda = new ArrayList<Card>();
    /**  手 */
    protected ArrayList<Card> te = new ArrayList<Card>();

    /**
     * 名前を指定してプレイヤインスタンスを作る
     * @param name 名前
     */
    public Player(String name) {
        super();
        setName(name);
    }

    /**
     * 名前を取得する
     * @return 名前
     */
    public String getName() {
        return name;
    }

    /**
     * 名前をセットする
     * @param name 名前
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 手札を取得する
     * @return 手札
     */
    public ArrayList<Card> getTefuda() {
        return tefuda;
    }

    /**
     * 手札をセットする
     * @param tefuda 手札
     */
    public void setTefuda(ArrayList<Card> tefuda) {
        this.tefuda = tefuda;
    }

    /**
     * 手を取得する
     * @return 手
     */
    public ArrayList<Card> getTe() {
        return te;
    }

    /**
     * 手をセットする
     * @param te 手
     */
    public void setTe(ArrayList<Card> te) {
        this.te = te;
    }

    /**
     * 手を選ぶ抽象メソッド．具体的な実装はサブクラスに任せる．
     */
    public abstract ArrayList<Card> chooseTe(int s);

    /**
     * 手札を表示する
     */
    public void showTefuda() {
        for (int i = 0; i < tefuda.size(); i++) {
            System.out.println(i + ":" + tefuda.get(i).toString());
        }
    }

    /**
     * 手札を並べ替える
     */
    public void sort() {
        Comparator<Card> comparator = new Comparator<Card>() {
            @Override
            public int compare(Card C1, Card C2) {
                int c1 = C1.getNumber();
                int c2 = C2.getNumber();
                int judgement = Integer.valueOf(c1).compareTo(Integer.valueOf(c2));
                return judgement;
            }
        };
        Collections.sort(tefuda, comparator);
    }

}

