package TrumpGame;

import TrumpGame.CPU;
import TrumpGame.User;

import java.awt.*;

/**
 * 大富豪アプリケーションを表すクラス
 *
 * @author 浅野卓磨
 */
public class DaifugoApplication {
    /**
     * ユーザ
     */
    private User user = new User(null);
    /**
     * 大富豪ゲーム
     */
    private DaifugoDisplay disp = new DaifugoDisplay();


    /**
     * 空の大富豪アプリケーションインスタンスを作る
     */
    public DaifugoApplication() {
        super();
    }

    /**
     * ユーザを取得する
     *
     * @return ユーザ
     */
    public User getUser() {
        return user;
    }

    /**
     * ユーザをセットする
     *
     * @param user ユーザ
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * 大富豪ゲームを取得する
     *
     * @return 大富豪ゲーム
     */
    public DaifugoDisplay getDisplay() {
        return disp;
    }

    /**
     * 大富豪ゲームをセットする
     *
     * @param disp 大富豪ゲーム
     */
    public void setDisp(DaifugoDisplay disp) {
        this.disp = disp;
    }

    /**
     * 大富豪アプリケーションをスタートする
     */
    public void startApplication() {
        initialize();
        doGame();


    }

    /**
     * 初期設定を行う
     */
    public void initialize() {
        String name = "あなた";
        user.setName(name);
        disp.addPlayer(user);

        int n = 4;

        for (int i = 0; i < n - 1; i++) {
            CPU cpu = new CPU("CPU" + String.valueOf(i + 1));
            //System.out.println("○新しいCPU「CPU" + (i + 1) + "」を作成しました．");
            disp.addPlayer(cpu);

        }
        //System.out.println("○新しいユーザ「" + name + "」を登録しました．");

        for (int i = 0; i < n - 1; i++) {
            //System.out.println("○「CPU" + (i + 1) + "」をゲームに登録しました");
        }
    }


    /**
     * 大富豪ゲームをする
     */
    public void doGame() {
        disp.startGame();
    }


}
