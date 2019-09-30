package kobeU.cs.samplesGUI;

import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Simple0 extends JFrame {

    private static final long serialVersionUID = 1L;
    JPanel pane; // ウィンドウの（メニューバーなどでない）本体部分。
    JButton button; // ボタン一つ
    JTextField text0, text1; // テキストフィールド２つ

    public Simple0() {
        super();
        initialize(); // なんとなく、別メソッドに分けてみただけ
    }

    void initialize() {
        setTitle("Simple0"); // ウィンドウタイトルをつけて
        setSize(300, 200); // サイズをつけて
        // "x"印をおされたら、Frameを終了して
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        button = new JButton(); // ボタン生成と、テキスト・サイズ設定
        button.setText("Push Here!");
        button.setPreferredSize(new Dimension(100, 50));
        text0 = new JTextField(); // テキストフィールドを作成
        text0.setPreferredSize(new Dimension(300, 50));
        text1 = new JTextField(); // テキストフィールドをもう一つ。
        text1.setPreferredSize(new Dimension(300, 50));

        pane = new JPanel(); // ウィンドウ本体部分を作成
        // レイアウト設定（上から順に並べるレイアウト）
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
        pane.add(button); // ボタンを一番上に
        pane.add(text0); // 次にtext0を
        pane.add(text1); // 最後に text1を
        this.setContentPane(pane); // pane を JFrameに登録
    }

    public static void main(String[] args) {
        // 詳しくは、次回以降解説するが、
        // main から GUI に仕事を依頼するときは、invokeLater
        // (後でやっといてね指示)を使うのが流儀。
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Simple0 frame = new Simple0(); // 生成して
                frame.setVisible(true); // 表示する
            }
        });
    }
}
