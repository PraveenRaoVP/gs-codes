package assignments.assignment15_synchronized;

/*
 * Producer-Consumer Problem with wait and notify
Objective:
Understand how to use wait and notify to synchronize threads.

Instructions:
Create a Buffer class that holds a single integer value.
Implement put and get methods in the Buffer class, using wait and notify to handle synchronization.
Create Producer and Consumer classes that implement Runnable.
Start multiple producer and consumer threads to demonstrate the producer-consumer problem.
 */


class Buffer {
    int value;
    boolean isEmpty = true;

    public synchronized void put(int value) {
        while(!isEmpty) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.value = value;
        isEmpty = false;
        System.out.println(Thread.currentThread().getName() + " Produced: " + value);
        notify();
    }

    public synchronized int get() {
        while(isEmpty) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        isEmpty = true;
        System.out.println(Thread.currentThread().getName() + " Consumed: " + value);
        notify();
        return value;
    }

}

class Producer implements Runnable {
    Buffer buffer;

    public Producer(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        for(int i=1; i<=5; i++) {
            buffer.put(i);
        }
    }
}

class Consumer implements Runnable {
    Buffer buffer;

    public Consumer(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        for(int i=1; i<=5; i++) {
            buffer.get();
        }
    }
}

public class Problem3 {
    public static void main(String[] args) {
        Buffer buffer = new Buffer();
        Producer producer1 = new Producer(buffer);
        Consumer consumer = new Consumer(buffer);

        Thread producer1Thread = new Thread(producer1, "Producer1");
        Thread producer2Thread = new Thread(producer1, "Producer2");
        Thread consumerThread = new Thread(consumer, "Consumer");

        producer1Thread.start();
        producer2Thread.start();
        consumerThread.start();

        try {
            producer1Thread.join();
            producer2Thread.join();
            consumerThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
