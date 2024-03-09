package hackerrank;
import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;



public class MultiplesOf3And5 {
    // public static int findSum(int n) {
    //     int three = (n-1)/3;
    //     int five = (n-1)/5;
    //     int fifteen = (n-1)/15;
        
    //     int sum = 0;
        
    //     sum+=3*(three * (three+1))/2;
    //     sum+=5*(five * (five+1))/2;
    //     sum-=15*(fifteen * (fifteen+1))/2;
    //     return sum;
    // }
    
    public static long calc(int n, int k) {
       long p = (n-1)/k;
        return k * (p) * (p+1)/2;
    }
    
    public static long calc2(int n, int k) {
        long p = (n-1)/k;
        return (p*(2*k + (p-1)*k))/2;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, t).forEach(tItr -> {
            try {
                int n = Integer.parseInt(bufferedReader.readLine().trim());
                long sum = calc2(n,3) + calc2(n,5) - calc2(n,15);
                //long sum = calc(n,3) + calc(n,5) - calc(n,15);
                System.out.println(sum);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
    }
}

