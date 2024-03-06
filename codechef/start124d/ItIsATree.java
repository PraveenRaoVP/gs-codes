package codechef.start124d;

/* It is a tree
This problem has subtasks.

You are given a tree
†
†
  with 
�
N vertices, rooted at vertex 
1
1.
Each vertex of this tree will have either 
0
0 people, or 
1
1 person standing on it.

Consider the following process on this tree:

Let 
score
�
score 
u
​
  denote the score of vertex 
�
u.
Initially, this is 
0
0 for all vertices.
Every second, each person in the tree will simultaneously move one step towards the root (vertex 
1
1).
The only exception is people already standing at the root: they will instead leave the tree entirely.
For each vertex 
�
u,
If exactly one person entered vertex 
�
u in this second, increase 
score
�
score 
u
​
  by 
1
1.
Otherwise, don't change 
score
�
score 
u
​
 .
Note that this means either nobody entered vertex 
�
u, or 
≥
2
≥2 people entered it simultaneously.
This will repeat till there is nobody standing on the tree anymore.
Initially, there are no people standing on the tree.
Process 
�
Q queries and updates of the following form:

Given 
�
u, consider the above process performed with the current set of people standing on the tree. Find the value of 
score
�
score 
u
​
  after it ends.
Given 
�
u, toggle the state of the person standing at vertex 
�
u.
This means, if there's nobody standing at 
�
u then place one person there; otherwise remove the person standing there.
Note that when answering queries for 
score
�
score 
u
​
 , the people don't actually move: you just have to find the answer assuming they moved.

†
†
  A tree with 
�
N vertices is a connected undirected graph that has 
�
−
1
N−1 edges.

Input Format
The first line of input will contain a single integer 
�
T, denoting the number of test cases.
Each test case consists of multiple lines of input.
The first line of each test case two space-separated integers 
�
N and 
�
Q, the number of vertices in the tree and the number of queries.
The next 
�
−
1
N−1 lines describe the edges. The 
�
i-th of these 
�
−
1
N−1 lines contains two space-separated integers 
�
�
u 
i
​
  and 
�
�
v 
i
​
 , denoting an edge between 
�
�
u 
i
​
  and 
�
�
v 
i
​
 .
The next 
�
Q lines describe the queries. The 
�
i-th of them contains two space-separated integers 
type
�
type 
i
​
  and 
�
�
u 
i
​
 .
If 
type
�
=
1
type 
i
​
 =1, find 
score
�
�
score 
u 
i
​
 
​
  for the current set of people.
If 
type
�
=
2
type 
i
​
 =2, toggle the state of the person standing at vertex 
�
�
u 
i
​
 .
Output Format
Print the answer for each query of type 
1
1 on a new line.
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
,
�
≤
2
⋅
1
0
5
1≤N,Q≤2⋅10 
5
 
1
≤
�
�
,
�
�
≤
�
1≤u 
i
​
 ,v 
i
​
 ≤N
The input edges describe a tree on 
�
N vertices.
type
�
∈
{
1
,
2
}
type 
i
​
 ∈{1,2}
The sum of 
�
N and sum of 
�
Q over all test cases both won't exceed 
2
⋅
1
0
5
2⋅10 
5
 .
Subtasks
Subtask 
1
1 (
20
20 points): If 
type
�
=
1
type 
i
​
 =1, then 
�
�
=
1
u 
i
​
 =1.
That is, all queries will ask for 
score
1
score 
1
​
 .
Subtask 
2
2 (
40
40 points): All queries will be asked only after all updates.
That is, if 
type
�
=
1
type 
i
​
 =1 and 
type
�
=
2
type 
j
​
 =2, then 
�
>
�
i>j will hold.
Subtask 
3
3 (
40
40 points): No further constraints.
Sample 1:
Input
Output
2
5 10
1 2
2 3
2 4
4 5
2 1
2 3
1 2
1 3
2 5
1 2
2 4
1 2
2 3
1 2
10 21
1 2
1 3
2 4
2 5
4 7
4 8
5 9
3 6
6 10
2 1
2 7
2 10
2 4
1 1
1 2
2 9
2 5
1 2
2 9
1 2
2 8
1 4
1 7
2 9
1 5
2 5
2 6
1 2
2 4
1 2
1
0
2
1
2
1
2
0
1
0
0
1
1
0
Explanation:
Test case 
1
1: The tree looks like this:


Now for the queries and updates.

2 1: a person now stands at vertex 
1
1.
2 3: a person now stands at vertex 
3
3.
1 2: Perform the process on the current tree. It proceeds at follows:
There's one person each standing on vertices 
1
1 and 
3
3.
In the first second, the first person leaves the tree, while the second moves up to vertex 
2
2. This increases 
score
2
score 
2
​
  by 
1
1.
In the next second, the only remaining person moves up to 
1
1. This increases 
score
1
score 
1
​
  by 
1
1.
Finally, the person leaves the tree, and the process ends.
We wanted 
score
2
score 
2
​
 , which is 
1
1.
1 3: the process is the exact same; and as seen from earlier, 
score
3
score 
3
​
  is 
0
0. Note that people don't increase the score of their starting vertices.
2 5: there's now a person standing at 
5
5.
1 2: perform the process and print 
score
2
score 
2
​
 . It can be shown that it equals 
2
2.
2 4: there's now a person standing at 
4
4.
1 2: perform the process and print 
score
2
score 
2
​
 , which now equals 
1
1.
Note that in the first second, the people standing at vertices 
3
3 and 
4
4 will both simultaneously move to vertex 
2
2. This doesn't increase 
score
2
score 
2
​
 .
2 3: there's no longer a person standing at vertex 
3
3.
1 2: it can be shown that 
score
2
=
2
score 
2
​
 =2 now.*/

public class ItIsATree {
    
}
