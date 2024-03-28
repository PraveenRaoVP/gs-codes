package codechef.start127d;

import java.util.Scanner;

/*
 * Problem Statement:

Chef recalls having a superincreasing array A of length N with him a long time ago, but has forgotten all its elements now. The only piece of information he recalls is that the value X occurred at index K of the array, i.e., A[K] = X.

Can you tell Chef if he recalls correctly? That is, does there exist a superincreasing array A of length N such that A[K] = X?

Input:

The first line of input contains a single integer T, denoting the number of test cases.
Each test case consists of a single line containing three space-separated integers N, K, and X:
N: The length of array A.
K: The index at which value X occurs in array A.
X: The value that occurred at index K in array A.
Output:

For each test case, output "Yes" if a valid superincreasing array exists, and "No" otherwise.

Constraints:

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
�
≤
2
×
1
0
5
1≤K≤N≤2×10 
5
 
1
≤
�
≤
1
0
9
1≤X≤10 
9
 
The sum of N across all test cases won't exceed 
2
×
1
0
5
2×10 
5
 .
Sample Input:

Copy code
4
3 2 3
3 3 3
5 3 6
6 2 1
Sample Output:

yaml
Copy code
Yes
No
Yes
No
Explanation:

In test case 1, a valid superincreasing array could be 
[
1
,
3
,
10
]
[1,3,10].
In test case 2, no superincreasing array of length 3 can have 
�
[
3
]
=
3
A[3]=3.
In test case 3, one example of a valid array is 
[
2
,
3
,
6
,
15
,
60
]
[2,3,6,15,60].
In test case 4, no superincreasing array can have 
�
[
2
]
=
1
A[2]=1.
 */

public class SuperIncreasing {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while(t-- > 0) {
            int n = sc.nextInt();
            int k = sc.nextInt();
            int x = sc.nextInt();
            if(k == 1) {
                System.out.println(x == 1 ? "Yes" : "No");
            } else {
                System.out.println(x <= (k * (k - 1) / 2) ? "Yes" : "No");
            }
        }
    }
}
