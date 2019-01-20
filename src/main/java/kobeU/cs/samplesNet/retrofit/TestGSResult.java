package kobeU.cs.samplesNet.retrofit;

public class TestGSResult<T> {
    public boolean success;
    public String text;
    public T json;
    public String toString() {
        return "" + success + ":" + text + ":"+json;
    }
}
