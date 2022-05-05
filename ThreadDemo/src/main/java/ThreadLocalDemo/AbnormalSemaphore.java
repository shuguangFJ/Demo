package ThreadLocalDemo;

import java.util.concurrent.Semaphore;

public class AbnormalSemaphore {

    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore = new Semaphore(0);
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new AbNormSemaphoreWorker(semaphore));
            thread.start();
        }

        System.out.println("Action Go");
        semaphore.release(5);
        System.out.println("Waiting for permits release");
        while (semaphore.availablePermits()!=0){
            Thread.sleep(100);
        }

        System.out.println("Action Go Again");
        semaphore.release(5);
    }
}

class AbNormSemaphoreWorker implements Runnable{

    private Semaphore semaphore;

    public AbNormSemaphoreWorker(Semaphore semaphore) {
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        try {
            semaphore.acquire();
            log("executed");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void log(String msg) {
        System.out.println(Thread.currentThread().getName() + " " + msg);
    }
}
