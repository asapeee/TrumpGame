package kobeU.cs.kadaiA;

import java.awt.Point;

public class SearchNode {
    double priority;
    Point point;

    public SearchNode(double priority, Point point) {
        this.priority = priority;
        this.point = point;
    }

    double getPriority() {
        return priority;
    }
}
