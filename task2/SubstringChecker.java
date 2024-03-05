package task2;

import java.util.Scanner;

public class SubstringChecker {

    public static int checkIfSubstring(String s1, String s2) {
        if(s2.length()  > s1.length()) System.out.println("Not applicable.");
        int flag=0;
        for(int i=0;i<s1.length();i++) {
            if(i+s2.length() < s1.length() && s1.charAt(i)==s2.charAt(0)) {
                for(int j=0;j<s2.length();j++) {
                    if(s1.charAt(i+j)!=s2.charAt(j)) {
                        flag=1;
                        break;
                    }
                }
                if(flag==0) return i+1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String s1 = sc.nextLine();
        String s2 = sc.nextLine();
        System.out.println(checkIfSubstring(s1, s2));
    }
}
