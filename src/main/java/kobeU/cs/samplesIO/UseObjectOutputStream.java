package kobeU.cs.samplesIO;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class UseObjectOutputStream {
    /**
     * ArrayList<String>をserialize
     * @param args main method 第一引数がファイル名
     * (省略時は、"sample.ser" を用いる)
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        String filename = (args.length < 1) ? "sample.ser" : args[0];
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename));
        ArrayList<String> obj = createSampleObj();
        out.writeInt(2); /* 数字も保存可能。*/
        out.writeObject(obj); /* 1回目 */
        out.writeObject(new SampleSerializable(1.0, "name0", obj));/* 2回目 */
        out.close();
    }

    private static ArrayList<String> createSampleObj() {
        ArrayList<String> result = new ArrayList<>();
        result.add("java");
        result.add("c");
        result.add("python");
        return result;
    }
}
