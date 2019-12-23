package collections_session;

import java.util.HashSet;
import java.util.Set;

public class HashSetExample {

    public static void main(String[] args) {
        Set<Integer> set = new HashSet<>();
        set.add(1);
        set.add(1);
        set.add(1);
        set.add(2);
        set.add(3);
        set.add(4);
        System.out.println(set);
        Set<Integer> set2 = new HashSet<>();
        set2.add(10);
        set2.add(10);

        set2.add(20);
        set2.add(30);
        set2.add(40);
        boolean b = set.addAll(set2);
        System.out.println("set = " + set);

        Set<Integer> set3 = new HashSet<>();
        set3.add(10);

        set3.add(20);
        set3.add(200);

        boolean b1 = set2.retainAll(set3);
        System.out.println("set2 = " + set2);
    }
}
