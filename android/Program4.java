package android;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/* given 5 numbers a,b,c,d,e, find the combination of arithmetic operations (+,-,*,/) to perform on a,b,c,d to get e as result. The numbers can be clubbed together in any order. */

// logic to follow: find all possible combinations of the arithmetic operations as string and evaluate them to get the result.

public class Program4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        int c = sc.nextInt();
        int d = sc.nextInt();
        int e = sc.nextInt();
        
        String[] operations = {"+","-","*","/"};
        List<int[]> allCombinations = findAllCombinations(a,b,c,d);
        
        for(int[] combination: allCombinations) {
            for(String operation1: operations) {
                for(String operation2: operations) {
                    for(String operation3: operations) {
                        int result = evaluate(combination, operation1, operation2, operation3);
                        if(result == e) {
                            System.out.println("The combination is: "+combination[0]+" "+operation1+" "+combination[1]+" "+operation2+" "+combination[2]+" "+operation3+" "+combination[3]);
                            return;
                        }
                    }
                }
            }
        }
        System.out.println("No combination found");
    }    

    public static int evaluate(int[] combination, String operation1, String operation2, String operation3) {
        int result = 0;
        if(operation1.equals("+")) {
            result = combination[0]+combination[1];
        } else if(operation1.equals("-")) {
            result = combination[0]-combination[1];
        } else if(operation1.equals("*")) {
            result = combination[0]*combination[1];
        } else if(operation1.equals("/")) {
            if(combination[1]==0) return Integer.MIN_VALUE;
            result = combination[0]/combination[1];
        }

        if(operation2.equals("+")) {
            result = result+combination[2];
        } else if(operation2.equals("-")) {
            result = result-combination[2];
        } else if(operation2.equals("*")) {
            result = result*combination[2];
        } else if(operation2.equals("/")) {
            if(combination[2]==0) return Integer.MIN_VALUE;
            result = result/combination[2];
        }

        if(operation3.equals("+")) {
            result = result+combination[3];
        } else if(operation3.equals("-")) {
            result = result-combination[3];
        } else if(operation3.equals("*")) {
            result = result*combination[3];
        } else if(operation3.equals("/")) {
            if(combination[3]==0) return Integer.MIN_VALUE;
            result = result/combination[3];
        }

        return result;
    }

    public static List<int[]> findAllCombinations(int a, int b, int c, int d) {
        // using recursion to find all possible combinations of the numbers
        List<int[]> allCombinations = new ArrayList<>();
        
        // recursion method to find all combinations
        findAllCombinationsHelper(a,b,c,d,0,allCombinations);

        return allCombinations;
    }

    public static void findAllCombinationsHelper(int a, int b, int c, int d, int index, List<int[]> allCombinations) {
        if(index==4) {
            allCombinations.add(new int[]{a,b,c,d});
            return;
        }

        findAllCombinationsHelper(a,b,c,d,index+1,allCombinations);
        findAllCombinationsHelper(a,b,d,c,index+1,allCombinations);
        findAllCombinationsHelper(a,c,b,d,index+1,allCombinations);
        findAllCombinationsHelper(a,c,d,b,index+1,allCombinations);
        findAllCombinationsHelper(a,d,b,c,index+1,allCombinations);
        findAllCombinationsHelper(a,d,c,b,index+1,allCombinations);
        findAllCombinationsHelper(b,a,c,d,index+1,allCombinations);
        findAllCombinationsHelper(b,a,d,c,index+1,allCombinations);
        findAllCombinationsHelper(b,c,a,d,index+1,allCombinations);
        findAllCombinationsHelper(b,c,d,a,index+1,allCombinations);
        findAllCombinationsHelper(b,d,a,c,index+1,allCombinations);
        findAllCombinationsHelper(b,d,c,a,index+1,allCombinations);
        findAllCombinationsHelper(c,a,b,d,index+1,allCombinations);
        findAllCombinationsHelper(c,a,d,b,index+1,allCombinations);
        findAllCombinationsHelper(c,b,a,d,index+1,allCombinations);
        findAllCombinationsHelper(c,b,d,a,index+1,allCombinations);
        findAllCombinationsHelper(c,d,a,b,index+1,allCombinations);
        findAllCombinationsHelper(c,d,b,a,index+1,allCombinations);
        findAllCombinationsHelper(d,a,b,c,index+1,allCombinations);
        findAllCombinationsHelper(d,a,c,b,index+1,allCombinations);
        findAllCombinationsHelper(d,b,a,c,index+1,allCombinations);
        findAllCombinationsHelper(d,b,c,a,index+1,allCombinations);
        findAllCombinationsHelper(d,c,a,b,index+1,allCombinations);
        findAllCombinationsHelper(d,c,b,a,index+1,allCombinations);
    }
    
}
