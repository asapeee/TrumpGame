package kobeU.cs.samplesCol;

public class MyPoint {
    final int x;
    final int y;

    public MyPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int sum() {
        return x + y;
    }

    /* 以下をコメントアウトした場合の実行も試しましょう*/
    public String toString() {
        return "(x: " + x + ", y: " + y + ")";
    }
}
