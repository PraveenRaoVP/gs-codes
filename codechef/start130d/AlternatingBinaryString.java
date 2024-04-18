package codechef.start130d;

import java.util.*;
import java.lang.*;
import java.io.*;

/*

Problem: Minimum Operations to Make Binary String Alternating

You are given a binary string 
�
S of length 
�
N. The string may contain only the characters '0' and '1'. You can perform the following operation on it:

Choose an index 
�
i such that 
1
≤
�
≤
�
1≤i≤N, and flip every character of 
�
S from index 
�
i to 
�
N. Flipping a character means turning '0' into '1' and '1' into '0'.
Your task is to find the minimum number of operations required to make the binary string 
�
S alternating. A string is said to be alternating if adjacent characters are different from each other.

Input:

The first line of input contains an integer 
�
T, denoting the number of test cases.
Each test case consists of two lines:
The first line contains an integer 
�
N, the length of the binary string 
�
S.
The second line contains the binary string 
�
S of length 
�
N.
Output:

For each test case, output on a new line the minimum number of operations required to make 
�
S alternating.
Constraints:


 .
Example:

Input:


3
1
1
2
11
4
0010
Output:


0
1
1

 * To approach the problem of making a binary string alternating, you can follow these steps:

Iterate through the binary string character by character.
Whenever you encounter adjacent equal characters, increment a count variable to keep track of the number of operations needed to make the string alternating.
Skip the next character in the iteration to avoid double-counting.
After iterating through the entire string, return the count of operations as the result.
Here's a more detailed approach:

Initialize a variable to keep track of the number of operations required, let's call it operations and set it to 0.
Iterate through the string from the second character to the last character.
Compare the current character with the previous character.
If they are equal, increment the operations variable by 1 and skip the next character in the iteration.
After iterating through the entire string, return the value of operations as the result.
By following this approach, you'll be able to find the minimum number of operations required to make the binary string alternating.
 */

class AlternatingBinaryString
{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt(); // Number of test cases

        while (t-- > 0) {
            int n = scanner.nextInt(); // Length of the binary string
            String s = scanner.next(); // Binary string

            // Count the number of operations required to make the string alternating
            int count = countOperations(s);
            System.out.println(count);
        }

        scanner.close();
    }

    public static int countOperations(String s) {
        int operations = 0;
        // handle edge cases
        if (s.length() == 1) {
            return 0;
        }

        // Iterate through the string from the second character to the last character
        // Compare the current character with the previous character
        // If they are equal, increment the operations variable by 1 and skip the next character in the iteration
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == s.charAt(i - 1)) {
                operations++;
                i++;
            }
        }

        return operations;
    }
}