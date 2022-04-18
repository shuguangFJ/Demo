package ThreadLocalDemo;

public class Test {

    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) throws InterruptedException {
        Thread thread0 = new Thread(){
            @Override
            public void run() {
                threadLocal.set(currentThread().getName()+"-0");
                System.out.println(threadLocal.get());
            }
        };

        Thread thread1 = new Thread(){
            @Override
            public void run() {
                threadLocal.set(currentThread().getName()+"-1");
                System.out.println(threadLocal.get());
            }
        };

        thread0.start();
        thread1.start();
    }
}
