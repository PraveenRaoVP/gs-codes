package assignments.assignment13_arraylist;

import java.util.ArrayList;

public class Problem4 {
    /* Write a program that compares the performance of different operations on ArrayLists with different capacities and data access patterns (random access vs. sequential access) */
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            list.add(i);
        }

        long end = System.currentTimeMillis();
        System.out.println("Time taken for adding: " + (end - start) + "ms");

        int index = 50000;
        start = System.currentTimeMillis();
        list.get(index);
        end = System.currentTimeMillis();
        System.out.println("Time taken for random access: " + (end - start) + "ms");

        start = System.currentTimeMillis();
        for(int i=0; i<100000; i++) {
            if(list.indexOf(list.get(i)) == index) {
                break;
            }
        }
        end = System.currentTimeMillis();
        
        System.out.println("Time taken for sequential access: " + (end - start) + "ms");
    }
}
