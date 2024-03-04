package leetcode;
// Given an integer x, return true if x is a 
// palindrome
// , and false otherwise.

 

// Example 1:

// Input: x = 121
// Output: true
// Explanation: 121 reads as 121 from left to right and from right to left.
// Example 2:

// Input: x = -121
// Output: false
// Explanation: From left to right, it reads -121. From right to left, it becomes 121-. Therefore it is not a palindrome.
// Example 3:

// Input: x = 10
// Output: false
// Explanation: Reads 01 from right to left. Therefore it is not a palindrome.

public class PalindromeNumber {
    public boolean isPalindrome(int x) {
        if(x<0){
            return false;
        }
        int newNumber = 0;
        int i=0;
        int temp = x;
        while(temp>0){
            int rem = (int)temp%10;
            newNumber = newNumber*10+rem;
            temp/=10;
        }
        //System.out.println(newNumber);

        return newNumber == x;    
    }
}
