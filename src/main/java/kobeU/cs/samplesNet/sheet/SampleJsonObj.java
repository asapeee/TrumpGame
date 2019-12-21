package kobeU.cs.samplesNet.sheet;


import java.awt.Point;
import java.util.ArrayList;

/**
 * Sample データ構造。 JSON 相当の文書のサンプル。Gson で変換予定
 * @author kamada
 *
 */
public class SampleJsonObj {
    ArrayList<Point> points = new ArrayList<>();
    SampleJsonObj(Point[] ps) {
        for(Point p:ps)
            points.add(p);
    }
    public String toString() {
        return "TestItem["+ points + "]";
    }
}
