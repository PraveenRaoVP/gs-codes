package assignments.assignment15_stack_queue_LL;

import java.util.Scanner;
import java.util.Stack;

// 1. Write a java program that takes a string of parentheses ((, ), [, ], {, and }) and checks if it is balanced.


public class Problem1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        System.out.println(isBalancedParanthesis(str) ? "Balanced" : "Not Balanced");
    }

    public static boolean isBalancedParanthesis(String str) {
        Stack<Character> stack = new Stack<>();
        for (char c : str.toCharArray()) {
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else if (c == ')' || c == ']' || c == '}') {
                if (stack.isEmpty()) {
                    return false;
                }
                char top = stack.pop();
                if (c == ')' && top != '(') {
                    return false;
                }
                if (c == ']' && top != '[') {
                    return false;
                }
                if (c == '}' && top != '{') {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}
