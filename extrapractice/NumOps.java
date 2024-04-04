package extrapractice;

public class NumOps {
    
}

/*
 

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

 given 5 numbers a,b,c,d,e, find the combination of arithmetic operations (+,-,*,/) to perform on a,b,c,d to get e as result. The numbers can be clubbed together in any order. 

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
            result = combination[0]/combination[1];
        }

        if(operation2.equals("+")) {
            result = result+combination[2];
        } else if(operation2.equals("-")) {
            result = result-combination[2];
        } else if(operation2.equals("*")) {
            result = result*combination[2];
        } else if(operation2.equals("/")) {
            result = result/combination[2];
        }

        if(operation3.equals("+")) {
            result = result+combination[3];
        } else if(operation3.equals("-")) {
            result = result-combination[3];
        } else if(operation3.equals("*")) {
            result = result*combination[3];
        } else if(operation3.equals("/")) {
            result = result/combination[3];
        }

        return result;
    }

    public static List<int[]> findAllCombinations(int a, int b, int c, int d) {
        List<int[]> allCombinations = new ArrayList<>();
        allCombinations.add(new int[]{a,b,c,d});
        allCombinations.add(new int[]{a,b,d,c});
        allCombinations.add(new int[]{a,c,b,d});
        allCombinations.add(new int[]{a,c,d,b});
        allCombinations.add(new int[]{a,d,b,c});
        allCombinations.add(new int[]{a,d,c,b});
        allCombinations.add(new int[]{b,a,c,d});
        allCombinations.add(new int[]{b,a,d,c});
        allCombinations.add(new int[]{b,c,a,d});
        allCombinations.add(new int[]{b,c,d,a});
        allCombinations.add(new int[]{b,d,a,c});
        allCombinations.add(new int[]{b,d,c,a});
        allCombinations.add(new int[]{c,a,b,d});
        allCombinations.add(new int[]{c,a,d,b});
        allCombinations.add(new int[]{c,b,a,d});
        allCombinations.add(new int[]{c,b,d,a});
        allCombinations.add(new int[]{c,d,a,b});
        allCombinations.add(new int[]{c,d,b,a});
        allCombinations.add(new int[]{d,a,b,c});
        allCombinations.add(new int[]{d,a,c,b});
        allCombinations.add(new int[]{d,b,a,c});
        allCombinations.add(new int[]{d,b,c,a});
        allCombinations.add(new int[]{d,c,a,b});
        allCombinations.add(new int[]{d,c,b,a});

        
        allCombinations.add(new int[]{Integer.parseInt(Integer.toString(a)+Integer.toString(b)),c,d});
        allCombinations.add(new int[]{Integer.parseInt(Integer.toString(a)+Integer.toString(b)),d,c});
        allCombinations.add(new int[]{Integer.parseInt(Integer.toString(a)+Integer.toString(c)),b,d});
        allCombinations.add(new int[]{Integer.parseInt(Integer.toString(a)+Integer.toString(c)),d,b});
        allCombinations.add(new int[]{Integer.parseInt(Integer.toString(a)+Integer.toString(d)),b,c});
        allCombinations.add(new int[]{Integer.parseInt(Integer.toString(a)+Integer.toString(d)),c,b});
        allCombinations.add(new int[]{Integer.parseInt(Integer.toString(b)+Integer.toString(c)),a,d});
        allCombinations.add(new int[]{Integer.parseInt(Integer.toString(b)+Integer.toString(c)),d,a});
        allCombinations.add(new int[]{Integer.parseInt(Integer.toString(b)+Integer.toString(d)),a,c});
        allCombinations.add(new int[]{Integer.parseInt(Integer.toString(b)+Integer.toString(d)),c,a});
        allCombinations.add(new int[]{Integer.parseInt(Integer.toString(c)+Integer.toString(d)),a,b});
        allCombinations.add(new int[]{Integer.parseInt(Integer.toString(c)+Integer.toString(d)),b,a});
        
        allCombinations.add(new int[]{a,Integer.parseInt(Integer.toString(b)+Integer.toString(c)),d});
        allCombinations.add(new int[]{a,Integer.parseInt(Integer.toString(b)+Integer.toString(d)),c});
        allCombinations.add(new int[]{a,Integer.parseInt(Integer.toString(c)+Integer.toString(d)),b});
        allCombinations.add(new int[]{b,Integer.parseInt(Integer.toString(a)+Integer.toString(c)),d});
        allCombinations.add(new int[]{b,Integer.parseInt(Integer.toString(a)+Integer.toString(d)),c});
        allCombinations.add(new int[]{b,Integer.parseInt(Integer.toString(c)+Integer.toString(d)),a});
        allCombinations.add(new int[]{c,Integer.parseInt(Integer.toString(a)+Integer.toString(b)),d});
        allCombinations.add(new int[]{c,Integer.parseInt(Integer.toString(a)+Integer.toString(d)),b});
        allCombinations.add(new int[]{c,Integer.parseInt(Integer.toString(b)+Integer.toString(d)),a});
        allCombinations.add(new int[]{d,Integer.parseInt(Integer.toString(a)+Integer.toString(b)),c});
        allCombinations.add(new int[]{d,Integer.parseInt(Integer.toString(a)+Integer.toString(c)),b});
        allCombinations.add(new int[]{d,Integer.parseInt(Integer.toString(b)+Integer.toString(c)),a});

        allCombinations.add(new int[]{a,b,Integer.parseInt(Integer.toString(c)+Integer.toString(d))});
        allCombinations.add(new int[]{a,c,Integer.parseInt(Integer.toString(b)+Integer.toString(d))});
        allCombinations.add(new int[]{a,d,Integer.parseInt(Integer.toString(b)+Integer.toString(c))});
        allCombinations.add(new int[]{b,c,Integer.parseInt(Integer.toString(a)+Integer.toString(d))});
        allCombinations.add(new int[]{b,d,Integer.parseInt(Integer.toString(a)+Integer.toString(c))});
        allCombinations.add(new int[]{c,d,Integer.parseInt(Integer.toString(a)+Integer.toString(b))});

        allCombinations.add(new int[]{Integer.parseInt(Integer.toString(a)+Integer.toString(b)),Integer.parseInt(Integer.toString(c)+Integer.toString(d))});
        allCombinations.add(new int[]{Integer.parseInt(Integer.toString(a)+Integer.toString(c)),Integer.parseInt(Integer.toString(b)+Integer.toString(d))});
        allCombinations.add(new int[]{Integer.parseInt(Integer.toString(a)+Integer.toString(d)),Integer.parseInt(Integer.toString(b)+Integer.toString(c))});
        allCombinations.add(new int[]{Integer.parseInt(Integer.toString(b)+Integer.toString(c)),Integer.parseInt(Integer.toString(a)+Integer.toString(d))});
        allCombinations.add(new int[]{Integer.parseInt(Integer.toString(b)+Integer.toString(d)),Integer.parseInt(Integer.toString(a)+Integer.toString(c))});
        allCombinations.add(new int[]{Integer.parseInt(Integer.toString(c)+Integer.toString(d)),Integer.parseInt(Integer.toString(a)+Integer.toString(b))});
        

        return allCombinations;
    }
}

 */
