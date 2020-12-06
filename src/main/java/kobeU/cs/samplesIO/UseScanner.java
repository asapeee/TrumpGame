package kobeU.cs.samplesIO;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class UseScanner {
    /**
     * @param args: main method 第一／二引数に入力ファイル名、文字コード指定可能。
     * （省略時は、それぞれ"sampleUTF8.txt", "UTF8" 利用）
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        String filename = (args.length < 1) ? "src/main/resources/sampleIO/sample.in": args[0];
        String code = (args.length < 2) ? "UTF8" : args[1];
        process(filename, code);
    }

    private static void process(String filename, String code)
            throws IOException {

        Scanner in = new Scanner(new File(filename), code);
        try {
            while (in.hasNext()) {
                String url = in.next();
                String name = in.next();
                double lat = in.nextDouble();
                double lon = in.nextDouble();
                System.out.println("名前:" + name + ", 緯度:" + lat +
                        ", 経度" + lon + ", Wikipedia URL: " + url);
            }
        } finally {
            in.close();
        }
    }
}
