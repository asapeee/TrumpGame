package kobeU.cs.samplesCol;

import java.util.Comparator;

public class MyPointComp0 implements Comparator<MyPoint> {

    public int compare(MyPoint o1, MyPoint o2) {
        int sum1 = o1.sum();
        int sum2 = o2.sum();
        if (sum1 != sum2)
            return Integer.compare(sum2, sum1);
        return Integer.compare(o2.y, o1.y);
    }
}
