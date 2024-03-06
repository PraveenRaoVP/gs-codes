package codechef.start124d;

import java.util.Arrays;
import java.util.Scanner;

/*
 * Table Strength
You have 
�
N table legs, of different strengths.
Pillar 
�
i can bear a weight of 
�
�
W 
i
​
 , and will break if it has to bear a larger weight.

You'd like to construct a table using some non-empty subset of these table legs.
When you place a weight on a table, its load is equally distributed to each of its legs.

For example, if you build a table with 
4
4 legs, and place a weight of 
18
18 on it, each leg will need to bear a weight of 
18
4
=
4.5
4
18
​
 =4.5.
So for instance, a table whose legs have strengths 
[
4
,
4
,
5
,
6
]
[4,4,5,6] will not be able to bear this weight (the two legs with strength 
4
4 will break), whereas a table with leg strengths 
[
5
,
6
,
6
,
8
]
[5,6,6,8] will be able to bear it.

Find the maximum possible weight that a table built out of some of these 
�
N legs can bear, without any of the legs breaking.
It can be proved that this maximum weight is always an integer.

Note: Subsets need not be contiguous: for example, 
[
1
,
3
]
[1,3] is a subset of 
[
1
,
4
,
3
,
2
]
[1,4,3,2].

Input Format
The first line of input will contain a single integer 
�
T, denoting the number of test cases.
Each test case consists of two lines of input.
The first line of each test case contains a single integer 
�
N, the number of table legs you have.
The second line of each test case contains 
�
n space-separated integers 
�
1
,
�
2
,
…
,
�
�
W 
1
​
 ,W 
2
​
 ,…,W 
N
​
 , denoting the weight each leg can withstand.
Output Format
For each test case, output on a new line the maximum weight the table can withstand by using some non-empty subset of table legs.
Constraints
1
≤
�
≤
2
⋅
1
0
4
1≤T≤2⋅10 
4
 
1
≤
�
≤
2
⋅
1
0
5
1≤n≤2⋅10 
5
 
1
≤
�
[
�
]
≤
1
0
4
1≤w[i]≤10 
4
 
it is guaranteed that the sum of n over all test cases won't exceed 
2
⋅
1
0
5
2⋅10 
5
 .
Sample 1:
Input
Output
2
3
1 2 3
4
2 2 10 3
4
10
Explanation:
Test case 
1
1: There are seven non-empty subsets of pillars. Let's look at each of them.

[
1
]
[1] - the maximum weight that can be held is 
1
1.
[
2
]
[2] - the maximum weight that can be held is 
2
2.
[
3
]
[3] - the maximum weight that can be held is 
3
3.
[
1
,
2
]
[1,2] - placing a weight of 
3
3 or more is impossible because 
3
2
=
1.5
>
1
2
3
​
 =1.5>1, but 
2
2 is possible.
[
1
,
3
]
[1,3] - placing a weight of 
3
3 or more is impossible, but 
2
2 is possible.
[
2
,
3
]
[2,3] - placing a weight of 
5
5 or more is impossible, but 
4
4 is possible.
[
1
,
2
,
3
]
[1,2,3] - placing a weight of 
4
4 or more is impossible, but 
3
3 is possible.
So, it's best to choose two legs, namely 
2
2 and 
3
3: this gives us a table that can withstand a weight of 
4
4.

Test case 
2
2: Choose the single leg 
[
10
]
[10], and it can bear a weight of 
10
10.
This is the maximum possible.
 */

public class TableStrength { 
    public static long maxWeight(int n, int[] w) {
        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {
                if(w[i]>w[j]) {
                    int temp = w[i];
                    w[i] = w[j];
                    w[j] = temp;
                }
            }
        }
        long mx=0;
        for (int i = 0; i < n; i++) {
            long current = (long)w[i]*(n - i); 
            mx = Math.max(mx, current); 
        }
        return mx;
    }
    
	public static void main (String[] args) throws java.lang.Exception
	{
		// your code goes here
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while(t>0) {
            int n = sc.nextInt();
            int[] weights = new int[n];
            for(int i=0;i<n;i++) 
                weights[i] = sc.nextInt();
            System.out.println(maxWeight(n,weights));
            t--;
        }
	}
}
