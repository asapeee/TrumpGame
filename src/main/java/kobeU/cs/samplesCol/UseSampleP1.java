package kobeU.cs.samplesCol;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.TreeSet;

public class UseSampleP1 {

    public static void main(String[] args) {
        /* TreeSet で実験0 */
        TreeSet<MyPoint> pset0 = new TreeSet<>(new MyPointComp0()); /* RecordComp0 は比較器 */
        addRecordSamples(pset0); /* 要素を詰める */
        System.out.println("pset0: " + pset0); /* 表示 */

        /* TreeSet で実験1 */
        TreeSet<MyPoint> pset1 = new TreeSet<>(new Comparator<MyPoint>() {
            /* 比較器定義をその場で書く方法 */
            public int compare(MyPoint o1, MyPoint o2) {
                int sum1 = o1.sum(), sum2 = o2.sum();
                if (sum1 != sum2)
                    return Integer.compare(sum2, sum1);
                return Integer.compare(o2.y, o1.y);
            }
        });
        addRecordSamples(pset1); /* 要素をつめて */
        System.out.println("pset1: " + pset1); /* 表示 */

        /* lambda function をつかった表記 */
        TreeSet<MyPoint> pset2 = new TreeSet<>((MyPoint o1, MyPoint o2) -> {
            int sum1 = o1.sum(), sum2 = o2.sum();
            if (sum1 != sum2)
                return Integer.compare(sum2, sum1);
            return Integer.compare(o2.y, o1.y);
        });
        addRecordSamples(pset2); /* 要素をつめて */
        System.out.println("pset2: " + pset2); /* 表示 */

        /* 関数型っぽい Comparator の利用例 */
        TreeSet<MyPoint> pset3 = new TreeSet<>(
                Comparator.comparing(MyPoint::sum)
                        .thenComparingInt((MyPoint o) -> o.y).reversed());
        addRecordSamples(pset3); /* 要素をつめて */
        System.out.println("pset3: " + pset3); /* 表示 */

        /* Collections.sort を利用してみる */
        ArrayList<MyPoint> list = new ArrayList<>();
        addRecordSamples(list);
        list.sort(new MyPointComp0()); /* 実験０と同じ比較器を利用 */
        System.out.println("list: " + list); /* 表示 */
    }

    public static void addRecordSamples(Collection<MyPoint> rcol) {
        rcol.add(new MyPoint(3, 4));
        rcol.add(new MyPoint(2, 18));
        rcol.add(new MyPoint(9, 3));
        rcol.add(new MyPoint(2, 18));
        rcol.add(new MyPoint(2, 10));
    }
}
