package codechef.start129d;

import java.util.Scanner;

/*
 * Palindromic Substrings Game

Alice and Bob are playing a game with a binary string S of length N. They take turns, and on each turn, a player does the following:

If there is no palindromic cyclic substring of length greater than 1, the player loses the game.
Otherwise, the player selects any palindromic cyclic substring of S with length greater than 1 and removes one character from it, reducing the length of S by 1.
Given that Alice starts the game and both players play optimally, determine who will win the game.

A cyclic substring of a string S of length N is defined by two integers L and R (1 ≤ L, R ≤ N), where:

If L ≤ R, the cyclic substring is S[L]S[L+1]...S[R].
If L > R, the cyclic substring is S[L]S[L+1]...S[N]S[1]...S[R].
Write a program to determine the winner of the game for multiple test cases.

Input:

The first line of input contains an integer T, the number of test cases.
For each test case:
The first line contains an integer N, the length of string S.
The second line contains the binary string S.
Output:

For each test case, print "Alice" or "Bob" (without quotes), denoting the winner of the game.
Constraints:

1 ≤ T ≤ 10^5
1 ≤ N ≤ 2*10^5
The sum of N over all test cases won't exceed 2*10^5
S is a binary string, containing only the characters '0' and '1'.
Sample Input:
4
1
1
2
10
4
0001
15
111000111000111
Sample Output:

Bob
Bob
Alice
Bob

Explanation:

In the first two test cases, there are no palindromic cyclic substrings of length greater than 1, so Bob wins.
In the third test case, Alice wins by removing characters strategically, leaving Bob with no valid moves.
 */

public class PalindromicSubstring {

  public static boolean isPalindrome(String s) {
    int n = s.length();
    for (int i = 0; i < n / 2; i++) {
      if (s.charAt(i) != s.charAt(n - i - 1)) {
        return false;
      }
    }
    return true;
  }

  public static boolean hasCyclicPalindrome(String s) {
    int n = s.length();
    int[] prefixFunction = new int[n];
    int j = 0;
    for (int i = 1; i < n; i++) {
      while (j > 0 && s.charAt(i) != s.charAt(j)) {
        j = prefixFunction[j - 1];
      }
      if (s.charAt(i) == s.charAt(j)) {
        j++;
      }
      prefixFunction[i] = j;
    }
    return prefixFunction[n - 1] > 0;
  }

 public static String playGame(String s) {
    if (isPalindrome(s) || hasCyclicPalindrome(s)) {
      return s.length() % 2 == 0 ? "Alice" : "Bob";
    }
    return "Bob";
}

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int t = scanner.nextInt();
    for (int i = 0; i < t; i++) {
      int n = scanner.nextInt();
      String s = scanner.next();
      String winner = playGame(s);
      System.out.println(winner);
    }
    scanner.close();
  }
}