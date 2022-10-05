package kobeU.cs.kadaiA21;

import java.awt.Point;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Solver {
    private ArrayList<Point> points; /* 与えられた点の集合 */
    private Point goal;              /* ゴールの点 */

    public static final double OneHop = 10.00001;

    void setupBoard(Scanner in, int n) {
        points = new ArrayList<>(n);
        for(int i=0; i< n; i++) {
            int x = in.nextInt();
            int y = in.nextInt();
            points.add(new Point(x,y));
        }
        goal = points.get(n-1);
    }

    public double solve() {
        PriorityQueue<SearchNode> q= new PriorityQueue<>(); /* 優先度キュー */
        HashMap<Point,Double> visited = new HashMap<>();  /* 探索済みの点の管理 */
        System.out.println("Points: " + points);
        System.out.println("Goal: " + goal);
        Point start = new Point(0,0);
        SearchNode rootNode = new SearchNode(0.0,start);
        System.out.println("search from " + rootNode);
        // TODO
        return 0.0;
    }

    public static void main(String[] args) throws IOException {
    	File dataDir = new File("src/main/resources/shortestPath");
    	File inFile = new File(dataDir, "sample.in");
        File ansFile = new File(dataDir, "sample.ans");
        Scanner in = new Scanner(new FileReader(inFile));
        Scanner ansIn = new Scanner(new FileReader(ansFile));

        int totalCount = 0, failCount = 0;
        while(true) {
            int n = in.nextInt();
            if(n==0) break;
            Solver solver = new Solver();
            solver.setupBoard(in, n);

            double result = solver.solve();
            double ans = ansIn.nextDouble();

            if(Math.abs(ans-result) > 0.001) {
                failCount++;
                System.out.println("You failed data No. "+totalCount
                        +" (result: "+ result+", ans: "+ans+")");
            } else {
                System.out.println("You passed data No. "+totalCount+
                        " (result: "+ result + ", ans: "+ans+")");

            }
            totalCount++;
        }
        in.close();
        ansIn.close();
        if(failCount==0)
            System.out.println("Congratulation! You passed all dataset!");
    }
}



