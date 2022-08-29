package kobeU.cs.samplesCol;

import java.util.ArrayList;
import java.util.Collection;
import java.util.TreeSet;

public class UseSampleI {

    public static void main(String[] args) {
        /* まずは、ArrayList で実験 */
        ArrayList<Integer> ilist = new ArrayList<>();
        addIntegerSamples(ilist); /* 要素をつめて */
        System.out.print("print ilist:");
        for (Integer i : ilist) { /* 要素を順番に表示 */
            System.out.print(" " + i);
        }
        System.out.println();
        /* ArrayList の toString() を試してみましょう */
        System.out.println("ArrayList.toString() for ilist: " + ilist);

        /* 次は、TreeSet で実験 */
        TreeSet<Integer> iset = new TreeSet<>();
        addIntegerSamples(iset); /* 要素をつめて */
        System.out.print("print iset:");
        for (Integer i : iset) { /* 要素を順番に表示 */
            System.out.print(" " + i);
        }
        System.out.println();
        /* TreeSet の toString() を試してみましょう */
        System.out.println("TreeSet.toString() for iset: " + iset);
    }

	/**
	 * Integer 要素を Collection<Integer> に詰めるメソッド。
	 * ArrayList<Integer> や TreeSet<Integer> も Collection<Integer> の
	 * 一種だから、それらに使ってもらってもOK。
	 * @param icol
	 */
	public static void addIntegerSamples(Collection<Integer> icol) {
		icol.add(Integer.valueOf(3));
		icol.add(Integer.valueOf(15));
		icol.add(Integer.valueOf(7));
		icol.add(8);
		// Object が来るところに、int がかかれていると、
		// 勝手に new Integer(8) をしてくれます。
		icol.add(9);
		icol.add(7);
	}
}
