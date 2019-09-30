package kobeU.cs.samplesIO.mapping;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.HashMap;

import javax.xml.bind.JAXB;

import com.google.gson.Gson;

public class UseGson {
    public static void main(String[] args) {
        HashMap<String,Faculty> faculties = new HashMap<>();
        faculties.put("工学部",
                new Faculty(1953, Arrays.asList("情報知能工学科", "電気電子工学科")));
        Univ univ = new Univ("神戸大学", 1949, faculties);

        /* Obj to JSon */
        Gson gson = new Gson();
        String univ2json = gson.toJson(univ);
        System.out.println(univ2json);

        /* Json to Obj */
        Univ univFromJson = gson.fromJson(univ2json, Univ.class);
        System.out.println(univFromJson);

        /* Obj to XML */
        StringWriter out = new StringWriter();
        JAXB.marshal(univ, out);
        String univ2xml = out.toString();
        System.out.println(univ2xml);

        /* XML to Obj */
        StringReader in = new StringReader(univ2xml);
        Univ univFromXml = JAXB.unmarshal(in, Univ.class);
        System.out.println(univFromXml);

    }

}
