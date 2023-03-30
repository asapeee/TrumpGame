package TrumpGame;

import java.util.ArrayList;
import java.util.Random;

/**
 * CPUを表すクラス
 * Playerクラスのサブクラス
 * @author 浅野卓磨
 *
 */
public class CPU extends Player {

    Random rand = new Random();

    /**
     * 名前を指定して，CPUインスタンスを作る
     * @param name 名前
     */
    public CPU(String name) {
        super(name);
    }

    /**
     * CPUが手を選択する
     * PlayerクラスのchooseTe()をオーバーライドしている
     * @return CPUの手
     */
    @Override
    public ArrayList<Card> chooseTe(int s) {
        sort();
        te.clear();
        int count = 0;
        if (s == 0 || s == 1) {
            te.add(tefuda.get(rand.nextInt(tefuda.size())));

        } else {
            for (int i = 0; i < tefuda.size(); i++) {
                Card c = tefuda.get(i);
                te.add(c);
                for (int j = i + 1; j < tefuda.size(); j++) {
                    if (c.getNumber() == tefuda.get(j).getNumber()) {
                        count++;
                        te.add(tefuda.get(j));
                    }
                }

                if (count + 1 < s) {
                    count = 0;
                    te.clear();

                } else if (count + 1 == s) {
                    break;

                } else {
                    for (int k = 0; k < (count + 1 - s); k++) {
                        te.remove(0);
                    }
                    break;
                }
            }
        }

        return te;
    }

}
