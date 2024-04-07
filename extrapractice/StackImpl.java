package extrapractice;

import java.util.Collection;

public class StackImpl {
    int[] stack;
    int top;

    public StackImpl(int size) {
        stack = new int[size];
        top = -1;
    }

    public void push(int data) {
        if(top == stack.length-1) {
            System.out.println("Stack is full");
            return;
        }
        stack[++top] = data;
    }

    public int peek() {
        return stack[top];
    }

    public boolean isEmpty() {
        return top==-1;
    }

    public int pop() {
        if(isEmpty()) {
            System.out.println("Stack is empty");
            return -1;
        }
        int el = stack[top];
        stack[top--] = Integer.MIN_VALUE;
        return el;
    }

    public int search(int data) {
        if(isEmpty()) {
            return -1;
        }
        
        for(int i=0;i<=top;i++) {
            if(stack[i] == data) {
                return i;
            }
        }
        return -1;
    }

    public int size() {
        return top+1;
    }

    public static void main(String[] args) {
        StackImpl stack = new StackImpl(5);
        stack.push(1);
        stack.push(2);
        System.out.println(stack.peek());
        System.out.println(stack.pop());
        System.out.println(stack.peek());
        System.out.println(stack.size());
        System.out.println(stack.search(2));
    }

}
