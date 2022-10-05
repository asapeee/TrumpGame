package kobeU.cs.samplesCol;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class SortSample {

    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        Random rand = new Random(2022);
        for(int i=0; i<20; i++) {
            list.add(rand.nextInt(1000));
        }
        System.out.println("Before: " + list);
        Collections.sort(list);
        System.out.println("Sorted: " + list);
        /*
        list.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                return Integer.compare(a, b);
            }
        });
        */
        list.sort((a, b)->{
            return Integer.compare(b, a);
        });

        System.out.println("Sorted Descendant: " + list);
        final int target = 200;
        list.sort((a, b)->{
            return Integer.compare(Math.abs(a-target), Math.abs(b-target));
        });

        System.out.println("Sorted from target("+target+"): "+ list);


    }
}
