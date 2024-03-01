package task2;

import java.util.Scanner;

/*
 Write a program to give the following output for the given input:
Eg 1:  Input: a1b10
Output: abbbbbbbbbb
Eg: 2:  Input: b3c6d15
           Output: bbbccccccddddddddddddddd
The number varies from 1 to 99.

 */

public class CharRepetition {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        
        charRepetition(str);
    } 

   public static void charRepetition(String str) {
        for(int i=0;i<str.length()-1;i++) {
            if(Character.isDigit(str.charAt(i+1)) && Character.isLetter(str.charAt(i))) {
                StringBuilder sb = new StringBuilder();
                int j = i+1;
                while(j < str.length() && Character.isDigit(str.charAt(j))) {
                    sb.append(str.charAt(j));
                    j++;
                }
                int count = Integer.parseInt(sb.toString());
                for(int k=0;k<count;k++) {
                    System.out.print(str.charAt(i));
                }
            }        
        }
   }
}
