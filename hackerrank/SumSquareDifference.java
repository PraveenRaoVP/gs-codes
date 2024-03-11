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


public class SumSquareDifference {
    public static long calcSumOfNaturalNos(long n){
        return (long) Math.pow((n*(n+1))/2, 2);
    }

    public static long calcSumOfSquares(long n){
        return (long) (n*(n+1)*(2*n+1))/6;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, t).forEach(tItr -> {
            try {
                int n = Integer.parseInt(bufferedReader.readLine().trim());
                long x = calcSumOfNaturalNos(n);
                long y = calcSumOfSquares(n);
                System.out.println(Math.abs((x-y)));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
    }
}
