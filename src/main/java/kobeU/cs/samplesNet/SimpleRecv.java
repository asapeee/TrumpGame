package kobeU.cs.samplesNet;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleRecv {
    static final int BUFSIZE = 0x1000; /* 仮に4KB */

    /**
     * 指定ポートで接続を待ち、受信内容を保存するプログラム
     * 
     * @param args main method 引数は、第一がポート番号、第二が保存ファイル名
     * （それぞれdefault値は、50010, "sample.out"）
     * @throws IOException 
     */
    public static void main(String[] args) throws IOException {
        int portNum = (args.length < 1) ? 50010 : Integer.parseInt(args[0]);
        String filename = (args.length < 2) ? "sampleFiles/SimpleRecv.out" : args[1];
        receiveFile(portNum, filename);
    }

    private static void receiveFile(int portNum, String filename)
            throws IOException {
        /* SFileInputStream作成, FileNotFoundException の可能性 */
        ServerSocket serverSocket = new ServerSocket(portNum);
        /* 接続待ち */
        System.out.println("Server waitting on serverSocket " + serverSocket);
        Socket socket = serverSocket.accept();
        /* 接続完了 */
        System.out.println("Connection established:  " + socket);
        /* InputStream 取得 */
        InputStream in = socket.getInputStream();
        /* FileOutputStream作成 */
        OutputStream out = new FileOutputStream(filename);
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
            System.out.println("Data receive completed.");
        } finally {
            out.close(); /* out の close(), IOException の可能性 */
            socket.close();
            serverSocket.close();
        }

    }
}
