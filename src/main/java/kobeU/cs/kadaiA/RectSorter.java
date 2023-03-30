package kobeU.cs.kadaiA;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

public class RectSorter {

    public static double getArea(Rectangle rect) {
        return rect.getWidth() * rect.getHeight();
    }
    public static double getDistance(Rectangle rect, Point p) {
        return p.distance(rect.getX() + rect.getWidth() / 2.0,
                rect.getY() + rect.getHeight() / 2.0);
    }

    public static void sortByArea(List<? extends Rectangle> list) {
        // TODO
        list.sort((Rectangle o1, Rectangle o2) -> {
            return Double.compare(getArea(o2), getArea(o1));
        });
    }
    public static void sortByDistance(List<? extends Rectangle> list, Point p) {
        // TODO
        list.sort((Rectangle o1, Rectangle o2) -> {
            return Double.compare(getDistance(o1, p), getDistance(o2, p));
        });
    }
    public static void customPrintList(String tag, java.util.List<Rectangle> list, Function<Rectangle, String> func) {
        System.out.println("----   " + tag + " ---");
        list.forEach((elem)->{
            System.out.println(func.apply(elem));
        });
        System.out.println("-DONE: "  + tag + " ---");
    }

    public static void sortCheck(List<? extends Rectangle> sorted, Function<Rectangle, Double> func) {
        // TODO
        double min = 0;
        double max = 10000;
        int count = 0;
        int a;

        if(func.apply(sorted.get(0))> func.apply(sorted.get(1))){
            a = 1;
        }else{
            a = 0;
        }

        for(Rectangle j: sorted){
            if(a == 0) {
                if (func.apply(j) < min) {
                    System.out.println("error");
                    count++;
                }
                min = func.apply(j);
            }else{
                if (func.apply(j) > max) {
                    System.out.println("error");
                    count++;
                }
                max = func.apply(j);
            }
        }
        if(count == 0){
            System.out.println("Sort check PASSED.");
        }
    }



    public static void main(String[] args) {
        Random rand = new Random(2022);
        ArrayList<Rectangle> list = new ArrayList<>();
        for(int i=0; i< 10; i++) {
            int x = i*10 % 80;
            int y = i*25 % 80;
            int height = 10 + rand.nextInt(12);
            int width = 10 + rand.nextInt(12);
            list.add(new Rectangle(new Point(x, y), new Dimension(width, height)));
        }
        Point p1 = new Point(50, 50);
        Point p2 = new Point(0, 0);

        customPrintList("Init", list, (elem)-> elem.toString());
        sortByArea(list);
        customPrintList("Sort by area (in descending order)" , list, (elem)->getArea(elem) + " " + elem); // 面積も表示されるように改変すること
        // 面積が大きい順にソートされているか sortChekc で確認
        sortCheck(list, (elem) -> getArea(elem));
        sortByDistance(list, p1);
        customPrintList("Sort by closest to " + p1, list, (elem)->getDistance(elem, p1) + " " + elem); // p1 からの距離も表示されるように改変すること
        // p1 からの距離が小さい順にソートされているか sortCheck で確認
        sortCheck(list, (elem) -> getDistance(elem, p1));
        sortByDistance(list, p2);
        customPrintList("Sort by closest to " + p2, list, (elem)->getDistance(elem, p2) + " " +  elem); // p2 からの距離も表示されるように改変すること
        // p2 からの距離が小さい順にソートされているか sortCheck で確認
        sortCheck(list, (elem) -> getDistance(elem, p2));
    }
}

