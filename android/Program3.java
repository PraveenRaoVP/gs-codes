package android;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Program3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        // Set<String> set = new HashSet<>();
        // for(int i=0;i<s.length();i++) {
        //     for(int j=i+1;j<=s.length();j++) {
        //         set.add(s.substring(i,j));
        //     }
        // }

        // List<String> list = new ArrayList<>(set);
        // Collections.sort(list, Comparator.comparingInt(str->str.length()));
        // for(String str: list) {
        //     System.out.print(str+" ");
        // }
        // System.out.println();
        String ans = "";
        Set<String> newSet = new HashSet<>();
        recursion(s, ans, newSet);
        // List<String> nlist = new ArrayList<>(newSet);
        // Collections.sort(nlist, Comparator.comparingInt(str->str.length()));
        // for(String i: nlist) {
        //     System.out.print(i+" ");
        // }
        System.out.println();
    }

    public static void recursion(String str, String ans, Set<String> newSet) {
        if(str.length()==0) {
            // newSet.add(ans);
            System.out.println(ans);
            return;
        }
        recursion(str.substring(1), ans+str.charAt(0), newSet);
        recursion(str.substring(1), ans, newSet);
    }
}
