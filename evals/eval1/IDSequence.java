package evals.eval1;

import java.util.HashSet;
import java.util.Stack;

/*
 * Given a sequence of length <= 8 consisting of I and D, where I denotes the increasing
sequence and D denotes the decreasing sequence, decode the sequence to construct a
minimum number without repeated digits.
For example,
sequence        output
IIDDIDID  ——>  125437698
IDIDII    ——>  1325467
DDDD      ——>  54321
IIII      ——>  12345
 */

public class IDSequence {

    public static String getMinNumber(String sequence) {
        Stack<Integer> stack = new Stack<>();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i <= sequence.length(); i++) {
            stack.push(i + 1);
            if (i == sequence.length() || sequence.charAt(i) == 'I') {
                while (!stack.isEmpty()) {
                    result.append(stack.pop());
                }
            }
        }
        return result.toString();
    }

    public static void main(String[] args) {
        System.out.println(getMinNumber("IIDDIDID"));
        System.out.println(getMinNumber("IDIDII"));
        System.out.println(getMinNumber("DDDD"));
        System.out.println(getMinNumber("IIII")); 
    }
}
