package kobeU.cs.samplesIO;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class UseBufferedIO {
    /**
     * テキストファイルを読み込んで、行番号をつけて、ファイルと標準出力に出力
     *
     * @param args main  引数は、第一／二がコピー元／先ファイル名
     * （無引数の場合は、"sampleIn.txt"の内容を "sampleOut.txt"に出力）
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        String from = (args.length < 1) ? "src/main/resources/sampleIO/sample.in": args[0];
        String to = (args.length < 2) ? "sample2.out" : args[1];
        copyfile(from, to);
    }

    private static void copyfile(String from, String to)
            throws IOException {
        /* File読み込み用Reader */
        BufferedReader in = new BufferedReader(new FileReader(from));
        /* File出力用PrintWriter */
        PrintWriter out = new PrintWriter(to);
        try {
            String line;
            int i = 0;
            while ((line = in.readLine()) != null) { /* 一行read */
                String outline = i + " " + line; /* 行番号つけて */
                System.out.println(outline); /* 標準出力にprint */
                out.println(outline); /* ファイルにもprint */
                i++;
            }
        } finally {
            in.close();
            out.close(); /* PrintWriter の close()処理 */
        }
    }
}
