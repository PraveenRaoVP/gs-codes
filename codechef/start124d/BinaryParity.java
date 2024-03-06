package codechef.start124d;

import java.util.Scanner;

/*
 * Binary Parity
The binary parity of an integer 
�
N is defined as follows:

First, write 
�
N in binary.
For example, 
�
=
13
N=13 is written as 
1101
1101 in binary, and 
�
=
5
N=5 is written as 
101
101.
Compute 
�
�
S 
N
​
 , the sum of the binary digits of 
�
N.
For example, from the earlier examples, 
�
13
=
1
+
1
+
0
+
1
=
3
S 
13
​
 =1+1+0+1=3 and 
�
5
=
1
+
0
+
1
=
2
S 
5
​
 =1+0+1=2.
The binary parity of 
�
N is then the parity
†
†
  of 
�
�
S 
N
​
 .
�
13
=
3
S 
13
​
 =3 is odd, so 
13
13 is said to have odd binary parity; while 
�
6
=
2
S 
6
​
 =2 is even, so 
5
5 has even binary parity.
Given an integer 
�
N, find its binary parity.

†
†
  The parity of an integer is, quite simply, whether it's even or odd.
We say an integer has even parity if it is a multiple of 
2
2, and odd parity otherwise.

Input Format
The first line of input will contain a single integer 
�
T, denoting the number of test cases.
The first and only line of each test case will contain a single integer 
�
N.
Output Format
For each test case, output on a new line the binary parity of 
�
N — either "EVEN" or "ODD" (without quotes).

Each character of the output may be printed in either lowercase or uppercase, i.e, the strings Odd, ODD, oDd, and ODd will all be treated as equivalent.

Constraints
1
≤
�
≤
1
0
5
1≤T≤10 
5
 
1
≤
�
≤
1
0
9
1≤N≤10 
9
 
Sample 1:
Input
Output
2
3
4
EVEN
ODD
Explanation:
Test case 
1
1: 
�
=
3
N=3 is written as 
11
11 in binary.
This gives us 
�
3
=
1
+
1
=
2
S 
3
​
 =1+1=2, which is even - so the binary parity of 
3
3 is even.

Test case 
2
2: 
�
=
4
N=4 is written as 
100
100 in binary.
This gives us 
�
4
=
1
+
0
+
0
=
1
S 
4
​
 =1+0+0=1, which is odd - so the binary parity of 
4
4 is odd.
 */

public class BinaryParity {
    public static void main (String[] args) throws java.lang.Exception
    {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while(t>0) {
            int n = sc.nextInt();
            int sum = 0;
            while(n>0) {
                sum+=n%2;
                n/=2;
            }
            System.out.println(sum%2==0?"EVEN":"ODD");
            t--;
        }
        sc.close();
    }
}
