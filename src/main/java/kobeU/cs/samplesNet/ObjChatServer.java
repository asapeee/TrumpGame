package kobeU.cs.samplesNet;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

public class ObjChatServer {

    /**
     * 指定ポートで接続を待ち、受信内容を保存するプログラム
     * 
     * @param args main method 引数は、第一がポート番号
     * （default値は、50011）
     * @throws IOException 
     * @throws ClassNotFoundException 
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        int portNum = (args.length < 1) ? 50012 : Integer.parseInt(args[0]);

        ServerSocket serverSocket = new ServerSocket(portNum);
        System.out.println("Server waitting on serverSocket " + serverSocket);
        Socket socket = serverSocket.accept(); /* 接続待ち */
        System.out.println("Connection established"); /* 接続完了 */
        /* ObjectStreamの場合、 out から先に作ってください */
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        try {
            receiveAndSend(in, out);
        } finally {
            socket.close();
            serverSocket.close();
        }
    }

    private static void receiveAndSend(ObjectInputStream in, ObjectOutputStream out)
            throws IOException, ClassNotFoundException {
        Scanner userIn = new Scanner(System.in);
        try {
            Random randomeMaker = new Random(System.currentTimeMillis());

            while (true) {
                ObjMessage recvMsg = (ObjMessage) in.readObject();
                System.out.println("Client msg: " + recvMsg);
                if (recvMsg.toFinish)
                    break;

                System.out.print("Server Input> ");
                System.out.flush();
                String userInput = userIn.nextLine();
                ObjMessage sendMsg = new ObjMessage(userInput,
                        new Date(),
                        randomeMaker.nextDouble(),
                        userInput.equals("exit"));
                System.out.println("Server will send msg: " + sendMsg);
                out.writeObject(sendMsg);
                out.flush();
                out.reset();
                if (sendMsg.toFinish)
                    break;

            }
            System.out.println("normal termination");
        } finally {
            in.close();
            out.close();
        }
    }
}
