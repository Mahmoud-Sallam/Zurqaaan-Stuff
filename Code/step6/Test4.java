package step6;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.stream.IntStream;

public class Test4 {

    public static void main(String[] args) {
        PriorityQueue<Integer> lower = new PriorityQueue<>((a,b)->b-a);
        PriorityQueue<Integer> upper = new PriorityQueue<>();

        add(10,upper,lower);
        add(1,upper,lower);
        add(2,upper,lower);
        add(3,upper,lower);
        add(40,upper,lower);
        add(50,upper,lower);
        add(30,upper,lower);
        add(13,upper,lower);
        add(66,upper,lower);
        add(0,upper,lower);

        System.out.println(lower.peek());
        System.out.println(upper.peek());
        System.out.println(lower);
        System.out.println(upper);


        ArrayBlockingQueue<Integer> x = new ArrayBlockingQueue<>(6);

        IntStream
                .range(0,7)
                .forEach(i-> {
                    boolean added = x.offer(i);
                    System.out.println("added = " + added);
                });






    }

    private static void add(int item,Queue<Integer>  upper, Queue<Integer>  lower){

        upper.add(item);
        if(upper.size()>(lower.size()+1)){
            lower.add(upper.poll());
        }
    }
}
