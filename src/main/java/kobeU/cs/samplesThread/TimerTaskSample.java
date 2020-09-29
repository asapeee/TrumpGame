package kobeU.cs.samplesThread;

import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class TimerTaskSample {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Timer timer = new Timer(true);
        TimerTask task = new TimerTask() {
            public void run() {
                System.out.println("Input Please");
            }
        };
        timer.schedule(task, 1*1000, 5*1000);
        String line = in.nextLine();
        System.out.println("Your input is ...." + line);
        task.cancel();
        System.out.println("Please re-input the text...." + line);
        String line2 = in.nextLine();
        System.out.println("Your inputs are  ...." +
                (line.equals(line2)? "same.": "different."));
    }
}
