package ThreadLocalDemo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class UsualSemaphore {

    public static void main(String[] args) {
        System.out.println("Action GO");
        Semaphore semaphore = new Semaphore(5);
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new SemaphoreWorker(semaphore));
            threads.add(thread);
        }
        for (Thread thread : threads) {
            thread.start();
        }
    }
}

class SemaphoreWorker implements Runnable{

    private Semaphore semaphore;

    public SemaphoreWorker(Semaphore semaphore) {
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        try {
            log("waiting for a permit");
            semaphore.acquire();
            Thread.sleep(100);
            log("acquired a permit");
            log("executed");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            log("release a permit");
            semaphore.release();
        }
    }

    private void log(String msg) {
        System.out.println(Thread.currentThread().getName() + " " + msg);
    }
}
