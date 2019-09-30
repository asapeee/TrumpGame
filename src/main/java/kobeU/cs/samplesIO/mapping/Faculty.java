package kobeU.cs.samplesIO.mapping;

import java.util.List;

public class Faculty {
    public int established;
    public List<String> departments;

    public Faculty(){}
    public Faculty(int established, List<String> departments) {
        this.established = established;
        this.departments = departments;
    }

    public String toString() {
        return ""+ established + ":" + departments;
    }
}
