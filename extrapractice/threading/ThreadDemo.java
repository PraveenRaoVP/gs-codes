package extrapractice.threading;

import java.util.ArrayList;
import java.util.List;

public class ThreadDemo {
    List<Integer> arr = new ArrayList<>();
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new MyThread();
        Thread t2 = new Thread(new RunnableThread());
        System.out.println(Thread.currentThread().getName());
        t1.setName("first");
        t2.setName("second");
        // Thread.sleep(3000);
        t1.run();
        t2.run();
        // t1.join();
        // t2.join();
    }
}

class MyThread extends Thread {
    List<Integer> arr = new ArrayList<>();
    @Override
    public void run() {
        for(int i=1;i<=10;i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
        }
    }
}

class RunnableThread implements Runnable {
    @Override
    public void run() {
        for(int i=1;i<=10;i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
        }
    }
}