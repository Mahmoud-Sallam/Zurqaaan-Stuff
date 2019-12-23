package collections_session;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class BlockingQueueExample {

    public static void main(String[] args) throws InterruptedException {

        BlockingQueue<Integer> queue = new LinkedBlockingQueue<>();


        new Thread(() -> {
            sleep();
            queue.add(10);
        }).start();

        for (int i = 0; i < 3; i++) {

            Integer polledData = queue.poll(5, TimeUnit.SECONDS);

            System.out.println("polledData = " + polledData);
        }

    }

    private static void sleep() {
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
