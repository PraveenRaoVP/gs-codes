package extrapractice.threading;

import java.util.ArrayList;
import java.util.List;

public class ThreadDemo {
    List<Integer> arr = new ArrayList<>();
    public static void main(String[] args) throws InterruptedException {
        Thread.currentThread().setPriority(Thread.MAX_PRIORITY); // this priority is inherited by all the threads created by this thread i.e the main thread in this case.
        Thread.currentThread().setName("Main-Thread");
        System.out.println(Thread.currentThread().getName() + " " + Thread.currentThread().getPriority());
        Thread t1 = new MyThread();
        Thread t2 = new Thread(new RunnableThread());
        t1.setName("first");
        t2.setName("second");
        // Thread.sleep(3000);
        t1.start();
        t2.start();
        // t1.join();
        // t2.join();
    }
}

class MyThread extends Thread {
    List<Integer> arr = new ArrayList<>();
    @Override
    public void run() {
        for(int i=1;i<=10;i++) {
            // System.out.println(Thread.currentThread().getName() + " " + i);
            System.out.println(Thread.currentThread().getName() + " " + Thread.currentThread().getPriority());
            System.out.println();
        }
    }
}

class RunnableThread implements Runnable {
    @Override
    public void run() {
        for(int i=1;i<=10;i++) {
            // System.out.println(Thread.currentThread().getName() + " " + i);
            System.out.println(Thread.currentThread().getName() + " " + Thread.currentThread().getPriority());
            System.out.println();
        }
    }
}