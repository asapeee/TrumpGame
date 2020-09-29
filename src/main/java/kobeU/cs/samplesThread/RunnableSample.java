package kobeU.cs.samplesThread;
import java.util.Scanner;

public class RunnableSample {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Thread threadB = new Thread(new Runnable() {
            public void run() {
                try {
                    while(true) {
                        System.out.println("Input Please");
                        Thread.sleep(3*1000); //3sec
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        // threadB.setDaemon(true);
        threadB.start();
        String line = in.nextLine();
        System.out.println("Your input is ...." + line);
    }
}
