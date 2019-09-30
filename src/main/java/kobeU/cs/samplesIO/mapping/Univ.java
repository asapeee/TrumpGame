package kobeU.cs.samplesIO.mapping;

import java.util.HashMap;

import javax.xml.bind.annotation.XmlAttribute;

public class Univ {
    public String name;
    @XmlAttribute
    public int established;
    public HashMap<String, Faculty> faculties;

    public Univ(){}
    public Univ(String name, int established, HashMap<String, Faculty> faculties) {
        this.name = name;
        this.established = established;
        this.faculties = faculties;
    }

    public String toString() {
        return name +"::" + established + "::" + faculties;
    }

}
