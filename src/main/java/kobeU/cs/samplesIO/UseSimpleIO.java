package kobeU.cs.samplesIO;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class UseSimpleIO {
    static final int BUFSIZE = 0x1000; /* 仮に4KB */

    /**
     * ファイルを読み込んで、出力するプログラム
     *
     * @param args main method 引数は、第一がコピー元、第二がコピー先ファイル名
     * （無引数の場合は、"sample.in"の内容を "sample.out"に出力）
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        String from = (args.length < 1) ? "src/main/resources/sampleIO/sample.in": args[0];
        String to = (args.length < 2) ? "sample.out": args[1];
        copyfile(from, to);
    }

    private static void copyfile(String from, String to)
            throws IOException {
        /* FileInputStream作成, FileNotFoundException の可能性 */
        InputStream in = new FileInputStream(from);
        /* FileOutputStream作成, FileNotFoundException の可能性 */
        OutputStream out = new FileOutputStream(to);
        byte[] buf = new byte[BUFSIZE];
        try {
            while (true) {
                /* in から read。IOException の可能性 */
                int size = in.read(buf, 0, BUFSIZE);
                if (size < 0) { /* EOF なら、loop を抜けて */
                    break;
                } else {
                    /* size 分, out に出力。IOException の可能性 */
                    out.write(buf, 0, size);
                }
            }
        } finally {
            in.close();
            out.close();
        }
    }
}
