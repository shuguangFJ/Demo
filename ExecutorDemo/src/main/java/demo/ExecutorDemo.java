package demo;

import java.util.concurrent.*;

public class ExecutorDemo {

    public static void main(String[] args) {
//        Executor
//        ExecutorService

//        Executors

        ExecutorService executorService = Executors.newFixedThreadPool(5);
        executorService.execute(()->{
            while (true){
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("thread execute0");
            }
        });
        executorService.execute(()->{
            while (true){
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("thread execute1");
            }
        });
        executorService.shutdown();

        Executors.newCachedThreadPool(new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                return null;
            }
        });

        new ThreadPoolExecutor(10,20,10,TimeUnit.SECONDS);


    }
}
