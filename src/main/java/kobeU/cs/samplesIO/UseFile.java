package kobeU.cs.samplesIO;

import java.io.File;
import java.io.IOException;

public class UseFile {
    /**
     * 指定directory 内にあるファイル一覧を出力するプログラム
     * （デフォルトで、カレントdirectory を検索）
     * @param args: main method の第一引数として、検索対象のdirectory名指定可能
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        String target = ".";
        if (args.length > 1)
            target = args[0];
        File file = new File(target);
        processFileOrDir(file);
    }

    private static void processFileOrDir(File file) throws IOException {
        if (!file.exists())
            return;
        if (file.isDirectory()) {
            File[] children = file.listFiles();
            for (File child : children) {
                processFileOrDir(child);
            }
        } else {
            System.out.println(file.getCanonicalPath());
        }
    }
}
