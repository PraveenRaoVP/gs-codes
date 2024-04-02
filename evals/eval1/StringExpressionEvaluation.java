package evals.eval1;

import java.util.Scanner;
import java.util.Stack;

public class StringExpressionEvaluation {
    public static int ans(String expr) {
        Stack<Character> ops = new Stack<>();
        Stack<Integer> nums = new Stack<>();
        Stack<Character> operators = new Stack<>();
        Stack<Integer> operands = new Stack<>();
        for (int i = 0; i < expr.length(); i++) {
            char ch = expr.charAt(i);
            if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
                ops.push(ch);
            } else if (ch >= '0' && ch <= '9') {
                nums.push(ch - '0');
            }
        }
        while(!ops.isEmpty()) {
            operators.push(ops.pop());
        }
        while(!nums.isEmpty()) {
            operands.push(nums.pop());
        }
        while(!operators.isEmpty()) {
            char op = operators.pop();
            int a = operands.pop();
            int b = operands.pop();
            if(op == '+') {
                operands.push(a+b);
            } else if(op == '-') {
                operands.push(a-b);
            } else if(op == '*') {
                operands.push(a*b);
            } else if(op == '/') {
                if(b==0) {
                    return -1;
                }
                operands.push(a/b);
            }
        }
        return operands.pop();
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String expr = sc.nextLine();
        System.out.println(ans(expr)); // 1+3*2 = 8 (no bodmas)
    }
}