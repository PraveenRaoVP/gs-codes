package codechef.start124d;

import java.util.Scanner;

/*
 * Swap and Unite
You have a string 
�
S consisting of lowercase letters.

You can perform the following operation on 
�
S:

Choose two indices 
�
i and 
�
j (
1
≤
�
<
�
≤
∣
�
∣
1≤i<j≤∣S∣), and swap 
�
�
S 
i
​
  and 
�
�
S 
j
​
 .
For example, if 
�
=
codechef
S=codechef, choosing 
�
=
3
i=3 and 
�
=
6
j=6 results in the string 
cohecdef
cohecdef.
You've always liked unity. To this end, find the minimum number of operations required so that for at least one character that appears in 
�
S, all its occurrences form a contiguous segment in 
�
S — in short, are united.

For example, if 
�
=
baaba
S=baaba, swapping the first and third characters results in 
�
=
aabba
S=aabba.
All the occurrences of 'b' form a contiguous segment now. Note that the 'a's don't form a contiguous segment, which is fine.

Input Format
The first line of input will contain a single integer 
�
T, denoting the number of test cases.
Each test case consists of one line of input, containing a string 
�
S.
Output Format
For each test case, output the minimum number of operations required to unite at least one character in the given string.

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
∣
�
∣
≤
2
⋅
1
0
5
1≤∣S∣≤2⋅10 
5
 
�
�
S 
i
​
  is a lowercase letter, i.e, between 'a' and 'z'.
It is guaranteed that the sum of 
∣
�
∣
∣S∣ over all test cases won't exceed 
≤
2
⋅
1
0
5
≤2⋅10 
5
 .
Sample 1:
Input
Output
4
aba
abab
baabba
ppssppss
0
1
1
2
Explanation:
Test case 
1
1: For 
�
=
aba
S=aba, all occurrences of 
b
b already form a contiguous segment, so no operations are needed.

Test case 
2
2: We have 
�
=
abab
S=abab. Swap the first and second characters to obtain 
baab
baab.
All the 'a's are now together.

Test case 
3
3: We have 
�
=
baabba
S=baabba. Swap the first and third characters to obtain 
aabbba
aabbba.
All the 'b's are now together.
 */

public class SwapAndUnite {
    public static int fun(String s) {
        int[] count = new int[26]; 
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }
        int mo = Integer.MAX_VALUE;
        for (int c : count) {
            if (c > 1) {
                mo = Math.min(mo, c-1);
            }
        }
        return mo == Integer.MAX_VALUE ? 0 : mo;
    }
    
	public static void main (String[] args) throws java.lang.Exception
	{
		// your code goes here
		Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while(t>0) {
            String s = sc.nextLine();
            System.out.println(fun(s));
            t--;
        }
	}
}
