package kobeU.cs.samplesIO;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class UseObjectInputStream {


    /**
     * file から、obj を次々serializeして、toString()
     * @param args main method 第一引数がファイル名
     * (省略時は、"sample.ser" を用いる)
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String filename = (args.length < 1) ? "sample.ser" : args[0];
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename));
        try {
            int num = in.readInt();
            System.out.println("num:" + num);
            Object obj1 = in.readObject();
            System.out.println("Obj1:" + obj1);
            SampleSerializable obj2 = (SampleSerializable) in.readObject();
            System.out.println("Obj2:" + obj2);
            System.out.println("obj1==obj2.elem: " + (obj1 == obj2.elem));
        } finally {
            in.close();
        }
    }
}
