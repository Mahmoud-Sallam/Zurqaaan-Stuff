package step6;

import java.util.LinkedList;
import java.util.Queue;

public class Test6 {


    public static void main(String[] args) {

        Queue<Integer> queue= new LinkedList<>();

        queue.add(10);
        queue.add(20);
        queue.offer(33);

        System.out.println("queue.poll() = " + queue.poll());
        System.out.println("queue.poll() = " + queue.poll());

    }
}
