package kobeU.cs.samplesNet.retrofit;

import java.awt.Point;
import java.util.ArrayList;

/* テスト用のデータ構造。各自の目的に合わせて利用してください。 */

public class TestGSItem {
    ArrayList<Point> points = new ArrayList<>();
    TestGSItem(Point[] ps) {
        for(Point p:ps)
            points.add(p);
    }
    public String toString() {
        return "TestItem["+ points + "]";
    }
}
