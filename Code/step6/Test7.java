package step6;

import java.lang.reflect.Array;
import java.lang.reflect.ParameterizedType;
import java.util.SortedSet;
import java.util.TreeSet;

@SuppressWarnings("ALL")
public class Test7<E> {

    public Test7() {

//        @SuppressWarnings("unchecked")
//        Class<E> actualTypeArgument = (Class<E>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
//
//        @SuppressWarnings("unchecked")
//        E[] o = (E[]) Array.newInstance(actualTypeArgument, 100);





    }

    public static void main(String[] args) {
        TreeSet<String> tree = new TreeSet<>();

        tree.add("Z");
        tree.add("A");
        tree.add("X");
        tree.add("B");
        tree.add("C");

        for (String s : tree) {
            System.out.println("s = " + s);
        }


        SortedSet<String> head = tree.headSet("C");
        System.out.println("head = " + head);

        SortedSet<String> tail = tree.tailSet("C");
        System.out.println("tail = " + tail);

        String lower = tree.lower("I");
        System.out.println("lower = " + lower);
    }
}
