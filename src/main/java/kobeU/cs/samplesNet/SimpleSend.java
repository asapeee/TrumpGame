package kobeU.cs.samplesNet;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class SimpleSend {
    static final int BUFSIZE = 0x1000; /* 仮に4KB */

    /**
     * サーバに接続し、ファイルの内容を送信するプログラム
     * 
     * @param args main method 引数は、第一が送信ファイル、
     * 第二、三引数が、送信先ホスト名とポート番号
     * （それぞれ、default 値は, "sample.in", "localhost", 50010）
     * @throws IOException 
     */
    public static void main(String[] args) throws IOException {
        String from = (args.length < 1) ? "sampleFiles/sampleUTF8.txt" : args[0];
        String hostName = (args.length < 2) ? "localhost" : args[1];
        int portNum = (args.length < 3) ? 50010 : Integer.parseInt(args[2]);
        sendFile(from, hostName, portNum);
    }

    private static void sendFile(String from, String hostName, int portNum)
            throws IOException {
        /* IP address の取得 */
        InetAddress host = InetAddress.getByName(hostName);
        /* Socket の生成 */
        System.out.println("Connecting to " + host + ":" + portNum);
        Socket socket = new Socket(host, portNum);
        /* OutputStream の取得 */
        OutputStream out = socket.getOutputStream();

        /* FileInputStream作成: UseSimpleIO と同じ */
        InputStream in = new FileInputStream(from);
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
            System.out.println("Data send completed.");
        } finally {
            in.close();
            out.close(); /* out の close(), IOException の可能性 */
            socket.close();
        }
    }
}
