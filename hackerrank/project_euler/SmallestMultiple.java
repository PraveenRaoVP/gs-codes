package hackerrank.project_euler;

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



public class SmallestMultiple {
    
    public static long gcd(long a, long b) {
        if(b==0) return a;
        return gcd(b,a%b);
    }
    
    public static long calcLCM(long a, long b) {
        return (a*b)/gcd(a,b);
    }
    
    public static long computeSmallestMultiple(int n) {
        long lcm = 1;
        for(int i=2;i<=n;i++) {
            lcm = calcLCM(lcm, i);
        }
        return lcm;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, t).forEach(tItr -> {
            try {
                int n = Integer.parseInt(bufferedReader.readLine().trim());
                System.out.println(computeSmallestMultiple(n));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
    }
}

