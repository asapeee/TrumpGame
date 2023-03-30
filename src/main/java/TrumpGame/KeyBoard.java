package TrumpGame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class KeyBoard {

    public static int inputNumber() {
        int number;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            String line = br.readLine();
            number = Integer.parseInt(line);
        } catch (NumberFormatException e) {
            System.err.println("フォーマット例外です．もう一度．");
            number = inputNumber(); // 再起呼び出し
        } catch (IOException e) {
            System.err.println("入出力例外です．もう一度．");
            number = inputNumber(); // 再起呼び出し
        }

        return number;
    }

    public static String inputString() {
        String line;

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            line = br.readLine();
        } catch (IOException e) {
            System.err.println("エラー：入出力例外です．もう一度入力．");
            line = inputString();
        }

        return line;
    }

}
