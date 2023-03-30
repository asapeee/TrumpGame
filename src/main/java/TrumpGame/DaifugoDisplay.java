package TrumpGame;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * 大富豪ゲームを表すクラス
 *
 * @author 浅野卓磨
 */
public class DaifugoDisplay {
    /**
     * プレイヤリスト
     */
    private ArrayList<Player> players = new ArrayList<Player>();
    /**
     * 場札
     */
    private ArrayList<ArrayList<Card>> ba = new ArrayList<ArrayList<Card>>();
    /**
     * 山札
     */
    private CardDeck yamafuda = new CardDeck();

    private JFrame disp;

    private
    JPanel top_panel, mid_panel1, mid_panel2, bottom_panel;

    private JLabel msg_lbl;

    private JToggleButton btn_0, btn_1, btn_2, btn_3, btn_4, btn_5, btn_6, btn_7, btn_8, btn_9, btn_10, btn_11, btn_12;
    private ArrayList<JToggleButton> btns = new ArrayList<JToggleButton>();

    private JButton pass, decide;

    int passFlag = 0;
    int decideFlag = 0;

    private JLabel first_suit_lbl, first_no_lbl, second_suit_lbl, second_no_lbl, third_suit_lbl, third_no_lbl, fourth_suit_lbl, fourth_no_lbl;


    Random rand = new Random();

    /**
     * 空の大富豪ゲームインスタンスを作る
     */
    public DaifugoDisplay() {
        super();

        disp = new JFrame("Daifugo");
        disp.setSize(1300, 700);
        disp.setLocationRelativeTo(null);
        disp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        disp.setResizable(false);

        top_panel = new JPanel();
        setPanel(top_panel, Color.ORANGE, null, new Dimension(1300, 200));
        disp.add(top_panel, BorderLayout.NORTH);

        msg_lbl = new JLabel();
        top_panel.add(msg_lbl);
        setLabelFont(msg_lbl, Color.BLACK, 0, 15, 1300, 20, 20, false);

        mid_panel1 = new JPanel();
        setPanel(mid_panel1, Color.CYAN, null, new Dimension(1200, 400));
        disp.add(mid_panel1, BorderLayout.CENTER);

        mid_panel2 = new JPanel();
        setPanel(mid_panel2, Color.LIGHT_GRAY, new BoxLayout(mid_panel2, BoxLayout.Y_AXIS), new Dimension(100, 400));
        disp.add(mid_panel2, BorderLayout.EAST);

        first_suit_lbl = new JLabel();
        first_no_lbl = new JLabel("");

        mid_panel1.add(first_suit_lbl);
        mid_panel1.add(first_no_lbl);

        setLabelFont(first_suit_lbl, Color.CYAN, 100, 20, 120, 150, 20, false);
        setLabelFont(first_no_lbl, Color.CYAN, 100, 60, 120, 150, 20, true);

        second_suit_lbl = new JLabel("");
        second_no_lbl = new JLabel("");

        mid_panel1.add(second_suit_lbl);
        mid_panel1.add(second_no_lbl);

        setLabelFont(second_suit_lbl, Color.CYAN, 300, 20, 120, 150, 20, false);
        setLabelFont(second_no_lbl, Color.CYAN, 300, 60, 120, 150, 20, true);

        third_suit_lbl = new JLabel("");
        third_no_lbl = new JLabel("");

        mid_panel1.add(third_suit_lbl);
        mid_panel1.add(third_no_lbl);

        setLabelFont(third_suit_lbl, Color.CYAN, 500, 20, 120, 150, 20, false);
        setLabelFont(third_no_lbl, Color.CYAN, 500, 60, 120, 150, 20, true);

        fourth_suit_lbl = new JLabel("");
        fourth_no_lbl = new JLabel("");

        mid_panel1.add(fourth_suit_lbl);
        mid_panel1.add(fourth_no_lbl);

        setLabelFont(fourth_suit_lbl, Color.CYAN, 700, 20, 120, 150, 20, false);
        setLabelFont(fourth_no_lbl, Color.CYAN, 700, 60, 120, 150, 20, true);


        bottom_panel = new JPanel();
        setPanel(bottom_panel, Color.GREEN, new BoxLayout(bottom_panel, BoxLayout.X_AXIS), new Dimension(1300, 100));
        disp.add(bottom_panel, BorderLayout.SOUTH);


        btn_0 = new JToggleButton();
        btns.add(btn_0);
        btn_1 = new JToggleButton();
        btns.add(btn_1);
        btn_2 = new JToggleButton();
        btns.add(btn_2);
        btn_3 = new JToggleButton();
        btns.add(btn_3);
        btn_4 = new JToggleButton();
        btns.add(btn_4);
        btn_5 = new JToggleButton();
        btns.add(btn_5);
        btn_6 = new JToggleButton();
        btns.add(btn_6);
        btn_7 = new JToggleButton();
        btns.add(btn_7);
        btn_8 = new JToggleButton();
        btns.add(btn_8);
        btn_9 = new JToggleButton();
        btns.add(btn_9);
        btn_10 = new JToggleButton();
        btns.add(btn_10);
        btn_11 = new JToggleButton();
        btns.add(btn_11);
        btn_12 = new JToggleButton();
        btns.add(btn_12);

        for (int i = 0; i < 13; i++) {
            setButton(btns.get(i), 10, 50, 20);
            int finalI = i;
            bottom_panel.add(btns.get(i));
        }

        pass = new JButton("　パス　");
        decide = new JButton(("　決定　"));

        pass.setPreferredSize(new Dimension(100, 200));
        decide.setPreferredSize(new Dimension(100, 200));

        pass.addActionListener((e) -> passFlag = 1);
        decide.addActionListener((e) -> decideFlag = 1);

        decide.setEnabled(false);

        mid_panel2.add(pass);
        mid_panel2.add(decide);

        disp.setVisible(true);

    }

    /**
     * プレイヤリストを取得する
     *
     * @return プレイヤリスト
     */
    public ArrayList<Player> getPlayers() {
        return players;
    }

    /**
     * プレイヤリストをセットする
     *
     * @param players
     */
    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    /**
     * 場札を取得する
     *
     * @return 場札
     */
    public ArrayList<ArrayList<Card>> getBa() {
        return ba;
    }

    /**
     * 場札をセットする
     *
     * @param ba
     */
    public void setBa(ArrayList<ArrayList<Card>> ba) {
        this.ba = ba;
    }

    /**
     * プレイヤをプレイヤリストに追加する
     *
     * @param player
     */
    public void addPlayer(Player player) {
        players.add(player);
    }

    /**
     * 場札にカードを出す
     *
     * @param te
     */
    public void addBa(ArrayList<Card> te) {
        ba.add(te);
    }

    /**
     * 大富豪のルール（カードの数字の強さ）
     *
     * @param te
     * @return カードが出せるならtrue，出せないならfalse
     */
    public boolean rule(ArrayList<Card> te) {
        switch (te.get(0).getNumber()) {
            case 0:
                return true;

            case 1:
                if (ba.size() == 0 || ba.get(ba.size() - 1).get(0).getNumber() > 2) {
                    return true;
                } else {
                    return false;
                }

            case 2:
                if (ba.size() == 0) {
                    return true;
                }
                if (ba.get(ba.size() - 1).get(0).getNumber() != 2 && ba.get(ba.size() - 1).get(0).getNumber() != 0) {
                    return true;
                } else {
                    return false;
                }

            default:
                if (ba.size() == 0 || te.get(0).getNumber() > ba.get(ba.size() - 1).get(0).getNumber()) {
                    if (ba.size() > 0 && ba.get(ba.size() - 1).get(0).getNumber() <= 2) {
                        return false;
                    } else
                        return true;
                } else {
                    return false;
                }

        }

    }

    /**
     * 大富豪の役を実装する
     *
     * @param number カードの数字
     * @param i      プレイヤの番号(0ならユーザ，0以外はCPU)
     */
    public void yaku(int number, int i) {
        int selectedCounter = 0;
        switch (number) {
            case 7:
                msg_lbl.setText("!!! 7渡し !!!");
                try {
                    Thread.sleep(1 * 1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                int j = 0;
                if (i == 0) {
                    msg_lbl.setText("カードを1枚次の人に渡すことができます．どれを渡しますか？(渡さない場合:パス)");
                    for (JToggleButton btn : btns) {
                        btn.setSelected(false);
                    }
                    decide.setEnabled(false);
                    while (true) {
                        if (passFlag == 1) {
                            j = -1;
                            break;
                        }
                        decideFlag = 0;
                        selectedCounter = 0;
                        for (JToggleButton btn : btns) {
                            if (btn.isSelected()) {
                                selectedCounter++;
                            }
                        }
                        if (selectedCounter == 1) {
                            decide.setEnabled(true);
                            if (decideFlag == 1) {
                                int index = 0;
                                for (JToggleButton btn : btns) {
                                    if (btn.isSelected()) {
                                        j = index;
                                    }
                                    index++;
                                }
                                break;
                            }
                        }
                    }
                } else {
                    j = rand.nextInt(players.get(i).getTefuda().size());
                }
                if (j == -1) {
                    break;
                } else {
                    Card c = players.get(i).getTefuda().get(j);
                    players.get(i).getTefuda().remove(c);
                    if (i == players.size() - 1) {
                        players.get(0).getTefuda().add(c);
                    } else {
                        players.get(i + 1).getTefuda().add(c);
                    }
                    msg_lbl.setText(c.toString() + "を渡しました．");
                    try {
                        Thread.sleep(1 * 1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                break;

            case 8:
                msg_lbl.setText("!!! 8切り !!!");
                try {
                    Thread.sleep(1 * 1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                msg_lbl.setText("場札を流します．");
                ba.clear();
                initBa();
                try {
                    Thread.sleep(1 * 1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                msg_lbl.setText("もう一度");
                break;

            case 10:
                msg_lbl.setText("!!! 10捨て !!!");
                try {
                    Thread.sleep(1 * 1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                int k = 0;
                if (i == 0) {
                    msg_lbl.setText("カードを1枚捨てることができます．どれを捨てますか？(捨てない場合:パス)");
                    for (JToggleButton btn : btns) {
                        btn.setSelected(false);
                    }
                    decide.setEnabled(false);
                    while (true) {
                        if (passFlag == 1) {
                            k = -1;
                            break;
                        }
                        decideFlag = 0;
                        selectedCounter = 0;
                        for (JToggleButton btn : btns) {
                            if (btn.isSelected()) {
                                selectedCounter++;
                            }
                        }
                        if (selectedCounter == 1) {
                            decide.setEnabled(true);
                            if (decideFlag == 1) {
                                int index = 0;
                                for (JToggleButton btn : btns) {
                                    if (btn.isSelected()) {
                                        k = index;
                                    }
                                    index++;
                                }
                                break;
                            }
                        }
                    }
                } else {
                    k = rand.nextInt(players.get(i).getTefuda().size());
                }
                if (k == -1) {
                    break;
                } else {
                    Card c = players.get(i).getTefuda().get(k);
                    players.get(i).getTefuda().remove(c);
                    msg_lbl.setText(c.toString() + "を捨てました．");
                    try {
                        Thread.sleep(1 * 1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                break;
        }

    }

    /**
     * 大富豪ゲームをスタートする
     */
    public void startGame() {
        msg_lbl.setText("■大富豪を開始します．");
        yamafuda.createFulDeck();
        yamafuda.shuffle();

        int n = players.size();
        int m = yamafuda.size() / n;

        try {
            Thread.sleep(2 * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        msg_lbl.setText("■手札を配ります．");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                players.get(i).getTefuda().add(yamafuda.takeCard());
            }
        }

        doDaifugo();

    }

    // パネルのフォント設定を行うメソッド
    public static void setPanel(JPanel panel, Color color, BoxLayout layout, Dimension dimension) {
        panel.setBackground(color);        // 背景色を設定
        panel.setLayout(layout);           // レイアウトを設定
        panel.setPreferredSize(dimension); // 表示サイズを設定

        return;
    }

    // ラベルのフォント設定を行うメソッド
    public static void setLabelFont(JLabel label, Color clr,
                                    int x_pos, int y_pos,
                                    int x_size, int y_size,
                                    int strSize, boolean opq) {
        label.setBackground(clr);        // 背景色を設定
        label.setLocation(x_pos, y_pos); // 表示位置を設定
        label.setSize(x_size, y_size);   // 表示サイズを設定
        label.setFont(new Font("ＭＳ ゴシック", Font.PLAIN, strSize)); // 書式、文字サイズを設定
        label.setHorizontalAlignment(JLabel.CENTER); // 水平方向中央揃え
        label.setVerticalAlignment(JLabel.CENTER);   // 垂直方向中央揃え
        label.setOpaque(opq); // ラベルの透明性を設定(true＝不透明、false＝透明)

        return;
    }

    // ボタンの設定を行うメソッド
    public static void setButton(JToggleButton btn, int x_size, int y_size, int strSize) {
        btn.setPreferredSize(new Dimension(x_size, y_size));      // 表示サイズを設定
        btn.setFont(new Font("ＭＳ ゴシック", Font.PLAIN, strSize)); // 書式、文字サイズを設定

        return;
    }

    /**
     * 大富豪をする
     *
     * @return 手札がなくなったらtrue
     */
    public void doDaifugo() {
        int count1 = 0;
        int count2 = 0;

        try {
            Thread.sleep(2 * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        while (true) {
            if (count1 == players.size() - 1) {
                System.out.println("他のプレイヤが全員パスしたので場のカードを流します．");
                msg_lbl.setText("他のプレイヤが全員パスしたので場のカードを流します．");
                ba.clear();
                initBa();
                count1 = 0;
                try {
                    Thread.sleep(1 * 1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            int s;
            if (ba.size() == 0) {
                s = 0;
            } else {
                s = ba.get(ba.size() - 1).size();
            }

            System.out.println("[場札]");
            if (ba.size() > 0) {
                int i = 0;
                initBa();
                for (Card t : ba.get(ba.size() - 1)) {
                    t.show();
                    arrangeBa(i, t);
                    i++;
                }
            }
            players.get(0).sort();
            for (int i = 0; i < players.get(0).tefuda.size(); i++) {
                btns.get(i).setText(players.get(0).tefuda.get(i).toString());
                btns.get(i).setEnabled(true);
            }
            for (int j = players.get(0).tefuda.size(); j < 13; j++) {
                btns.get(j).setText("");
                btns.get(j).setEnabled(false);
            }

            if (players.get(0).getTefuda().size() == 0) {  //最後に8切りをして手札がなくなった時
                System.out.println("◎手札がなくなりました．" + players.get(0).getName() + "の勝ちです!");
                msg_lbl.setText("◎手札がなくなりました．" + players.get(0).getName() + "の勝ちです!");
                return;
            }
            ArrayList<Card> te1 = chooseYourTe(s);

            if (te1.size() > 0) {
                while (rule(te1) == false) {
                    System.out.println("そのカードは出せません．別のカードを出す，もしくはパスしてください．");
                    msg_lbl.setText("そのカードは出せません．別のカードを出す，もしくはパスしてください．");
                    try {
                        Thread.sleep(2 * 1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    te1 = chooseYourTe(s);
                    if (te1.size() == 0) {
                        break;
                    }
                }

                if (te1.size() == 0) {
                    System.out.println("パスしました．");
                    msg_lbl.setText("パスしました．");
                    count1++;

                } else {

                    addBa(te1);
                    int l = 0;
                    initBa();
                    for (Card v : te1) {
                        v.show();
                        arrangeBa(l, v);
                        l++;
                    }

                    yaku(te1.get(0).getNumber(), 0);

                    for (Card u : te1) {
                        players.get(0).getTefuda().remove(u);
                    }

                    if (te1.get(0).getNumber() == 8) {
                        continue;
                    }

                    count1 = 0;

                }
            } else if (te1.size() == 0) {
                System.out.println("パスしました．");
                msg_lbl.setText("パスしました．");
                count1++;
            }

            if (players.get(0).getTefuda().size() == 0) {
                System.out.println("◎手札がなくなりました．" + players.get(0).getName() + "の勝ちです!");
                msg_lbl.setText("◎手札がなくなりました．" + players.get(0).getName() + "の勝ちです!");
                return;
            }

            for (int i = 1; i < players.size(); i++) {
                try {
                    Thread.sleep(2 * 1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("◎CPU" + i + "の番です．[残り枚数：" + players.get(i).getTefuda().size() + "]");
                msg_lbl.setText("◎CPU" + i + "の番です．[残り枚数：" + players.get(i).getTefuda().size() + "]");
                try {
                    Thread.sleep(2 * 1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if (count1 == players.size() - 1) {
                    System.out.println("他のプレイヤが全員パスしたので場のカードを流します．");
                    msg_lbl.setText("他のプレイヤが全員パスしたので場のカードを流します．");
                    ba.clear();
                    initBa();
                    count1 = 0;
                    try {
                        Thread.sleep(1 * 1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

                if (ba.size() == 0) {
                    s = 0;
                } else {
                    s = ba.get(ba.size() - 1).size();
                }

                System.out.println("[場札]");
                if (ba.size() > 0) {
                    int k = 0;
                    initBa();
                    for (Card t : ba.get(ba.size() - 1)) {
                        t.show();
                        arrangeBa(k, t);
                        k++;
                    }
                }

                ArrayList<Card> te2 = players.get(i).chooseTe(s);

                if (te2.size() > 0) {
                    while (rule(te2) == false) {
                        te2 = players.get(i).chooseTe(s);
                        count2++;
                        if (count2 > 10) {
                            te2.clear();
                            break;
                        }

                        if (te2.size() == 0) {
                            break;
                        }
                    }

                    if (te2.size() == 0) {
                        System.out.println("パスしました．");
                        msg_lbl.setText("パスしました．");
                        count1++;

                    } else {

                        addBa(te2);
                        int t = 0;
                        initBa();
                        for (Card v : te2) {
                            arrangeBa(t, v);
                            t++;
                        }

                        yaku(te2.get(0).getNumber(), i);

                        for (Card u : te2) {
                            players.get(i).getTefuda().remove(u);
                        }

                        if (te2.get(0).getNumber() == 8) {
                            i--;
                            continue;
                        }

                        count1 = 0;

                    }
                } else if (te2.size() == 0) {
                    System.out.println("パスしました．");
                    msg_lbl.setText("パスしました．");
                    count1++;
                }

                if (players.get(i).getTefuda().size() == 0) {
                    System.out.println(
                            "◎" + players.get(i).getName() + "の手札がなくなりました．" + players.get(i).getName() + "の勝ちです!");
                    msg_lbl.setText(
                            "◎" + players.get(i).getName() + "の手札がなくなりました．" + players.get(i).getName() + "の勝ちです!");
                    return;
                }

            }
        }

    }

    public void arrangeBa(int i, Card t) {
        if (i == 0) {
            first_no_lbl.setBackground((Color.WHITE));
            first_no_lbl.setText(t.getStringNumber(t.getNumber()));
            first_suit_lbl.setBackground(Color.WHITE);
            first_suit_lbl.setIcon(getSuitIcon(t.getSuit()));
        } else if (i == 1) {
            second_no_lbl.setBackground((Color.WHITE));
            second_no_lbl.setText(t.getStringNumber(t.getNumber()));
            second_suit_lbl.setBackground(Color.WHITE);
            second_suit_lbl.setIcon(getSuitIcon(t.getSuit()));
        } else if (i == 2) {
            third_no_lbl.setBackground((Color.WHITE));
            third_no_lbl.setText(t.getStringNumber(t.getNumber()));
            third_suit_lbl.setBackground(Color.WHITE);
            third_suit_lbl.setIcon(getSuitIcon(t.getSuit()));
        } else if (i == 3) {
            fourth_no_lbl.setBackground((Color.WHITE));
            fourth_no_lbl.setText(t.getStringNumber(t.getNumber()));
            fourth_suit_lbl.setBackground(Color.WHITE);
            fourth_suit_lbl.setIcon(getSuitIcon(t.getSuit()));
        }
    }

    public void initBa() {

        first_no_lbl.setBackground((Color.CYAN));
        first_no_lbl.setText("");
        first_suit_lbl.setBackground(Color.CYAN);
        first_suit_lbl.setIcon(getSuitIcon(-1));

        second_no_lbl.setBackground((Color.CYAN));
        second_no_lbl.setText("");
        second_suit_lbl.setBackground(Color.CYAN);
        second_suit_lbl.setIcon(getSuitIcon(-1));

        third_no_lbl.setBackground((Color.CYAN));
        third_no_lbl.setText("");
        third_suit_lbl.setBackground(Color.CYAN);
        third_suit_lbl.setIcon(getSuitIcon(-1));

        fourth_no_lbl.setBackground((Color.CYAN));
        fourth_no_lbl.setText("");
        fourth_suit_lbl.setBackground(Color.CYAN);
        fourth_suit_lbl.setIcon(getSuitIcon(-1));
    }

    public static ImageIcon getSuitIcon(int suit) {
        ImageIcon icon;

        // マークに応じた画像を読み込んでリターンする
        switch (suit) {
            case 0: // スペード
                icon = new ImageIcon(("src/main/resources/img/cards/spade.png"));
                return icon;

            case 1: // ダイヤ
                icon = new ImageIcon(("src/main/resources/img/cards/diamond.png"));
                return icon;

            case 2: // ハート
                icon = new ImageIcon(("src/main/resources/img/cards/heart.png"));
                return icon;

            case 3: // クラブ
                icon = new ImageIcon(("src/main/resources/img/cards/club.png"));
                return icon;

            default: // マークが不正の場合
                return null;
        }
    }

    public ArrayList<Card> chooseYourTe(int s) {
        int selectedCounter = 0;
        passFlag = 0;
        players.get(0).te.clear();
        msg_lbl.setText("◎あなたの番です．カードを選択してください．選び終わったら決定ボタン，パスする場合はパスボタンを押してください．");

        for (JToggleButton btn : btns) {
            btn.setEnabled(true);
            btn.setSelected(false);
        }
        decide.setEnabled(false);
        while (true) {
            if (passFlag == 1) {
                return players.get(0).te;
            }
            decideFlag = 0;
            selectedCounter = 0;
            for (JToggleButton btn : btns) {
                if (btn.isSelected()) {
                    selectedCounter++;
                }
            }
            if (s > 0 && selectedCounter == s || s == 0 && selectedCounter > 0) {
                decide.setEnabled(true);
                if (decideFlag == 1) {
                    int index = 0;
                    players.get(0).te.clear();
                    for (JToggleButton btn : btns) {
                        if (btn.isSelected()) {
                            players.get(0).te.add(players.get(0).tefuda.get(index));
                        }
                        index++;
                    }
                    int sameFlag = 1;
                    for (Card t : players.get(0).te) {
                        if (t.getNumber() != players.get(0).te.get(0).getNumber()) {
                            sameFlag = 0;
                        }
                    }
                    if (sameFlag == 1) {
                        return players.get(0).te;
                    } else {
                        msg_lbl.setText("[エラー]同じ数字のカードを選択してください．リセットします．");
                    }
                }
            }
        }
    }

}
