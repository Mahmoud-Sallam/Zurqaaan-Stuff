package step6;

import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class Test {

    public static void main(String[] args) throws InterruptedException {


        BlockingQueue<Integer> bl= new LinkedBlockingQueue();

        (new Thread(()->{
            try {
                Thread.sleep(6000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            bl.add(5);
        })).start();

        Integer data = bl.poll(5, TimeUnit.SECONDS);
        System.out.println("data = " + data);

    }
}
