package kobeU.cs.samplesCol;

import java.util.ArrayList;
import java.util.Collection;

public class UseSampleP0 {

    public static void main(String[] args) {
        /* ArrayList で実験 */
        ArrayList<MyPoint> plist = new ArrayList<>();
        addRecordSamples(plist); /* 要素を詰める */
        System.out.println("plist: " + plist); /* 表示 */
    }

    public static void addRecordSamples(Collection<MyPoint> rcol) {
        rcol.add(new MyPoint(3, 4));
        rcol.add(new MyPoint(2, 18));
        rcol.add(new MyPoint(9, 7));
        rcol.add(new MyPoint(2, 18));
        rcol.add(new MyPoint(2, 10));
    }
}
