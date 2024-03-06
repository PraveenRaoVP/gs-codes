package codechef.start124d;

import java.util.Scanner;

/*
 * Summer Time
Mamalesh likes to drink mango lassi when it's hot, and only when it's hot. If (and only if) the temperature on a given day is strictly greater than 
35
35 degrees, Mamalesh will drink mango lassi.

Mamalesh sees that today's temperature is 
�
X degrees Celsius. Will he drink mango lassi today?
Print "Yes" if he will, and "No" otherwise (without quotes).

Input Format
The only line of input will contain a single integer 
�
X, denoting today's temperature.
Output Format
Print "YES" if Mamalesh will drink mango lassi today, and "NO" otherwise (without quotes).

Each letter of the output may be printed in either uppercase or lowercase, i.e, the strings NO, no, No, and nO will all be treated as equivalent.

Constraints
1
≤
�
≤
50
1≤X≤50
Sample 1:
Input
Output
35
NO
Explanation:
Today's temperature is 
35
35, which is not strictly greater than 
35
35 degrees Celsius.
So, Mamalesh will not drink mango lassi today.

Sample 2:
Input
Output
38
YES
Explanation:
Today's temperature is 
38
38, which is greater than 
35
35 degrees Celsius.
So, Mamalesh will drink mango lassi today.
 */

public class SummerTime {
    public static void main (String[] args) throws java.lang.Exception
	{
		// your code goes here
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        
        if(x>35) System.out.println("YES");
        else System.out.println("NO");
        
        sc.close();
	}
}
