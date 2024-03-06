package task2;

// ) Write a program to print the following output for the given input. You can assume
// the string is of odd length
// Eg 1:  Input: 12345
//         Output:
// 1       5
//   2   4
//     3
//   2   4
// 1        5

public class XPattern {
    
    public static void printXPattern(String s) {
        int n = s.length();
        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {
                if(i==j) {
                    System.out.print(s.charAt(i));
                } else if(i+j==n-1) {
                    System.out.print(s.charAt(n-i-1));
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
    
    public static void main(String[] args) {
        printXPattern("12345");
    }
}
