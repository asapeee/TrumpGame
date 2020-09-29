package kobeU.cs.samplesNet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ChatServer {

    /**
     * 指定ポートで接続を待ち、受信内容を保存するプログラム
     * 
     * @param args main method 引数は、第一がポート番号
     * （default値は、50011）
     * @throws IOException 
     */
    public static void main(String[] args) throws IOException {
        int portNum = (args.length < 1) ? 50011 : Integer.parseInt(args[0]);

        ServerSocket serverSocket = new ServerSocket(portNum);
        System.out.println("Server waitting on serverSocket " + serverSocket);
        Socket socket = serverSocket.accept(); /* 接続待ち */
        System.out.println("Connection established"); /* 接続完了 */
        try {
            receiveAndSend(new Scanner(socket.getInputStream()),
                    new PrintWriter(socket.getOutputStream()));
        } finally {
            socket.close();
            serverSocket.close();
        }
    }

    private static void receiveAndSend(Scanner netIn, PrintWriter netOut)
            throws IOException {
        Scanner userIn = new Scanner(System.in);
        try {
            while (true) {
                String recvMsg = netIn.nextLine();
                System.out.println("Client says: " + recvMsg);
                if (recvMsg.equals("exit"))
                    break;

                System.out.print("Server Input> ");
                System.out.flush();
                String userMsg = userIn.nextLine();
                netOut.println(userMsg);
                netOut.flush();
                if (userMsg.equals("exit"))
                    break;
            }
            System.out.println("normal termination");
        } finally {
            netIn.close();
            netOut.close();
        }
    }
}
