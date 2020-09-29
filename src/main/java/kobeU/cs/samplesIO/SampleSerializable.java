package kobeU.cs.samplesIO;

import java.io.Serializable;
import java.util.List;

public class SampleSerializable implements Serializable {
    private static final long serialVersionUID = -8502149233835734087L;
    double x;
    String name;
    List<String> elem;
    int p = 10;

    public SampleSerializable(double x, String name, List<String> elem) {
        super();
        this.x = x;
        this.name = name;
        this.elem = elem;
    }

    public String toString() {
        return "[SampleSerializable with " + x + ", " + name + ",  and " + elem + "]";
    }
}
