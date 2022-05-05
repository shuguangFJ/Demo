package ThreadLocalDemo;

public class Hello {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("hello world");
        Thread.currentThread().join();
        ThreadGroup threadGroup = Thread.currentThread().getThreadGroup();
        ThreadGroup top = null;
        while (threadGroup != null) {
            top = threadGroup;
            threadGroup = threadGroup.getParent();
        }
//        System.out.println(top.activeCount());
//        top.list();

        int nowThreads = top.activeCount();
        Thread[] lstThreads = new Thread[nowThreads];
        top.enumerate(lstThreads);
        for (int i = 0; i < nowThreads; i++) {
            System.out.println("线程number： " + i + " = " + lstThreads[i].getName());
        }
    }
}
