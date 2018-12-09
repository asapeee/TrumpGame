package kobeU.cs.samplesNet;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class URLAccess {
    /**
	 * 指定ポートで接続を待ち、受信内容を保存するプログラム
	 * 
	 * @param args main method 引数は、第一がポート番号、第二が保存ファイル名
	 * （それぞれdefault値は、50010, "sample.out"）
	 * @throws IOException 
	 */
    public static void main(String[] args) throws IOException {
        String urlString = (args.length < 1)?  "http://www.fine.cs.kobe-u.ac.jp/": args[0];
        URL url = new URL(urlString);
        URLConnection con = url.openConnection();
        /* InputStream 取得 */
        InputStream in0 = con.getInputStream();
        BufferedReader in = new BufferedReader(new InputStreamReader(in0));
        /* FileOutputStream作成 */
        while(true) {
            /* in から read。IOException の可能性 */
            String line = in.readLine(); 
            if (line == null) { /* 読み込み完了したらloop を抜けて */
                break;
            } else {
                /* line の表示 */
                System.out.println(line); 
            }
        }
        System.out.println();
        System.out.println("Data receive completed.");
    }		
}
