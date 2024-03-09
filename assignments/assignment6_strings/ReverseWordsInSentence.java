package assignments.assignment6_strings;

import java.util.Scanner;

public class ReverseWordsInSentence {

    public static String reverseWords(String str) {
        String[] words = str.split(" ");
        String reversed = "";
        for(int i=words.length-1;i>=0;i--) {
            reversed += words[i] + " ";
        }
        return reversed;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a string: ");
        String str = sc.nextLine();
        System.out.println(reverseWords(str));
    }
}
