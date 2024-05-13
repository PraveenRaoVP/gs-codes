package assignments.assignment6_strings;
//5. Write a program to capitalize the first letter of each words in a sentence.

public class CapitalizeString {
    public static String capitalize(String str) {
        String[] words = str.split(" ");
        for(int i=0;i<words.length;i++) {
            words[i] = words[i].substring(0,1).toUpperCase() + words[i].substring(1);
        }
        String result = "";
        for(int i=0;i<words.length;i++) {
            result += words[i] + " ";
        }   
        return result;
    }
    public static void main(String[] args) {
        String str = "hello world";
        System.out.println(capitalize(str));
    }
}
