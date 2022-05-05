package ThreadLocalDemo;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class DeadLock extends Thread {

    private String first;

    private String second;

    public DeadLock(String first, String second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public void run() {
        synchronized (this.first){
            System.out.println(currentThread().getName() + " obtained1: " + first);
            try {
                Thread.sleep(1000);
                synchronized (this.second){
                    System.out.println(currentThread().getName() + " obtained2: " + second);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        Runnable dlCheck = new Runnable() {
            @Override
            public void run() {
                long[] deadlockedThreads = threadMXBean.findDeadlockedThreads();
                if (deadlockedThreads != null){
                    ThreadInfo[] threadInfo = threadMXBean.getThreadInfo(deadlockedThreads);
                    System.out.println("detected deadlock thread:");
                    for (ThreadInfo info : threadInfo) {
                        System.out.println(info.getThreadName());
                    }
                }
            }
        };
        ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
        service.scheduleAtFixedRate(dlCheck,5L,10L, TimeUnit.SECONDS);

        String lockA = "lockA";
        String lockB = "lockB";
        DeadLock deadLock = new DeadLock(lockA, lockB);
        DeadLock deadLock1 = new DeadLock(lockB, lockA);
        deadLock.start();
        deadLock1.start();
    }
}
