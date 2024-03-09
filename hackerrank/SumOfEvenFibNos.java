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



public class SumOfEvenFibNos {
    
    public static long returnSumOfEvenFibNos(long n) {
        if(n<2) return 0;
        long a = 0, b=2;
        long sum = a+b;
        while(b <= n) {
            long c = 4*b+a;
            if(c > n) break;
            a=b;
            b=c;
            sum+=b;
        }
        return sum;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, t).forEach(tItr -> {
            try {
                long n = Long.parseLong(bufferedReader.readLine().trim());
                System.out.println(returnSumOfEvenFibNos(n));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
    }
}

