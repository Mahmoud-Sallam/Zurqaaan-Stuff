package step6;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingDeque;

public class Test3 {

    public static void main(String[] args) throws InterruptedException {
//        Deque q = new LinkedList();
//        Deque q = new ConcurrentLinkedDeque();
        Deque q = new LinkedBlockingDeque();
        CountDownLatch countDownLatch = new CountDownLatch(100);

        for (int i = 0; i < 100; i++) {

            (new Thread(() -> {
                for (int j = 0; j < 100; j++) {

                    q.push("test");
                    q.remove();
                }
                countDownLatch.countDown();

            })).start();
        }
        countDownLatch.await();

        System.out.println(q.size());
        sleep(10000);
        System.out.println(q.size());
    }

    private static void sleep(int maxWait) {
        try {
            Thread.sleep(new Random().nextInt(maxWait));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
