package extrapractice;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.Semaphore;

class Philosopher implements Runnable {
    private final int id;
    private final Lock leftFork;
    private final Lock rightFork;
    private final Semaphore waiter;

    public Philosopher(int id, Lock leftFork, Lock rightFork, Semaphore waiter) {
        this.id = id;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
        this.waiter = waiter;
    }

    private void think() throws InterruptedException {
        System.out.println("Philosopher " + id + " is thinking.");
        Thread.sleep((int) (Math.random() * 1000));
    }

    private void eat() throws InterruptedException {
        System.out.println("Philosopher " + id + " is eating.");
        Thread.sleep((int) (Math.random() * 1000));
    }

    @Override
    public void run() {
        try {
            while (true) {
                think();

                // Waiter ensures that only up to 4 philosophers can attempt to pick up forks
                waiter.acquire();

                // Pick up forks
                leftFork.lock();
                System.out.println("Philosopher " + id + " picked up left fork.");
                rightFork.lock();
                System.out.println("Philosopher " + id + " picked up right fork.");

                // Eat
                eat();

                // Put down forks
                rightFork.unlock();
                System.out.println("Philosopher " + id + " put down right fork.");
                leftFork.unlock();
                System.out.println("Philosopher " + id + " put down left fork.");

                // Release waiter
                waiter.release();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return;
        }
    }
}

public class Main {
    public static void main(String[] args) {
        int numPhilosophers = 5;
        Lock[] forks = new ReentrantLock[numPhilosophers];
        Semaphore waiter = new Semaphore(numPhilosophers - 1);  

        for (int i = 0; i < numPhilosophers; i++) {
            forks[i] = new ReentrantLock();
        }

        Philosopher[] philosophers = new Philosopher[numPhilosophers];
        Thread[] threads = new Thread[numPhilosophers];

        for (int i = 0; i < numPhilosophers; i++) {
            Lock leftFork = forks[i];
            Lock rightFork = forks[(i + 1) % numPhilosophers];
            philosophers[i] = new Philosopher(i, leftFork, rightFork, waiter);
            threads[i] = new Thread(philosophers[i]);
            threads[i].start();
        }
    }
}
