package collections_session;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.*;

public class ConcurrentQueue {


    public static void main(String[] args) throws InterruptedException {
//        Queue<Integer> queu = new LinkedList<>();

        BlockingQueue<Integer> queu = new LinkedBlockingQueue<>();

        CountDownLatch countDownLatch = new CountDownLatch(100);
        for (int i = 0; i < 100; i++) {
            (new Thread(
                    ()->{

                        for (int j = 0; j < 100; j++) {
//                            synchronized (queu){ // don't use this

                                queu.add(10);
//                            }
//                            queu.remove();
                        }
//                        sleep();
                        countDownLatch.countDown();
                    }
            )).start();
        }

        //wait up to 20 seconds
        countDownLatch.await(20, TimeUnit.SECONDS);
        System.out.println("queu.size() = " + queu.size());
    }

    private static void sleep() {
        try {
            Thread.sleep(5000);//5 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
