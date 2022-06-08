package ThreadLocalDemo;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.regex.Pattern;

public class Hello {

    public static void main(String[] args) throws InterruptedException {
        Vector
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
