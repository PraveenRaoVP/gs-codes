package assignments.assignment14_multithreading;

import java.nio.Buffer;
import java.util.LinkedList;
import java.util.Queue;

/*
 * Simple Producer-Consumer (Thread Communication):
Create two threads: a producer and a consumer.
The producer adds items to a shared buffer (e.g., an array).
The consumer removes items from the buffer and processes them.
Implement synchronization (e.g., wait-notify) to ensure the consumer doesn't access empty buffers and the producer doesn't overflow.
 */

public class Problem2 {
    public static void main(String[] args) {
        BlockingQueue queue = new BlockingQueue(5);

        Thread producer = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i < 10; i++) {
                    queue.add(i);
                    System.out.println("Produced: " + i);
                }
            }
        });
        
        Thread consumer = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i < 10; i++) {
                    int element = queue.remove();
                    System.out.println("Consumed: " + element);
                }
            }
        });
        
        producer.start();
        consumer.start();
    }
}

class BlockingQueue {
    Queue<Integer> queue;
    int size;

    public BlockingQueue(int size) {
        queue = new LinkedList<>();
        this.size = size;
    }

    public synchronized void add(int element) {
        if(queue.size() == size) {
            try{
                wait();
            } catch(Exception e) {
                e.printStackTrace();
            }
        } else {
            queue.add(element);
            notifyAll();
        }
    }

    public synchronized int remove() {
        if(queue.size() == 0) {
            try{
                wait();
            } catch(Exception e) {
                e.printStackTrace();
            }
        } else {
            int element = queue.remove();
            notifyAll();
            return element;
        }
        return -1;
    }
}
