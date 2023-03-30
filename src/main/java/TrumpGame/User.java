package TrumpGame;

import java.util.ArrayList;
import TrumpGame.KeyBoard;

/**
 * ユーザを表すクラス
 * Playerクラスのサブクラス
 * @author 浅野卓磨
 *
 */
public class User extends Player {

    /**
     * 名前を指定してユーザインスタンスを作る
     * @param name 名前
     */
    public User(String name) {
        super(name);
    }

    /**
     * ユーザが手を選択する
     * PlayerクラスのchooseTe()をオーバーライドしている
     * @return ユーザの手
     */
    @Override
    public ArrayList<Card> chooseTe(int s) {
        te.clear();
        sort();
        System.out.println("[手札]");
        showTefuda();
        int n = -1;
        while (n == -1) {
            System.out.println("[1:カードを出す，-1:パス]");
            int k = KeyBoard.inputNumber();
            while (k != 1 && k != -1) {
                System.out.println("[エラー]1か-1を入力してください．");
                k = KeyBoard.inputNumber();
            }

            if (k == -1) {
                return te;

            } else {
                int m;
                if (s == 0) {
                    System.out.println("何枚出しますか？(1以上の整数)");
                    m = KeyBoard.inputNumber();
                    while (m < 1) {
                        System.out.println("[エラー]1以上の整数を入力してください．");
                        m = KeyBoard.inputNumber();
                    }
                } else
                    m = s;

                System.out.println("カードを選択してください．(始めからやり直す場合は-1を入力)");
                for (int i = 1; i <= m; i++) {
                    System.out.print(i + "枚目：");
                    n = KeyBoard.inputNumber();
                    while (n < -1 || n > tefuda.size()-1) {
                        System.out.println("[エラー]-1から"+(tefuda.size()-1)+"までの数字を入力してください．");
                        n = KeyBoard.inputNumber();
                    }
                    if (n == -1) {
                        te.clear();
                        break;
                    }
                    if (te.contains(tefuda.get(n))) {
                        System.out.println("[エラー]同じカードを複数選択しています．異なるカードを選択してください．(始めからやり直す場合は-1を入力)");
                        i--;
                        continue;
                    } else if (te.size() > 0) {
                        if (te.get(0).getNumber() != tefuda.get(n).getNumber()) {
                            System.out.println("[エラー]同じ数字のカードを選択してください．(始めからやり直す場合は-1を入力)");
                            i--;
                            continue;
                        }
                    }
                    te.add(tefuda.get(n));
                }
            }

        }
        return te;
    }

}
