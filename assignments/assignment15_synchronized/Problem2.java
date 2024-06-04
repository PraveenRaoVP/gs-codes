package assignments.assignment15_synchronized;

/*
 * Simulate deadlock

Instructions:
Create two classes, ResourceA and ResourceB.
Each class should have a method that attempts to lock both ResourceA and ResourceB using nested synchronized blocks.
Create two threads that call these methods and demonstrate a deadlock situation
 */


class ResourceA {
    public void methodA(ResourceB resourceB) {
        synchronized(this) {
            System.out.println("ResourceA: Locked ResourceA");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized(resourceB) {
                System.out.println("ResourceA: Locked ResourceB");
            }
        }
    }
}

class ResourceB {
    public void methodB(ResourceA resourceA) {
        synchronized(this) {
            System.out.println("ResourceB: Locked ResourceB");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized(resourceA) {
                System.out.println("ResourceB: Locked ResourceA");
            }
        }
    }
}

public class Problem2 {
    public static void main(String[] args) {
        ResourceA resourceA = new ResourceA();
        ResourceB resourceB = new ResourceB();

        Thread thread1 = new Thread(() -> {
            resourceA.methodA(resourceB);
        });

        Thread thread2 = new Thread(() -> {
            resourceB.methodB(resourceA);
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

