package assignments.assignment6_strings;

import java.util.Scanner;

public class CountVowels {
    public static int countVowels(String str) {
        int count = 0 ;
        for(int i=0;i<str.length();i++) {
            String a = (str.charAt(i)+ "").toLowerCase();
            if(a.equals("a") || a.equals("e") || a.equals("i") || a.equals("o") || a.equals("u")) {
                count++;
            }
        }
        return count;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a string: ");
        String str = sc.nextLine();
        System.out.println("The number of vowels in the string are: " + countVowels(str));
    }
}
