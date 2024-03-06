package codechef.start124d;

import java.util.Scanner;

/*Cricket Tournament
Udyam'24 is organizing a cricket tournament, in which 
�
N teams are participating.
This tournament is a knockout tournament, meaning that the loser of each match will be knocked out of the tournament and won't play any more matches.

The winner of the tournament is whichever team remains in the end without being knocked out.

The tournament will proceed as follows:

If there is only one team remaining, that team will be declared the winner, and the tournament finishes.
Otherwise, some two teams will play against each other; with the loser being knocked out.
You think that the tournament will be interesting if at least 
�
M matches will be played before the winner is decided.
Given 
�
N and 
�
M, is it possible for the tournament to be interesting?

Input Format
The first line of input will contain a single integer 
�
T, denoting the number of test cases.
Each test case consists of a single line containing two space-separated integers 
�
N and 
�
M — the number of teams and the minimum number of matches for an interesting tournament.
Output Format
For each test case, output on a new line the answer: "YES" if the tournament can be interesting, and "NO" otherwise (without quotes).

Each letter of the output may be printed in either uppercase or lowercase, i.e, the strings NO, no, No, and nO will all be treated as equivalent.

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
,
�
≤
100
1≤N,M≤100
Sample 1:
Input
Output
2
4 4
10 5
NO
YES
Explanation:
Test case 
1
1: There are 
4
4 teams, and it's impossible for there to be 
4
4 matches before a winner is decided. At most there can be three matches, for example:

Teams 
1
1 and 
2
2 play, with team 
1
1 winning. Team 
2
2 is knocked out.
Teams 
3
3 and 
4
4 play, with team 
4
4 winning. Team 
3
3 is knocked out.
Teams 
1
1 and 
4
4 play, with team 
1
1 winning. Team 
4
4 is knocked out.
Only team 
1
1 remains, so they're declared the winner and the tournament ends.
Test case 
2
2: With 
10
10 teams, it's easily possible to get at least 
5
5 matches before a winner is decided.
 */

public class CricketTournament {
    public static String isInteresting(int n, int m) {
        if(n==1) return "NO";
        if(n-1>=m) return "YES";
        return "NO";
    }
    
	public static void main (String[] args) throws java.lang.Exception
	{
		// your code goes here
		Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while(t>0) {
            
            int n = sc.nextInt();
            int m = sc.nextInt();
            
            System.out.println(isInteresting(n,m));
            
            t--;
        }
	} 
}
