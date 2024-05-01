package extrapractice;

import java.util.Comparator;
import java.util.function.Predicate;

@FunctionalInterface
interface Student {
    int x = 5;
    void study();
    boolean equals(Object obj);
    default void play() {
        System.out.println("Student is playing with " + x + " balls.");
    }
}

@FunctionalInterface
interface AdditionInterface {
    int add(int a, int b);
}

public class LambdaExpressionsDemo implements Student {
    public static void main(String[] args) {
        // Student s = () -> System.out.println("Student is studying.");
        // AdditionInterface x = (a,b) -> System.out.println("Sum: " + (a+b));
        // x.add(5, 10);
        // LambdaExpressionsDemo led = new LambdaExpressionsDemo();
        // led.study();
        // s.study();
        // s.play();
        // AdditionInterface add = new AdditionInterface() {
        //     @Override
        //     public int add(int a, int b) {
        //         return a + b;
        //     }
        // };
        // System.out.println(add.add(5, 10));
 
        // Thread t1 = new Thread(() -> {
        //     for (int i = 0; i < 5; i++) {
        //         System.out.println("Thread 1: " + i);
        //         try {
        //             Thread.sleep(1000);
        //         } catch (InterruptedException e) {
        //             e.printStackTrace();
        //         }
        //     }
        // });

        // Thread t2 = new Thread(() -> {
        //     for (int i = 0; i < 5; i++) {
        //         System.out.println("Thread 2: " + i);
        //         try {
        //             Thread.sleep(1000);
        //         } catch (InterruptedException e) {
        //             e.printStackTrace();
        //         }
        //     }
        // });

        // t1.start();
        // t2.start();
        // try {
        //     t1.join();
        //     t2.join();
        // } catch (InterruptedException e) {
        //     e.printStackTrace();
        // }

        Predicate<Integer> p = i -> i%2==0;
        System.out.println(p.test(10));
        Student s = () -> System.out.println("Student is studying. 1");
        s.study();
        Student s2 = () -> System.out.println("Student is studying. 2");
        s2.study();
        System.out.println(s.equals(s2));
    }

    @Override
    public void study() {
        System.out.println("Student is studying. 2");
    }
    
}