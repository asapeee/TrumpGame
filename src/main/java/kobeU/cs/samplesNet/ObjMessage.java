package kobeU.cs.samplesNet;

import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;
import java.util.Date;

public class ObjMessage implements Serializable {

    private static final long serialVersionUID = 3249940705431916304L;
    public final String body;
    public final Date date;
    public final double dval;
    public final boolean toFinish;

    public ObjMessage(String body, Date date, double dval, boolean toFinish) {
        this.body = body;
        this.date = date;
        this.dval = dval;
        this.toFinish = toFinish;
    }

    public String toString() {
        StringWriter out0 = new StringWriter();
        PrintWriter out = new PrintWriter(out0);
        out.println("[ObjMsg:" + body + ",");
        out.println(" Date:" + date + ",");
        out.println(" random double:" + dval + ",");
        out.println(" (finish?:" + toFinish + ")]");
        out.close();
        return out0.toString();
    }
}
