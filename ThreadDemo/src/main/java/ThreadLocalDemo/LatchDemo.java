package ThreadLocalDemo;

import java.util.concurrent.CountDownLatch;

public class LatchDemo {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(new FirstBatch(countDownLatch));
            thread.start();
        }

        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(new SecondBatch(countDownLatch));
            thread.start();
        }

        while (countDownLatch.getCount() != 1){
            Thread.sleep(100);
        }
        System.out.println("waiting for firstBatch finish");

        countDownLatch.countDown();
    }
}

class FirstBatch implements Runnable {
    private CountDownLatch countDownLatch;

    public FirstBatch(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        System.out.println("firstbatch executed");
        countDownLatch.countDown();
    }
}

class SecondBatch implements Runnable {

    private final CountDownLatch countDownLatch;

    public SecondBatch(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try {
            countDownLatch.await();
            System.out.println("secondbatch executed");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}