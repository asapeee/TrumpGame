package kobeU.cs.samplesNet;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

public class ObjChatClient {

    /**
     * サーバに接続し、チャットを開始。交互にデータの送受信。
     * 最初のトークは、クライアントから。どちらかが、"exit"を入力したら終了。
     * 
     * @param args main method 引数は、 第1、2引数が、送信先ホスト名とポート番号
     * （それぞれ、default 値は, "localhost", 50011）
     * @throws IOException 
     * @throws ClassNotFoundException 
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String hostName = (args.length < 1) ? "localhost" : args[0];
        int portNum = (args.length < 2) ? 50012 : Integer.parseInt(args[1]);

        /* IP address の取得 */
        InetAddress host = InetAddress.getByName(hostName);
        /* Socket の生成 */
        System.out.println("Connecting to " + host + ":" + portNum);
        Socket socket = new Socket(host, portNum);
        System.out.println("Connection established");
        /* ObjectStreamの場合、 out から先に作ってください */
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        try {
            sendAndReceive(in, out);
        } finally {
            socket.close();
        }
    }

    private static void sendAndReceive(ObjectInputStream in, ObjectOutputStream out)
            throws IOException, ClassNotFoundException {
        Scanner userIn = new Scanner(System.in);
        try {
            Random randomeMaker = new Random(System.currentTimeMillis());

            while (true) {
                System.out.print("Client Input> ");
                System.out.flush();
                String userInput = userIn.nextLine();
                ObjMessage sendMsg = new ObjMessage(userInput,
                        new Date(),
                        randomeMaker.nextDouble(),
                        userInput.equals("exit"));
                System.out.println("Client will send msg: " + sendMsg);
                out.writeObject(sendMsg);
                out.flush();
                out.reset();
                if (sendMsg.toFinish)
                    break;

                ObjMessage recvMsg = (ObjMessage) in.readObject();
                System.out.println("Server msg: " + recvMsg);
                if (recvMsg.toFinish)
                    break;
            }
            System.out.println("normal termination");
        } finally {
            in.close();
            out.close();
        }
    }
}
