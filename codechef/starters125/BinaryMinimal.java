package codechef.starters125;

/*
 * 
 * Binary Minimal

Alice is given a binary string S of length N. She can perform the following operations on string S:

Select an index 1 ≤ i ≤ |S| and delete the character Si.
Select an index 1 ≤ i ≤ |S| and flip the character Si. Note that while flipping 1 becomes 0 and vice-versa.
Find the lexicographically smallest string Alice can obtain using at most K operations.

Note that string X is said to be lexicographically smaller than string Y if:

String X is a prefix of string Y, or;
The smallest index i where X and Y differ, Xi < Yi.
Input Format

The first line of input will contain a single integer T, denoting the number of test cases. Each test case consists of multiple lines of input. The first line of each test case contains two space-separated integers N and K — denoting length of the string and the maximum number of operations, respectively. The next line contains a binary string S of length N.

Output Format

For each test case, output on a new line, the lexicographically smallest string that Alice can make.

Constraints

1 ≤ T ≤ 10^5
1 ≤ N ≤ 2 * 10^5
0 ≤ K < N
S is a Binary String
The sum of N over all test cases won't exceed 2 * 10^5.

Sample 1:

Input
2
3 1
000
2 0
11

Output
00
11
 */
public class BinaryMinimal {
    import java.util.*;
import java.lang.*;
import java.io.*;

class Codechef
{
	  public static String getMinimalString(int n, int k, String s) {
        int remainingOps = k;
        List<Character> result = new ArrayList<>();

        // Iterate through each character in s
        for (char c : s.toCharArray()) {
            // If c is '0', append it to result
            if (c == '0') {
                result.add(c);
            } else { // If c is '1'
                // If remaining operations is greater than 0, append '0' to result and decrement remainingOps
                if (remainingOps > 0) {
                    result.add('0');
                    remainingOps--;
                } else { // Otherwise, append '1' to result
                    result.add(c);
                }
            }
        }

        // If there are remaining operations, remove the last remainingOps characters from result
        if (remainingOps > 0) {
            int lastIndex = result.size() - 1;
            for (int i = 0; i < remainingOps; i++) {
                result.remove(lastIndex - i);
            }
        }

        // Convert the list of characters to a string and return the result
        StringBuilder sb = new StringBuilder();
        for (char ch : result) {
            sb.append(ch);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt(); // Number of test cases

        while (t-- > 0) {
            int n = scanner.nextInt(); // Length of the string
            int k = scanner.nextInt(); // Maximum number of operations
            String s = scanner.next(); // Binary string

            String minimalString = getMinimalString(n, k, s);
            System.out.println(minimalString);
        }
    }
}

}



import java.util.*;
import java.lang.*;
import java.io.*;

class Codechef
{
	   public static String getMinimalString(int n, int k, String s) {
        int remainingOps = k;
        List<Character> result = new ArrayList<>();

        for (char c : s.toCharArray()) {
            if (c == '0') {
                result.add(c);
            } else if (c == '1') {
                if (remainingOps > 0) {
                    result.add('0');
                    remainingOps--;
                } else {
                    result.add('1');
                }
            }
        }

        // Remove any remaining '1's if needed
        while (remainingOps > 0 && !result.isEmpty()) {
            result.remove(result.size() - 1);
            remainingOps--;
        }

        // Convert the list of characters to a string and return the result
        StringBuilder sb = new StringBuilder();
        for (char ch : result) {
            sb.append(ch);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt(); // Number of test cases

        while (t-- > 0) {
            int n = scanner.nextInt(); // Length of the string
            int k = scanner.nextInt(); // Maximum number of operations
            String s = scanner.next(); // Binary string

            String minimalString = getMinimalString(n, k, s);
            System.out.println(minimalString);
        }
    }
}
