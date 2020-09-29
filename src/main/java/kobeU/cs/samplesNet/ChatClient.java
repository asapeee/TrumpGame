package kobeU.cs.samplesNet;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {

    /**
     * サーバに接続し、チャットを開始。交互にデータの送受信。
     * 最初のトークは、クライアントから。どちらかが、"exit"を入力したら終了。
     * 
     * @param args main method 引数は、 第1、2引数が、送信先ホスト名とポート番号
     * （それぞれ、default 値は, "localhost", 50011）
     * @throws IOException 
     */
    public static void main(String[] args) throws IOException {
        String hostName = (args.length < 1) ? "localhost" : args[0];
        int portNum = (args.length < 2) ? 50011 : Integer.parseInt(args[1]);

        /* IP address の取得 */
        InetAddress host = InetAddress.getByName(hostName);
        /* Socket の生成 */
        System.out.println("Connecting to " + host + ":" + portNum);
        Socket socket = new Socket(host, portNum);
        System.out.println("Connection established");
        try {
            sendAndReceive(new Scanner(socket.getInputStream()),
                    new PrintWriter(socket.getOutputStream()));
        } finally {
            socket.close();
        }
    }

    private static void sendAndReceive(Scanner netIn, PrintWriter netOut)
            throws IOException {
        Scanner userIn = new Scanner(System.in);
        try {
            while (true) {
                System.out.print("Client Input> ");
                System.out.flush();
                String userMsg = userIn.nextLine();
                netOut.println(userMsg);
                netOut.flush();
                if (userMsg.equals("exit"))
                    break;

                String recvMsg = netIn.nextLine();
                System.out.println("Server says: " + recvMsg);
                if (recvMsg.equals("exit"))
                    break;
            }
            System.out.println("normal termination");
        } finally {
            netIn.close();
            netOut.close();
        }
    }
}
