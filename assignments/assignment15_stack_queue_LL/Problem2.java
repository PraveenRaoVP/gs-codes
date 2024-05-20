package assignments.assignment15_stack_queue_LL;

import java.util.Stack;

// 2. Write a function that converts an infix expression (e.g., a + b * c) to postfix notation (e.g., a b c * +).

public class Problem2 {
    public static void main(String[] args) {
        System.out.println(infixToPostfix("a+b*c"));
    }

    public static String infixToPostfix(String infix) {
        StringBuilder ans = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        for(char i: infix.toCharArray()) {
            if(Character.isLetterOrDigit(i)) {
                ans.append(i);
            }
            else if(i == '(') {
                stack.push(i);
            }
            else if(i == ')') {
                while(!stack.isEmpty() && stack.peek() != '(') {
                    ans.append(stack.pop());
                }
                stack.pop();
            } else {
                while(!stack.isEmpty() && 
                    precedence(i) <= precedence(stack.peek()) || 
                    precedence(i) == precedence(stack.peek()) 
                ) {
                    ans.append(stack.pop());
                }
                stack.push(i);
            }
        }

        while(!stack.isEmpty()) {
            ans.append(stack.pop());
        }

        return ans.toString();
    }

    public static int precedence(char c) {
        switch(c) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
        }
        return -1;
    }
}
