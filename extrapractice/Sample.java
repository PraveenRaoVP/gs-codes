package extrapractice;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

/* */

interface A {
    void a();
}

public class Sample {
    public static void main(String[] args) {
        A aObj = new A() {
            @Override
            public void a() {
                System.out.println("A in anonymous class");
            }

        };
        aObj.a();

        A bObj = () -> System.out.println("A in lambda expression");
        bObj.a();
    }
}