package codechef.start124d;

/*Make It One
You're given two positive integers 
�
L and 
�
R (
�
<
�
L<R).

Let 
�
=
�
−
�
+
1
N=R−L+1.
Consider the array 
�
=
[
�
,
�
+
1
,
�
+
2
,
…
,
�
]
A=[L,L+1,L+2,…,R] of length 
�
N that contains every integer from 
�
L to 
�
R exactly once, in order.

Find any array 
�
B of length 
�
N such that:

Every integer from 
�
L to 
�
R appears exactly once in 
�
B; and
For each 
1
≤
�
≤
�
1≤i≤N, 
gcd
⁡
(
�
�
,
�
�
)
=
1
gcd(A 
i
​
 ,B 
i
​
 )=1.
If no such 
�
B exists, print 
−
1
−1 instead.

Input Format
The first line of input will contain a single integer 
�
T, denoting the number of test cases.
Each test case consists of a single line, containing two space-separated integers 
�
L and 
�
R.
Output Format
For each test case,
If no valid array 
�
B exists, print the single integer 
−
1
−1.
Otherwise, print 
�
N space-separated integers denoting the array 
�
B.
Each integer from 
�
L to 
�
R should appear exactly once, and 
gcd
⁡
(
�
�
,
�
�
)
=
1
gcd(A 
i
​
 ,B 
i
​
 )=1 should hold for each 
�
i.
If there are multiple solutions possible, you may print any of them.

Constraints
1
≤
�
≤
1
0
4
1≤T≤10 
4
 
1
≤
�
<
�
≤
2
⋅
1
0
5
1≤L<R≤2⋅10 
5
 
The sum of 
�
−
�
R−L across all test cases won't exceed 
2
⋅
1
0
5
2⋅10 
5
 .
Explanation:
Test case 
1
1: With 
�
=
3
L=3 and 
�
=
7
R=7, we have 
�
=
[
3
,
4
,
5
,
6
,
7
]
A=[3,4,5,6,7].
Consider 
�
=
[
7
,
3
,
6
,
5
,
4
]
B=[7,3,6,5,4]. Looking at each index:

gcd
⁡
(
�
1
,
�
1
)
=
gcd
⁡
(
3
,
7
)
=
1
gcd(A 
1
​
 ,B 
1
​
 )=gcd(3,7)=1
gcd
⁡
(
�
2
,
�
2
)
=
gcd
⁡
(
4
,
3
)
=
1
gcd(A 
2
​
 ,B 
2
​
 )=gcd(4,3)=1
gcd
⁡
(
�
3
,
�
3
)
=
gcd
⁡
(
5
,
6
)
=
1
gcd(A 
3
​
 ,B 
3
​
 )=gcd(5,6)=1
gcd
⁡
(
�
4
,
�
4
)
=
gcd
⁡
(
6
,
5
)
=
1
gcd(A 
4
​
 ,B 
4
​
 )=gcd(6,5)=1
gcd
⁡
(
�
5
,
�
5
)
=
gcd
⁡
(
7
,
4
)
=
1
gcd(A 
5
​
 ,B 
5
​
 )=gcd(7,4)=1
�
B also contains every integer from 
�
L to 
�
R exactly once, so it's a valid array.

Test case 
2
2: With 
�
=
1
L=1 and 
�
=
3
R=3, we have 
�
=
[
1
,
2
,
3
]
A=[1,2,3].
You may verify that the valid arrays 
�
B are 
[
1
,
3
,
2
]
,
[
2
,
3
,
1
]
,
[
3
,
1
,
2
]
[1,3,2],[2,3,1],[3,1,2].
Printing any one of them will be considered correct. */

public class MakeItOne {

    

    public static void main(String[] args) {

    }
}
